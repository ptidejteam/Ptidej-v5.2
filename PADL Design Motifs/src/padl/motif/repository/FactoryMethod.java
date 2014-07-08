/*
 * (c) Copyright 2001, 2002 Hervé Albin-Amiot and Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes
 * Object Technology International, Inc.
 * Soft-Maint S.A.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the authors, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHORS ARE ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.motif.repository;

import org.apache.commons.lang.ArrayUtils;
import padl.kernel.Constants;
import padl.kernel.IClass;
import padl.kernel.ICreation;
import padl.kernel.IInterface;
import padl.kernel.IMethod;
import padl.kernel.IUseRelationship;
import padl.motif.models.CreationalMotifModel;
import util.multilingual.MultilingualManager;
import com.ibm.toad.cfparse.utils.Access;

/**
 * @author Foutse Khomh
 * @since  2007/03/01
 */
public class FactoryMethod extends CreationalMotifModel implements Cloneable {
	public static final char[] CREATOR = "Creator".toCharArray();
	public static final char[] PRODUCT = "Product".toCharArray();
	public static final char[] CONCRETE_CREATOR = "ConcreteCreator"
		.toCharArray();
	public static final char[] CONCRETE_PRODUCT = "ConcreteProduct"
		.toCharArray();
	private static final char[] CREATE = "create".toCharArray();
	private static final char[] FACTORY_METHOD = "FactoryMethod".toCharArray();
	private static final char[] KNOW = "know".toCharArray();
	private static final long serialVersionUID = -8562314568282064257L;

	public FactoryMethod() {
		super(FactoryMethod.FACTORY_METHOD);

		final IInterface abstractFactory =
			this.getFactory().createInterface(
				FactoryMethod.CREATOR,
				FactoryMethod.CREATOR);
		final IClass concreteFactory =
			this.getFactory().createClass(
				FactoryMethod.CONCRETE_CREATOR,
				FactoryMethod.CONCRETE_CREATOR);
		concreteFactory.addInheritedEntity(abstractFactory);

		this.addConstituent(abstractFactory);
		this.addConstituent(concreteFactory);

		this.addProduct(new char[][] { FactoryMethod.PRODUCT,
				FactoryMethod.CONCRETE_PRODUCT });
	}

	public void addProduct(final char[][] names) {
		// names[0] is abstractProductName
		// names[1] is concreteProductName
		final char[] abstractProductName = names[0];
		final char[] concreteProductName = names[1];

		final IInterface abstractFactory =
			(IInterface) this.getConstituentFromName(FactoryMethod.CREATOR);
		final IClass concreteFactory =
			(IClass) this
				.getConstituentFromName(FactoryMethod.CONCRETE_CREATOR);
		final IInterface abstractProduct =
			this.getFactory().createInterface(
				abstractProductName,
				abstractProductName);
		final IClass concreteProduct =
			this.getFactory().createClass(
				concreteProductName,
				concreteProductName);
		concreteProduct.addImplementedInterface(abstractProduct);

		final IUseRelationship useRelationship =
			this.getFactory().createUseRelationship(
				FactoryMethod.KNOW,
				abstractProduct,
				Constants.CARDINALITY_ONE);
		abstractFactory.addConstituent(useRelationship);

		final IMethod abstractCreateMethod =
			this.getFactory().createMethod(
				ArrayUtils.addAll(FactoryMethod.CREATE, abstractProductName),
				ArrayUtils.addAll(FactoryMethod.CREATE, abstractProductName));
		abstractCreateMethod.setReturnType(abstractProductName);
		abstractCreateMethod.setVisibility(Access.ACC_ABSTRACT);
		abstractFactory.addConstituent(abstractCreateMethod);

		final IMethod concreteCreateMethod =
			this.getFactory().createMethod(
				ArrayUtils.addAll(FactoryMethod.CREATE, abstractProductName),
				ArrayUtils.addAll(FactoryMethod.CREATE, abstractProductName));
		concreteCreateMethod.setCodeLines("return new "
				+ String.valueOf(concreteProductName) + "();");
		concreteCreateMethod.attachTo(abstractCreateMethod);
		concreteFactory.addConstituent(concreteCreateMethod);

		final ICreation creationLink =
			this.getFactory().createCreationRelationship(
				FactoryMethod.CREATE,
				concreteProduct,
				Constants.CARDINALITY_ONE);
		concreteFactory.addConstituent(creationLink);

		this.addConstituent(abstractProduct);
		this.addConstituent(concreteProduct);
	}

	public String getIntent() {
		return MultilingualManager.getString("INTENT", FactoryMethod.class);
	}

	public char[] getName() {
		return FactoryMethod.FACTORY_METHOD;
	}

	public void removeProduct(final String[] names) {
	}
}
