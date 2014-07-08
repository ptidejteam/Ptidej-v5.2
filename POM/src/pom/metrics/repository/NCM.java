/**
 * NCM - Number of Changed Methods
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

public class NCM extends AbstractMetric implements IMetric, IUnaryMetric {
	protected double concretelyCompute(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity firstClassEntity) {

		return super.getUnaryMetricInstance("NMA").compute(
			anAbstractModel,
			firstClassEntity)
				+ super.getUnaryMetricInstance("NMO").compute(
					anAbstractModel,
					firstClassEntity)
				+ super.getUnaryMetricInstance("NMI").compute(
					anAbstractModel,
					firstClassEntity);
	}
	public String getDefinition() {
		final String def = "Number of changed methods of in an entity.";
		return def;
	}
	//	public List listOfElements(IEntity entity) {
	//		List list = new ArrayList();
	//
	//		list.addAll(super.getInstanceOfUnaryMetric("NMA").listOfElements(
	//				entity));
	//		list.addAll(super.getInstanceOfUnaryMetric("NMO").listOfElements(
	//				entity));
	//		list.addAll(super.getInstanceOfUnaryMetric("NMI").listOfElements(
	//				entity));
	//
	//		return list;
	//	}
}
