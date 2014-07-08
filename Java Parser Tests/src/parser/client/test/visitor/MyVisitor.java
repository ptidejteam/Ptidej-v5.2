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
package parser.client.test.visitor;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Type;

import parser.client.WrapperClientWithLog;

/*
 * A simple test visitor
 */
public class MyVisitor extends ASTVisitor {

	private final WrapperClientWithLog javaProject;

	public MyVisitor(final WrapperClientWithLog javaProject) {
		this.javaProject = javaProject;
	}

	private void cannotSolve(final ASTNode node) {
		// this.javaProject.log("Cannot solve "+node);
	}

	@Override
	public boolean visit(final MethodDeclaration node) {

		final Type type = node.getReturnType2();
		if (type != null) {
			final ITypeBinding typeBinding = type.resolveBinding();

			if (typeBinding != null) {
				this.javaProject.log(typeBinding.getQualifiedName());
			} else {
				this.cannotSolve(type);
			}
		} else {
			this.cannotSolve(node);
		}

		return super.visit(node);
	}
}
