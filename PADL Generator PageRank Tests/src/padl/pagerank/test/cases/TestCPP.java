/* (c) Copyright 2008 and following years, Yann-Gaël Guéhéneuc,
 * École Polytechnique de Montréal.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the author, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
 * ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHOR IS ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.pagerank.test.cases;

import java.io.StringWriter;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.pagerank.helper.PageRankCallerWithNoParameters;
import padl.pagerank.utils.InputDataGeneratorWith9RelationsForCPP;

public class TestCPP extends TestCase {
	private static InputDataGeneratorWith9RelationsForCPP Generator;
	public TestCPP(final String name) {
		super(name);
	}
	protected void setUp() throws Exception {
		super.setUp();

		if (TestCPP.Generator == null) {
			TestCPP.Generator =
				new InputDataGeneratorWith9RelationsForCPP(false, true);
			final StringWriter writer = new StringWriter();
			PageRankCallerWithNoParameters.callForSomeCPPFiles(
				"Simple",
				"../PADL Creator C++ (Eclipse) Tests/rsc/Simple/",
				TestCPP.Generator,
				writer);
			writer.close();
		}
	}
	public void testType4() {
		// Called methods
		Assert.assertEquals(4, TestCPP.Generator
			.getRelationsType4CalledMethods()
			.size());
	}
	public void testType5() {
		// Field accesses
		Assert.assertEquals(0, TestCPP.Generator
			.getRelationsType5FieldAccesses()
			.size());
	}
	public void testType6() {
		// Types of fields
		Assert.assertEquals(0, TestCPP.Generator
			.getRelationsType6TypesOfFields()
			.size());
	}
	public void testType7() {
		// Return types of methods
		Assert.assertEquals(0, TestCPP.Generator
			.getRelationsType7ReturnTypesOfMethods()
			.size());
	}
	public void testType8() {
		// Parameter types of methods
		Assert.assertEquals(7, TestCPP.Generator
			.getRelationsType8ParameterTypesOfMethods()
			.size());
	}
}
