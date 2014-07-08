/*
 * (c) Copyright 2001-2004 Yann-Gaël Guéhéneuc,
 * University of Montréal.
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

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/08/19
 */
public class MultiChannelOutputStream extends OutputStream {
	private final OutputStream firstStream;
	private final OutputStream secondStream;

	public MultiChannelOutputStream(
		final OutputStream theFirstOutputStream,
		final OutputStream theSecondOutputStream) {

		this.firstStream = theFirstOutputStream;
		this.secondStream = theSecondOutputStream;
	}
	public MultiChannelOutputStream(
		final PrintWriter theFirstOutputStream,
		final OutputStream theSecondOutputStream) {

		this.firstStream = new WriterOutputStream(theFirstOutputStream);
		this.secondStream = theSecondOutputStream;
	}
	public void write(final int b) throws IOException {
		this.firstStream.write(b);
		this.secondStream.write(b);
	}
}
