/* InfiniteComicBookSuiteReaderFactory.java created on 2014/4/21
 *
 * This file is a part of the Infinite Comic Reader plug-in.
 */
package tw.comicsurfer.readers.infinite;

import tw.funymph.comicsurfer.readers.ComicBookSuiteReader;
import tw.funymph.comicsurfer.readers.ComicBookSuiteReaderFactory;

/**
 * This class is responsible to create the Infinite comic book reader.
 * 
 * @author Spirit
 * @version 1.0
 */
public class InfiniteComicBookSuiteReaderFactory implements ComicBookSuiteReaderFactory {

	@Override
	public ComicBookSuiteReader[] createReaders() {
		ComicBookSuiteReader reader = new InfiniteComicBookSuiteReader();
		return new ComicBookSuiteReader[] { reader };
	}
}
