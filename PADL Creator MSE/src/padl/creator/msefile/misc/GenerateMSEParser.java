/*
 * (c) Copyright 2001-2003 Yann-Gaël Guéhéneuc,
 * École des Mines de Nantes and Object Technology International, Inc.
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
package padl.creator.msefile.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Writer;
import util.io.ProxyDisk;

/**
 * @author Yann-Gaël Guéhéneuc
 */
public class GenerateMSEParser {
	public static void main(final String[] args) throws Exception {
		padl.creator.msefile.javacup.Main.main(new String[] { "-parser",
				"MSEParser", "-symbols", "MSESymbols", "-interface",
				"-nonterms", "-progress", "rsc/MSE.cup" });

		final File previousParser = new File("src/padl/creator/MSEParser.java");
		previousParser.delete();
		final File generatedParser = new File("MSEParser.java");
		generatedParser.renameTo(previousParser);

		final File previousSymbols =
			new File("src/padl/creator/MSESymbols.java");
		previousSymbols.delete();
		final File generatedSymbols = new File("MSESymbols.java");
		generatedSymbols.renameTo(previousSymbols);

		/*
		 * Some code to replace reference to "java_cup.runtime"
		 * with "padl.creator.javacup.runtime".
		 */
		final LineNumberReader reader =
			new LineNumberReader(new InputStreamReader(new FileInputStream(
				"src/padl/creator/MSEParser.java")));
		final StringBuffer buffer = new StringBuffer();
		String readLine;
		while ((readLine = reader.readLine()) != null) {
			buffer.append(readLine);
			buffer.append('\n');
		}
		reader.close();

		final String toBeRemovedString = "java_cup.runtime";
		int pos;
		while ((pos = buffer.indexOf(toBeRemovedString)) > 0) {
			buffer.replace(
				pos,
				pos + toBeRemovedString.length(),
				"padl.creator.javacup.runtime");
		}

		final Writer writer =
			ProxyDisk.getInstance().fileAbsoluteOutput(
				"src/padl/creator/MSEParser.java");
		writer.write(buffer.toString());
		writer.close();
	}
}
