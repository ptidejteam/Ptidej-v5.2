/**
 * oneWayCoupling - One Way Coupling
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

import java.util.Iterator;
import padl.kernel.IAbstractModel;
import padl.kernel.IFirstClassEntity;
import pom.metrics.IBinaryMetric;
import pom.metrics.IMetric;

public class oneWayCoupling extends AbstractMetric implements IMetric,
		IBinaryMetric {

	protected double concretelyCompute(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity anEntity) {

		double result = 0;

		final Iterator iterator =
			anAbstractModel.getIteratorOnTopLevelEntities();
		while (iterator.hasNext()) {
			final IFirstClassEntity firstClassEntity =
				(IFirstClassEntity) iterator.next();
			result +=
				this.concretelyCompute(
					anAbstractModel,
					anEntity,
					firstClassEntity);
		}
		return result;
	}
	protected double concretelyCompute(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity anEntityA,
		final IFirstClassEntity anEntityB) {

		final double value =
			super.methodPrimitives.numberOfUsesByFieldsOrMethods(
				anEntityA,
				anEntityB);

		return value;
	}
	public String getDefinition() {
		final String def =
			"Coupling between objects of two entities in one way only: \"A\" is coupled with \"B\".";
		return def;
	}
}