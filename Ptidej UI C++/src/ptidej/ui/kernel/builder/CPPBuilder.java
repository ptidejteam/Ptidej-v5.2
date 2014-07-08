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
package ptidej.ui.kernel.builder;

import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import padl.cpp.kernel.ICPPClass;
import padl.cpp.kernel.IDestructor;
import padl.cpp.kernel.IEnum;
import padl.cpp.kernel.IGlobalField;
import padl.cpp.kernel.IGlobalFunction;
import padl.cpp.kernel.IStructure;
import padl.cpp.kernel.IUnion;
import padl.kernel.IConstituent;
import padl.kernel.IConstituentOfEntity;
import padl.kernel.IConstituentOfModel;
import ptidej.ui.Utils;
import ptidej.ui.kernel.Class;
import ptidej.ui.kernel.Destructor;
import ptidej.ui.kernel.Element;
import ptidej.ui.kernel.Entity;
import ptidej.ui.kernel.Enum;
import ptidej.ui.kernel.GlobalField;
import ptidej.ui.kernel.GlobalFunction;
import ptidej.ui.kernel.Structure;
import ptidej.ui.kernel.Union;
import ptidej.ui.primitive.IPrimitiveFactory;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/12/16
 */
public class CPPBuilder extends Builder {
	private static Map UniqueInstances;
	public static Builder getCurrentBuilder(
		final IPrimitiveFactory aPrimitiveFactory) {
		if (CPPBuilder.UniqueInstances == null) {
			CPPBuilder.UniqueInstances = new HashMap();
		}
		if (CPPBuilder.UniqueInstances.get(aPrimitiveFactory) == null) {
			CPPBuilder.UniqueInstances.put(aPrimitiveFactory, new CPPBuilder(
				aPrimitiveFactory));
		}
		return (CPPBuilder) CPPBuilder.UniqueInstances.get(aPrimitiveFactory);
	}

	private CPPBuilder(final IPrimitiveFactory aPrimitiveFactory) {
		super(aPrimitiveFactory);
	}
	protected Element createElement(
		final IConstituentOfModel anEntity,
		final IConstituentOfEntity anElement) {

		final Element aGraphicalElement;

		if (anElement instanceof IDestructor) {
			aGraphicalElement =
				new Destructor(
					this.getPrimitiveFactory(),
					(IDestructor) anElement);
		}
		else {
			aGraphicalElement = null;
		}

		return aGraphicalElement;
	}
	protected Entity createEntity(final IConstituentOfModel anEntity) {
		final Entity aGraphicalEntity;

		if (anEntity instanceof ICPPClass) {
			aGraphicalEntity =
				new Class(
					this.getPrimitiveFactory(),
					this,
					(ICPPClass) anEntity);
		}
		else if (anEntity instanceof IEnum) {
			aGraphicalEntity =
				new Enum(this.getPrimitiveFactory(), this, (IEnum) anEntity);
		}
		else if (anEntity instanceof IGlobalField) {
			aGraphicalEntity =
				new GlobalField(
					this.getPrimitiveFactory(),
					this,
					(IGlobalField) anEntity);
		}
		else if (anEntity instanceof IGlobalFunction) {
			aGraphicalEntity =
				new GlobalFunction(
					this.getPrimitiveFactory(),
					this,
					(IGlobalFunction) anEntity);
		}
		else if (anEntity instanceof IStructure) {
			aGraphicalEntity =
				new Structure(
					this.getPrimitiveFactory(),
					this,
					(IStructure) anEntity);
		}
		else if (anEntity instanceof IUnion) {
			aGraphicalEntity =
				new Union(this.getPrimitiveFactory(), this, (IUnion) anEntity);
		}
		else {
			aGraphicalEntity = null;
		}

		return aGraphicalEntity;
	}
	protected Icon createLabelIcon(final IConstituent aConstituent) {
		String iconImageName = null;

		if (aConstituent instanceof IGlobalField) {
			if (aConstituent.isPrivate()) {
				iconImageName = "Global_Field_Private.gif";
			}
			else if (aConstituent.isProtected()) {
				iconImageName = "Global_Field_Protected.gif";
			}
			else if (aConstituent.isPublic()) {
				iconImageName = "Global_Field_Public.gif";
			}
			else {
				iconImageName = "Global_Field_Default.gif";
			}
		}
		else if (aConstituent instanceof IGlobalFunction) {
			if (aConstituent.isPrivate()) {
				iconImageName = "Global_Function_Private.gif";
			}
			else if (aConstituent.isProtected()) {
				iconImageName = "Global_Function_Protected.gif";
			}
			else if (aConstituent.isPublic()) {
				iconImageName = "Global_Function_Public.gif";
			}
			else {
				iconImageName = "Global_Function_Default.gif";
			}
		}

		if (iconImageName == null) {
			return null;
		}
		else {
			return Utils.getIcon(iconImageName);
		}
	}
	protected String createLabelText(final IConstituent aConstituent) {
		if (aConstituent instanceof IGlobalFunction) {
			return aConstituent.getDisplayName() + "(...)";
		}
		return "";
	}
}
