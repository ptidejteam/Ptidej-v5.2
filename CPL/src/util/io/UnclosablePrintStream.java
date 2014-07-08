/* (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
 * University of Montreal.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the author, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
 * ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHOR IS ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package util.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class UnclosablePrintStream extends PrintStream {
	public UnclosablePrintStream(final OutputStream out) {
		super(out);
	}
	public UnclosablePrintStream(final String fileName)
			throws FileNotFoundException {
		super(fileName);
	}
	public UnclosablePrintStream(final File file) throws FileNotFoundException {
		super(file);
	}

	public UnclosablePrintStream(final OutputStream out, final boolean autoFlush) {
		super(out, autoFlush);
	}
	public UnclosablePrintStream(final String fileName, final String csn)
			throws FileNotFoundException, UnsupportedEncodingException {

		super(fileName, csn);
	}
	public UnclosablePrintStream(final File file, final String csn)
			throws FileNotFoundException, UnsupportedEncodingException {

		super(file, csn);
	}
	public UnclosablePrintStream(
		final OutputStream out,
		final boolean autoFlush,
		final String encoding) throws UnsupportedEncodingException {

		super(out, autoFlush, encoding);
	}
	public void close() {
		// super.close();
		ProxyConsole.getInstance().warningOutput().print("");
	}
}
