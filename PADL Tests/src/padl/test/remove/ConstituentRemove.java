package padl.test.remove;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IPackage;
import padl.kernel.impl.Factory;

public class ConstituentRemove extends TestCase {
	public ConstituentRemove(final String name) {
		super(name);
	}
	public void testRemoveTopLevelClass2() {
		final String classID = "example.Class1";
		final String packageID = "example";
		final ICodeLevelModel model =
			Factory.getInstance().createCodeLevelModel("");
		final IPackage p =
			model.getFactory().createPackage(packageID.toCharArray());
		model.addConstituent(p);
		p.addConstituent(model.getFactory().createClass(
			classID.toCharArray(),
			"Class1".toCharArray()));
		final IFirstClassEntity entity =
			model.getTopLevelEntityFromID(classID.toCharArray());
		Assert.assertNotNull(entity);
		model.removeTopLevelEntityFromID(classID.toCharArray());
		final IFirstClassEntity entity1 =
			model.getTopLevelEntityFromID(classID.toCharArray());
		Assert.assertNull(entity1);
	}
	public void testRemoveTopLevelClass3() {
		final String classID = "example.Class1";
		final String packageID = "example";
		final ICodeLevelModel model =
			Factory.getInstance().createCodeLevelModel("Model");
		final IPackage p =
			model.getFactory().createPackage(packageID.toCharArray());
		model.addConstituent(p);
		p.addConstituent(model.getFactory().createClass(
			classID.toCharArray(),
			"Class1".toCharArray()));
		final IFirstClassEntity entity =
			model.getTopLevelEntityFromID(classID.toCharArray());
		Assert.assertNotNull(entity);
		model.removeTopLevelEntityFromID(classID.toCharArray());
		final IFirstClassEntity entity1 =
			model.getTopLevelEntityFromID(classID.toCharArray());
		Assert.assertNull(entity1);
	}
	public void testRemoveTopLevelClass1() {
		final String classID = "example.Class1";
		final String packageID = "example";
		final ICodeLevelModel model =
			Factory.getInstance().createCodeLevelModel("");
		final IPackage p =
			model.getFactory().createPackage(packageID.toCharArray());
		p.addConstituent(model.getFactory().createClass(
			classID.toCharArray(),
			"Class1".toCharArray()));
		model.addConstituent(p);
		final IFirstClassEntity entity =
			model.getTopLevelEntityFromID(classID.toCharArray());
		Assert.assertNotNull(entity);
		model.removeTopLevelEntityFromID(classID.toCharArray());
		final IFirstClassEntity entity1 =
			model.getTopLevelEntityFromID(classID.toCharArray());
		Assert.assertNull(entity1);
	}
	public void testRemoveConstitiuent() {
		final ICodeLevelModel model =
			Factory.getInstance().createCodeLevelModel("");
		final String classID = "example.Class1";
		final String packageID = "example";
		final IPackage p =
			model.getFactory().createPackage(packageID.toCharArray());
		p.addConstituent(model.getFactory().createClass(
			classID.toCharArray(),
			"Class1".toCharArray()));
		model.addConstituent(p);

		final IFirstClassEntity entity =
			model.getTopLevelEntityFromID(classID.toCharArray());
		Assert.assertNotNull(entity);

		p.removeConstituentFromID(classID.toCharArray());
		final IFirstClassEntity entity1 =
			(IFirstClassEntity) p.getConstituentFromID(classID.toCharArray());
		Assert.assertNull(entity1);

		final IPackage p1 =
			(IPackage) model.getConstituentFromID(packageID.toCharArray());
		Assert.assertNotNull(p1);

		model.removeConstituentFromID(packageID.toCharArray());

		final IPackage p2 =
			(IPackage) model.getConstituentFromID(packageID.toCharArray());
		Assert.assertNull(p2);
	}
	public void testRemoveClassFromPackage() {
		final ICodeLevelModel model =
			Factory.getInstance().createCodeLevelModel("");
		final String classID = "example.Class1";
		final String packageID = "example";
		final IPackage p =
			model.getFactory().createPackage(packageID.toCharArray());
		p.addConstituent(model.getFactory().createClass(
			classID.toCharArray(),
			"Class1".toCharArray()));
		model.addConstituent(p);

		final IFirstClassEntity entity =
			model.getTopLevelEntityFromID(classID.toCharArray());
		Assert.assertNotNull(entity);

		p.removeConstituentFromID(classID.toCharArray());
		final IFirstClassEntity entity1 =
			(IFirstClassEntity) p.getConstituentFromID(classID.toCharArray());
		Assert.assertNull(entity1);

		final IFirstClassEntity entity2 =
			model.getTopLevelEntityFromID(classID.toCharArray());
		Assert.assertNull(entity2);
	}
}
