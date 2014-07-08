/**
 * DIT - Depth of inheritance tree
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

import java.util.List;
import padl.kernel.IAbstractModel;
import padl.kernel.IFirstClassEntity;
import pom.metrics.IMetric;
import pom.metrics.IUnaryMetric;

public class DIT extends AbstractMetric implements IMetric, IUnaryMetric {
	protected double concretelyCompute(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity firstClassEntity) {

		final List parentsOfEntity = this.listOfElements(firstClassEntity);
		return parentsOfEntity.size() == 0 ? 0 : 1 + this.maxDIT(
			anAbstractModel,
			parentsOfEntity);
	}
	public String getDefinition() {
		final String def =
			"Depth of inheritance tree of an entity. Uses a recursive algorithm to calculate it.";
		return def;
	}
	private List listOfElements(final IFirstClassEntity firstClassEntity) {
		return this.classPrimitives.listOfAllDirectParents(firstClassEntity);
	}
	/**
	 * Returns the highest DIT in the list of entities This method is used by
	 * the DIT method to get the maximum DIT of the parents of an entity.
	 * 
	 * @param list
	 * @return the highest DIT in the list of entities
	 */
	private double maxDIT(
		final IAbstractModel anAbstractModel,
		final List list) {

		final int size = list.size();
		final double[] resultDITs = new double[size];
		for (int i = 0; i < size; i++) {
			final IFirstClassEntity firstClassEntity =
				(IFirstClassEntity) list.get(i);
			resultDITs[i] = compute(anAbstractModel, firstClassEntity);
		}
		return this.maxValue(resultDITs);
	}
	/**
	 * Returns the max value in an array of integers If the array is null, the
	 * returned value is -1.
	 * 
	 * @param array
	 * @return the max value in an array
	 */
	private double maxValue(final double[] array) {
		if (array == null) {
			return -1;
		}
		double maxValue = -1;
		for (int i = 0; i < array.length; i++) {
			if (maxValue < array[i]) {
				maxValue = array[i];
			}
		}
		return maxValue;
	}
}
