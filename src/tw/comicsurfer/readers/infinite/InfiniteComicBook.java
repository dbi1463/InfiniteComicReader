/* InfiniteComicBook.java created on 2014/9/8
 *
 * This file is a part of the Infinite Comic Reader plug-in.
 */
package tw.comicsurfer.readers.infinite;

import java.util.ArrayList;
import java.util.List;

import tw.funymph.comicsurfer.core.Page;
import tw.funymph.comicsurfer.readers.AbstractComicBook;
import tw.funymph.comicsurfer.readers.defaults.URLPage;

/**
 * This class represent a comic book loading from the www.8comic.com.
 * 
 * @author Spirit
 * @version 1.0
 */
public class InfiniteComicBook extends AbstractComicBook {

	private int _suiteId;
	private List<Page> _pages;
	private InfiniteComicBookURLCode _code;

	/**
	 * Construct a <code>InfiniteComicBook</code> instance with the suite ID that
	 * the book belongs and the code that contains all the needed information.
	 * 
	 * @param suiteId the suite ID
	 * @param code the URL code
	 */
	public InfiniteComicBook(int suiteId, InfiniteComicBookURLCode code) {
		_code = code;
		_suiteId = suiteId;
		_pages = new ArrayList<Page>();
	}

	@Override
	public void close() {
		_pages.clear();
	}

	@Override
	public Page getPage(int index) {
		return _pages.get(index);
	}

	@Override
	public int getPageCount() {
		return _pages.size();
	}

	@Override
	public void open() {
		int pagesCount = _code.getPagesCount();
		for (int index = 1; index <= pagesCount; index++) {
			_pages.add(new URLPage(_code.getImageURL(_suiteId, index)));
		}
	}
}
