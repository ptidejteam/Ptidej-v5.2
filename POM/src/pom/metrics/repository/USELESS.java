package pom.metrics.repository;

import padl.kernel.IAbstractModel;
import padl.kernel.IFirstClassEntity;
import pom.metrics.IMetric;
import pom.metrics.IUnaryMetric;

/**
 * @author Alban Tiberghien
 * @since 2008/09/26
 */
public class USELESS extends AbstractMetric implements IMetric, IUnaryMetric {
	public String getDefinition() {
		final String def = "Same value for any entity: 1.";
		return def;
	}
	protected double concretelyCompute(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity firstClassEntity) {

		return 1;
	}
}