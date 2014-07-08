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
import util.io.ProxyConsole;
import common.tools.file.FileTools;

/**
 * Source file is not necessary to be ended with .java
 * 
 * @author Wei Wu
 * 
 */
public class SourceFileNamedReader extends NamedReader {

	/**
	 * @param name
	 * @param type
	 */
	public SourceFileNamedReader(final String name, final NamedReaderType type) {
		super(name, type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see parser.reader.NamedReader#read()
	 */
	@Override
	public NamedReader[] read() {
		final File file = new File(this.getName());

		if (!file.isFile()) {
			ProxyConsole
				.getInstance()
				.errorOutput()
				.println(this.getName() + " is not a file!");
			return null;
		}

		return new NamedReader[] { NamedReaderFactory.Instance
			.createNamedReader(
				new String(FileTools.Instance.readFile(file)),
				NamedReaderType.SourceCode) };
	}

}
