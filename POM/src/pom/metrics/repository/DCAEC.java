/**
 * DCAEC - Descendants Class-Attribute Export Coupling
 * 
 * @author Farouk ZAIDI
 * @since  2004/01/31 
 * 
 * @author Duc-Loc Huynh
 * @since  2005/08/18
 * 
 * Modifications made to fit the new architecture
 */

package pom.metrics.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import padl.kernel.IAbstractModel;
import padl.kernel.IField;
import padl.kernel.IFirstClassEntity;
import pom.metrics.IMetric;
import pom.metrics.IUnaryMetric;

public class DCAEC extends AbstractMetric implements IMetric, IUnaryMetric {
	protected double concretelyCompute(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity firstClassEntity) {

		return listOfElements(firstClassEntity).size();
	}
	public String getDefinition() {
		final String def =
			"Descendants class-attribute export coupling of an entity.";
		return def;
	}
	private List listOfElements(IFirstClassEntity firstClassEntity) {
		final List dcaecList = new ArrayList();
		final char[] entityName = firstClassEntity.getID();
		final List descendents =
			super.classPrimitives.listOfDescendents(firstClassEntity);

		for (final Iterator iterDesc = descendents.iterator(); iterDesc
			.hasNext();) {
			final IFirstClassEntity aDesc = (IFirstClassEntity) iterDesc.next();
			final List descFields =
				super.classPrimitives.listOfImplementedFields(aDesc);
			for (final Iterator iterDescField = descFields.iterator(); iterDescField
				.hasNext();) {
				final IField field = (IField) iterDescField.next();
				if (Arrays.equals(field.getType(), entityName)) {
					dcaecList.add(iterDescField);
				}

			}
		}
		return dcaecList;
	}
}
