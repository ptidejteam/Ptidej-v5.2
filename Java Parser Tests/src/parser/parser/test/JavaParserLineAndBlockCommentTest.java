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
package parser.parser.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;
import org.eclipse.jdt.core.dom.Comment;
import parser.input.SourceInputsHolder;
import parser.input.impl.FileSystemJavaProject;
import parser.reader.impl.filesystem.FileNamedReaderFactory;
import parser.wrapper.JavaParser;
import parser.wrapper.NamedCompilationUnit;

public class JavaParserLineAndBlockCommentTest {
	public void testParse() throws Exception {
		final String[] classpathEntries = new String[] { "" };
		final String[] sourcepathEntries =
			new String[] { "../Java Parser Tests/resources/NestingClasses/src" };

		final SourceInputsHolder javaProject =
			new FileSystemJavaProject(
				Arrays.asList(classpathEntries),
				Arrays.asList(sourcepathEntries));

		final NamedCompilationUnit[] namedCompilationUnits =
			new JavaParser(javaProject).parse();

		final List<String> comments = new ArrayList<String>();

		for (final NamedCompilationUnit namedCompilationUnit : namedCompilationUnits) {
			for (final Comment comment : namedCompilationUnit
				.getLineAndBlockComment()) {
				comments.add(namedCompilationUnit.getCommentContent(
					comment,
					FileNamedReaderFactory.Instance
						.createNamedReaderFromFile(namedCompilationUnit
							.getJavaFilePath())));
			}
		}
		Assert.assertEquals(
			JavaParserLineAndBlockCommentTest.class.getName(),
			2,
			comments.size());
	}
}
