/* InfiniteComicBookURLCodeTests.java created on 2014/9/7
 *
 * This file is a part of the Infinite Comic Reader plug-in.
 */
package tw.comicsurfer.readers.infinite;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the functionalities of {@link InfiniteComicBookURLCode}.
 * 
 * @author Spirit
 * @version 1.0
 */
public class InfiniteComicBookURLCodeTests {

	private InfiniteComicBookURLCode _testee;

	@Before
	public void setUp() {
		_testee = new InfiniteComicBookURLCode("jem1m23186qjgav9k8jh8v986j29r8m34pc7v6qxrwyaa7dg5j");
	}

	@Test
	public void testGetPagesCount() {
		assertEquals(186, _testee.getPagesCount());
	}

	@Test
	public void testGetImageSiteId() {
		assertEquals(2, _testee.getImageSiteId());
	}

	@Test
	public void testImageFolderId() {
		assertEquals(3, _testee.getImageForderId());
	}

	@Test
	public void testImageIndexSuffix() {
		assertEquals("k8j", _testee.getImageIndexSuffix(3));
		assertEquals("h8v", _testee.getImageIndexSuffix(4));
		assertEquals("986", _testee.getImageIndexSuffix(5));
		assertEquals("6qx", _testee.getImageIndexSuffix(10));
	}

	@Test
	public void testGetImageURL() {
		assertEquals("http://img2.8comic.com/3/3927/1/001_qjg.jpg", _testee.getImageURL(3927, 1));
		assertEquals("http://img2.8comic.com/3/3927/1/010_6qx.jpg", _testee.getImageURL(3927, 10));
		assertEquals("http://img2.8comic.com/3/3927/1/100_dg5.jpg", _testee.getImageURL(3927, 100));
		assertEquals("http://img2.8comic.com/3/3927/1/150_wya.jpg", _testee.getImageURL(3927, 150));
	}

	@Test
	public void testGetBookId() {
		assertEquals(1, _testee.getBookId());
	}
}
