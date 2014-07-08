/*
 * (c) Copyright 2001-2004 Yann-Gaël Guéhéneuc,
 * (c) Copyright 2012-2012 Sébastien Colladon,
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
package padl.cpp.kernel.impl;

import padl.cpp.kernel.ICPPClass;
import padl.cpp.kernel.ICPPFactoryEclipse;
import padl.cpp.kernel.ICPPGhost;
import padl.cpp.kernel.ICPPMemberClass;
import padl.cpp.kernel.ICPPMemberGhost;
import padl.cpp.kernel.IDestructor;
import padl.cpp.kernel.IEnum;
import padl.cpp.kernel.IEnumValue;
import padl.cpp.kernel.IGlobalField;
import padl.cpp.kernel.IGlobalFunction;
import padl.cpp.kernel.IGlobalFunctionGhost;
import padl.cpp.kernel.IMemberStructure;
import padl.cpp.kernel.IStructure;
import padl.cpp.kernel.IUnion;
import padl.cpp.kernel.event.CPPEventGenerator;
import padl.kernel.IEntity;
import padl.kernel.IFactory;
import padl.kernel.IParameter;
import padl.kernel.impl.Factory;
import padl.visitor.IWalker;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since 2005/07/11
 */
public class CPPFactoryEclipse extends Factory implements ICPPFactoryEclipse {
	private static final long serialVersionUID = 3342247491732965777L;

	private static IFactory UniqueInstance;
	public static IFactory getInstance() {
		if (CPPFactoryEclipse.UniqueInstance == null) {
			CPPFactoryEclipse.UniqueInstance = new CPPFactoryEclipse();
		}
		return CPPFactoryEclipse.UniqueInstance;
	}

	private CPPFactoryEclipse() {
	}

	public ICPPClass createClass(final char[] anID, final char[] aName) {
		return new CPPClass(anID, aName);
	}

	public IDestructor createDestructor(final char[] anID, final char[] aName) {

		return new Destructor(anID, aName);
	}

	public IEnum createEnum(final char[] anID) {

		return new Enum(anID);
	}

	public IEnumValue createEnumValue(final char[] anID) {
		final IEnumValue anEnumValue = new EnumValue(anID);
		return anEnumValue;
	}

	public ICPPGhost createGhost(final char[] anID, final char[] aName) {
		return new CPPGhost(anID, aName);
	}

	public IGlobalField createGlobalField(
		final char[] anID,
		final char[] aName,
		final char[] aType,
		final int aCardinality) {

		return new GlobalField(anID, aName, aType, aCardinality);
	}

	public IGlobalFunction createGlobalFunction(
		final char[] anID,
		final char[] aName) {

		return new GlobalFunction(anID, aName);
	}

	public IGlobalFunctionGhost createGlobalFunctionGhost(
		final char[] anID,
		final char[] aName) {

		return new GlobalFunctionGhost(anID, aName);
	}

	public ICPPMemberClass createMemberClass(
		final char[] anID,
		final char[] aName) {

		return new CPPMemberClass(anID, aName);
	}

	public ICPPMemberGhost createMemberGhost(
		final char[] anID,
		final char[] aName) {

		return new CPPMemberGhost(anID, aName);
	}

	public IMemberStructure createMemberStructure(final char[] aName) {
		return new MemberStructure(aName);
	}

	public IParameter createParameter(
		final IEntity anEntity,
		final char[] aName,
		final char[] aQualification,
		final int aCardinality) {

		return new CPPParameter(anEntity, aName, aQualification, aCardinality);
	}

	public IStructure createStructure(final char[] aName) {
		return new Structure(aName);
	}

	public IUnion createUnion(final char[] aName) {
		return new Union(aName);
	}

	protected IWalker getEventGenerator() {
		return CPPEventGenerator.getInstance();
	}
}
