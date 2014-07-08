/**
 * Copyright © 2010, Wei Wu  All rights reserved.
 * 
 * @author Wei Wu
 * @created 2010-11-17
 *
 * This program is free for non-profit use. For the purpose, you can 
 * redistribute it and/or modify it under the terms of the GNU General 
 * Public License as published by the Free Software Foundation, either 
 * version 3 of the License, or (at your option) any later version.

 * For other uses, please contact the author at:
 * wu.wei.david@gmail.com

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * For the GNU General Public License, see <http://www.gnu.org/licenses/>.
 */
package common.tools.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import util.io.ProxyConsole;
import util.io.ProxyDisk;
import common.tools.constants.Constants;

public enum FileTools {

	Instance;

	private void addFileByExtension(
		final List<String> list,
		final File file,
		final String extension) {
		if (this.isAFileWithAnExtension(file, extension)) {
			list.add(this.getCanonicalPath(file));
		}
	}

	public File createNoExistingFile(final File file) {
		final File newFile = file;
		try {
			if (!newFile.exists()) {
				final File parent = newFile.getParentFile();

				if (!parent.exists()) {
					if (parent.mkdirs() == false) {
						return null;
					}
				}

				if (newFile.createNewFile() == false) {
					return null;
				}
			}

		}
		catch (final Exception e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

		return newFile;
	}

	public String getCanonicalPath(final File file) {

		String path = null;
		try {
			path = file.getCanonicalPath();
		}
		catch (final IOException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		return path;
	}

	public String getCurrentDirectory() {
		try {
			return new File(Constants.POINT + Constants.SEPARATOR)
				.getCanonicalPath();
		}
		catch (final IOException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		return Constants.EMPTY_STR;
	}

	public String[] getElementList(final File dir) {
		final String elements[] = dir.list();
		StringBuffer absPath = null;
		final String[] paths = new String[elements.length];

		for (int i = elements.length - 1; i >= 0; i--) {
			absPath =
				new StringBuffer(dir.getPath())
					.append(Constants.SEPARATOR)
					.append(elements[i]);
			paths[i] = absPath.toString();
		}

		return paths;
	}

	public List<String> getFileListByExtension(
		final File file,
		final String extension) {
		final List<String> fileList = new ArrayList<String>();
		if (file.isDirectory()) {
			final String elements[] = file.list();
			StringBuffer absPath = null;
			for (final String name : elements) {
				absPath =
					new StringBuffer(file.getPath())
						.append(Constants.SEPARATOR)
						.append(name);
				final File element = new File(absPath.toString());

				if (element.isDirectory()) {
					fileList.addAll(this.getFileListByExtension(
						element,
						extension));
				}
				else {
					this.addFileByExtension(fileList, element, extension);
				}
			}

		}
		else {
			this.addFileByExtension(fileList, file, extension);
		}
		return fileList;
	}

	public boolean isAFileWithAnExtension(
		final File file,
		final String extension) {
		return file.getPath().toLowerCase().endsWith(extension);
	}

	public boolean isJarFile(final File file) {
		return this.isAFileWithAnExtension(file, Constants.JAR_FILE_EXT);
	}

	public boolean isJavaFile(final File file) {
		return this.isAFileWithAnExtension(file, Constants.JAVA_FILE_EXT);
	}

	public char[] readFile(final File file) {
		if (file.isDirectory()) {
			return null;
		}

		char[] buffer = null;

		try {
			final FileReader fileReader =
				ProxyDisk.getInstance().fileAbsoluteInput(file);
			final BufferedReader bufferedReader =
				new BufferedReader(fileReader);
			buffer = new char[(int) file.length()];
			bufferedReader.read(buffer);
			bufferedReader.close();
			fileReader.close();
		}
		catch (final Exception e) {
			buffer = null;
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		return buffer;
	}
	public char[] readTempFile(final File file) {
		// TODO Unify readTempFile() and readAbsoluteFile()
		if (file.isDirectory()) {
			return null;
		}

		final StringBuffer buffer = new StringBuffer();
		try {
			final FileReader fileReader =
				ProxyDisk.getInstance().fileTempInput(file);
			int c;
			while ((c = fileReader.read()) != -1) {
				buffer.append((char) c);
			}
			fileReader.close();
		}
		catch (final Exception e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		return buffer.toString().toCharArray();
	}

	/**
	 * 
	 * @param file
	 * @param text
	 * @param append
	 * @return true if write successfully, otherwise false.
	 */

	public boolean writeTempFile(
		final File file,
		final char[] content,
		final boolean append) {

		if (file.isDirectory()) {
			return false;
		}
		try {
			final Writer fileWriter =
				ProxyDisk.getInstance().fileTempOutput(file, append);
			final BufferedWriter bufferedWriter =
				new BufferedWriter(fileWriter);

			bufferedWriter.write(content);
			bufferedWriter.flush();
			fileWriter.flush();
			bufferedWriter.close();
			fileWriter.close();

			return true;
		}
		catch (final Exception e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		return false;
	}
}
