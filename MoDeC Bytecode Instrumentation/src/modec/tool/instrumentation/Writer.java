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
package modec.tool.instrumentation;

import java.io.BufferedWriter;
import util.io.ProxyDisk;

/**
 * @author Janice Ng
 */
public class Writer {
	public static void write(final String filename, final Object log) {
		try {
			final java.io.Writer fstream =
				ProxyDisk.getInstance().fileAbsoluteOutput(filename, true);
			final BufferedWriter out = new BufferedWriter(fstream);
			if (log instanceof java.lang.StackTraceElement) {
				out.write(log.toString() + "\n");
			}
			else {
				out.write(log.toString());
			}
			out.close();
		}
		catch (final Exception e) {
			// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
}
