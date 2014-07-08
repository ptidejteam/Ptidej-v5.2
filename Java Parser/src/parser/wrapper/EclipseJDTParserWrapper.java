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
package parser.wrapper;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import parser.input.SourceInputsHolder;
import parser.reader.NamedReader;
import common.tools.constants.Constants;

public class EclipseJDTParserWrapper {
	private final SourceInputsHolder javaProject;
	private final ASTParser parser;

	public EclipseJDTParserWrapper(final SourceInputsHolder javaProject) {
		this.javaProject = javaProject;

		JavaCore.setComplianceOptions(
			this.javaProject.getCompilerCompliance(),
			JavaCore.getOptions());

		this.parser = ASTParser.newParser(AST.JLS4);
	}

	/**
	 * Covert NamedReades' name to the form that the Eclipse JDT parser accept.
	 * For now, they are in the same format.
	 * 
	 * @param namedReaders
	 * @return
	 */
	private final String[] getNamesFromNamedReaders(
		final NamedReader[] namedReaders) {
		final String[] names = new String[namedReaders.length];
		for (int i = namedReaders.length - 1; i >= 0; i--) {
			names[i] = namedReaders[i].getName();
		}
		return names;
	}

	/**
	 * The source code stored in the name of the only NamedReader gotten from
	 * compilationUnit.
	 * 
	 * @param compilationUnit
	 * @return SourceCode
	 */
	private String getSourceCode(final NamedReader compilationUnit) {

		final StringBuffer sourceCode = new StringBuffer(Constants.EMPTY_STR);

		for (final NamedReader reader : compilationUnit.read()) {
			sourceCode.append(reader.getName());
		}

		return sourceCode.toString();
	}

	public ASTNode[] parse() {

		final List<ASTNode> astNodes = new ArrayList<ASTNode>();

		for (final NamedReader compilationUnit : this.javaProject
			.getCompilationUnitList()) {
			astNodes.add(this.parseJavaSourceCode(compilationUnit));
		}
		return astNodes.toArray(new ASTNode[0]);
	}

	/*
	 * Parse a given Java File of the Project
	 */
	public ASTNode parseJavaSourceCode(final NamedReader compilationUnit) {
		this.parser.setResolveBindings(true);
		this.parser.setBindingsRecovery(true);
		this.parser.setEnvironment(
			this.getNamesFromNamedReaders(this.javaProject
				.getClasspathEntries()),
			this.getNamesFromNamedReaders(this.javaProject
				.getSourcepathEntries()),
			null,
			true);

		this.parser
			.setSource(this.getSourceCode(compilationUnit).toCharArray());
		this.parser.setUnitName(compilationUnit.getName());

		final ASTNode node = this.parser.createAST(null);
		return node;
	}
}
