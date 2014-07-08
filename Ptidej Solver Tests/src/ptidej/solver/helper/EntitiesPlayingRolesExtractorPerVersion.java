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
package ptidej.solver.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang.ArrayUtils;
import ptidej.solver.Occurrence;
import ptidej.solver.OccurrenceBuilder;
import ptidej.solver.OccurrenceComponent;
import util.io.ProxyDisk;

/**
 *  Take a directory full of ini files with
 *  occurrences of patterns/smells and
 *  extract a list of the (unique) entities
 *  playing some roles in these occurrences.
 *  
 *  @author Yann
 *  @since  2009/03/24
 *  @author Aminata
 *  @since  2011/11/05
 *  
 */

/**
 * Extract for each design pattern or anti pattern the list of classes playing a role; those lists are given by pattern
 * To make a unique list for all patterns of one version, use AntipatternClassesExtractor and DesignPatternClassesExtractor
 * @author Imane
 *
 */
public class EntitiesPlayingRolesExtractorPerVersion {

	//			private static final String sourcePath =
	//				"../Ptidej Solver Data/CheckStyle Versions/";
	//		private static final String destinationPath =
	//			"../Ptidej Solver Data/Pattern Classes/Checkstyle/DesignPatterns/";
	//	private static final String sourcePath =
	//		"../SAD Tests/rsc/CheckStyle Versions/";
	//	private static final String destinationPath =
	//		"../Ptidej Solver Data/Pattern Classes/Checkstyle/Antipatterns/";

	//			private static final String sourcePath =
	//				"../Ptidej Solver Data/ArgoUml Versions 1/";
	//		private static final String destinationPath =
	//			"../Ptidej Solver Data/Pattern Classes/ArgoUml/DesignPatterns/";
	private static final String sourcePath =
		"../SAD Tests/rsc/ArgoUml Versions 1/";
	private static final String destinationPath =
		"../Ptidej Solver Data/Pattern Classes/ArgoUml/Antipatterns/";

	public static void main(final String[] args) {
		final EntitiesPlayingRolesExtractorPerVersion extractor =
			new EntitiesPlayingRolesExtractorPerVersion();
		extractor.extractAll(
			EntitiesPlayingRolesExtractorPerVersion.sourcePath,
			EntitiesPlayingRolesExtractorPerVersion.destinationPath);
	}

	//ListOfEntitiesPlayingSomeRoles.csv
	/**
	 * 
	 * @param sourcePath
	 * @param destinationFile
	 */
	public void extractAll(final String sourcePath, final String destinationFile) {
		final File sourcePathFile = new File(sourcePath);

		final String[] files = sourcePathFile.list();

		for (int i = 0; i < files.length; i++) {
			String currentSourcePath =
				new StringBuffer()
					.append(sourcePathFile.getAbsolutePath())
					.append(File.separatorChar)
					.append(files[i])
					.toString();
			final String currentDestinationPath =
				new StringBuffer()
					.append(destinationFile)
					.append(File.separatorChar)
					.append(files[i])
					.toString();
			final String[] listOfIniFiles;
			if (new File(currentSourcePath).isDirectory()) {
				listOfIniFiles = new File(currentSourcePath).list();
			}
			else {
				listOfIniFiles = new String[] { files[i] };
				currentSourcePath =
					new StringBuffer()
						.append(sourcePathFile.getAbsolutePath())
						.toString();
			}

			this.extractPerVersion(
				currentSourcePath,
				currentDestinationPath,
				listOfIniFiles);

		}

		System.out.println("Done.");
	}

	/**
	 * 
	 * @param sourcePath
	 * @param destinationPath
	 * @param listniFiles
	 * @return
	 */
	private final void extractPerVersion(
		final String sourcePath,
		final String destinationPath,
		final String[] listIniFiles) {
		final OccurrenceBuilder builder = OccurrenceBuilder.getInstance();
		final File destinationDirectory = new File(destinationPath);
		if (!destinationDirectory.exists()) {
			destinationDirectory.mkdir();
		}
		for (int i = 0; i < listIniFiles.length; i++) {
			final Set setOfEntities = new TreeSet();
			final String file =
				new StringBuffer()
					.append(sourcePath)
					.append(File.separatorChar)
					.append(listIniFiles[i])
					.toString();
			System.out.print("Analysing ");
			System.out.println(listIniFiles[i]);
			try {
				final InputStream inputStream = new FileInputStream(file);
				final Properties properties = new Properties();
				properties.load(inputStream);
				inputStream.close();

				final Occurrence[] occurrences =
					builder.getAllOccurrences(properties);
				for (int j = 0; j < occurrences.length; j++) {
					final Occurrence occurrence = occurrences[j];
					final Iterator components =
						occurrence.getComponents().iterator();
					while (components.hasNext()) {
						final OccurrenceComponent component =
							(OccurrenceComponent) components.next();
						final char[] value = component.getValue();
						// I only keep classes/interfaces, i.e., with a package name...
						if (ArrayUtils.indexOf(value, '.') > -1) {
							setOfEntities.add(String.valueOf(value));
						}
					}
				}
			}
			catch (final FileNotFoundException e) {
				System.err.println("File: " + file);
				e.printStackTrace();
			}
			catch (final IOException e) {
				System.err.println("File: " + file);
				e.printStackTrace();
			}

			System.out.println("Writing the list...");
			try {
				String name =
					listIniFiles[i].replaceAll("ConstraintResults", "Classes");
				name = name.replaceAll("DetectionResults", "Classes");
				name = name.replaceAll(".ini", ".csv");
				final String destinationFile =
					new StringBuffer()
						.append(destinationPath)
						.append(File.separatorChar)
						.append(name)
						.toString();
				final Writer writer =
					ProxyDisk.getInstance().fileTempOutput(destinationFile);
				final Iterator iterator = setOfEntities.iterator();
				while (iterator.hasNext()) {
					final String entityId = (String) iterator.next();
					writer.write(entityId);
					writer.write('\n');
				}
				writer.close();
			}
			catch (final IOException e) {
				e.printStackTrace();
			}
		}

	}
}
