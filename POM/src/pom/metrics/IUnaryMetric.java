package pom.metrics;

import padl.kernel.IAbstractModel;
import padl.kernel.IFirstClassEntity;

public interface IUnaryMetric extends IMetric {
	double compute(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity anEntity);
}
