/* InfiniteComicBookSuiteReader.java created on 2014/4/21
 *
 * This file is a part of the Infinite Comic Reader plug-in.
 */
package tw.comicsurfer.readers.infinite;

import java.net.URI;

import tw.funymph.comicsurfer.core.ComicBookSuite;
import tw.funymph.comicsurfer.readers.AbstractComicBookSuiteReader;
import tw.funymph.commons.io.IOConstants;

/**
 * @author Spirit
 * @version 1.0
 */
public class InfiniteComicBookSuiteReader extends AbstractComicBookSuiteReader {

	private static final String INFINITE_COMIC_HOST = "www.8comic.com/"; 
	private static final String[] SUPPORTED_SCHEMES = { IOConstants.URI_SCHEME_HTTP }; 

	@Override
	public boolean canRead(URI uri) {
		return uri.getHost().contains(INFINITE_COMIC_HOST);
	}

	@Override
	public String getDescription() {
		return "Infinite Comic Book Reader";
	}

	@Override
	public String getExtension() {
		return null;
	}

	@Override
	public String[] getSupportedSchemes() {
		return SUPPORTED_SCHEMES;
	}

	@Override
	public boolean isFolderReader() {
		return false;
	}

	@Override
	public ComicBookSuite read(URI uri) {
		return null;
	}
}
