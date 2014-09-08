/* InfiniteComicBookURLCodeParser.java created on 2014/9/8
 *
 * This file is a part of the Infinite Comic Reader plug-in.
 */
package tw.comicsurfer.readers.infinite;

import static tw.comicsurfer.readers.infinite.URLUtils.getContentFromURL;

import java.util.HashMap;
import java.util.Map;

/**
 * This class parse the HTML content and parse all the codes that are used
 * to decode the final image URL. The code is a 50-character string, and all
 * codes are connected together without ordering. The ordering is hidden in
 * the code itself, so the parser will parsed the ordering.
 * 
 * @author Spirit
 * @version 1.0
 */
public class InfiniteComicBookURLCodeParser {

	private static final int CODE_LENGTH = 50;
	private static final String ENTRY_URL_PATTERN = "%1$d.html?ch=%2$d";

	/**
	 * Get all the codes.
	 * 
	 * @param baseURL the base URL of the suite
	 * @param suiteID the suite ID
	 * @return all the codes
	 */
	public Map<Integer, InfiniteComicBookURLCode> getCodes(String baseURL, int suiteID) {
		String entry = String.format(ENTRY_URL_PATTERN, suiteID, 1);
		String content = getContentFromURL(baseURL + entry);
		int csVarBeginIndex = content.indexOf("var cs=\'") + 8;
		int csVarEndIndex = content.indexOf("\';", csVarBeginIndex);
		String csVar = content.substring(csVarBeginIndex, csVarEndIndex);
		int codesCount = csVar.length() / CODE_LENGTH;
		Map<Integer, InfiniteComicBookURLCode> codes = new HashMap<Integer, InfiniteComicBookURLCode>();
		for (int index = 0; index < codesCount; index++) {
			InfiniteComicBookURLCode code = new InfiniteComicBookURLCode(csVar.substring(index * CODE_LENGTH, (index + 1) * CODE_LENGTH));
			codes.put(code.getBookId(), code);
		}
		return codes;
	}
}
