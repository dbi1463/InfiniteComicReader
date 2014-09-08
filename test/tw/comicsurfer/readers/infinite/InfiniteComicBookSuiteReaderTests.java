/* InfiniteComicBookSuiteReaderTests.java created on 2014/9/8
 *
 * This file is a part of the Infinite Comic Reader plug-in.
 */
package tw.comicsurfer.readers.infinite;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the functionalities of {@link InfiniteComicBookSuiteReader}.
 * 
 * @author Spirit
 * @version 1.0
 */
public class InfiniteComicBookSuiteReaderTests {

	private InfiniteComicBookSuiteReader _testee;

	@Before
	public void setUp() {
		_testee = new InfiniteComicBookSuiteReader();
	}

	@Test
	public void testCanRead() throws URISyntaxException {
		assertTrue(_testee.canRead(new URI("http://www.8comic.com/html/3927.html")));
	}

	@Test
	public void testGetDescription() {
		assertEquals("Infinite Comic Book Reader", _testee.getDescription());
	}

	@Test
	public void testGetExtension() {
		assertNull(_testee.getExtension());
	}

	@Test
	public void testGetSupportedSchemes() {
		assertArrayEquals(new String[] { "http" }, _testee.getSupportedSchemes());
	}

	@Test
	public void testIsFolderReader() {
		assertFalse(_testee.isFolderReader());
	}

	@Test
	public void testRead() throws URISyntaxException {
		assertNotNull(_testee.read(new URI("http://www.8comic.com/html/3927.html")));
		assertNull(_testee.read(new URI("http://www.comic.com/html/3927.html")));
	}
}
