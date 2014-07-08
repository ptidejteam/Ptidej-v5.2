/* (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
 * University of Montreal.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the author, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
 * ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHOR IS ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package squad.quality.pqmod.repository;

import padl.kernel.IAbstractModel;
import padl.kernel.IFirstClassEntity;
import pom.metrics.IUnaryMetric;
import pom.metrics.MetricsRepository;
import squad.quality.AbstractQualityAttribute;
import squad.quality.INominalQualityAttribute;
import squad.quality.IQualityAttribute;

public class ModularityAtRuntime extends AbstractQualityAttribute implements
		IQualityAttribute, INominalQualityAttribute {

	public String concretelyComputeNomValue(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity entity) {

		final double Lcom5 =
			((IUnaryMetric) MetricsRepository.getInstance().getMetric("LCOM5"))
				.compute(anAbstractModel, entity);
		final double Dcaec =
			((IUnaryMetric) MetricsRepository.getInstance().getMetric("DCAEC"))
				.compute(anAbstractModel, entity);
		final double Ncm =
			((IUnaryMetric) MetricsRepository.getInstance().getMetric("NCM"))
				.compute(anAbstractModel, entity);
		final double Noa =
			((IUnaryMetric) MetricsRepository.getInstance().getMetric("NOA"))
				.compute(anAbstractModel, entity);

		if ((Lcom5 >= 1.02) && (Dcaec <= 0)) {
			return "N";
		}
		else if ((Ncm >= 62.78) || (Noa <= 2)) {
			return "N";
		}
		else {
			return "G";
		}
	}
	public String getDefinition() {
		final String def =
			"Degree to which the functions of a system are independent from one another at runtime.";
		return def;
	}
}
