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
/**
 * 
 */
package ptidej.ui.layout.repository.sugiyama.horizentalLayout;

import ptidej.ui.layout.repository.sugiyama.graph.INode;

/**
 * @author kahlamoh
 * @date 26-06-2006
 *
 */
public class QuadraticHorizentalSolver extends HorizentalSolver {
	private int nbLevels;
	private double[][] bestXcoordonatesMatrix;
	private final double c = 0.5;

	/**
	 * 
	 */
	public QuadraticHorizentalSolver(
		final int[][][] aBinaryGraphModel,
		final INode[][] aMatrix,
		final int anNbLevel) {

		super(aMatrix, aBinaryGraphModel, anNbLevel);
		// we make a copie
		this.bestXcoordonatesMatrix =
			new double[super.vertexGlobalOrdring.length][1];
		for (int i = 0; i < this.bestXcoordonatesMatrix.length; i++) {
			this.bestXcoordonatesMatrix[i] =
				new double[super.vertexGlobalOrdring[i].length];
			for (int j = 0; j < super.vertexGlobalOrdring[i].length; j++) {
				this.bestXcoordonatesMatrix[i][j] =
					super.vertexGlobalOrdring[i][j];
			}
		}

	}

	private boolean deboging = false;

	/**
	 * 
	 */
	public boolean getDebugStatus() {
		return this.deboging;

	}

	/**
	 * 
	 * 
	 */
	public void activateDebuging() {
		this.deboging = true;
	}

	/**
	 * 
	 * 
	 */
	public void deActivateDebuging() {
		this.deboging = false;
	}

	/**
	 * 
	 * 
	 */
	public void printDebugMessage(String message) {
		if (getDebugStatus())
			System.out.println(message);

	}

	public void solveQuadratic() {
		System.out.println("\n\nf1 = " + computeF1());
		System.out.println("\n\nf2 = " + computeF2());

		double f = computeF1() + this.c * computeF2();
		System.out.println("\n\nf = " + f);

	}

	/**
	 * close layout of vertices connected to each other
	 * @return result
	 */
	private double computeF1() {
		double result = 0;
		for (int i = 0; i < this.nbLevels - 1; i++) {
			for (int k = 0; k < this.matrix[i].length; k++) {
				for (int l = 0; l < this.matrix[i + 1].length; l++) {
					result +=
						Math
							.pow(
								(super.vertexGlobalOrdring[i][k] - super.vertexGlobalOrdring[i + 1][l]),
								2)
								* this.binaryGraphModel[i][l][k]; // [level][down][up]
				}
			}
		}
		return result;
	}

	/**
	 * balanced lauout of edges coming into or outgoing from a
	 * vertex 
	 * @return result
	 */
	private double computeF2() {
		double result = 0;
		printDebugMessage("NbLevels :" + this.nbLevels);
		for (int i = 0; i < this.nbLevels - 1; i++) {
			printDebugMessage("result 0:" + result);
			for (int k = 0; k < this.matrix[i].length; k++) {
				printDebugMessage("result 1:" + result);
				// only vertex connected to than a vertex are important
				if (computeLowerConnectivity(i, k) > 1) {
					double diff =
						super.vertexGlobalOrdring[i][k]
								- computeLowerBarycenter(i, k);

					printDebugMessage("LowerBary : "
							+ computeLowerBarycenter(i, k));
					printDebugMessage("diff :" + diff);
					result +=
						Math.pow(diff, 2)
								* binaryU(computeLowerConnectivity(i, k) - 1);
				}
			}
		}
		printDebugMessage("result 2 :" + result);
		for (int i = 1; i < this.nbLevels; i++) {
			for (int k = 0; k < this.matrix[i].length; k++) {
				//				System.out.println("result 3 :" + result);
				// only vertex connected to than a vertex are important
				if (computeUpperConnectivity(i, k) > 1) {
					result +=
						Math
							.pow(
								(super.vertexGlobalOrdring[i][k] - computeUpperBarycenter(
									i,
									k)),
								2)
								* binaryU(computeUpperConnectivity(i, k) - 1);
				}
			}
			printDebugMessage("result 4 :" + result);
		}

		printDebugMessage("result :" + result);
		return result;
	}

	/**
	 * 
	 * @return result
	 */
	private int binaryU(double z) {
		if (z > 0)
			return 1;
		return 0;
	}

	//	 Constraints

}
