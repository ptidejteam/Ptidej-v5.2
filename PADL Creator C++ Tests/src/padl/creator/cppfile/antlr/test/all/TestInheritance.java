/*
 * (c) Copyright 2004 Sebastien Robidoux, Ward Flores
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
 * Created: 2004/08/24
 */
package padl.creator.cppfile.antlr.test.all;

import java.util.ArrayList;
import java.util.Iterator;
import junit.framework.Assert;
import padl.creator.cppfile.antlr.CPPCreator;
import padl.creator.cppfile.antlr.test.CppPrimitive;
import padl.kernel.IClass;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IElement;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IGhost;
import padl.kernel.exception.CreationException;
import padl.util.Util;
import util.lang.Modifier;

/**
 * @author floresvw
 */
public class TestInheritance extends CppPrimitive {
	private static IFirstClassEntity[] FirstClassEntities = null;
	private static IElement[] Elements1 = null;
	private static IElement[] Elements2 = null;
	private static IElement[] Elements3 = null;
	private static ArrayList nonEmptyList = new ArrayList();

	public TestInheritance(String aName) {
		super(aName);
	}

	protected void setUp() {

		if (TestInheritance.FirstClassEntities == null
				|| TestInheritance.Elements1 == null
				|| TestInheritance.Elements2 == null
				|| TestInheritance.Elements3 == null) {

			final ICodeLevelModel codeLevelModel =
				CppPrimitive.getFactory().createCodeLevelModel("her.cpp");
			try {
				codeLevelModel.create(new CPPCreator(
					new String[] { "../PADL Creator C++ Tests/rsc/her.cpp" }));
			}
			catch (final CreationException e) {
				e.printStackTrace();
			}

			// All the entities
			TestInheritance.FirstClassEntities =
				Util.getArrayOfTopLevelEntities(codeLevelModel);

			// All the element of the first class
			TestInheritance.Elements1 =
				Util.getArrayOfElements(TestInheritance.FirstClassEntities[0]);

			// All the element of the second class
			TestInheritance.Elements2 =
				Util.getArrayOfElements(TestInheritance.FirstClassEntities[1]);

			// All the element of the third class
			TestInheritance.Elements3 =
				Util.getArrayOfElements(TestInheritance.FirstClassEntities[2]);
			//
			//			for (int i = 0; i < Entities.length; ++i)
			//				System.out.println(Entities[i]);
			//
			//			System.out.println("\n");
			//
			//			for (int i = 0; i < Elements1.length; ++i)
			//				System.out.println(Elements1[i]);
			//
			//			System.out.println("\n");
			//
			//			for (int i = 0; i < Elements2.length; ++i)
			//				System.out.println(Elements2[i]);
			//
			//			System.out.println("\n");
			//
			//			for (int i = 0; i < Elements3.length; ++i)
			//				System.out.println(Elements3[i]);
			//
			//			System.out.println("****************************\n");
			//			System.out.println(TestInheritance.Entities[3].getDisplayName());
			//			System.out.println(TestInheritance.Entities[3].listOfInheritedEntities());
			//			System.out.println(TestInheritance.Entities[3].listOfInheritingEntities());
		}
	}

	public void testClass() {
		//---------------------------------------
		Assert.assertEquals(
			"Class Name",
			"Forme",
			((IClass) TestInheritance.FirstClassEntities[0]).getDisplayName());

		Assert.assertEquals(
			"Class acces",
			Modifier.PUBLIC,
			((IClass) TestInheritance.FirstClassEntities[0]).getVisibility());

		//---------------------------------------
		Assert.assertEquals(
			"Class Name",
			"Forme1",
			((IClass) TestInheritance.FirstClassEntities[1]).getDisplayName());

		Assert.assertEquals(
			"Class acces",
			Modifier.PUBLIC,
			((IClass) TestInheritance.FirstClassEntities[1]).getVisibility());

		//---------------------------------------
		Assert.assertEquals(
			"Class Name",
			"Rectangle",
			((IClass) TestInheritance.FirstClassEntities[3]).getDisplayName());

		Assert.assertEquals(
			"Class acces",
			Modifier.PUBLIC,
			((IClass) TestInheritance.FirstClassEntities[3]).getVisibility());
	}

	public void testGhost() {
		//---------------------------------------
		Assert.assertEquals(
			"Ghost Name",
			"Forme2",
			((IGhost) TestInheritance.FirstClassEntities[2]).getDisplayName());

		Assert.assertEquals(
			"Class acces",
			Modifier.PUBLIC,
			((IGhost) TestInheritance.FirstClassEntities[2]).getVisibility());
	}

	public void testInheritedEntities() {
		//---------------------------------------
		Assert.assertEquals(
			"Inherited Forme Entities",
			false,
			TestInheritance.FirstClassEntities[0]
				.getIteratorOnInheritedEntities()
				.hasNext());

		//---------------------------------------
		Assert.assertEquals(
			"Inherited Forme1 Entities",
			false,
			TestInheritance.FirstClassEntities[1]
				.getIteratorOnInheritedEntities()
				.hasNext());

		//---------------------------------------
		Assert.assertEquals(
			"Inherited Forme2 Entities",
			false,
			TestInheritance.FirstClassEntities[2]
				.getIteratorOnInheritedEntities()
				.hasNext());

		//---------------------------------------
		//Rectangle extends Forme et Forme2
		final Iterator iterator =
			TestInheritance.FirstClassEntities[3].getIteratorOnInheritedEntities();
		Assert.assertEquals(
			"Inherited Rectangle Entities",
			TestInheritance.FirstClassEntities[0],
			iterator.next());
		Assert.assertEquals(
			"Inherited Rectangle Entities",
			TestInheritance.FirstClassEntities[2],
			iterator.next());
		nonEmptyList.clear();
	}
	public void testInheritingEntities() {
		//---------------------------------------
		//Forme is superClass of Rectangle
		nonEmptyList.add(TestInheritance.FirstClassEntities[3]);
		Assert.assertEquals(
			"Inheriting Forme Entities",
			true,
			TestInheritance.FirstClassEntities[0]
				.getIteratorOnInheritingEntities()
				.hasNext());
		nonEmptyList.clear();

		//---------------------------------------
		Assert.assertEquals(
			"Inheriting Forme1 Entities",
			false,
			TestInheritance.FirstClassEntities[1]
				.getIteratorOnInheritingEntities()
				.hasNext());

		//---------------------------------------
		//Forme2 is superClass of Rectangle
		nonEmptyList.add(TestInheritance.FirstClassEntities[3]);
		Assert.assertEquals(
			"Inheriting Forme2 Entities",
			true,
			TestInheritance.FirstClassEntities[2]
				.getIteratorOnInheritingEntities()
				.hasNext());
		nonEmptyList.clear();

		//---------------------------------------
		Assert.assertEquals(
			"Inheriting Rectangle Entities",
			false,
			TestInheritance.FirstClassEntities[3]
				.getIteratorOnInheritingEntities()
				.hasNext());
	}
}
