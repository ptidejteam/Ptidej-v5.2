/*
 * (c) Copyright 2000-2002 Yann-Gaël Guéhéneuc,
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import util.multilingual.MultilingualManager;

public final class LogoBytesArrayBuilder {
	public static String createBytesArrayBuilder(final String fileName) {
		// System.out.println(LogoBytesArrayBuilder.createBytesArrayBuilder("E:/Work/JPtidej/Ptidej.gif"));

		try {
			final File file = new File(fileName);
			final FileInputStream fis = new FileInputStream(file);
			final byte[] bytes = new byte[(int) file.length()];
			fis.read(bytes, 0, bytes.length);
			fis.close();

			final StringBuffer buffer = new StringBuffer();
			buffer
				.append("\tprivate static final byte[] imageBytes = new byte[] {");
			for (int i = 0; i < bytes.length; i++) {
				if (i % 10 == 0) {
					buffer.append("\n\t\t");
				}
				buffer.append(bytes[i]);
				if (i < bytes.length - 1) {
					buffer.append(", ");
				}
			}
			buffer.append("};\n");

			return buffer.toString();
		}
		catch (final FileNotFoundException fnfe) {
			fnfe.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		catch (final IOException ioe) {
			ioe.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

		return MultilingualManager.getString(
			"Err_BUILDING_BYTES",
			LogoBytesArrayBuilder.class);
	}
}
