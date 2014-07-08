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
package sad.detection.generators;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class SmellDetectionCaller {
	public static void main(final String[] args) {
		//	final SmellDetectionCaller caller = new SmellDetectionCaller();
		//	caller.analyseCodeLevelModelFromEclipse(
		//		"C:/Program Files (x86)/Eclipse 3.5/",
		//		"rsc/Smells/");
		//	caller.analyseCodeLevelModelFromEclipse(
		//		"../../P-MARt Workspace/eclipse-SDK-2.1-win32/",
		//		"rsc/Smells/");

		//	caller.analyseCodeLevelModelFromJavaClassFiles(
		//		"../../P-MARt Workspace/SIP Communicator v1.0-draft/bin/",
		//		"SIP-communicator-1-0-draft",
		//		"rsc/Smells/");
		//	caller.analyseCodeLevelModelFromJavaClassFiles(
		//		"../../P-MARt Workspace/Pooka v2.0/bin/",
		//		"Pooka v2.0",
		//		"rsc/Smells/");

		//	final SmellDetectionCaller sdc = new SmellDetectionCaller();
		//
		//	final String path = "../../P-MARt Workspace/";
		//	final String[] folders = new File(path).list(new FilenameFilter() {
		//		public boolean accept(final File dir, final String name) {
		//			return name.toLowerCase().startsWith("argouml-")
		//					&& !(name.indexOf("0.15.1") > -1
		//							|| name.indexOf("0.15.4") > -1
		//							|| name.indexOf("0.19.1") > -1
		//							|| name.indexOf("0.19.2") > -1
		//							|| name.indexOf("0.23.1") > -1
		//							|| name.indexOf("0.25.2") > -1
		//							|| name.indexOf("0.26.2") > -1 || name
		//						.indexOf("0.27.1") > -1);
		//		}
		//	});
		//	Arrays.sort(folders);
		//	for (int i = 0; i < folders.length; i++) {
		//		final String argoUMLName = folders[i];
		//		final String argoUMLPath = path + argoUMLName + '/';
		//		final String[] jarFiles =
		//			new File(argoUMLPath).list(new FilenameFilter() {
		//				public boolean accept(final File dir, final String name) {
		//					return (name.toLowerCase().startsWith("argouml") || name
		//						.toLowerCase()
		//						.startsWith("nsuml"))
		//							&& name.endsWith(".jar");
		//				}
		//			});
		//
		//		if (jarFiles != null) {
		//			for (int j = 0; j < jarFiles.length; j++) {
		//				jarFiles[j] = argoUMLPath + jarFiles[j];
		//			}
		//			sdc
		//				.analyseCodeLevelModelFromJARs(
		//					jarFiles,
		//					argoUMLName,
		//					"rsc/");
		//		}
		//		else {
		//			System.err.print(argoUMLPath);
		//			System.err.println(" is not a path.");
		//		}
		//	}

		//	final SmellDetectionCaller sdc = new SmellDetectionCaller();
		//
		//	final String path = "../../P-MARt Workspace/";
		//	final String[] folders = new File(path).list(new FilenameFilter() {
		//		public boolean accept(final File dir, final String name) {
		//			return name.toLowerCase().startsWith("xerces v");
		//		}
		//	});
		//	Arrays.sort(folders);
		//	for (int i = 0; i < folders.length; i++) {
		//		final String name = folders[i];
		//		final String directory = path + name + "/bin/";
		//		sdc
		//			.analyseCodeLevelModelFromJavaClassFiles(
		//				directory,
		//				name,
		//				"rsc/");
		//	}

		final String path = "../../P-MARt Workspace/";
		final String[] folders = new File(path).list(new FilenameFilter() {
			public boolean accept(final File dir, final String name) {
				return name.toLowerCase().startsWith("xerces v");
			}
		});
		Arrays.sort(folders);
		for (int i = 0; i < folders.length; i++) {
			final String name = folders[i];
			final String directory = path + name + "/bin/";
			SmellDetectionHelper.analyseCodeLevelModelFromJavaClassFiles(
				directory,
				name,
				"rsc/");
		}
	}
}
