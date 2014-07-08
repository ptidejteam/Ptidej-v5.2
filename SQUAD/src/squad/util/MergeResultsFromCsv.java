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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;

public class MergeResultsFromCsv {

	/**
	 * @param args
	 */
	private static Pattern pattern;
	private static Matcher matcher;
	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub

		/*String path = "D:/Software/Tests Workspace/foutsekh-program-BugDefects/rsc/Results";
		String fileName = "D:/Software/Tests Workspace/foutsekh-program-BugDefects/rsc/Results/Results of Eclipse.v 3.0.b.csv";
		indexFiles(path);
		*/
		final String t = "org.eclipse(.[a-z]*)+";
		String path =
			"D:/Software/Tests Workspace/foutsekh-program-BugDefects/rsc/Bugs/issues/test";

		printDefectedClasses(path, path, "Eclipse", t);

		/*ResultsFileIndexer csvindex = new ResultsFileIndexer(
			"rsc/Results/index" + getFileVersion(fileName) + ".dat");
		csvindex.indexFileOrDirectory(fileName);
		*/
		//final Directory index = FSDirectory
		//	.getDirectory("rsc/Results/index0.dat");
		/*final Analyzer analyser = new StandardAnalyzer();
		Query q = new QueryParser("class", analyser).parse("org.*");

		IndexSearcher s = new IndexSearcher(index);

		Hits hits = s.search(q);
		System.out.println("Found " + hits.length() + " hits.");
		for (int i = 0; i < hits.length(); ++i) {
			System.out.println((i + 1) + ". " + hits.doc(i).get("class"));
		}
		s.close();*/
	}

	private static String getFileVersion(final String FileName) {
		final String substring1 = "v";
		final String substring2 = ".csv";
		final int begin = FileName.indexOf(substring1);
		final int end = FileName.indexOf(substring2);
		final String aFileVersion =
			FileName.substring(begin + substring1.length(), end);
		return aFileVersion;
	}

	/*private static void indexFiles(String path) throws IOException,
		ParseException {

		final File pathFile = new File(path);

		final String[] subPaths = pathFile.list();
		for (int i = 0; i < subPaths.length; i++) {
			final String fileName = path + "/" + subPaths[i];

			if (fileName.endsWith(".csv") == true) {

				ResultsFileIndexer csvindex = new ResultsFileIndexer(
					"rsc/Results/index" + getFileVersion(fileName) + ".dat");
				csvindex.indexFileOrDirectory(fileName);

			}
		}

	}
	*/private static void printDefectedClasses(
		String path,
		String outputDir,
		String progName,
		String t) throws IOException, ParseException {

		TreeSet<String> defectClasses = new TreeSet<String>();

		try {
			FileOutputStream output =
				new FileOutputStream(outputDir
						+ "/TotalNbDefectsBugsPerClasses in" + progName
						+ ".csv");

			//			if (!output.exists()) {
			//				output.createNewFile();
			//			}
			System.setOut(new PrintStream(output));
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}

		System.out.println("Classes, nb_bug, time");

		final File pathFile = new File(path);

		final String[] subPaths = pathFile.list();
		for (int i = 0; i < subPaths.length; i++) {
			final String fileName = path + "/" + subPaths[i];

			if (fileName.endsWith(".csv") == true) {

				printFile(fileName, defectClasses, t);

			}
		}
		System.setOut(System.out);
	}

	/*
	 * Print defected classes from a file
	 * 
	 */
	public static void printFile(
		String fileName,
		TreeSet<String> defectClasses,
		String t) throws FileNotFoundException, CorruptIndexException,
			IOException {

		LineNumberReader fr = null;
		try {

			// ===================================================
			// add contents of file
			// ===================================================

			final Vector<String> eltFields = new Vector<String>();

			fr = new LineNumberReader(new FileReader(fileName));
			StringTokenizer st1 = new StringTokenizer(fr.readLine(), ",");
			while (st1.hasMoreTokens())
				eltFields.addElement(st1.nextToken());

			String line;
			while ((line = fr.readLine()) != null) {

				String[] ucharFields = line.split(",", eltFields.size());

				if (!defectClasses.contains(ucharFields[0])) {

					// test if the name does contains org.eclipse			
					StringTokenizer st = new StringTokenizer(ucharFields[0]);
					while (st.hasMoreTokens()) {

						pattern = Pattern.compile(t);
						matcher = pattern.matcher(st.nextToken());
						if (matcher.find()) {

							defectClasses.add(ucharFields[0]);
							System.out.println(ucharFields[0] + ", "
									+ ucharFields[3] + ", " + ucharFields[4]);

						}
					}
					/*if(Integer.parseInt(ucharFields[3])>0){
						
						number_class_bugs=number_class_bugs+1;	
						
					}*/
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			fr.close();
		}

	}

}
