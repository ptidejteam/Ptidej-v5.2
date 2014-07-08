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
package parser.input;

import java.util.Arrays;
import parser.reader.NamedReader;

public abstract class SourceInputsHolder {
	private NamedReader[] classpathEntries;
	private NamedReader[] sourcepathEntries;
	private NamedReader[] compilationUnitList;
	private String compilerCompliance = "1.6";

	abstract protected NamedReader[] buildCompilationUnitList();

	public NamedReader[] getClasspathEntries() {
		if (this.classpathEntries == null) {
			this.classpathEntries = new NamedReader[0];
		}
		return this.classpathEntries;
	}

	public NamedReader[] getCompilationUnitList() {

		if (this.compilationUnitList == null) {
			this.setCompilationUnitList(this.buildCompilationUnitList());
		}
		return this.compilationUnitList;
	}

	public String getCompilerCompliance() {
		return this.compilerCompliance;
	}

	public NamedReader[] getSourcepathEntries() {
		if (this.sourcepathEntries == null) {
			this.sourcepathEntries = new NamedReader[0];
		}
		return this.sourcepathEntries;
	}

	protected SourceInputsHolder setClasspathEntries(
		final NamedReader[] classpathEntries) {
		this.classpathEntries = classpathEntries;
		return this;
	}

	protected SourceInputsHolder setCompilationUnitList(
		final NamedReader[] compilationUnitList) {
		Arrays.sort(compilationUnitList);
		this.compilationUnitList = compilationUnitList;
		return this;
	}

	public SourceInputsHolder setCompilerCompliance(
		final String compilerCompliance) {
		this.compilerCompliance = compilerCompliance;
		return this;
	}

	protected SourceInputsHolder setSourcepathEntries(
		final NamedReader[] sourcepathEntries) {
		this.sourcepathEntries = sourcepathEntries;
		return this;
	}

}
