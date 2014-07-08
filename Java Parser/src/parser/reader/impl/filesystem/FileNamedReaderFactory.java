/**
 * Copyright 2011 and later, Wei Wu  All rights reserved.
 * 
 * @author Wei Wu
 * @created 2011-01-14
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
package parser.reader.impl.filesystem;

import java.io.File;
import parser.reader.NamedReader;
import parser.reader.NamedReaderType;
import parser.reader.impl.NamedReaderFactory;
import common.tools.file.FileTools;

public enum FileNamedReaderFactory {

	Instance;

	public NamedReader createNamedReaderFromFile(
		final String sourceFileOrDirectory) {
		final File file = new File(sourceFileOrDirectory);
		NamedReaderType type = null;

		if (file.isDirectory()) {
			type = NamedReaderType.Directory;
		}
		else if (file.isFile()) {
			if (FileTools.Instance.isJarFile(file)) {
				type = NamedReaderType.JarFile;
			}
			else if (FileTools.Instance.isJavaFile(file)) {
				type = NamedReaderType.JavaFile;
			}
			else {
				type = NamedReaderType.Other;
			}
		}
		else {
			type = NamedReaderType.Other;
		}

		return NamedReaderFactory.Instance.createNamedReader(
			FileTools.Instance.getCanonicalPath(file),
			type);
	}

}
