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

import sad.rule.creator.model.IConstituent;
import sad.rule.creator.model.IOperator;

/**
 * @author Pierre Leduc
 */
public class Operator extends Constituent implements IOperator {
	private final int operatorType;
	private IConstituent operand1;
	private IConstituent operand2;

	public Operator(
		final String anID,
		final int anOperatorType,
		final IConstituent anOperand1,
		final IConstituent anOperand2) {
		super(anID);
		this.operatorType = anOperatorType;
		this.operand1 = anOperand1;
		this.operand2 = anOperand2;
	}

	/**
	 * @return Returns the operand 1.
	 */
	public IConstituent getOperand1() {
		return this.operand1;
	}

	/**
	 * @return Returns the operand 2.
	 */
	public IConstituent getOperand2() {
		return this.operand2;
	}

	/**
	 * @return Returns the operator type.
	 */
	public int getOperatorType() {
		return this.operatorType;
	}

	public void setOperand1(final IConstituent aConstituent) {
		this.operand1 = aConstituent;
	}

	public void setOperand2(final IConstituent aConstituent) {
		this.operand2 = aConstituent;
	}

	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append(this.getClass().getName());
		buffer.append("\nName: ");
		buffer.append(this.getID());
		buffer.append("\nOperator type: ");
		buffer.append(this.getOperatorType());
		buffer.append("\nOperand #1: \n");
		buffer.append(this.getOperand1().getID());
		buffer.append("\n\nOperand #2: \n");
		buffer.append(this.getOperand2().getID());

		return buffer.toString();
	}
}
