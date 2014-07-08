/* (c) Copyright 2001 and following years, Aminata SABANÉ,
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
package padl.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class FilesUtils {
	public static void unzip(String aZipFilePath) throws ZipException,
			IOException {

		// TODO: Final the local variable plus what is this magic number?
		int BUFFER = 2048;
		File file = new File(aZipFilePath);

		ZipFile zip = new ZipFile(file);
		String newPath = aZipFilePath.substring(0, aZipFilePath.length() - 4);

		new File(newPath).mkdir();
		Enumeration zipFileEntries = zip.entries();

		// Process each entry
		while (zipFileEntries.hasMoreElements()) {
			// grab a zip file entry
			ZipEntry currentEntry = (ZipEntry) zipFileEntries.nextElement();

			String currentEntryName = currentEntry.getName();

			File destFile = new File(newPath, currentEntryName);
			//destFile = new File(newPath, destFile.getName());
			// create the parent directory structure if needed
			if (currentEntryName.endsWith(".zip")) {
				// TODO: Remove ugly code!
				System.out.println("ici");
			}
			if (currentEntry.isDirectory()) {
				destFile.mkdirs();
			}

			else {
				File destinationParent = destFile.getParentFile();
				if (destinationParent != null) {
					destinationParent.mkdirs();
				}

				BufferedInputStream is =
					new BufferedInputStream(zip.getInputStream(currentEntry));
				int currentByte;
				// establish buffer for writing file
				byte data[] = new byte[BUFFER];

				// write the current file to disk
				FileOutputStream fos = new FileOutputStream(destFile);
				BufferedOutputStream dest =
					new BufferedOutputStream(fos, BUFFER);

				// read and write until last byte is encountered
				while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, currentByte);
				}
				dest.flush();
				dest.close();
				is.close();
			}
			if (currentEntryName.endsWith(".zip")) {
				// found a zip file, try to open
				unzip(destFile.getAbsolutePath());
			}

		}
		zip.close();
		new File(aZipFilePath).delete();
	}
	/**
	 * 
	 * @param absolutePath
	 * @return
	 */
	public static List getZipsList(String absolutePath) {

		List zipsList = new ArrayList();

		File file = new File(absolutePath);
		if (file.isFile() && file.getPath().endsWith(".tar.gz")) {
			zipsList.add(absolutePath);

		}
		else if (file.isDirectory()) {
			File[] files = file.listFiles();

			for (int i = 0; i < files.length; i++) {
				zipsList.addAll(getZipsList(files[i].getAbsolutePath()));
			}
		}

		return zipsList;
	}

	/**
	 * 
	 * @param dir
	 * @return
	 */
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success)
					return false;
			}
		}

		// The directory is now empty so delete it
		return dir.delete();
	}

	/**
	 * Clean the dir and keep only javaFiles and jarfiles
	 * @param dir
	 * @return
	 */
	public static void filterDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				filterDir(new File(dir, children[i]));

			}
			if (dir.list().length == 0) {
				dir.delete();
			}
		}
		else {
			if (!dir.getName().endsWith(".java")) {
				dir.delete();
			}
		}

	}

	public static long countJavaFiles(File dir) {
		long javaFilesNumber = 0;
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				javaFilesNumber =
					javaFilesNumber
							+ countJavaFiles(new File(dir, children[i]));

			}

		}
		else {
			if (dir.getName().endsWith(".java")) {
				javaFilesNumber = 1;

			}
		}

		return javaFilesNumber;
	}
}
