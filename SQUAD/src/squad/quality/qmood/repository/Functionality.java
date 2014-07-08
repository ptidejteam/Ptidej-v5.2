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
package squad.quality.qmood.repository;

import padl.kernel.IAbstractModel;
import padl.kernel.IFirstClassEntity;
import pom.metrics.IUnaryMetric;
import pom.metrics.MetricsRepository;
import squad.quality.AbstractQualityAttribute;
import squad.quality.INumericQualityAttribute;
import squad.quality.IQualityAttribute;
import util.help.IHelpURL;

public final class Functionality extends AbstractQualityAttribute implements
		IQualityAttribute, INumericQualityAttribute, IHelpURL {

	public double concretelyComputeNumValue(
		final IAbstractModel anAbstractModel,
		final IFirstClassEntity entity) {
		double result =
			(0.12)
					* ((IUnaryMetric) MetricsRepository
						.getInstance()
						.getMetric("CAM"))
						.compute(anAbstractModel, entity)
					+ (0.22)
					* ((IUnaryMetric) MetricsRepository
						.getInstance()
						.getMetric("NOPM")).compute(
						anAbstractModel,
						entity)
					+ (0.22)
					* ((IUnaryMetric) MetricsRepository
						.getInstance()
						.getMetric("CIS"))
						.compute(anAbstractModel, entity)
					+ (0.22)
					* ((IUnaryMetric) MetricsRepository
						.getInstance()
						.getMetric("DSC"))
						.compute(anAbstractModel, entity)
					+ (0.22)
					* ((IUnaryMetric) MetricsRepository
						.getInstance()
						.getMetric("NOH"))
						.compute(anAbstractModel, entity);
		return result;
	}
	public String getDefinition() {
		final String def =
			"Responsibilities assigned to the classes of a design, which are made available by the classes through their public interfaces.";
		return def;
	}
	public String getHelpURL() {
		return "http://dl.acm.org/citation.cfm?id=513066";
	}
}
