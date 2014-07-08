package pom.metrics.repository;

import padl.kernel.IAbstractModel;
import padl.kernel.IFirstClassEntity;
import pom.metrics.IMetric;
import pom.metrics.IUnaryMetric;

/**
 * @author Foutse Khomh
 * @since  2007/03/01
 */
public class MFA extends AbstractMetric implements IMetric, IUnaryMetric {
	public String getDefinition() {
		final String def =
			"Ratio of the number of methods inherited by an entity to the number of methods accessible by member methods of the entity.";
		return def;
	}
	protected double concretelyCompute(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity firstClassEntity) {

		if ((super.classPrimitives
			.listOfDeclaredMethods(firstClassEntity)
			.size() + super.classPrimitives.listOfInheritedMethods(
			firstClassEntity).size()) > 0) {

			return (double) (super.classPrimitives
				.listOfInheritedMethods(firstClassEntity).size())
					/ (double) (super.classPrimitives.listOfDeclaredMethods(
						firstClassEntity).size() + super.classPrimitives
						.listOfInheritedMethods(firstClassEntity)
						.size());
		}
		else {
			return 0;
		}
	}
}
