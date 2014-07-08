package pom.metrics.repository;

import padl.kernel.IAbstractModel;
import padl.kernel.IFirstClassEntity;
import pom.metrics.IDependencyIndependentMetric;
import pom.metrics.IMetric;
import pom.metrics.IUnaryMetric;

/**
 * @author Foutse Khomh
 * @since  2007/03/01
 */
public class NOM extends AbstractMetric implements IMetric, IUnaryMetric, 
	IDependencyIndependentMetric {
	protected double concretelyCompute(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity firstClassEntity) {

		return super.classPrimitives
			.listOfDeclaredMethods(firstClassEntity)
			.size();
	}
	public String getDefinition() {
		final String def = "Number of all methods defined in an entity.";
		return def;
	}
}
