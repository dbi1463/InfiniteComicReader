/* InfiniteComicBookSuiteReader.java created on 2014/4/21
 *
 * This file is a part of the Infinite Comic Reader plug-in.
 */
package tw.comicsurfer.readers.infinite;

import java.net.URI;
import java.util.regex.Pattern;

import tw.funymph.comicsurfer.core.ComicBookSuite;
import tw.funymph.comicsurfer.readers.AbstractComicBookSuiteReader;
import tw.funymph.commons.io.IOConstants;

/**
 * This class checks the URI and create a {@link InfiniteComicBookSuite} if the
 * URI is point to a comic book suite on www.8comic.com.
 * 
 * @author Spirit
 * @version 1.0
 */
public class InfiniteComicBookSuiteReader extends AbstractComicBookSuiteReader {

	private static final String[] SUPPORTED_SCHEMES = { IOConstants.URI_SCHEME_HTTP };
	private static final String SUPPORTED_URI_PATTERN = "http://www.8comic.com/html/\\d+.html[?((&*\\w=\\w)+)*]*"; 

	@Override
	public boolean canRead(URI uri) {
		return Pattern.matches(SUPPORTED_URI_PATTERN, uri.toString());
	}

	@Override
	public String getDescription() {
		return "Infinite Comic Book Reader";
	}

	@Override
	public String getExtension() {
		return null;
	}

	@Override
	public String[] getSupportedSchemes() {
		return SUPPORTED_SCHEMES;
	}

	@Override
	public boolean isFolderReader() {
		return false;
	}

	@Override
	public ComicBookSuite read(URI uri) {
		return canRead(uri)? new InfiniteComicBookSuite(uri) : null;
	}
}
