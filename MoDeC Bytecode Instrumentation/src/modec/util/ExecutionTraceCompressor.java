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
package modec.util;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.Writer;

public class ExecutionTraceCompressor {
	// Yann 2010/03/29: RLE
	// A simple implementation of the RLE
	// see http://en.wikipedia.org/wiki/Run_length_encoding
	public static void main(final String[] args) {
	}
	public void compress(final Reader aReader, final Writer aWriter) {
		try {
			final LineNumberReader reader = new LineNumberReader(aReader);
			String line = null;
			String previousLine = null;
			while ((line = reader.readLine()) != null) {
				if (previousLine == null) {
					previousLine = line;
					aWriter.write(line);
				}
				else {
					if (line.equals(previousLine)) {
						aWriter.write(line);
					}
					else {
						previousLine = line;
						aWriter.write(line);
					}
				}
			}
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
