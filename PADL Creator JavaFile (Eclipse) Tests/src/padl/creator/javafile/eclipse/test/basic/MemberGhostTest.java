/* (c) Copyright 2011 and following years, Aminata SABANÉ,
 * ÉCole Polytechnique de Montréal.
 * 
 * @author: Aminata SABANÉ
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
package padl.creator.javafile.eclipse.test.basic;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.creator.javafile.eclipse.test.util.Utils;
import padl.kernel.IClass;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IEntity;
import padl.kernel.IMethod;
import padl.kernel.IParameter;
import util.io.ProxyConsole;

public class MemberGhostTest extends TestCase {
	public MemberGhostTest(final String aName) {
		super(aName);
	}

	protected void setUp() {
	}

	/**
	 * B and M don't exist in the package
	 */
	public void testGhostMember1() {

		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] listOfFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/member1/MemberGhostExample.java" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.ghost.member1.MemberGhostExample");
		Assert.assertNotNull(clazz);

		final IMethod method = (IMethod) clazz.getConstituentFromName("foo");
		Assert.assertNotNull(method);

		final IParameter parameter =
			(IParameter) method.getConstituentFromName("aMember");
		Assert.assertNotNull(parameter);
		try {
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.MemberGhost"),
				parameter.getType().getClass());

			Assert.assertEquals("M", parameter.getType().getDisplayName());

			final IEntity clazz2 = model.getTopLevelEntityFromID("B");

			Assert.assertNotNull(clazz2);
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Ghost"),
				clazz2.getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
	}

	/**
	 * B exists and M no
	 */
	public void testGhostMember2() {
		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] listOfFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/member2/" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.ghost.member2.MemberGhostExample");
		Assert.assertNotNull(clazz);

		final IMethod method = (IMethod) clazz.getConstituentFromName("foo");
		Assert.assertNotNull(method);

		final IParameter parameter =
			(IParameter) method.getConstituentFromName("aMember");
		Assert.assertNotNull(parameter);

		try {
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.MemberGhost"),
				parameter.getType().getClass());

			Assert.assertEquals("M", parameter.getType().getDisplayName());

			final IEntity clazz2 =
				model.getTopLevelEntityFromID("padl.example.ghost.member2.B");
			Assert.assertNotNull(clazz2);
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Class"),
				clazz2.getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
	}

	/**
	 * B exists but it is not on the list of parsed files and M does not exist
	 */
	public void testGhostMember2bis() {
		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] listOfFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/member2/MemberGhostExample.java" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.ghost.member2.MemberGhostExample");
		Assert.assertNotNull(clazz);

		final IMethod method = (IMethod) clazz.getConstituentFromName("foo");
		Assert.assertNotNull(method);

		final IParameter parameter =
			(IParameter) method.getConstituentFromName("aMember");
		Assert.assertNotNull(parameter);

		try {
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.MemberGhost"),
				parameter.getType().getClass());

			Assert.assertEquals("M", parameter.getType().getDisplayName());

			final IEntity clazz2 =
				model.getTopLevelEntityFromID("padl.example.ghost.member2.B");
			Assert.assertNotNull(clazz2);
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Ghost"),
				clazz2.getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
	}

	/**
	 * B exists, C and M no
	 */
	public void testGhostMember3() {
		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] listOfFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/member3/" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.ghost.member3.MemberGhostExample");
		Assert.assertNotNull(clazz);

		final IMethod method = (IMethod) clazz.getConstituentFromName("foo");
		Assert.assertNotNull(method);

		final IParameter parameter2 =
			(IParameter) method.getConstituentFromName("c");
		Assert.assertNotNull(parameter2);
		try {
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Ghost"),
				parameter2.getType().getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

		Assert.assertEquals("C", parameter2.getType().getDisplayName());

		final IParameter parameter1 =
			(IParameter) method.getConstituentFromName("aMember");
		Assert.assertNotNull(parameter1);

		try {
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.MemberGhost"),
				parameter1.getType().getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

		Assert.assertEquals("M", parameter1.getType().getDisplayName());
	}

	/**
	 * B, M1 and M2 don't exist
	 */
	public void testGhostMember4() {
		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] listOfFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/member4/MemberGhostExample.java" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.ghost.member4.MemberGhostExample");
		Assert.assertNotNull(clazz);

		final IMethod method = (IMethod) clazz.getConstituentFromName("foo");
		Assert.assertNotNull(method);

		final IParameter parameter =
			(IParameter) method.getConstituentFromName("aMember");
		Assert.assertNotNull(parameter);
		try {
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.MemberGhost"),
				parameter.getType().getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		Assert.assertEquals("M2", parameter.getType().getDisplayName());
	}

	/**
	 * B and C exist and M no
	 */
	public void testGhostMember5() {
		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] listOfFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/member5/" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.ghost.member5.MemberGhostExample");
		Assert.assertNotNull(clazz);

		final IEntity clazz1 =
			model.getTopLevelEntityFromID("padl.example.ghost.member5.B");
		Assert.assertNotNull(clazz1);
		try {
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Class"),
				clazz1.getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

		final IMethod method = (IMethod) clazz.getConstituentFromName("foo");
		Assert.assertNotNull(method);

		final IParameter parameter2 =
			(IParameter) method.getConstituentFromName("c");
		Assert.assertNotNull(parameter2);
		try {
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Class"),
				parameter2.getType().getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		Assert.assertEquals("C", parameter2.getType().getDisplayName());

		final IParameter parameter1 =
			(IParameter) method.getConstituentFromName("aMember");
		Assert.assertNotNull(parameter1);
		try {
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.MemberGhost"),
				parameter1.getType().getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		Assert.assertEquals("M", parameter1.getType().getDisplayName());
	}

	/**
	 * B and C exist but are not parsed and M no
	 */
	public void testGhostMember5bis() {
		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] listOfFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/member5/MemberGhostExample.java" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.ghost.member5.MemberGhostExample");
		Assert.assertNotNull(clazz);

		final IMethod method = (IMethod) clazz.getConstituentFromName("foo");
		Assert.assertNotNull(method);

		final IEntity clazz1 =
			model.getTopLevelEntityFromID("padl.example.ghost.member5.B");
		Assert.assertNotNull(clazz1);
		try {
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Ghost"),
				clazz1.getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

		final IParameter parameter2 =
			(IParameter) method.getConstituentFromName("c");
		Assert.assertNotNull(parameter2);
		try {
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Ghost"),
				parameter2.getType().getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		Assert.assertEquals("C", parameter2.getType().getDisplayName());

		final IParameter parameter1 =
			(IParameter) method.getConstituentFromName("aMember");
		Assert.assertNotNull(parameter1);
		try {
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.MemberGhost"),
				parameter1.getType().getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		Assert.assertEquals("M", parameter1.getType().getDisplayName());
	}

	/**
	 * D does not exist
	 */
	public void testInheritanceGhost1() {
		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] listOfFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/inheritance1/InheritanceGhostExample.java" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.ghost.inheritance1.InheritanceGhostExample");
		Assert.assertNotNull(clazz);
		try {
			Assert.assertEquals(Class.forName("padl.kernel.impl.Ghost"), clazz
				.getInheritedEntityFromName("D".toCharArray())
				.getClass());

			final IEntity clazz2 =
				model.getTopLevelEntityFromID("unknown.ghost.packag.D"
					.toCharArray());
			Assert.assertNotNull(clazz2);
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

	}

	/**
	 * Interface B exist in another package and it is not parsed
	 */
	public void testInheritanceGhost10() {
		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] listOfFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/inheritance10/InheritanceGhostExample.java" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.ghost.inheritance10.InheritanceGhostExample");
		Assert.assertNotNull(clazz);
		try {
			Assert.assertEquals(Class.forName("padl.kernel.impl.Ghost"), clazz
				.getImplementedInterface("B".toCharArray())
				.getClass());

			final IEntity clazz2 =
				model
					.getTopLevelEntityFromID("padl.example.ghost.inheritance9.B");
			Assert.assertNotNull(clazz2);
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Ghost"),
				clazz2.getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

	}

	/**
	 * Interface B exist in another package and it is parsed
	 */
	public void testInheritanceGhost10bis() {
		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] listOfFiles =
			new String[] {
					"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/inheritance10/InheritanceGhostExample.java",
					"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/inheritance9/B.java" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.ghost.inheritance10.InheritanceGhostExample");
		Assert.assertNotNull(clazz);
		try {
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Interface"),
				clazz.getImplementedInterface("B".toCharArray()).getClass());

			final IEntity clazz2 =
				model
					.getTopLevelEntityFromID("padl.example.ghost.inheritance9.B");
			Assert.assertNotNull(clazz2);
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Interface"),
				clazz2.getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

	}

	/**
	 * B and M don't exist
	 */
	public void testInheritanceGhost2() {
		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] listOfFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/inheritance2/InheritanceGhostExample.java" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.ghost.inheritance2.InheritanceGhostExample");
		Assert.assertNotNull(clazz);
		try {

			Assert.assertEquals(
				Class.forName("padl.kernel.impl.MemberGhost"),
				clazz.getInheritedEntityFromName("M".toCharArray()).getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

		final IEntity clazz2 = model.getTopLevelEntityFromID("B");
		Assert.assertNotNull(clazz2);
		try {

			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Ghost"),
				clazz2.getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

	}

	/**
	 * B exist and M no
	 */
	public void testInheritanceGhost3() {
		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] listOfFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/inheritance3/" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.ghost.inheritance3.InheritanceGhostExample");
		Assert.assertNotNull(clazz);
		try {
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.MemberGhost"),
				clazz.getInheritedEntityFromName("M".toCharArray()).getClass());

			final IEntity clazz2 =
				model
					.getTopLevelEntityFromID("padl.example.ghost.inheritance3.B");
			Assert.assertNotNull(clazz2);
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Class"),
				clazz2.getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

	}

	/**
	 * B exist but it is not parsed and M does not exist
	 */
	public void testInheritanceGhost3bis() {
		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] listOfFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/inheritance3/InheritanceGhostExample.java" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.ghost.inheritance3.InheritanceGhostExample");
		Assert.assertNotNull(clazz);
		try {
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.MemberGhost"),
				clazz.getInheritedEntityFromName("M".toCharArray()).getClass());

			final IEntity clazz2 =
				model
					.getTopLevelEntityFromID("padl.example.ghost.inheritance3.B");
			Assert.assertNotNull(clazz2);
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Ghost"),
				clazz2.getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

	}

	/**
	 * B exists
	 */
	public void testInheritanceGhost4() {
		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] listOfFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/inheritance4/" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.ghost.inheritance4.InheritanceGhostExample");
		Assert.assertNotNull(clazz);
		try {
			Assert.assertEquals(Class.forName("padl.kernel.impl.Class"), clazz
				.getInheritedEntityFromName("B".toCharArray())
				.getClass());

			final IEntity clazz2 =
				model
					.getTopLevelEntityFromID("padl.example.ghost.inheritance4.B");
			Assert.assertNotNull(clazz2);
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Class"),
				clazz2.getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

	}

	/**
	 * B exists but it is not parsed
	 */
	public void testInheritanceGhost4bis() {
		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] listOfFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/inheritance4/InheritanceGhostExample.java" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.ghost.inheritance4.InheritanceGhostExample");
		Assert.assertNotNull(clazz);
		try {
			Assert.assertEquals(Class.forName("padl.kernel.impl.Ghost"), clazz
				.getInheritedEntityFromName("B".toCharArray())
				.getClass());

			final IEntity clazz2 =
				model
					.getTopLevelEntityFromID("padl.example.ghost.inheritance4.B");
			Assert.assertNotNull(clazz2);
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Ghost"),
				clazz2.getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

	}

	//B esist but it is not parsed
	public void testInheritanceGhost5() {
		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] listOfFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/inheritance5/InheritanceGhostExample.java" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.ghost.inheritance5.InheritanceGhostExample");
		Assert.assertNotNull(clazz);
		try {
			Assert.assertEquals(Class.forName("padl.kernel.impl.Ghost"), clazz
				.getInheritedEntityFromName("B".toCharArray())
				.getClass());

			final IEntity clazz2 =
				model
					.getTopLevelEntityFromID("padl.example.ghost.inheritance4.B");
			Assert.assertNotNull(clazz2);
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Ghost"),
				clazz2.getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

	}

	/**
	 * Interface B does not exist 
	 */
	public void testInheritanceGhost6() {
		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] listOfFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/inheritance6/InheritanceGhostExample.java" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.ghost.inheritance6.InheritanceGhostExample");
		Assert.assertNotNull(clazz);
		try {
			Assert.assertEquals(Class.forName("padl.kernel.impl.Ghost"), clazz
				.getImplementedInterface("D".toCharArray())
				.getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

	}

	/**
	 * Interface B and M do not exist
	 */
	public void testInheritanceGhost7() {
		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] listOfFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/inheritance7/InheritanceGhostExample.java" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.ghost.inheritance7.InheritanceGhostExample");
		Assert.assertNotNull(clazz);
		try {

			Assert.assertEquals(
				Class.forName("padl.kernel.impl.MemberGhost"),
				clazz.getImplementedInterface("M".toCharArray()).getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

		final IEntity clazz2 = model.getTopLevelEntityFromID("B");
		Assert.assertNotNull(clazz2);
		try {

			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Ghost"),
				clazz2.getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

	}

	/**
	 * Interface B exist and M no
	 */
	public void testInheritanceGhost8() {
		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] listOfFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/inheritance8/" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.ghost.inheritance8.InheritanceGhostExample");
		Assert.assertNotNull(clazz);
		try {
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.MemberGhost"),
				clazz.getImplementedInterface("M".toCharArray()).getClass());

			final IEntity clazz2 =
				model
					.getTopLevelEntityFromID("padl.example.ghost.inheritance8.B");
			Assert.assertNotNull(clazz2);
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Interface"),
				clazz2.getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

	}

	/**
	 * Interface B exist in the same package
	 */
	public void testInheritanceGhost9() {
		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] listOfFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/inheritance9/" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.ghost.inheritance9.InheritanceGhostExample");
		Assert.assertNotNull(clazz);
		try {
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Interface"),
				clazz.getImplementedInterface("B".toCharArray()).getClass());

			final IEntity clazz2 =
				model
					.getTopLevelEntityFromID("padl.example.ghost.inheritance9.B");
			Assert.assertNotNull(clazz2);
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Interface"),
				clazz2.getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}

	}

	/**
	 * Interface B exist in the same package but it is not parsed
	 */
	public void testInheritanceGhost9bis() {
		final String classPathEntry = "";
		final String sourceCodePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] listOfFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/ghost/inheritance9/InheritanceGhostExample.java" };

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourceCodePath,
				classPathEntry,
				listOfFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.ghost.inheritance9.InheritanceGhostExample");
		Assert.assertNotNull(clazz);
		try {
			Assert.assertEquals(Class.forName("padl.kernel.impl.Ghost"), clazz
				.getImplementedInterface("B".toCharArray())
				.getClass());

			final IEntity clazz2 =
				model
					.getTopLevelEntityFromID("padl.example.ghost.inheritance9.B");
			Assert.assertNotNull(clazz2);
			Assert.assertEquals(
				Class.forName("padl.kernel.impl.Ghost"),
				clazz2.getClass());
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
	}
}
