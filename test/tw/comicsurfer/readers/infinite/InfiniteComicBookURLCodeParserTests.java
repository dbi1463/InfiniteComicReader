/* InfiniteComicBookURLCodeParserTests.java created on 2014/9/8
 *
 * This file is a part of the Infinite Comic Reader plug-in.
 */
package tw.comicsurfer.readers.infinite;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

/**
 * This class tests the functionalities of {@link InfiniteComicBookURLCodeParser}.
 * 
 * @author Spirit
 * @version 1.0
 */
public class InfiniteComicBookURLCodeParserTests {

	@Test
	public void testGetCodes() {
		InfiniteComicBookURLCodeParser testee = new InfiniteComicBookURLCodeParser();
		Map<Integer, InfiniteComicBookURLCode> codes = testee.getCodes("http://new.comicvip.com/show/cool-", 3927);
		assertEquals(42, codes.size());
		assertEquals(50, codes.get(1).getRawCode().length());
		assertEquals("jem1m23186qjgav9k8jh8v986j29r8m34pc7v6qxrwyaa7dg5j", codes.get(1).getRawCode());
	}
}
