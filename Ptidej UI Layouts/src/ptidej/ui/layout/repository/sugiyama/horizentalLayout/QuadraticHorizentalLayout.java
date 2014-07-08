/* (c) Copyright 2001 and following years, Yann-Ga‘l GuŽhŽneuc,
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
/**
 * 
 */
package ptidej.ui.layout.repository.sugiyama.horizentalLayout;

import ptidej.ui.layout.repository.sugiyama.graph.Node;

/**
 * @author mohamedkahla
 * date 14-06-2006
 *
 */
public final class QuadraticHorizentalLayout extends HorizentalLayout {

	/**
	 * The quadratic programming layout method
	 */
	public QuadraticHorizentalLayout(Node[][] aMatrix) {
		super(aMatrix); // HorizentalLayout constructor

	}

	// quadratic programming
	public void doHorizentalLayout() {
		if (super.nbLevels > 1) {
			buildBinaryGraphModel();
		}

		final QuadraticHorizentalSolver solver =
			new QuadraticHorizentalSolver(
				super.binaryGraphModel,
				super.matrix,
				super.nbLevels);

		solver.solveQuadratic();

	}

	/**
	 * 
	 */
	public double[][] getVertexGlobalOrdring() {
		return super.solver.getVertexGlobalOrdring();
	}

}
