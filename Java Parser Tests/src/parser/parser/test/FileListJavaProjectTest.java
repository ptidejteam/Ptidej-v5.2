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

import junit.framework.Assert;
import parser.input.SourceInputsHolder;
import parser.input.impl.FileListJavaProject;
import parser.wrapper.JavaParser;
import parser.wrapper.NamedCompilationUnit;

public class FileListJavaProjectTest {
	public void testParse() throws Exception {
		final SourceInputsHolder javaProject =
			new FileListJavaProject(
				null,
				null,
				"../Java Parser Tests/resources/NestingClasses/FileList.txt");

		final NamedCompilationUnit[] namedCompilationUnits =
			new JavaParser(javaProject).parse();

		Assert.assertEquals("JavaParserTest", namedCompilationUnits.length, 3);
	}
	public void testParseAnyFile() throws Exception {
		final SourceInputsHolder javaProject =
			new FileListJavaProject(
				null,
				null,
				"../Java Parser Tests/resources/NestingClasses/FileListNotJavaFile.txt");

		final NamedCompilationUnit[] namedCompilationUnits =
			new JavaParser(javaProject).parse();

		Assert.assertEquals("JavaParserTest", namedCompilationUnits.length, 3);
	}
}
