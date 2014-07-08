package pom.metrics.repository;

import java.util.List;

import padl.kernel.IAbstractModel;
import padl.kernel.IFirstClassEntity;
import pom.metrics.IDependencyIndependentMetric;
import pom.metrics.IMetric;
import pom.metrics.IUnaryMetric;

/**
 * LCOM2 - Lack of COhesion in Methods Version 2
 * 
 * @author Farouk ZAIDI
 * @since  2004/01/31 
 * 
 * @author Duc-Loc Huynh
 * @since  2005/08/18
 * 
 * Modifications made to fit the new architecture
 */
public class LCOM2 extends AbstractLCOM implements IMetric, IUnaryMetric, IDependencyIndependentMetric {
	protected double concretelyCompute(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity anEntity) {

		final double numberOfCouples =
			super.pairsOfMethodNotSharingFields(anEntity);
		final List methodsOfClass =
			super.classPrimitives.listOfDeclaredMethods(anEntity);
		final double nbPairsOfMethodsSharingField =
			(methodsOfClass.size() * (methodsOfClass.size() - 1) - numberOfCouples) / 2;
		final double nbPairsOfMethodsWithoutSharedField = numberOfCouples / 2;
		double lcom =
			nbPairsOfMethodsWithoutSharedField - nbPairsOfMethodsSharingField;
		if (lcom < 0) {
			lcom = 0;
		}
		return lcom;
	}
	public String getDefinition() {
		final String def = "Lack of cohesion in methods of an entity.";
		return def;
	}
}
