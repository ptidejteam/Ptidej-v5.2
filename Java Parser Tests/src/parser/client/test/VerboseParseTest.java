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
package parser.client.test;

import java.util.Arrays;
import org.eclipse.jdt.core.dom.ASTVisitor;
import parser.client.WrapperClientWithLog;
import parser.client.test.visitor.MyVisitor;
import parser.input.SourceInputsHolder;
import parser.input.impl.FileSystemJavaProject;

/**
 * 
 */
public class VerboseParseTest extends ParseTest {
	public VerboseParseTest() throws Exception {
		this("VerboseParseTest");
	}
	public VerboseParseTest(final String aName) throws Exception {
		super(aName);

		final String classPathEntry =
			"../Java Parser Tests/resources/CodeAnalyser/libs/tools.jar";
		final String[] classpathEntries = new String[] { classPathEntry };

		final String sourcePathEntry =
			"../Java Parser Tests/resources/CodeAnalyser/src";
		final String[] sourcePathEntries = new String[] { sourcePathEntry };

		final String resultFilePath = "log_verbose.txt";

		final SourceInputsHolder javaProject =
			new FileSystemJavaProject(
				Arrays.asList(classpathEntries),
				Arrays.asList(sourcePathEntries));

		final WrapperClientWithLog parserClient =
			new WrapperClientWithLog(javaProject, resultFilePath);

		final String oracleFilePath =
			"../Java Parser Tests/resources/CodeAnalyser/log/log_oracle_verbose.txt";

		final String testCaseName = "CodeAnalyser Verbose Test Case";

		final ASTVisitor visitor = new MyVisitor(parserClient);

		this.init(
			resultFilePath,
			oracleFilePath,
			testCaseName,
			parserClient,
			visitor);
	}

	public void testParse() {
		super.testParse();
	}
}
