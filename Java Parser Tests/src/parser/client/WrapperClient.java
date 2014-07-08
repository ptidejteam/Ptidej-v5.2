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
package parser.client;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;

import parser.input.SourceInputsHolder;
import parser.reader.NamedReader;
import parser.wrapper.EclipseJDTParserWrapper;

public class WrapperClient {

	protected SourceInputsHolder javaProject;
	protected EclipseJDTParserWrapper parser;

	public WrapperClient(final SourceInputsHolder javaProject) {
		this.javaProject = javaProject;
		this.parser = new EclipseJDTParserWrapper(this.javaProject);
	}

	public void parse(final NamedReader[] javaSourceList,
			final ASTVisitor[] visitors) {

		for (final NamedReader javaFilePath : javaSourceList) {
			this.parseAJavaSource(javaFilePath, visitors);
		}
	}

	private void parseAJavaSource(final NamedReader javaSourcePath,
			final ASTVisitor[] visitors) {

		final ASTNode node = this.parser.parseJavaSourceCode(javaSourcePath);

		if (visitors != null) {
			for (final ASTVisitor visitor : visitors) {
				node.accept(visitor);
			}
		}
	}

	public void parseAllJavaSources(final ASTVisitor[] visitors) {
		this.parse(this.javaProject.getCompilationUnitList(), visitors);
	}

}
