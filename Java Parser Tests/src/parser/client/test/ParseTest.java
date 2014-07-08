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

import java.io.File;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.eclipse.jdt.core.dom.ASTVisitor;
import parser.client.WrapperClient;
import common.tools.constants.Constants;
import common.tools.file.FileTools;

/**
 * 
 */
public abstract class ParseTest extends TestCase {
	private String resultFilePath;
	private String oracleFilePath;
	private String testCaseName;
	private WrapperClient parserClient;
	private ASTVisitor visitor;

	public ParseTest(final String aName) {
		super(aName);
	}
	protected void init(
		final String resultFilePath,
		final String oracleFilePath,
		final String testCaseName,
		final WrapperClient wrapper,
		final ASTVisitor visitor) {

		this.resultFilePath = resultFilePath;
		this.oracleFilePath = oracleFilePath;
		this.testCaseName = testCaseName;
		this.parserClient = wrapper;
		this.visitor = visitor;
	}

	/**
	 * Test method for
	 * {@link parser.wrapper.jdt.EclipseJDTParserWrapper#parse()}.
	 */
	protected void testParse() {
		this.parserClient
			.parseAllJavaSources(new ASTVisitor[] { this.visitor });

		final String STR = " java files took ";

		String oracle =
			new String(FileTools.Instance.readFile(new File(
				this.oracleFilePath))).replace(
				Constants.CR_LF,
				Constants.NEW_LINE);
		oracle = oracle.substring(1, oracle.lastIndexOf(STR));

		String result =
			new String(FileTools.Instance.readTempFile(new File(
				this.resultFilePath))).replace(
				Constants.CR_LF,
				Constants.NEW_LINE);
		result = result.substring(1, result.lastIndexOf(STR));

		Assert.assertEquals(this.testCaseName, oracle, result);
	}
}
