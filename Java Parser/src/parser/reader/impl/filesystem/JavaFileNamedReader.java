/**
 * Copyright © 2010, Wei Wu  All rights reserved.
 * 
 * @author Wei Wu
 * @created 2010-12-18
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

import parser.reader.NamedReader;
import parser.reader.NamedReaderType;
import util.io.ProxyConsole;
import common.tools.constants.Constants;

public class JavaFileNamedReader extends SourceFileNamedReader {

	public JavaFileNamedReader(final String name, final NamedReaderType type) {
		super(name.trim(), type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.wrapper.input.NamedReader#read()
	 */
	@Override
	public NamedReader[] read() {

		if (!this.getName().endsWith(Constants.JAVA_FILE_EXT)) {
			ProxyConsole
				.getInstance()
				.errorOutput()
				.println(this.getName() + " is not a Java file!");
			return null;
		}

		return super.read();
	}

}
