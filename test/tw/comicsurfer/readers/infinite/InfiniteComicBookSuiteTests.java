/* InfiniteComicBookSuiteReaderTests.java created on 2014/9/7
 *
 * This file is a part of the Infinite Comic Reader plug-in.
 */
package tw.comicsurfer.readers.infinite;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

/**
 * This class tests the functionalities of {@link InfiniteComicBookSuite}.
 * 
 * @author Spirit
 * @version 1.0
 */
public class InfiniteComicBookSuiteTests {

	@Test
	public void testOpen() throws URISyntaxException {
		InfiniteComicBookSuite suite = new InfiniteComicBookSuite(new URI("http://www.8comic.com/html/3927.html"));
		suite.open();
		assertEquals(42, suite.getComicBookCount());
		assertEquals("Às¯] (Chapters on www.8comic.com)", suite.getSuiteName());
	}
}
