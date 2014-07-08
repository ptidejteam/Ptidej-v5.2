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
package modec.solver.constraint;

import java.util.List;
import java.util.Set;

import metamodel.scenarioDiagram.Classifier;
import metamodel.scenarioDiagram.Instance;
import metamodel.scenarioDiagram.ScenarioDiagram;
import choco.Constraint;
import choco.ContradictionException;
import choco.integer.IntVar;
import choco.palm.PalmProblem;
import choco.palm.explain.PalmConstraintPlugin;
import choco.palm.integer.AbstractPalmBinIntConstraint;
import choco.palm.integer.PalmIntDomain;
import choco.palm.integer.PalmIntVar;
import choco.util.IntIterator;

/**
 * @author Janice Ng
 */
public class SameType
	extends AbstractPalmBinIntConstraint
	implements Constraint {

	private ScenarioDiagram sd;
	public SameType(
		IntVar v0,
		IntVar v1,
		ScenarioDiagram sd,
		List componentsMessages,
		List allClassifiers) {
		this.v0 = v0;
		this.v1 = v1;
		this.sd = sd;
		this.hook = new PalmConstraintPlugin(this);
	}

	public void propagate() {
		if (this.v0.getDomain().getSize() > 0) {
			final IntIterator iterator0 = this.v0.getDomain().getIterator();
			//boolean toBeRemoved = true;

			while (iterator0.hasNext()) {
				final int index_e0 = iterator0.next();
				if (index_e0 > -1) {
					boolean toBeRemoved = true;
					Classifier class_e0 = this.sd.getIdxClassifier(index_e0);

					IntIterator iterator1 = this.v1.getDomain().getIterator();
					while (iterator1.hasNext() && toBeRemoved) {

						final int index_e1 = iterator1.next();
						Classifier class_e1 =
							this.sd.getIdxClassifier(index_e1);

						if (class_e0 instanceof Instance
							&& class_e1 instanceof Instance
							&& (((Instance) (class_e0))
								.containsAllOfClass(
									((Instance) (class_e1)).getOfClass()))) {
							toBeRemoved = false;
						}
					}
					if (toBeRemoved) {
						choco.palm.explain.Explanation expl =
							((PalmProblem) this.getProblem()).makeExplanation();
						((PalmConstraintPlugin) this.getPlugIn()).self_explain(
							expl);
						((PalmIntVar) this.v1).self_explain(
							PalmIntDomain.DOM,
							expl);
						((PalmIntVar) this.v0).removeVal(
							index_e0,
							this.cIdx0,
							expl);
					}
				}

			}
		}

		if (this.v1.getDomain().getSize() > 0) {
			final IntIterator iterator1 = this.v1.getDomain().getIterator();
			//			boolean toBeRemoved = true;
			while (iterator1.hasNext() /*& toBeRemoved*/
				) {
				final int index_e1 = iterator1.next();
				if (index_e1 > -1) {
					boolean toBeRemoved = true;
					Classifier class_e1 = this.sd.getIdxClassifier(index_e1);

					IntIterator iterator0 = this.v0.getDomain().getIterator();
					while (iterator0.hasNext() && toBeRemoved) {

						final int index_e0 = iterator0.next();
						Classifier class_e0 =
							this.sd.getIdxClassifier(index_e0);

						if (class_e0 instanceof Instance
							&& class_e1 instanceof Instance
							&& (((Instance) (class_e1))
								.containsAllOfClass(
									((Instance) (class_e0)).getOfClass()))) {
							toBeRemoved = false;
						}
					}
					
					if (toBeRemoved) {
						choco.palm.explain.Explanation expl =
							((PalmProblem) this.getProblem()).makeExplanation();
						((PalmConstraintPlugin) this.getPlugIn()).self_explain(
							expl);
						((PalmIntVar) this.v0).self_explain(
							PalmIntDomain.DOM,
							expl);
						((PalmIntVar) this.v1).removeVal(
							index_e1,
							this.cIdx1,
							expl);
					}
				}

			}
		}

	}

	/* (non-Javadoc)
	 * @see choco.palm.integer.PalmIntVarListener#awakeOnRestoreVal(int, int)
	 */
	public void awakeOnRestoreVal(int idx, int val)
		throws ContradictionException {
		propagate();
	}
	/* (non-Javadoc)
	 * @see choco.palm.integer.PalmIntVarListener#whyIsTrue()
	 */
	public Set whyIsTrue() {

		return null;
	}
	/* (non-Javadoc)
	 * @see choco.palm.integer.PalmIntVarListener#whyIsFalse()
	 */
	public Set whyIsFalse() {

		return null;
	}
	/* (non-Javadoc)
	 * @see choco.Constraint#isSatisfied()
	 */
	public boolean isSatisfied() {

		return false;
	}

}
