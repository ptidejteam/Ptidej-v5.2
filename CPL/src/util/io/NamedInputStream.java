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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/07/21
 */
public class NamedInputStream {
	private static final int BYTE_ARRAY_SIZE = 15000;
	private static final int BYTE_ARRAY_INCREMENT = 2;

	// Yann 2004/07/21: InputStreams.
	// It is *never* a good idea to store instances of InputStream
	// because once read, an input stream cannot be easily reset,
	// reread, and because it may be closed unexpectedly. It is
	// *far better* to store the data (bytes) and to produce a new
	// input stream on-demand.
	private final byte[] bytes;
	private final String fileName;

	public NamedInputStream(final String aFileName, final InputStream aStream) {

		this.fileName = Files.normalizePath(aFileName);
		byte[] array1 = new byte[NamedInputStream.BYTE_ARRAY_SIZE];
		int b = 0;
		int i = 0;

		try {
			// Yann 2004/12/04: Availability...
			// The available() method cannot be trusted because
			// some stream just cannot tell how much data is
			// available. So, I grow little by little the array
			// until it reaches the appropriate size.
			//	this.bytes = new byte[aStream.available()];
			while ((b = aStream.read()) != -1) {
				array1[i++] = (byte) b;
				if (i == array1.length) {
					final byte[] array2 =
						new byte[i * NamedInputStream.BYTE_ARRAY_INCREMENT];
					System.arraycopy(array1, 0, array2, 0, i);
					array1 = array2;
				}
			}
		}
		catch (final IOException ioe) {
			ioe.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

		this.bytes = new byte[i];
		System.arraycopy(array1, 0, this.bytes, 0, i);
	}
	public String getName() {
		return this.fileName;
	}
	public InputStream getStream() {
		return new ByteArrayInputStream(this.bytes);
	}
	public String toString() {
		return this.getName();
	}
}
