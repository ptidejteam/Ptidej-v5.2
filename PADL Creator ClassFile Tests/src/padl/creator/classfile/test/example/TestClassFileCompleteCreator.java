/*
 * (c) Copyright 2001, 2002 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Gaël Guéhéneuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR B PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.creator.classfile.test.example;

import junit.framework.Test;
import junit.framework.TestSuite;

public final class TestClassFileCompleteCreator extends TestSuite {
	public TestClassFileCompleteCreator() {
	}
	public TestClassFileCompleteCreator(final Class theClass) {
		super(theClass);
	}
	public TestClassFileCompleteCreator(final String name) {
		super(name);
	}
	public static Test suite() {
		final TestClassFileCompleteCreator suite =
			new TestClassFileCompleteCreator();
		suite.addTest(new TestClassFileCompleteCreator(Aggregation1.class));
		suite.addTest(new TestClassFileCompleteCreator(Aggregation10.class));
		suite.addTest(new TestClassFileCompleteCreator(Aggregation11.class));
		suite.addTest(new TestClassFileCompleteCreator(Aggregation13.class));
		suite.addTest(new TestClassFileCompleteCreator(Aggregations.class));
		suite.addTest(new TestClassFileCompleteCreator(
			Aggregation_CLASS_INSTANCE_FROM_FIELD_1.class));
		suite.addTest(new TestClassFileCompleteCreator(
			Association_INSTANCE_INSTANCE_2.class));
		suite.addTest(new TestClassFileCompleteCreator(ChainOfMessages.class));
		suite.addTest(new TestClassFileCompleteCreator(Composite1.class));
		suite.addTest(new TestClassFileCompleteCreator(
			Composite4AbstractDocument.class));
		suite.addTest(new TestClassFileCompleteCreator(
			CreationLink_INSTANCE_CREATION_1.class));
		suite.addTest(new TestClassFileCompleteCreator(
			CreationLink_INSTANCE_CREATION_3.class));
		suite.addTest(new TestClassFileCompleteCreator(Interfaces.class));
		suite.addTest(new TestClassFileCompleteCreator(
			UseRelationship_CLASS_CLASS_1.class));
		suite.addTest(new TestClassFileCompleteCreator(
			UseRelationship_CLASS_CLASS_3.class));
		suite.addTest(new TestClassFileCompleteCreator(Relationships.class));
		suite.addTest(new TestClassFileCompleteCreator(
			SuperEntitiesConnection.class));
		suite.addTest(new TestClassFileCompleteCreator(Ghost.class));
		suite.addTest(new TestClassFileCompleteCreator(TestDup.class));
		suite.addTest(new TestClassFileCompleteCreator(TestNew.class));
		return suite;
	}
}
