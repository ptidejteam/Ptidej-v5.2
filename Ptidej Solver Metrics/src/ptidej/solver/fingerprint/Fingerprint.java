/*
 * (c) Copyright 2001-2004 Jean-Yves Guyomarc'h,
 * University of Montréal.
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
package ptidej.solver.fingerprint;

import padl.kernel.IAbstractLevelModel;
import padl.kernel.IFirstClassEntity;
import pom.metrics.IUnaryMetric;
import pom.metrics.MetricsRepository;
import util.multilingual.MultilingualManager;

/**
 * @author Jean-Yves Guyomarc'h
 * @since  2004/10/14
 */
public class Fingerprint {
	// OPERATORS
	public static final int GT = 0;
	public static final int LT = 1;
	public static final int EQ = 2;
	public static final int GEQ = 3;
	public static final int LEQ = 4;

	private final String metricName;
	private final int operator;
	private final double value;

	public Fingerprint(
		final String metricName,
		final int operator,
		final double value) {

		this.metricName = metricName;
		this.operator = operator;
		this.value = value;
	}
	public boolean compute(final double result) {
		if (this.operator == Fingerprint.GT) {
			return result > this.value;
		}
		else if (this.operator == Fingerprint.LT) {
			return result < this.value;
		}
		else if (this.operator == Fingerprint.EQ) {
			return result == this.value;
		}
		else if (this.operator == Fingerprint.GEQ) {
			return result >= this.value;
		}
		else if (this.operator == Fingerprint.LEQ) {
			return result <= this.value;
		}
		throw new RuntimeException(new IllegalArgumentException(
			MultilingualManager.getString(
				"IAException_UNKNOWN_OP",
				Fingerprint.class)));
	}
	public boolean compute(
		final MetricsRepository metricRepository,
		final IAbstractLevelModel anAbstractLevelModel,
		final IFirstClassEntity firstClassEntity) {

		return this.compute(((IUnaryMetric) metricRepository
			.getMetric(this.metricName)).compute(
			anAbstractLevelModel,
			firstClassEntity));
	}
	public String getMetricsName() {
		return this.metricName;
	}
	public int getOperator() {
		return this.operator;
	}
	public double getValue() {
		return this.value;
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer(this.metricName);

		if (this.operator == Fingerprint.GT) {
			buffer.append(" > ");
		}
		else if (this.operator == Fingerprint.LT) {
			buffer.append(" < ");
		}
		else if (this.operator == Fingerprint.EQ) {
			buffer.append(" == ");
		}
		else if (this.operator == Fingerprint.GEQ) {
			buffer.append(" >= ");
		}
		else if (this.operator == Fingerprint.LEQ) {
			buffer.append(" <= ");
		}
		buffer.append(this.value);

		return buffer.toString();
	}
}
