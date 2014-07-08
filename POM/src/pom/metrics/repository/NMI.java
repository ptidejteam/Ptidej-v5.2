/**
 * NMI - Number Of Methods Inherited
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

import padl.kernel.IAbstractModel;
import padl.kernel.IFirstClassEntity;
import pom.metrics.IMetric;
import pom.metrics.IUnaryMetric;

public class NMI extends AbstractMetric implements IMetric, IUnaryMetric {
	protected double concretelyCompute(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity firstClassEntity) {

		return super.classPrimitives
			.listOfInheritedMethods(firstClassEntity)
			.size();
	}
	public String getDefinition() {
		final String def =
			"Number of methods inherited of an entity. Constructors or not considered as method, they are not counted in the result of the metric.";
		return def;
	}
}