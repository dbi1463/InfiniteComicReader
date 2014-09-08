/* URLUtils.java created on 2014/9/8
 *
 * This file is a part of the Infinite Comic Reader plug-in.
 */
package tw.comicsurfer.readers.infinite;

import java.io.ByteArrayOutputStream;
import java.net.URL;

import tw.funymph.commons.io.IOUtilities;

/**
 * This class provides a set of helper method to obtain the content from
 * a given URL.
 * 
 * @author Spirit
 * @version 1.0
 */
public class URLUtils {

	/**
	 * Get the content from the URL.
	 * 
	 * @param url the URL
	 * @return the content of the URL
	 */
	public static String getContentFromURL(String url) {
		String content = null;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			IOUtilities.copy(new URL(url).openStream(), output);
			content = new String(output.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
}
