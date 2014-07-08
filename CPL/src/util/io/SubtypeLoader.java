/*
 * (c) Copyright 2001, 2002 Hervé Albin-Amiot and Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes
 * Object Technology International, Inc.
 * Soft-Maint S.A.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the authors, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHORS ARE ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package util.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import util.multilingual.MultilingualManager;
import com.ibm.toad.cfparse.ClassFile;

public final class SubtypeLoader {
	private static class ClassNameAlphabeticalComparator implements Comparator<Object> {

		public int compare(final ClassFile c1, final ClassFile c2) {
			return c1.getName().compareTo(c2.getName());
		}
		public int compare(final Object o1, final Object o2) {
			if (o1 instanceof ClassFile && o2 instanceof ClassFile) {
				return this.compare((ClassFile) o1, (ClassFile) o2);
			}
			return 0;
		}
	}
	public static ClassFile[] loadRecursivelySubtypesFromDir(
		final String supertypeName,
		final String directory,
		final String extension) {

		final File currentDirectory = new File(directory);
		final String currentList[] = currentDirectory.list();

		if (currentList == null || currentList.length == 0) {
			ProxyConsole
				.getInstance()
				.warningOutput()
				.println(
					MultilingualManager.getString(
						"Err_FILES_NOT_FOUND",
						SubtypeLoader.class,
						new Object[] { directory }));
			return new ClassFile[0];
		}

		final List<ClassFile> currentListOfClasses = new ArrayList<ClassFile>(currentList.length);
		for (int x = 0; x < currentList.length; x++) {
			final String itemName = directory + currentList[x];

			if (new File(itemName).isFile()) {
				currentListOfClasses.addAll(Arrays.asList(SubtypeLoader
					.loadSubtypeFromFile(supertypeName, itemName, extension)));
			}
			else if (new File(itemName).isDirectory()) {
				currentListOfClasses.addAll(Arrays.asList(SubtypeLoader
					.loadRecursivelySubtypesFromDir(supertypeName, itemName
							+ File.separatorChar, extension)));
			}
		}

		if (currentListOfClasses.size() == 0) {
			ProxyConsole
				.getInstance()
				.warningOutput()
				.println(
					MultilingualManager.getString(
						"Err_FILES_NOT_FOUND",
						SubtypeLoader.class,
						new Object[] { directory }));
			return new ClassFile[0];
		}
		else {
			final ClassFile[] results =
				new ClassFile[currentListOfClasses.size()];
			currentListOfClasses.toArray(results);
			Arrays.sort(results, new ClassNameAlphabeticalComparator());
			return results;
		}
	}
	public static ClassFile[] loadSubtypeFromFile(
		final String supertypeName,
		final String file,
		final String extension) {

		final List<ClassFile> currentListOfClasses = new ArrayList<ClassFile>();
		if (file.endsWith(extension)) {
			ProxyConsole
				.getInstance()
				.debugOutput()
				.print(
					MultilingualManager.getString(
						"LOADING_FROM",
						SubtypeLoader.class,
						new Object[] { file }));

			try {
				final InputStream inputStream = new FileInputStream(file);
				SubtypeLoader.loadSubtypeFromStream(
					supertypeName,
					currentListOfClasses,
					inputStream);
				inputStream.close();
			}
			catch (final Exception e) {
				ProxyConsole
					.getInstance()
					.errorOutput()
					.print("Exception while reading file: ");
				ProxyConsole.getInstance().errorOutput().println(file);
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
		}

		final ClassFile[] results = new ClassFile[currentListOfClasses.size()];
		currentListOfClasses.toArray(results);
		return results;
	}
	// TODO: David made this method public
	public static void loadSubtypeFromStream(
		final String supertypeName,
		final List<ClassFile> currentListOfClasses,
		final InputStream inputStream) throws IOException {

		final ClassFile currentClass = new ClassFile(inputStream);

		//	final ClassParser parser = new ClassParser(inputStream, file);
		//	final ClassFile currentClass =
		//		CFParseBCELConvertor.convertClassFile(parser.parse());

		if (supertypeName == null
				|| currentClass.getSuperName().equals(supertypeName)) {

			currentListOfClasses.add(currentClass);
		}
		else {
			boolean isSuperInterfaceFound = false;
			for (int i = 0; i < currentClass.getInterfaces().length()
					&& !isSuperInterfaceFound; i++) {

				if (currentClass.getInterfaces().get(i).equals(supertypeName)) {

					currentListOfClasses.add(currentClass);
					isSuperInterfaceFound = true;
				}
			}
		}
	}
	public static ClassFile[] loadSubtypesFromDir(
		final String supertypeName,
		final String directory,
		final String extension) {

		final File currentDirectory = new File(directory);
		final String currentList[] =
			currentDirectory.list(new ExtensionBasedFilenameFilter(extension));
		if (currentList == null || currentList.length == 0) {
			ProxyConsole
				.getInstance()
				.warningOutput()
				.println(
					MultilingualManager.getString(
						"Err_FILES_NOT_FOUND",
						SubtypeLoader.class,
						new Object[] { directory }));
			return new ClassFile[0];
		}

		final List<ClassFile> currentListOfClasses = new ArrayList<ClassFile>(currentList.length);
		for (int x = 0; x < currentList.length; x++) {
			currentListOfClasses.addAll(Arrays.asList(SubtypeLoader
				.loadSubtypeFromFile(
					supertypeName,
					directory + currentList[x],
					extension)));
		}

		final ClassFile[] results = new ClassFile[currentListOfClasses.size()];
		currentListOfClasses.toArray(results);
		Arrays.sort(results, new ClassNameAlphabeticalComparator());
		return results;
	}
	public static ClassFile[] loadSubtypesFromJar(
		final ClassFile supertype,
		final String jarFileName,
		final String extension) {

		try {
			final JarFile currentJarFile = new JarFile(jarFileName);
			if (currentJarFile.size() == 0) {
				ProxyConsole
					.getInstance()
					.errorOutput()
					.println(
						MultilingualManager.getString(
							"Err_READING_FILE",
							SubtypeLoader.class,
							new Object[] { jarFileName }));
				currentJarFile.close();
				throw new RuntimeException("JAR file has size 0!?");
			}

			final List<ClassFile> currentListOfClasses = new ArrayList<ClassFile>();
			final Enumeration<?> classList = currentJarFile.entries();

			while (classList.hasMoreElements()) {
				try {
					final JarEntry jarEntry =
						(JarEntry) classList.nextElement();

					if (!jarEntry.isDirectory()
							&& jarEntry.getName().endsWith(extension)) {

						final InputStream inputStream =
							currentJarFile.getInputStream(jarEntry);
						final ClassFile currentClass =
							new ClassFile(inputStream);

						if (supertype == null
								|| currentClass.getSuperName() != null
								&& currentClass.getSuperName().equals(
									supertype.getName())) {

							currentListOfClasses.add(currentClass);
						}
						else {
							boolean isSuperInterfaceFound = false;
							for (int i = 0; i < currentClass
								.getInterfaces()
								.length() && !isSuperInterfaceFound; i++) {
								if (currentClass
									.getInterfaces()
									.get(i)
									.equals(supertype.getName())) {

									currentListOfClasses.add(currentClass);
									isSuperInterfaceFound = true;
								}
							}
						}

						inputStream.close();
					}
				}
				catch (final Exception e) {
					e.printStackTrace(ProxyConsole.getInstance().errorOutput());
				}
			}
			currentJarFile.close();

			final ClassFile[] results =
				new ClassFile[currentListOfClasses.size()];
			currentListOfClasses.toArray(results);
			Arrays.sort(results, new ClassNameAlphabeticalComparator());
			return results;
		}
		catch (final IOException ioe) {
			ProxyConsole
				.getInstance()
				.errorOutput()
				.println(
					MultilingualManager.getString(
						"Err_READING_FILE",
						SubtypeLoader.class,
						new Object[] { jarFileName }));
			ioe.printStackTrace(ProxyConsole.getInstance().errorOutput());
			throw new RuntimeException(ioe);
		}
	}
	
	public static ClassFile[] loadSubtypesFromJarInputStream(
		final ClassFile supertype,
		final JarInputStream jarInputStream,
		final String extension) {

		try {
			if (jarInputStream.available() == 0) {
				ProxyConsole
					.getInstance()
					.errorOutput()
					.println(
						MultilingualManager.getString(
							"Err_READING_STREAM",
							SubtypeLoader.class,
							new Object[] { "jar input stream" }));
				jarInputStream.close();
				throw new RuntimeException("No data available");
			}

			final List<ClassFile> currentListOfClasses = new ArrayList<ClassFile>();
		
			for (JarEntry jarEntry = jarInputStream.getNextJarEntry() ; jarEntry != null; 
					jarEntry = jarInputStream.getNextJarEntry()) {
				try {
					if (!jarEntry.isDirectory()
							&& jarEntry.getName().endsWith(extension)) {						
						
						final InputStream inputStream = jarInputStream;
/*						 final BufferedInputStream inputStream =
								new BufferedInputStream(jarInputStream,(int) jarEntry.getSize());
*/						
						final ClassFile currentClass =
							new ClassFile(inputStream);

						if (supertype == null
								|| currentClass.getSuperName() != null
								&& currentClass.getSuperName().equals(
									supertype.getName())) {

							currentListOfClasses.add(currentClass);
						}
						else {
							boolean isSuperInterfaceFound = false;
							for (int i = 0; i < currentClass
								.getInterfaces()
								.length() && !isSuperInterfaceFound; i++) {
								if (currentClass
									.getInterfaces()
									.get(i)
									.equals(supertype.getName())) {

									currentListOfClasses.add(currentClass);
									isSuperInterfaceFound = true;
								}
							}
						}
					}
					
					jarInputStream.closeEntry();
				}
				catch (final Exception e) {
					e.printStackTrace(ProxyConsole.getInstance().errorOutput());
				}
			}
			jarInputStream.close();

			final ClassFile[] results =
				new ClassFile[currentListOfClasses.size()];
			currentListOfClasses.toArray(results);
			Arrays.sort(results, new ClassNameAlphabeticalComparator());
			return results;
		}
		catch (final IOException ioe) {
			ProxyConsole
				.getInstance()
				.errorOutput()
				.println(
					MultilingualManager.getString(
						"Err_READING_FILE",
						SubtypeLoader.class,
						new Object[] { "Jar Input Stream" }));
			ioe.printStackTrace(ProxyConsole.getInstance().errorOutput());
			throw new RuntimeException(ioe);
		}
	}
	
	
	
	public static ClassFile[] loadSubtypesFromStream(
		final String supertypeName,
		final NamedInputStream[] files,
		final String packageName,
		final String extension) {

		final String directory =
			packageName.replace('.', util.io.Files.getSeparatorChar())
					+ util.io.Files.getSeparatorChar();
		final List<ClassFile> currentListOfClasses = new ArrayList<ClassFile>();
		for (int i = 0; i < files.length; i++) {
			int index;
			if (files[i].getName().endsWith(extension)
					&& (index = files[i].getName().indexOf(directory)) > -1
					&& files[i].getName().indexOf(
						File.separatorChar,
						index + directory.length() + 1) == -1) {

				ProxyConsole
					.getInstance()
					.debugOutput()
					.print(
						MultilingualManager.getString(
							"LOADING_FROM",
							SubtypeLoader.class,
							new Object[] { files[i].getName() }));
				try {
					SubtypeLoader.loadSubtypeFromStream(
						supertypeName,
						currentListOfClasses,
						files[i].getStream());
				}
				catch (final IOException e) {
					ProxyConsole
						.getInstance()
						.errorOutput()
						.print("Exception while reading file: ");
					ProxyConsole.getInstance().errorOutput().println(files[i]);
					e.printStackTrace(ProxyConsole.getInstance().errorOutput());
				}
			}
		}

		final ClassFile[] results = new ClassFile[currentListOfClasses.size()];
		currentListOfClasses.toArray(results);
		Arrays.sort(results, new ClassNameAlphabeticalComparator());
		return results;
	}
	private SubtypeLoader() {
	}
}