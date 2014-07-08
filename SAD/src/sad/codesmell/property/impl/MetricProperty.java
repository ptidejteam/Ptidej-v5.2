/* (c) Copyright 2001 and following years, Yann-Ga�l Gu�h�neuc,
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

package sad.codesmell.property.impl;

import java.util.Map;

import sad.codesmell.property.ICodeSmellProperty;

/**
 * Hold information about a metric
 *
 * @author Pierre Leduc
 * @version 1.0
 * @since 2006/05/29
 */
public class MetricProperty implements ICodeSmellProperty {
	final private String metricName;
	final private double metricValue;
	final private Map metricThreshold;
	final private double fuzziness;

	public MetricProperty(
		final String metricName,
		final double value,
		final Map threshold) {

		this.metricName = metricName;
		this.metricValue = value;
		this.metricThreshold = threshold;
		this.fuzziness = 100;
	}
	public MetricProperty(
		final String metricName,
		final double value,
		final Map threshold,
		final double fuzziness) {

		this.metricName = metricName;
		this.metricValue = value;
		this.metricThreshold = threshold;
		this.fuzziness = fuzziness;
	}
	public String getMetricName() {
		return this.metricName;
	}
	public Map getMetricThreshold() {
		return this.metricThreshold;
	}
	public double getMetricValue() {
		return this.metricValue;
	}
	public String toString(
		final int count,
		final int propertyCount,
		final String codesmellName) {

		final StringBuffer buffer = new StringBuffer();
		buffer.append(
			"\n"
				+ count
				+ "."
				+ (int) this.fuzziness
				+ "."
				+ codesmellName
				+ "."
				+ this.metricName
				+ "-"
				+ propertyCount
				+ " = "
				+ this.metricValue);
		if (this.metricThreshold != null) {
			buffer.append(
				"\n"
					+ count
					+ "."
					+ (int) this.fuzziness
					+ "."
					+ codesmellName
					+ "."
					+ this.metricName
					+ "_MaxBound-"
					+ propertyCount
					+ " = "
					+ this.metricThreshold);
		}
		return buffer.toString();
	}
}
