/* InfiniteComicBookBaseURLRetrieverTests.java created on 2014/9/8
 *
 * This file is a part of the Infinite Comic Reader plug-in.
 */
package tw.comicsurfer.readers.infinite;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

/**
 * This class tests the functionalities of {@link InfiniteComicBookBaseURLRetriever}.
 * 
 * @author Spirit
 * @version 1.0
 */
public class InfiniteComicBookBaseURLRetrieverTests {

	@Test
	public void testGetBaseURLs() {
		InfiniteComicBookBaseURLRetriever testee = new InfiniteComicBookBaseURLRetriever();
		Map<Integer, String> baseURLs = testee.getBaseURLs();
		assertEquals(22, baseURLs.size());
		assertEquals("http://new.comicvip.com/show/cool-", baseURLs.get(4));
		assertEquals("http://new.comicvip.com/show/cool-", baseURLs.get(6));
		assertEquals("http://new.comicvip.com/show/cool-", baseURLs.get(12));
		assertEquals("http://new.comicvip.com/show/cool-", baseURLs.get(22));

		assertEquals("http://new.comicvip.com/show/cool-", baseURLs.get(1));
		assertEquals("http://new.comicvip.com/show/cool-", baseURLs.get(17));
		assertEquals("http://new.comicvip.com/show/cool-", baseURLs.get(19));
		assertEquals("http://new.comicvip.com/show/cool-", baseURLs.get(21));

		assertEquals("http://new.comicvip.com/show/cool-", baseURLs.get(2));
		assertEquals("http://new.comicvip.com/show/cool-", baseURLs.get(5));
		assertEquals("http://new.comicvip.com/show/cool-", baseURLs.get(7));
		assertEquals("http://new.comicvip.com/show/cool-", baseURLs.get(9));

		assertEquals("http://new.comicvip.com/show/best-manga-", baseURLs.get(10));
		assertEquals("http://new.comicvip.com/show/best-manga-", baseURLs.get(11));
		assertEquals("http://new.comicvip.com/show/best-manga-", baseURLs.get(13));
		assertEquals("http://new.comicvip.com/show/best-manga-", baseURLs.get(14));

		assertEquals("http://new.comicvip.com/show/best-manga-", baseURLs.get(3));
		assertEquals("http://new.comicvip.com/show/best-manga-", baseURLs.get(8));
		assertEquals("http://new.comicvip.com/show/best-manga-", baseURLs.get(15));
		assertEquals("http://new.comicvip.com/show/best-manga-", baseURLs.get(16));
		assertEquals("http://new.comicvip.com/show/best-manga-", baseURLs.get(18));
		assertEquals("http://new.comicvip.com/show/best-manga-", baseURLs.get(20));
	}
}
