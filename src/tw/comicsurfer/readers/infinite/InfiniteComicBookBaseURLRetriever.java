/* InfiniteComicBookBaseURLRetriever.java created on 2014/9/8
 *
 * This file is a part of the Infinite Comic Reader plug-in.
 */
package tw.comicsurfer.readers.infinite;

import static tw.comicsurfer.readers.infinite.StringUtils.getParenthesizedContent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import tw.funymph.commons.io.IOCloser;

/**
 * This class retrieves and cache the base URLs used by infinite comic from
 * the JavaScript file <code>"http://www.8comic.com/js/comicview.js"</code>.
 * 
 * @author Spirit
 * @version 1.0
 */
public class InfiniteComicBookBaseURLRetriever {

	private static final String BASE_URL_INFO = "http://www.8comic.com/js/comicview.js";

	private static Map<Integer, String> baseURLs;

	/**
	 * Get the base URLs. The key is the category ID of the comic book suite.
	 * 
	 * @return the base URLs
	 */
	public Map<Integer, String> getBaseURLs() {
		if (baseURLs != null && baseURLs.size() > 0) {
			return baseURLs;
		}

		BufferedReader reader = null;
		baseURLs = new HashMap<Integer, String>();
		try {
			reader = new BufferedReader(new InputStreamReader(new URL(BASE_URL_INFO).openStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				int urlStartIndex = line.indexOf("\"") + 1;
				int urlEndIndex = line.lastIndexOf("\"");
				if ((line.contains("baseurl")) && (line.contains("if(catid")) && (urlStartIndex > -1) && (urlEndIndex > -1)) {
					String url = line.substring(urlStartIndex, urlEndIndex);
					String ifContent = getParenthesizedContent(line).replace(" ", "");
					String[] equalContents = ifContent.split("\\|\\|");
					for (String equalContent : equalContents) {
						int categoryId = Integer.parseInt(equalContent.split("==")[1]);
						baseURLs.put(categoryId, url);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOCloser.close(reader);
		}
		return baseURLs;
	}
}
