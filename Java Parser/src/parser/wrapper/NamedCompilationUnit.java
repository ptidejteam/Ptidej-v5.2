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
import java.util.Collections;
import java.util.List;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import parser.reader.NamedReader;

public class NamedCompilationUnit {
	private final String javaFilePath;
	private final CompilationUnit compilationUnit;

	public NamedCompilationUnit(
		final String javaFilePath,
		final CompilationUnit compilationUnit) {
		this.javaFilePath = javaFilePath;
		this.compilationUnit = compilationUnit;
	}

	public void accept(final ExtendedASTVisitor visitor) {
		if (visitor != null) {
			if (visitor.visit(this)) {
				if (visitor.visitJavaFilePath(this.javaFilePath)) {
					this.compilationUnit.accept(visitor);
				}
				visitor.endVisitJavaFilePath(this.javaFilePath);
			}
			visitor.endVisit(this);
		}
	}

	public String getCommentContent(
		final Comment comment,
		final NamedReader sourceReader) {
		final NamedReader[] sources = sourceReader.read();

		String src = "";

		if (sources != null) {
			for (final NamedReader source : sources) {
				src += source.getName();
			}
		}

		return src.substring(
			comment.getStartPosition(),
			comment.getStartPosition() + comment.getLength());
	}

	public CompilationUnit getCompilationUnit() {
		return this.compilationUnit;
	}

	public String getJavaFilePath() {
		return this.javaFilePath;
	}

	public List<Comment> getLineAndBlockComment() {
		if (this.compilationUnit != null) {
			@SuppressWarnings("unchecked")
			final List<Comment> comments =
				this.compilationUnit.getCommentList();
			if (comments != null) {
				final List<Comment> lineAndBlockComments =
					new ArrayList<Comment>();
				for (final Comment comment : comments) {
					if (comment.getNodeType() != ASTNode.JAVADOC) {
						lineAndBlockComments.add(comment);
					}
				}
				return Collections.unmodifiableList(lineAndBlockComments);
			}
		}
		return null;
	}
}
