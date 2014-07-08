/*
 * (c) Copyright 2003-2006 Jean-Yves Guyomarc'h,
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
package padl.creator.aspectj.test;

import java.util.Iterator;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.aspectj.kernel.IAspect;
import padl.aspectj.kernel.IAspectJFactory;
import padl.aspectj.kernel.exception.AspectCreationException;
import padl.aspectj.kernel.impl.AspectJFactory;
import padl.creator.aspectjlst.AspectCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IConstituent;
import padl.kernel.IContainer;
import padl.kernel.IFactory;
import util.io.ProxyConsole;

public class TestModelCreation extends TestCase {
	private static IAspectJFactory factory = (IAspectJFactory) AspectJFactory
		.getInstance();

	public static IFactory getFactory() {
		return TestModelCreation.factory;
	}
	public static void setFactory(final IAspectJFactory aFactory) {
		TestModelCreation.factory = aFactory;
	}
	private ICodeLevelModel codeLevelModel;
	//	private void indent(int nb){
	//		for(int i = 0; i < nb; i++){
	//			System.out.print("\t");
	//		}
	//	}
	public TestModelCreation(String aName) {
		super(aName);
	}
	public ICodeLevelModel getCodeLevelModel() {
		return this.codeLevelModel;
	}
	private void iterate(final IContainer container, int indent) {
		//Iterator iterate = container.listOfConstituents().iterator();
		Iterator iterate = container.getIteratorOnConstituents();

		while (iterate.hasNext()) {
			IConstituent c = (IConstituent) iterate.next();
			if (c instanceof IAspect) {
				// this.indent(indent);
				ProxyConsole
					.getInstance()
					.debugOutput()
					.println("Aspect: " + c.getDisplayName());
				//IAspect aspect = (IAspect) c;
				//this.iterate(aspect, indent + 1);
			}
			else {
				ProxyConsole
					.getInstance()
					.debugOutput()
					.println("Non-AJ: " + c.getDisplayName());
			}
			//	if (c instanceof IAspectElement) {
			//		this.indent(indent);
			//		System.out.println("AElement: " + c.getDisplayName());
			//	}
			//	else {
			//		this.indent(indent);
			//		System.out.println(c.getClass().getName() + ": " + c.getDisplayName());
			//	}

		}
	}
	protected void setUp() throws AspectCreationException {
		final String lst =
			"../PADL Creator AspectJ Tests/rsc/examples/AJ1.2Example/observer/files.lst";
		final AspectCreator ac = new AspectCreator(new String[] { lst });

		this.codeLevelModel =
			AspectJFactory.getInstance().createCodeLevelModel(this.getName());

		ac.create(this.codeLevelModel);

		this.iterate(this.codeLevelModel, 0);

	}
	public void testCreation() {
		Assert.assertEquals(
			"CLM Creation",
			true,
			(this.getCodeLevelModel() != null));
	}
}
