/*
 * (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
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

package sad.rule.creator.model.impl;

import sad.rule.creator.model.IMetric;

/**
 * @author Pierre Leduc
 * @modified Naouel 2007/10/18 : add comparison operators
 */
public class Metric extends Attribute implements IMetric {
	private int value_ordi;
	private double value;
	private int comparisonOperator;
	private double fuzziness;

	private final int operator;
	private final IMetric operand1;
	private final IMetric operand2;

	public Metric(
		final String aName,
		final double aValue,
		final double fuzziness) {
		super(aName);
		this.value = aValue;
		this.value_ordi = 0;
		this.fuzziness = fuzziness;

		this.operator = -1;
		this.operand1 = null;
		this.operand2 = null;
	}

	public Metric(
		final String aName,
		final double aValue,
		final int aComparisonOp,
		final double fuzziness) {
		super(aName);
		this.value = aValue;
		this.comparisonOperator = aComparisonOp;
		this.value_ordi = 0;
		this.fuzziness = fuzziness;

		this.operator = -1;
		this.operand1 = null;
		this.operand2 = null;
	}

	public Metric(
		final String aName,
		final int aValue_ordi,
		final double fuzziness) {
		super(aName);
		this.value_ordi = aValue_ordi;
		this.value = 0;
		this.fuzziness = fuzziness;

		this.operator = -1;
		this.operand1 = null;
		this.operand2 = null;
	}

	public Metric(
		final String aName,
		final int anOperator,
		final IMetric anOperand1,
		final IMetric anOperand2) {
		super(aName);
		this.value_ordi = 0;
		this.value = 0;
		this.fuzziness = 0;

		this.operator = anOperator;
		this.operand1 = anOperand1;
		this.operand2 = anOperand2;
	}

	public int getComparisonOperator() {
		return this.comparisonOperator;
	}

	public double getFuzziness() {
		return this.fuzziness;
	}

	public IMetric getMetric1() {
		return this.operand1;
	}

	public IMetric getMetric2() {
		return this.operand2;
	}

	public double getNumericValue() {
		return this.value;
	}

	public int getOperator() {
		return this.operator;
	}

	public int getOrdinalValue() {
		return this.value_ordi;
	}

	public void setComparisonOperator(final int value) {
		this.comparisonOperator = value;
	}

	public void setFuzziness(final double value) {
		this.fuzziness = value;
	}

	public void setNumericValue(final double value) {
		this.value = value;
	}

	public void setOrdinalValue(final int value) {
		this.value_ordi = value;
	}

	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append(this.getClass().getName());
		buffer.append("\nName: ");
		buffer.append(this.getID());
		buffer.append("\nOrdinal value: ");
		buffer.append(this.getOrdinalValue());
		buffer.append("\nValue: ");
		buffer.append(this.getNumericValue());
		buffer.append("\nComparisonOperator: ");
		buffer.append(this.getComparisonOperator());
		buffer.append("\nFuzziness: ");
		buffer.append(this.getNumericValue());

		return buffer.toString();
	}
}
