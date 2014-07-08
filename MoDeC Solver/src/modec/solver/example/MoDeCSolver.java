/*
 * (c) Copyright 2001-2004 Yann-Gaël Guéhéneuc,
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
package modec.solver.example;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import choco.Constraint;
import choco.integer.IntVar;
import choco.palm.PalmProblem;

/**
 * @author Yann-Gaël Guéhéneuc
 * @author Janice Ka-yee Ng
 * @since  2007/04/05
 */
public class MoDeCSolver {
	public static void main(final String[] args) {
		final PalmProblem problem = new PalmProblem();
		final IntVar variable1 = problem.makeBoundIntVar("v1", 0, 2);
		final IntVar variable2 = problem.makeBoundIntVar("v2", 0, 2);
		final Constraint constraint = problem.neq(variable1, variable2);

		// Yann 2013/08/12: Needed?
		//	problem.logger.setLevel(Level.INFO);
		problem.logger.addHandler(new Handler() {
			public void close() throws SecurityException {
			}
			public void flush() {
			}
			public void publish(LogRecord record) {
				if (record.getMessage().equals("A solution was found.")) {
					// System.out.println(variable1.isInstantiated());
					// System.out.println(variable2.isInstantiated());

					System.out.print('[');
					System.out.print(variable1.getDomain().getInf());
					System.out.print(',');
					System.out.print(variable1.getDomain().getSup());
					System.out.println(']');

					System.out.print('[');
					System.out.print(variable2.getDomain().getInf());
					System.out.print(',');
					System.out.print(variable2.getDomain().getSup());
					System.out.println(']');
				}
			}
		});
		problem.post(constraint);
		problem.solve(true);
		System.out.println(problem.getPalmSolver().solutions);
	}
}
