/*
 * (c) Copyright 2001-2003 Yann-Gaël Guéhéneuc,
 * École des Mines de Nantes and Object Technology International, Inc.
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
package padl.cpp.kernel;

import padl.kernel.IEntity;
import padl.kernel.IFactory;
import padl.kernel.IParameter;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since 2005/07/12
 */
public interface ICPPFactoryEclipse extends IFactory {
	ICPPClass createClass(final char[] anID, final char[] aName);
	IDestructor createDestructor(final char[] anID, final char[] aName);
	IEnum createEnum(final char[] anID);
	IEnumValue createEnumValue(final char[] anID);
	ICPPGhost createGhost(final char[] anID, final char[] aName);
	IGlobalField createGlobalField(
		final char[] aID,
		final char[] aName,
		final char[] aType,
		final int aCardinality);
	IGlobalFunction createGlobalFunction(final char[] anID, final char[] aName);
	IGlobalFunctionGhost createGlobalFunctionGhost(
		final char[] anID,
		final char[] aName);
	IMemberStructure createMemberStructure(final char[] anID);
	IParameter createParameter(
		final IEntity parameterEntity,
		final char[] parameterName,
		final char[] parameterQualification,
		final int cardinality);
	IStructure createStructure(final char[] anID);
	IUnion createUnion(final char[] anID);
}
