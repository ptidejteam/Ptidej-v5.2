/*
 * (c) Copyright 2000-2002 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Gaël Guéhéneuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.util;

import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;
import padl.kernel.IAbstractModel;
import padl.kernel.IAggregation;
import padl.kernel.IAssociation;
import padl.kernel.IComposition;
import padl.kernel.IContainerAggregation;
import padl.kernel.IElement;
import padl.kernel.IFirstClassEntity;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2002/10/08
 */
public final class ExternalDataProcessor {
	private static void addAggregationRelationship(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity anOriginEntity,
		final String aRelationshipName,
		final IFirstClassEntity aTargetEntity,
		final int aCardinality) {

		final IAggregation aggregation =
			anAbstractModel.getFactory().createAggregationRelationship(
				aRelationshipName.toCharArray(),
				aTargetEntity,
				aCardinality);
		anOriginEntity.addConstituent(aggregation);
	}
	private static void addCompositionRelationship(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity anOriginEntity,
		final String aRelationshipName,
		final IFirstClassEntity aTargetEntity,
		final int aCardinality) {

		final IComposition composition =
			anAbstractModel.getFactory().createCompositionRelationship(
				aRelationshipName.toCharArray(),
				aTargetEntity,
				aCardinality);
		anOriginEntity.addConstituent(composition);
	}
	private static void convertAggregationInComposition(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity anOriginEntity,
		final IFirstClassEntity aTargetEntity) {

		final Iterator iterator =
			anOriginEntity.getConcurrentIteratorOnConstituents();
		while (iterator.hasNext()) {
			final IElement element = (IElement) iterator.next();

			if (element instanceof IAssociation) {
				final IAssociation association = (IAssociation) element;
				if (association.getTargetEntity().isAboveInHierarchy(
					aTargetEntity)) {

					anOriginEntity.removeConstituentFromID(association.getID());
					if (association instanceof IContainerAggregation) {
						anOriginEntity
							.addConstituent(anAbstractModel
								.getFactory()
								.createContainerCompositionRelationship(
									association));
					}
					else if (association instanceof IAggregation) {
						anOriginEntity.addConstituent(anAbstractModel
							.getFactory()
							.createCompositionRelationship(association));
					}
				}
			}
		}
	}
	public static void process(
		final Properties someProperties,
		final IAbstractModel anAbstractModel) {

		final Iterator iterator = someProperties.keySet().iterator();
		while (iterator.hasNext()) {
			final String key = (String) iterator.next();
			if (key.startsWith("Relation")) {
				final String command = someProperties.getProperty(key);
				final int parenthesisIndex = command.indexOf('(');
				final String name = command.substring(0, parenthesisIndex);
				final String arguments =
					command.substring(
						parenthesisIndex + 1,
						command.length() - 1);

				if (name.equals("addAggregationRelationship")
						|| name.equals("addCompositionRelationship")) {

					final StringTokenizer tokenizer =
						new StringTokenizer(arguments, "(,)");
					final IFirstClassEntity originEntity =
						(IFirstClassEntity) anAbstractModel
							.getConstituentFromName(tokenizer
								.nextToken()
								.trim()
								.toCharArray());
					final String relationshipName =
						tokenizer.nextToken().trim();
					final IFirstClassEntity targetEntity =
						(IFirstClassEntity) anAbstractModel
							.getConstituentFromName(tokenizer
								.nextToken()
								.trim()
								.toCharArray());
					final Integer cardinality =
						new Integer(tokenizer.nextToken().trim());

					if (originEntity != null && targetEntity != null) {
						if (name.equals("addAggregationRelationship")) {
							ExternalDataProcessor.addAggregationRelationship(
								anAbstractModel,
								originEntity,
								relationshipName,
								targetEntity,
								cardinality.intValue());
						}
						else if (name.equals("addCompositionRelationship")) {
							ExternalDataProcessor.addCompositionRelationship(
								anAbstractModel,
								originEntity,
								relationshipName,
								targetEntity,
								cardinality.intValue());
						}
					}
				}
				else if (name.equals("convertAggregationInComposition")) {
					final StringTokenizer tokenizer =
						new StringTokenizer(arguments, ",");
					final IFirstClassEntity originEntity =
						(IFirstClassEntity) anAbstractModel
							.getConstituentFromName(tokenizer
								.nextToken()
								.trim()
								.toCharArray());
					final IFirstClassEntity targetEntity =
						(IFirstClassEntity) anAbstractModel
							.getConstituentFromName(tokenizer
								.nextToken()
								.trim()
								.toCharArray());

					if (originEntity != null && targetEntity != null) {
						ExternalDataProcessor.convertAggregationInComposition(
							anAbstractModel,
							originEntity,
							targetEntity);
					}
				}
			}
		}
	}
}
