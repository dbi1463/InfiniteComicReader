/* InfiniteComicBookSuite.java created on 2014/9/7
 *
 * This file is a part of the Infinite Comic Reader plug-in.
 */
package tw.comicsurfer.readers.infinite;

import static java.lang.Integer.parseInt;
import static tw.comicsurfer.readers.infinite.StringUtils.getParenthesizedContent;
import static tw.funymph.commons.file.FileUtilities.removeExtension;
import static tw.funymph.commons.io.IOUtilities.getQueryValues;
import static tw.funymph.commons.io.IOUtilities.parseFilename;

import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tw.funymph.comicsurfer.readers.AbstractComicBookSuite;

/**
 * This class represents a suite of comic books from www.8comic.com.
 * 
 * @author Spirit
 * @version 1.0
 */
public class InfiniteComicBookSuite extends AbstractComicBookSuite {

	public static final String SHOW_TYPE = "show";
	public static final String TYPE_VOLUMES = "Volumes";
	public static final String TYPE_CHAPTERS = "Chapters";

	private static final String SUITE_NAME_PATTERN = "%1$s (%2$s on www.8comic.com)";

	private String _type;
	private Elements _selection;

	private InfiniteComicBookURLCodeParser _codeParser;
	private Map<Integer, InfiniteComicBookURLCode> _codes;
	private InfiniteComicBookBaseURLRetriever _baseURLRetriever;

	/**
	 * Construct a <code>InfiniteComicBookSuite</code> with the specified URI.
	 * 
	 * @param url the URI of the suite
	 */
	public InfiniteComicBookSuite(URI url) {
		super(url);
	    _type = isChapterType()? "Chapters" : "Volumes";
	    _codeParser = new InfiniteComicBookURLCodeParser();
	    _baseURLRetriever = new InfiniteComicBookBaseURLRetriever();
	}

	@Override
	public void open() {
		String classToSelect = !isChapterType()? ".Vol" : ".Ch";
		try {
			Document localDocument = Jsoup.connect(getSuitePath().toString()).get();
			String suiteName = localDocument.select("#Comic").text();
			setName(String.format(SUITE_NAME_PATTERN, suiteName.substring(0, suiteName.indexOf(" ")), _type));
			_selection = localDocument.select(classToSelect);
			int suiteId = parseInt(removeExtension(parseFilename(getSuitePath())));
			Map<Integer, String> baseURLs = _baseURLRetriever.getBaseURLs();
			if ((_selection != null) && (_selection.size() > 0)) {
				Iterator<Element> iterator = _selection.iterator();
				while (iterator.hasNext()) {
					Element element = iterator.next();
					int bookId = parseInt(element.attr("id").substring(1));
					int categoryId = parseInt(getParenthesizedContent(element.attr("onclick")).split(",")[1]);
					String baseURL = baseURLs.get(categoryId);
					InfiniteComicBookURLCode code = getCodes(baseURL, suiteId).get(bookId);
					books.add(new InfiniteComicBook(suiteId, code));
				}
			}
		} catch (IOException e) {
			System.err.println("unable to open the suite from " + getSuitePath());
		}
	}

	/**
	 * Check whether the user wants to see the suite in the chapter mode or
	 * in the volume mode.
	 * 
	 * @return true if the user wants to see the suite in the chapter mode.
	 */
	protected boolean isChapterType() {
		Map<String, String> parameters = getQueryValues(getSuitePath());
	    return parameters.containsKey(SHOW_TYPE) && parameters.get(SHOW_TYPE).equalsIgnoreCase(TYPE_CHAPTERS);
	}

	/**
	 * Get the codes from the URL consist of the base URL and the suite ID.
	 * 
	 * @param baseURL the base URL
	 * @param suiteId the suite ID
	 * @return the codes to read the comic books
	 */
	private Map<Integer, InfiniteComicBookURLCode> getCodes(String baseURL, int suiteId) {
		if (_codes == null || _codes.size() == 0) {
			_codes = _codeParser.getCodes(baseURL, suiteId);
		}
		return _codes;
	}
}
