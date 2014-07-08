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
package squad.util;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComputeNumberOfDefectsFrominiFiles {

	/**
	* @param args
	*/

	private static Pattern pattern;
	private static Matcher matcher;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String root =
			"D:/Software/Tests Workspace/foutsekh-program-BugDefects/rsc/Eclipse"
					+ args[0] + "/Smells";
		//String root2 = "rsc/smells";

		System.out.println("Nombre de Blob: "
				+ computeNumberOfFromDir("Blob", root));
		System.out.println("Nombre de LargeClass: "
				+ computeNumberOfFromDir("LargeClass", root));
		System.out.println("Nombre de LazyClass: "
				+ computeNumberOfFromDir("LazyClass", root));
		System.out.println("Nombre de SpaghettiCode: "
				+ computeNumberOfFromDir("SpaghettiCode", root));
		System.out.println("Nombre de SpeculativeGenerality: "
				+ computeNumberOfFromDir("SpeculativeGenerality", root));

	}

	public static int computeNumberOfFromDir(String aDefectName, String path) {
		int result = 0;

		final File pathFile = new File(path);
		final String[] subPaths = pathFile.list();
		for (int i = 0; i < subPaths.length; i++) {
			final String fileName = path + "/" + subPaths[i];
			final File file = new File(fileName);

			if ((fileName.endsWith(".ini") == true)
					&& (containsDefectName(aDefectName, fileName))) {
				result =
					result + computeNumberOfFromFile(aDefectName, fileName);

			}

		}
		return result;
	}

	public static int computeNumberOfFromFile(
		String aDefectName,
		String aFileName) {
		int result = 0;
		final String t = "Total:";

		try {
			final LineNumberReader inputFileReader =
				new LineNumberReader(new FileReader(aFileName));

			String line = null;
			//Read File Line By Line 	

			while ((line = inputFileReader.readLine()) != null) {
				// start analysing the content of the line
				// totalNumberOfLines++;   
				StringTokenizer st = new StringTokenizer(line);
				while (st.hasMoreTokens()) {

					pattern = Pattern.compile(t);
					matcher = pattern.matcher(st.nextToken());
					if (matcher.find()) {
						String substring = "Total:";
						int begin = line.indexOf(substring);
						String Value =
							line.substring(begin + substring.length());

						result = result + Integer.parseInt(Value);
						//System.out.println(Value);
					}

				}
			}
		}
		catch (Exception e) {//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		return result;
	}

	public static boolean containsDefectName(
		String aDefectName,
		String aFileName) {
		boolean bool = false;
		StringTokenizer st = new StringTokenizer(aFileName);
		while (st.hasMoreTokens()) {

			pattern = Pattern.compile(aDefectName);
			matcher = pattern.matcher(st.nextToken());
			if (matcher.find()) {

				bool = true;
			}
		}
		return bool;

	}

}