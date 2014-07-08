/*
 * (c) Copyright 2001-2004 Yann-Gaël Guéhéneuc,
 * University of Montréal.
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
package padl.creator.classfile.test.innerclasses;

import junit.framework.Assert;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.creator.classfile.test.ClassFilePrimitive;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IMemberGhost;
import padl.kernel.exception.CreationException;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2005/08/14
 */
public class TestInnerClasses extends ClassFilePrimitive {
	private IFirstClassEntity testedClass;
	private ICodeLevelModel testedModel;

	public TestInnerClasses(final String aName) {
		super(aName);
	}
	protected void setUp() throws CreationException {
		this.testedModel =
			ClassFilePrimitive.getFactory().createCodeLevelModel(
				"ptidej.example.innerclasses2");
		this.testedModel
			.create(new CompleteClassFileCreator(
				new String[] {
						"../PADL Creator ClassFile Tests/rsc/InternalClass/org/argouml/language/java/generator/GeneratorJava.class",
						"../PADL Creator ClassFile Tests/rsc/InternalClass/org/argouml/language/java/generator/GeneratorJava$1$TagExtractor.class" }));

		this.testedClass =
			(IFirstClassEntity) this.testedModel
				.getTopLevelEntityFromID("org.argouml.language.java.generator.GeneratorJava");
	}
	public void testMemberClass() {
		int numMembers =
			this.testedClass.getNumberOfConstituents(IMemberGhost.class);

		Assert.assertEquals(
			"Number of members contained should be ",
			0,
			numMembers);

		//    TestCase.assertNotNull("Could not find member class 'TagExtractor'", 
		//            this.testedClass.getConstituentFromID("TagExtractor"));
	}
}
