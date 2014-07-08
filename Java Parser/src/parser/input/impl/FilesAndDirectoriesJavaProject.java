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
package parser.input.impl;

import java.util.ArrayList;
import java.util.List;
import parser.reader.NamedReader;
import parser.reader.NamedReaderType;

public class FilesAndDirectoriesJavaProject extends FileSystemJavaProject {

	private final NamedReader[] namedReaders;
	public FilesAndDirectoriesJavaProject(
		final List<String> classPaths,
		final List<String> sourceFilesAndDirectories,
		final List<String> aListOfFilesAndDiecrtories) throws Exception {

		super(classPaths, sourceFilesAndDirectories);
		this.namedReaders =
			this.buildSourcepathEntries(aListOfFilesAndDiecrtories);

	}

	protected NamedReader[] buildCompilationUnitList() {
		final List<NamedReader> compilationUnits = new ArrayList<NamedReader>();

		for (final NamedReader src : this.namedReaders) {
			compilationUnits.addAll(this.findReadersByType(
				src,
				NamedReaderType.JavaFile));
		}
		return compilationUnits.toArray(new NamedReader[0]);
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
		else if (parent.getType() == NamedReaderType.JavaFile) {
			readersFound.add(parent);
		}
		return readersFound;
	}
}
