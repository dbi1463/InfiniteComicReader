/* StringUtilsTests.java created on 2014/9/8
 *
 * This file is a part of the Infinite Comic Reader plug-in.
 */
package tw.comicsurfer.readers.infinite;

import static org.junit.Assert.*;
import static tw.comicsurfer.readers.infinite.StringUtils.getParenthesizedContent;
import static tw.comicsurfer.readers.infinite.StringUtils.replacePlaceholders;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * This class tests the functionalities of {@link StringUtils}.
 * 
 * @author Spirit
 * @version 1.0
 */
public class StringUtilsTests {

	@Test
	public void testGetParenthesizedContent() {
		String input = "if(catid==4 || catid==6 || catid==12 ||catid==22 ) baseurl=\"http://new.comicvip.com/show/cool-\"";
		String expect = "catid==4 || catid==6 || catid==12 ||catid==22 "; 
		assertEquals(expect, getParenthesizedContent(input));
	}

	@Test
	public void testReplacePlaceholders() {
		String pattern = "http://img{siteId}.8comic.com/{folderId}/{suiteId}/{bookId}/{pageIndex}_{pageIndexSuffix}.jpg";
		String expect = "http://img2.8comic.com/3/3927/1/002_av9.jpg";
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("siteId", "2");
		parameters.put("folderId", "3");
		parameters.put("suiteId", "3927");
		parameters.put("bookId", "1");
		parameters.put("pageIndex", "002");
		parameters.put("pageIndexSuffix", "av9");
		assertEquals(expect, replacePlaceholders(pattern, parameters));
	}
}
