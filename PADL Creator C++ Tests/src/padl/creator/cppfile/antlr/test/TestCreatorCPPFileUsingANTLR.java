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
package padl.creator.cppfile.antlr.test;

import junit.framework.Test;
import junit.framework.TestSuite;
import padl.creator.cppfile.antlr.test.all.TestCPPCreator;
import padl.kernel.cpp.antlr.ICPPFactoryANTLR;
import padl.kernel.cpp.antlr.impl.CPPFactoryANTLR;

/**
 * @author robidose
 */
public class TestCreatorCPPFileUsingANTLR extends TestSuite {
	public TestCreatorCPPFileUsingANTLR() {
	}
	public TestCreatorCPPFileUsingANTLR(final Class theClass) {
		super(theClass);
	}
	public TestCreatorCPPFileUsingANTLR(final String name) {
		super(name);
	}
	public static Test suite() {
		CppPrimitive.setFactory((ICPPFactoryANTLR) CPPFactoryANTLR
			.getInstance());

		final TestCreatorCPPFileUsingANTLR suite =
			new TestCreatorCPPFileUsingANTLR();
		suite.addTest(TestCPPCreator.suite());
		return suite;
	}
}
