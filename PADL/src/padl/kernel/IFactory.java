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
package padl.kernel;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2003/12/05
 */
public interface IFactory {
	IAggregation createAggregationRelationship(
		final char[] aName,
		final IFirstClassEntity aTargetEntity,
		final int aCardinality);
	IAssociation createAssociationRelationship(
		final char[] aName,
		final IFirstClassEntity aTargetEntity,
		final int aCardinality);
	IClass createClass(final char[] anID, final char[] aName);
	ICodeLevelModel createCodeLevelModel(final char[] aName);
	ICodeLevelModel createCodeLevelModel(final String aName);
	IComposition createCompositionRelationship(
		final char[] aName,
		final IFirstClassEntity aTargetEntity,
		final int aCardinality);
	IComposition createCompositionRelationship(final IAssociation anAssociation);
	IConstructor createConstructor(final char[] anID, final char[] aName);
	IContainerAggregation createContainerAggregationRelationship(
		final char[] aName,
		final IFirstClassEntity aTargetEntity,
		final int aCardinality);
	IContainerComposition createContainerCompositionRelationship(
		final char[] aName,
		final IFirstClassEntity aTargetEntity,
		final int aCardinality);
	IContainerComposition createContainerCompositionRelationship(
		final IAssociation anAssociation);
	ICreation createCreationRelationship(
		final char[] aName,
		final IFirstClassEntity aTargetEntity,
		final int aCardinality);
	//	IDelegatingMethod createDelegatingMethod(
	//		final char[] aName,
	//		final IAssociation aTargetAssociation) ;
	IDelegatingMethod createDelegatingMethod(
		final char[] aName,
		final IAssociation aTargetAssociation,
		final IMethod aSupportMethod);
	IField createField(
		final char[] anID,
		final char[] aName,
		final char[] aType,
		final int aCardinality);
	IGetter createGetter(final char[] anID, final char[] aName);
	IGetter createGetter(final IMethod aMethod);
	IGhost createGhost(final char[] anID, final char[] aName);
	public IFirstClassEntity createHierarchyRoot();
	IIdiomLevelModel createIdiomLevelModel(final char[] aName);
	IInterface createInterface(final char[] anID, final char[] aName);
	IMemberClass createMemberClass(final char[] anID, final char[] aName);
	IMemberGhost createMemberGhost(final char[] anID, final char[] aName);
	IMemberInterface createMemberInterface(final char[] anID, final char[] aName);
	IMethod createMethod(final char[] anID, final char[] aName);
	IMethodInvocation createMethodInvocation(
		final int type,
		final int cardinality,
		final int visibility,
		final IFirstClassEntity targetEntity);
	IMethodInvocation createMethodInvocation(
		final int type,
		final int cardinality,
		final int visibility,
		final IFirstClassEntity targetEntity,
		final IFirstClassEntity entityDeclaringField);
	IPackage createPackage(final char[] aName);
	IPackageDefault createPackageDefault();
	IPackageGhost createPackageGhost(final char[] aName);
	IParameter createParameter(
		final IEntity aType,
		final char[] aName,
		final int aCardinality);
	IParameter createParameter(final IEntity aType, final int aCardinality);
	IPrimitiveEntity createPrimitiveEntity(final char[] aPrimitiveEntityName);
	ISetter createSetter(final char[] anID, final char[] aName);
	ISetter createSetter(final IMethod aMethod);
	IUseRelationship createUseRelationship(
		final char[] aName,
		final IFirstClassEntity aTargetEntity,
		final int aCardinality);
}
