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
package padl.analysis.repository.aacrelationships;

import java.util.Iterator;
import padl.kernel.IAbstractModel;
import padl.kernel.IElement;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IMethodInvocation;
import padl.kernel.IOperation;
import padl.kernel.IRelationship;
import padl.kernel.exception.ModelDeclarationException;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2005/08/04
 */
final class RelationshipAnalyzer {
	private static void addRelationship(
		final IFirstClassEntity anEntity,
		final IRelationship aRelationship) {

		// Yann 2004/08/07: Duplicates!
		// When adding relationships, I must check for duplicate
		// according to their type (use, association...), their
		// target entity, their visibility, and their multiplicity.
		// However, this check may dramatically decrease performance!
		final Iterator iterator = anEntity.getIteratorOnConstituents();
		IRelationship duplicateRelationship = null;
		while (iterator.hasNext() && duplicateRelationship == null) {
			final IElement element = (IElement) iterator.next();
			if (element.getClass().equals(aRelationship.getClass())) {
				final IRelationship relationship = (IRelationship) element;
				if (relationship.getTargetEntity().equals(
					aRelationship.getTargetEntity())
						&& relationship.getCardinality() == aRelationship
							.getCardinality()
						&& relationship.getVisibility() == aRelationship
							.getVisibility()) {

					duplicateRelationship = relationship;
				}
			}
		}

		// Yann 2005/07/11: Duplicate relationships
		// If a relationship is duplicated (existing because of two
		// different piece of code), I concatenate their names.
		if (duplicateRelationship == null) {
			anEntity.addConstituent(aRelationship);
		}
		else {
			final char[] newName =
				new char[duplicateRelationship.getName().length + 1
						+ aRelationship.getName().length];
			System.arraycopy(
				duplicateRelationship.getName(),
				0,
				newName,
				0,
				duplicateRelationship.getName().length);
			newName[duplicateRelationship.getName().length] = '+';
			System.arraycopy(
				aRelationship.getName(),
				0,
				newName,
				duplicateRelationship.getName().length + 1,
				aRelationship.getName().length);
			duplicateRelationship.setName(newName);
		}
	}
	public static void recognizeRelationships(
		final IAbstractModel anAbstractLevelModel,
		final IFirstClassEntity anEntity,
		final IOperation aMethod) {

		// Yann 2005/10/12: Iterator!
		// I have now an iterator able to iterate over a
		// specified type of constituent of a list.
		final Iterator iterator =
			aMethod.getIteratorOnConstituents(IMethodInvocation.class);
		IRelationship relationship = null;

		while (iterator.hasNext()) {
			final IMethodInvocation methodInvocation =
				(IMethodInvocation) iterator.next();
			final char[] name = aMethod.getName();

			//	System.err.print(
			//		methodInvocation.getType() == IMethodInvocation.CLASS_CLASS
			//			|| methodInvocation.getType()
			//				== IMethodInvocation.INSTANCE_CLASS);
			//	System.err.print(" ");
			//	System.err.println(methodInvocation.getTargetEntity() != null);

			if (methodInvocation.getTargetEntity() != null) {
				switch (methodInvocation.getType()) {
					case IMethodInvocation.CLASS_CLASS :
					case IMethodInvocation.INSTANCE_CLASS :
					case IMethodInvocation.CLASS_INSTANCE_FROM_FIELD :
						relationship =
							anAbstractLevelModel
								.getFactory()
								.createUseRelationship(
									name,
									methodInvocation.getTargetEntity(),
									methodInvocation.getCardinality());
						relationship.setVisibility(methodInvocation
							.getVisibility());

						RelationshipAnalyzer.addRelationship(
							anEntity,
							relationship);
						break;

					case IMethodInvocation.CLASS_INSTANCE :
					case IMethodInvocation.INSTANCE_INSTANCE :
						relationship =
							anAbstractLevelModel
								.getFactory()
								.createAssociationRelationship(
									name,
									methodInvocation.getTargetEntity(),
									methodInvocation.getCardinality());
						relationship.setVisibility(methodInvocation
							.getVisibility());

						RelationshipAnalyzer.addRelationship(
							anEntity,
							relationship);
						break;

					case IMethodInvocation.CLASS_CLASS_FROM_FIELD :
					case IMethodInvocation.INSTANCE_CLASS_FROM_FIELD :
					case IMethodInvocation.INSTANCE_INSTANCE_FROM_FIELD :
						relationship =
							anAbstractLevelModel
								.getFactory()
								.createAggregationRelationship(
									name,
									methodInvocation.getTargetEntity(),
									methodInvocation.getCardinality());
						relationship.setVisibility(methodInvocation
							.getVisibility());

						RelationshipAnalyzer.addRelationship(
							anEntity,
							relationship);
						break;

					case IMethodInvocation.INSTANCE_CREATION :
						relationship =
							anAbstractLevelModel
								.getFactory()
								.createCreationRelationship(
									name,
									methodInvocation.getTargetEntity(),
									methodInvocation.getCardinality());
						relationship.setVisibility(methodInvocation
							.getVisibility());

						RelationshipAnalyzer.addRelationship(
							anEntity,
							relationship);
						break;

					case IMethodInvocation.OTHER :
						break;

					default :
						throw new ModelDeclarationException(
							"An instance of MethodInvocation must have a recognized type value (was "
									+ methodInvocation.getType() + ')');
				}
			}
		}
	}
}
