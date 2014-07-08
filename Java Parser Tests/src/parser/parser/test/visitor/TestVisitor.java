/**
 * Copyright © 2010, Wei Wu  All rights reserved.
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
package parser.parser.test.visitor;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import parser.wrapper.ExtendedASTVisitor;
import parser.wrapper.NamedCompilationUnit;

/**
 * 
 */
public class TestVisitor extends ExtendedASTVisitor {

	private void display(final String str) {
		System.out.println(str);
	}

	@Override
	public void endVisit(final CompilationUnit node) {
		this.display("Leave a Compilation Unit");
		super.endVisit(node);
	}

	@Override
	public void endVisit(final NamedCompilationUnit aNamedCompilationUnit) {
		this.display("Leave a Named Compilation Unit \n");
		super.endVisit(aNamedCompilationUnit);
	}

	@Override
	public void endVisit(final TypeDeclaration node) {
		this.display("Finish " + node.resolveBinding().getQualifiedName());
		super.endVisit(node);
	}

	@Override
	public void endVisitJavaFilePath(final String javaFilePath) {
		this.display("Finish " + javaFilePath);
		super.endVisitJavaFilePath(javaFilePath);
	}

	@Override
	public boolean visit(final CompilationUnit node) {
		this.display("Enter a Compilation Unit");
		return super.visit(node);
	}

	@Override
	public boolean visit(final NamedCompilationUnit aNamedCompilationUnit) {
		this.display("Enter a Named Compilation Unit");
		return super.visit(aNamedCompilationUnit);
	}

	@Override
	public boolean visit(final TypeDeclaration node) {
		this.display("Visit " + node.resolveBinding().getQualifiedName());
		return super.visit(node);
	}

	@Override
	public boolean visitJavaFilePath(final String javaFilePath) {
		this.display("Visit " + javaFilePath);
		return super.visitJavaFilePath(javaFilePath);
	}

}
