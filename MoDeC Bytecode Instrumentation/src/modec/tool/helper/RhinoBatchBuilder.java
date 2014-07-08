/*
 * (c) Copyright 2001-2007 Yann-Gaël Guéhéneuc,
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
package modec.tool.helper;

import java.io.File;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/11/30
 */
public class RhinoBatchBuilder {
	private String mainPath;
	private String currentSection;
	public static void main(final String[] args) {
		final RhinoBatchBuilder rbb = new RhinoBatchBuilder();
		System.out.println("@ECHO OFF\n");
		rbb.mainPath =
			"D:/Software/P-MARt Workspace/Rhino v1.6R5/testdata/ecma";
		rbb.recurseInDirs(rbb.mainPath);
	}
	private void recurseInDirs(final String path) {
		final File pathFile = new File(path);
		final String[] elements = pathFile.list();
		for (int i = 0; i < elements.length; i++) {
			final String element = elements[i];
			final String newPath = path + '/' + element;
			final File newPathFile = new File(newPath);
			if (newPathFile.isFile()) {
				if (newPath.endsWith(".js")
					&& !newPath.endsWith("shell.js")
					&& !newPath.endsWith("browser.js")) {

					System.out.println(
						"JAVA   -classpath \"D:/Software/Ptidej Workspace/MoDeC Bytecode Instrumentation/bin\";\".\" org.mozilla.javascript.tools.shell.Main -f \"D:/Software/P-MARt Workspace/Rhino v1.6R5/testdata/shell.js\" -f \""
							+ this.mainPath
							+ "/shell.js\" -f \""
							+ this.mainPath
							+ "/"
							+ this.currentSection
							+ "/shell.js\" -f \""
							+ this.mainPath
							+ "/"
							+ this.currentSection
							+ "/"
							+ element
							+ "\"");
					System.out.println(
						"RENAME \"D:\\Software\\Ptidej Workspace\\MoDeC Bytecode Instrumentation Tests\\Instrumented Classes\\Rhino v1.6R5\\Trace.trace\" \"ecma - "
							+ this.currentSection
							+ " - "
							+ element.substring(0, element.indexOf(".js"))
							+ ".trace\"\n");
				}
			}
			else {
				this.currentSection = element;

				System.out.print("\n\n\nREM +-");
				for (int j = 0; j < this.currentSection.length(); j++) {
					System.out.print("-");
				}
				System.out.print("-+\nREM | ");
				System.out.print(this.currentSection);
				System.out.print(" |\nREM +-");
				for (int j = 0; j < this.currentSection.length(); j++) {
					System.out.print("-");
				}
				System.out.print("-+\n\n");

				this.recurseInDirs(newPath);
			}
		}
	}
}
