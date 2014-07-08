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
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class UnclosablePrintWriter extends PrintWriter {
	public UnclosablePrintWriter(final Writer out) {
		super(out);
	}
	public UnclosablePrintWriter(final OutputStream out) {
		super(out);
	}
	public UnclosablePrintWriter(final String fileName)
			throws FileNotFoundException {
		super(fileName);
	}
	public UnclosablePrintWriter(final File file) throws FileNotFoundException {
		super(file);
	}
	public UnclosablePrintWriter(final Writer out, final boolean autoFlush) {
		super(out, autoFlush);
	}
	public UnclosablePrintWriter(final OutputStream out, final boolean autoFlush) {
		super(out, autoFlush);
	}
	public UnclosablePrintWriter(final String fileName, final String csn)
			throws FileNotFoundException, UnsupportedEncodingException {

		super(fileName, csn);
	}
	public UnclosablePrintWriter(File file, String csn)
			throws FileNotFoundException, UnsupportedEncodingException {

		super(file, csn);
	}
	public void close() {
		// super.close();
		ProxyConsole.getInstance().warningOutput().print(this.getClass());
		ProxyConsole
			.getInstance()
			.warningOutput()
			.println(" reports that someone is trying to close it!");
	}
}
