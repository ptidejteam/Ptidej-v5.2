/**
 * Copyright � 2010, Wei Wu  All rights reserved.
 * 
 * @author Wei Wu
 * @created 2010-12-15
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
package parser.input.impl;

import java.util.ArrayList;
import java.util.List;
import parser.input.SourceInputsHolder;
import parser.reader.NamedReader;
import parser.reader.NamedReaderType;
import parser.reader.impl.filesystem.FileNamedReaderFactory;

public class FileSystemJavaProject extends SourceInputsHolder {
	public FileSystemJavaProject(
		final List<String> classPaths,
		final List<String> sourceFilesAndDirectories) throws Exception {

		this.buildClasspathEntries(classPaths);
		this.setSourcepathEntries(this
			.buildSourcepathEntries(sourceFilesAndDirectories));
	}

	protected void buildClasspathEntries(final List<String> classPaths) {
		final List<NamedReader> jars = new ArrayList<NamedReader>();

		if (classPaths != null) {
			for (final String classpath : classPaths) {
				final NamedReader reader =
					FileNamedReaderFactory.Instance
						.createNamedReaderFromFile(classpath);
				if (reader != null) {
					jars.addAll(this.findReadersByType(
						reader,
						NamedReaderType.JarFile));
				}
			}
		}

		this.setClasspathEntries(jars.toArray(new NamedReader[0]));
	}

	protected NamedReader[] buildCompilationUnitList() {
		final List<NamedReader> compilationUnits = new ArrayList<NamedReader>();

		for (final NamedReader src : this.getSourcepathEntries()) {
			compilationUnits.addAll(this.findReadersByType(
				src,
				NamedReaderType.JavaFile));
		}
		return compilationUnits.toArray(new NamedReader[0]);
	}

	protected NamedReader[] buildSourcepathEntries(
		final List<String> sourceFilesAndDirectories) {
		final List<NamedReader> readers = new ArrayList<NamedReader>();

		if (sourceFilesAndDirectories != null) {
			NamedReader reader = null;
			for (final String sourceFileOrDirectory : sourceFilesAndDirectories) {
				reader =
					FileNamedReaderFactory.Instance
						.createNamedReaderFromFile(sourceFileOrDirectory);
				if (reader != null) {
					readers.add(reader);
				}
			}
		}

		return readers.toArray(new NamedReader[0]);
	}

	protected List<NamedReader> findReadersByType(
		final NamedReader parent,
		final NamedReaderType type) {

		final List<NamedReader> readersFound = new ArrayList<NamedReader>();

		if (parent.getType() == NamedReaderType.JarFile) {
			readersFound.add(parent);
		}
		else if (parent.getType() == NamedReaderType.Directory) {
			final NamedReader[] elements = parent.read();
			if (elements != null) {
				for (final NamedReader element : elements) {
					if (element != null) {
						if (element.getType() == NamedReaderType.Directory) {
							readersFound.addAll(this.findReadersByType(
								element,
								type));
						}
						else if (element.getType() == type) {
							readersFound.add(element);
						}
					}
				}
			}
		}
		return readersFound;
	}

}
