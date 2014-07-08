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
package parser.wrapper;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import parser.input.SourceInputsHolder;
import parser.reader.NamedReader;

public class JavaParser {
	private final SourceInputsHolder javaProject;
	private final EclipseJDTParserWrapper wrapperClient;

	public JavaParser(final SourceInputsHolder javaProject) {
		this.javaProject = javaProject;
		this.wrapperClient = new EclipseJDTParserWrapper(this.javaProject);
	}

	public NamedCompilationUnit[] parse() {
		final List<NamedCompilationUnit> namedCompilationUnits =
			new ArrayList<NamedCompilationUnit>();

		for (final NamedReader compilationUnit : this.javaProject
			.getCompilationUnitList()) {
			final ASTNode astNode =
				this.wrapperClient.parseJavaSourceCode(compilationUnit);

			if (astNode instanceof CompilationUnit) {
				final NamedCompilationUnit namedCU =
					new NamedCompilationUnit(
						compilationUnit.getName(),
						(CompilationUnit) astNode);
				namedCompilationUnits.add(namedCU);
			}
		}

		return namedCompilationUnits.toArray(new NamedCompilationUnit[0]);
	}

	/**
	 * @param visitor
	 *            Set to null if not parsing the NamedCompilationUnit[]. *
	 */
	//	public void parse(final ExtendedASTVisitor visitor) {
	//
	//		for (final NamedReader compilationUnit : this.javaProject
	//				.getCompilationUnitList()) {
	//			final ASTNode astNode = this.wrapperClient
	//					.parseJavaSourceCode(compilationUnit);
	//
	//			if (astNode instanceof CompilationUnit) {
	//				final NamedCompilationUnit namedCU = new NamedCompilationUnit(
	//						compilationUnit.getName(), (CompilationUnit) astNode);
	//				namedCU.accept(visitor);
	//			}
	//		}
	//	}

	/**
	 * @param visitor
	 *            Set to null if not parsing the NamedCompilationUnit[]. *
	 */
	public void parse(final ExtendedASTVisitor visitor) {
		final NamedReader[] filesToParse =
			this.javaProject.getCompilationUnitList();
		for (int i = 0; i < filesToParse.length; i++) {
			final NamedReader fileToParse = filesToParse[i];

			final ASTNode astNode =
				this.wrapperClient.parseJavaSourceCode(fileToParse);

			if (astNode instanceof CompilationUnit) {
				final NamedCompilationUnit namedCU =
					new NamedCompilationUnit(
						fileToParse.getName(),
						(CompilationUnit) astNode);
				namedCU.accept(visitor);
			}
		}
	}
}
