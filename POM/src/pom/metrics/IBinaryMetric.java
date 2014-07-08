package pom.metrics;

import padl.kernel.IAbstractModel;
import padl.kernel.IFirstClassEntity;

public interface IBinaryMetric extends IMetric {
	double compute(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity anEntity,
		final IFirstClassEntity anotherEntity);

	boolean isSymmetrical();
}
