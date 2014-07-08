package pom.metrics.repository;

import padl.kernel.IAbstractModel;
import padl.kernel.IFirstClassEntity;
import pom.metrics.IMetric;
import pom.metrics.IUnaryMetric;

/**
 * @author Foutse Khomh
 * @since  2007/03/01
 */
public class DSC extends AbstractMetric implements IMetric, IUnaryMetric {
	protected double concretelyCompute(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity anEntity) {

		//       int numb=0;
		//       final Iterator iter =
		//			this.anAbstractModel.getIteratorOnTopLevelEntities(); 
		//       while (iter.hasNext()) {
		//			final IEntity anElement = (IEntity) iter.next();
		//			
		//			if(anElement instanceof IClass){
		//				numb=numb+1;
		//			}
		//			}
		//       
		//		return numb;

		return anAbstractModel.getNumberOfTopLevelEntities();
	}
	public String getDefinition() {
		final String def = "Total number of entities in a design.";
		return def;
	}
}
