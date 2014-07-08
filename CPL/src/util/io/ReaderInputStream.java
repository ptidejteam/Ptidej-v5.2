/*
 * (c) Copyright 2001-2002 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Gaël Guéhéneuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
 * ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package util.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * An adapter class between a Reader and an InputStream
 * 
 * @author  Yann-Gaël Guéhéneuc
 * @version	0.1
 */
public class ReaderInputStream extends InputStream {
	private final Reader reader;

	/**
	 * Constructor for ReaderInputStream.
	 */
	public ReaderInputStream(final Reader reader) {
		this.reader = reader;
	}

	//	/**
	//	 * @see InputStream#available()
	//	 */
	//	public int available() throws IOException {
	//		return Integer.MAX_VALUE;
	//	}

	//	/**
	//	 * @see InputStream#close()
	//	 */
	//	public void close() throws IOException {
	//		this.reader.close();
	//	}

	/**
	 * @see InputStream#read()
	 */
	public int read() throws IOException {
		return this.reader.read();
	}

	//	/**
	//	 * @see InputStream#skip()
	//	 */
	//	public long skip(long n) throws IOException {
	//		return this.reader.skip(n);
	//	}
}