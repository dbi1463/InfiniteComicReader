/* InfiniteComicBookURLCode.java created on 2014/9/7
 *
 * This file is a part of the Infinite Comic Reader plug-in.
 */
package tw.comicsurfer.readers.infinite;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represent a 50-character code and the meaning of each part in
 * the code. For example, a code like
 * <code>jem1m23186qjgav9k8jh8v986j29r8m34pc7v6qxrwyaa7dg5j</code>
 * should be analyze as<ol>
 * <li>substring from 1 to 4: <code>jem1</code> -> the integer value 1 is the book ID
 * <li>substring from 5 to 6: <code>m2</code> -> the integer value 2 is the image site ID
 * <li>substring from 7 to 8: <code>3</code> -> the integer value is the image folder ID
 * <li>substring from 8 to 10: <code>186</code> -> the integer value is number of the pages
 * <li>substring from 10 to 50: <code>qjgav9k8jh8v986j29r8m34pc7v6qxrwyaa7dg5j</code> is used
 * to get the 3-character image index suffix, the rule is <code>suffix = f(pageIndex)</code>,
 * <code>f = ((((pageIndex - 1) / 10) % 10) + (((pageIndex - 1) % 10) * 3))</code>
 * </ol>
 * 
 * @author Spirit
 * @version 1.0
 */
public class InfiniteComicBookURLCode {

	private static final String IMAGE_URL_PATTERN = "http://img{siteId}.8comic.com/{folderId}/{suiteId}/{bookId}/{pageIndex}_{pageIndexSuffix}.jpg";

	private String _code;

	/**
	 * Construct a <code>InfiniteComicBookURLCode</code> from a raw code string.
	 * 
	 * @param code the raw code string
	 */
	public InfiniteComicBookURLCode(String code) {
		_code = code;
	}

	/**
	 * Get the raw code string.
	 * 
	 * @return the raw code string
	 */
	public String getRawCode() {
		return _code;
	}

	/**
	 * Get the book ID from the raw code string.
	 * 
	 * @return the book ID
	 */
	public int getBookId() {
		return integerValueFromCode(0, 4);
	}

	/**
	 * Get the image site ID from the raw code string.
	 * 
	 * @return the image site ID
	 */
	public int getImageSiteId() {
		return integerValueFromCode(4, 2);
	}

	/**
	 * Get the image folder ID from the raw code string
	 * 
	 * @return the image folder ID
	 */
	public int getImageForderId() {
		return integerValueFromCode(6, 1);
	}

	/**
	 * Get the number of the pages from the raw code string.
	 * 
	 * @return the number of the pages
	 */
	public int getPagesCount() {
		return integerValueFromCode(7, 3);
	}

	/**
	 * Get the image index suffix for the given page index.
	 * 
	 * @param pageIndex the page index
	 * @return the image index suffix
	 */
	public String getImageIndexSuffix(int pageIndex) {
		int magicNumber = ((((pageIndex - 1) / 10) % 10) + (((pageIndex - 1) % 10) * 3)) + 10;
		return _code.substring(magicNumber, magicNumber + 3);
	}

	/**
	 * The image URL pattern is <br/>
	 * <code>http://img{siteId}.8comic.com/{folderId}/{suiteId}/{bookId}/{pageIndex}_{pageIndexSuffix}.jpg</code>.
	 * Given a raw code <code>jem1m23186qjgav9k8jh8v986j29r8m34pc7v6qxrwyaa7dg5j</code>, suiteId <code>3927</code>,
	 * pageIndex <code>2</code>, the URL will be <code>http://img2.8comic.com/3/3927/1/002_av9.jpg</code>.
	 * 
	 * @param pageIndex the page index
	 * @return the complete image URL
	 */
	public String getImageURL(int suiteId, int pageIndex) {
		Map<String, String> parameters = getBasicParameters();
		parameters.put("suiteId", valueOf(suiteId));
		parameters.put("pageIndex", String.format("%1$03d", pageIndex));
		parameters.put("pageIndexSuffix", getImageIndexSuffix(pageIndex));
		return StringUtils.replacePlaceholders(IMAGE_URL_PATTERN, parameters);
	}

	/**
	 * Get the parameters that is not varied with the page index.
	 * 
	 * @return the parameters
	 */
	private Map<String, String> getBasicParameters() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("bookId", valueOf(getBookId()));
		parameters.put("siteId", valueOf(getImageSiteId()));
		parameters.put("folderId", valueOf(getImageForderId()));
		return parameters;
	}

	/**
	 * Get the integer value in the substring specified from the given begin index
	 * and the length.
	 *  
	 * @param beginIndex the begin index of the substring
	 * @param length the length of the substring
	 * @return the integer value
	 */
	private int integerValueFromCode(int beginIndex, int length) {
		return parseInt(_code.substring(beginIndex, beginIndex + length).replaceAll("[a-zA-Z]", ""));
	}
}
