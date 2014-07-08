package padl.motif.repository;

import padl.kernel.IClass;
import padl.kernel.IMethod;
import padl.kernel.IUseRelationship;
import padl.motif.models.CreationalMotifModel;
import util.multilingual.MultilingualManager;

/**
 * @author Yann-Gael Gueheneuc
 * @since  2008/12/13
 */
public class Builder extends CreationalMotifModel implements Cloneable {
	private static final char[] BUILD_PART = "buildPart".toCharArray();
	private static final char[] BUILDER = "Builder".toCharArray();
	private static final char[] CONCRETE_BUILDER = "ConcreteBuilder"
		.toCharArray();
	private static final char[] CONSTRUCT = "Construct".toCharArray();
	private static final char[] CREATION = "creation".toCharArray();
	private static final char[] DIRECTOR = "Director".toCharArray();
	private static final char[] PRODUCT = "Product".toCharArray();
	private static final long serialVersionUID = 3734860224927500729L;

	public Builder() {
		super(Builder.BUILDER);

		final IClass director =
			this.getFactory().createClass(Builder.DIRECTOR, Builder.DIRECTOR);
		final IMethod constructMethod =
			this
				.getFactory()
				.createMethod(Builder.CONSTRUCT, Builder.CONSTRUCT);
		director.addConstituent(constructMethod);

		final IClass builder =
			this.getFactory().createClass(Builder.BUILDER, Builder.BUILDER);
		builder.setAbstract(true);
		final IMethod abstractBuildPartMethod =
			this.getFactory().createMethod(
				Builder.BUILD_PART,
				Builder.BUILD_PART);
		abstractBuildPartMethod.setAbstract(true);
		builder.addConstituent(abstractBuildPartMethod);

		final IClass concreteBuilder =
			this.getFactory().createClass(
				Builder.CONCRETE_BUILDER,
				Builder.CONCRETE_BUILDER);
		concreteBuilder.addInheritedEntity(builder);
		final IMethod concreteBuildPartMethod =
			this.getFactory().createMethod(
				Builder.BUILD_PART,
				Builder.BUILD_PART);
		concreteBuilder.addConstituent(concreteBuildPartMethod);

		final IClass product =
			this.getFactory().createClass(Builder.PRODUCT, Builder.PRODUCT);
		final IUseRelationship useFromCreation =
			this.getFactory().createUseRelationship(
				Builder.CREATION,
				product,
				1);
		concreteBuilder.addConstituent(useFromCreation);

		this.addConstituent(director);
		this.addConstituent(builder);
		this.addConstituent(concreteBuilder);
		this.addConstituent(product);
	}

	public String getIntent() {
		return MultilingualManager.getString("INTENT", Builder.class);
	}

	public char[] getName() {
		return Builder.BUILDER;
	}
}
