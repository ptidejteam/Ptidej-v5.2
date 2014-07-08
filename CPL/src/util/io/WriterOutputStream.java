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
import java.io.OutputStream;
import java.io.Writer;

/**
 * An adapter class between a Writer and an OutputStream
 * 
 * @author	Yann-Gaël Guéhéneuc
 * @version	2004/05/16
 */
public class WriterOutputStream extends OutputStream {
	private final Writer writer;

	/**
	 * Constructor for WriterOutputStream.
	 */
	public WriterOutputStream(final Writer writer) {
		this.writer = writer;
	}

	/**
	 * @see OutputStream#write()
	 */
	public void write(final int b) throws IOException {
		this.writer.write(b);
	}
}