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

public class Scalability extends AbstractQualityAttribute implements
		IQualityAttribute, INominalQualityAttribute {

	public String concretelyComputeNomValue(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity entity) {

		final double Noc =
			((IUnaryMetric) MetricsRepository.getInstance().getMetric("NOC"))
				.compute(anAbstractModel, entity);
		final double Aid =
			((IUnaryMetric) MetricsRepository.getInstance().getMetric("AID"))
				.compute(anAbstractModel, entity);
		final double AcMic =
			((IUnaryMetric) MetricsRepository.getInstance().getMetric("ACMIC"))
				.compute(anAbstractModel, entity);
		final double DcMec =
			((IUnaryMetric) MetricsRepository.getInstance().getMetric("DCMEC"))
				.compute(anAbstractModel, entity);
		final double Nma =
			((IUnaryMetric) MetricsRepository.getInstance().getMetric("NMA"))
				.compute(anAbstractModel, entity);
		final double connectivity =
			((IUnaryMetric) MetricsRepository.getInstance().getMetric(
				"connectivity")).compute(anAbstractModel, entity);
		final double Lcom5 =
			((IUnaryMetric) MetricsRepository.getInstance().getMetric("LCOM5"))
				.compute(anAbstractModel, entity);
		final double ICHClass =
			((IUnaryMetric) MetricsRepository.getInstance().getMetric(
				"ICHClass")).compute(anAbstractModel, entity);

		if ((DcMec >= 0.1) && (Noc <= 0.65) && (DcMec <= 0.2)) {
			return "G";
		}
		else if ((Aid >= 2.83) && (Aid <= 2.92)) {
			return "G";
		}
		else if ((connectivity <= 0.66) && (Noc >= 0.75) && (AcMic <= 0.2)) {
			return "B";
		}
		else if ((Lcom5 <= 0.63) && (ICHClass >= 0.25) && (Nma <= 5.88)) {
			return "B";
		}
		else {
			return "N";
		}
	}
	public String getDefinition() {
		final String def =
			"Degree to which the system can cope with large amount of data and computation at runtime.";
		return def;
	}

}
