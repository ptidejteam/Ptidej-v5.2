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
package parser.reader;

public abstract class NamedReader implements Comparable<NamedReader> {
	private final String name;

	private final NamedReaderType type;

	public NamedReader(final String name, final NamedReaderType type) {
		this.name = name;
		this.type = type;
	}

	public int compareTo(final NamedReader arg0) {
		return this.getName().compareTo(arg0.getName());
	}

	public String getName() {
		return this.name;
	}

	public NamedReaderType getType() {
		return this.type;
	}

	abstract public NamedReader[] read();
}
