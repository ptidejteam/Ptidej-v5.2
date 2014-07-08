/*
 * (c) Copyright 2001, 2002 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Gaël Guéhéneuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package ptidej.solver;

import padl.motif.IDesignMotifModel;
import padl.motif.util.adapter.DesignMotifModelAdapter;

public final class MicroArchitectureModel extends DesignMotifModelAdapter {
	private static final long serialVersionUID = -5975486033009802894L;
	private static final String NAME_STRING = "SolutionPatternModel";
	private static final char[] NAME_CHAR = MicroArchitectureModel.NAME_STRING
		.toCharArray();

	private final int solutionNumber;
	private final int percentage;

	public MicroArchitectureModel(final int solutionNumber, final int percentage) {
		super(MicroArchitectureModel.NAME_CHAR);

		this.solutionNumber = solutionNumber;
		this.percentage = percentage;
	}
	public int getClassification() {
		return IDesignMotifModel.SOLUTION_MOTIF;
	}
	public String getIntent() {
		return MicroArchitectureModel.NAME_STRING;
	}
	public char[] getName() {
		return MicroArchitectureModel.NAME_CHAR;
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("Solution pattern ");
		buffer.append(this.solutionNumber);
		buffer.append(" at ");
		buffer.append(this.percentage);
		buffer.append("%\n");

		buffer.append(super.toString());

		return buffer.toString();
	}
}
