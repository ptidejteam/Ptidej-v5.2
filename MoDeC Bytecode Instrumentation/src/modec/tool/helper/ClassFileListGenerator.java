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
 * @since  2007/11/11
 */
public class ClassFileListGenerator {
	public static void main(final String[] args) {
		// ClassFileListGenerator.printOutClassFiles("../Gombos/bin/");
		//	ClassFileListGenerator
		//		.printOutClassFiles("../../P-MARt Workspace/JHotDraw v5.4b1/bin/");
		//	ClassFileListGenerator
		//		.printOutClassFiles("../../P-MARt Workspace/JHotDraw v5.1/bin/");
		//	ClassFileListGenerator
		//		.printOutClassFiles("../../P-MARt Workspace/JEdit v4.2/");
		ClassFileListGenerator
			.printOutClassFiles("../../P-MARt Workspace/ArgoUML v0.19.8/");
		//	ClassFileListGenerator
		//		.printOutClassFiles("../../P-MARt Workspace/JFreeChart v1.0.13/");
	}
	private static void printOutClassFiles(final String aRootDirectory) {
		final File rootDirectoryFile = new File(aRootDirectory);
		final String[] list = rootDirectoryFile.list();
		for (int i = 0; i < list.length; i++) {
			final String stringFile =
				rootDirectoryFile.getAbsolutePath() + '/' + list[i];
			final File fileFile = new File(stringFile);
			if (fileFile.isFile()) {
				if (stringFile.endsWith(".class")) {
					final String filePath = stringFile.replace('\\', '/');
					System.out.println(filePath);
				}
			}
			else {
				ClassFileListGenerator.printOutClassFiles(stringFile);
			}
		}
	}
}
