/**
 * Copyright 2010, Wei Wu  All rights reserved.
 * 
 * @author Wei Wu
 * @created 2010-12-28
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
package parser.reader.impl;

import parser.reader.NamedReader;
import parser.reader.NamedReaderType;
import parser.reader.impl.filesystem.DirectoryNamedReader;
import parser.reader.impl.filesystem.JarFileNamedReader;
import parser.reader.impl.filesystem.JavaFileNamedReader;
import parser.reader.impl.filesystem.SourceFileNamedReader;

public enum NamedReaderFactory {

	Instance;

	public NamedReader createNamedReader(
		final String name,
		final NamedReaderType type) {
		NamedReader reader = null;

		switch (type) {
			case SourceCode :
				reader = new SourceCode(name, type);
				break;
			case JarFile :
				reader = new JarFileNamedReader(name, type);
				break;
			case JavaFile :
				reader = new JavaFileNamedReader(name, type);
				break;
			case SourceFile :
				reader = new SourceFileNamedReader(name, type);
				break;
			case Directory :
				reader = new DirectoryNamedReader(name, type);
				break;
			default :
				break;
		}

		return reader;
	}

}
