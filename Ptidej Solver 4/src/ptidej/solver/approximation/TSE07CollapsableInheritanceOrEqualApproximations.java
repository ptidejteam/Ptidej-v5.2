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
package ptidej.solver.approximation;

/**
 * @author	Yann-Gaël Guéhéneuc
 * @since	2006/08/16
 */
public class TSE07CollapsableInheritanceOrEqualApproximations implements
		IApproximations {

	private static final String[] APPROXIMATIONS = {
			"ptidej.solver.constraint.repository.StrictInheritanceConstraint",
			"ptidej.solver.constraint.repository.InheritanceConstraint",
			"ptidej.solver.constraint.repository.EqualConstraint" };

	private static TSE07CollapsableInheritanceOrEqualApproximations UniqueInstance;
	public static TSE07CollapsableInheritanceOrEqualApproximations getDefaultApproximations() {
		if (TSE07CollapsableInheritanceOrEqualApproximations.UniqueInstance == null) {
			TSE07CollapsableInheritanceOrEqualApproximations.UniqueInstance =
				new TSE07CollapsableInheritanceOrEqualApproximations();
		}
		return TSE07CollapsableInheritanceOrEqualApproximations.UniqueInstance;
	}

	private TSE07CollapsableInheritanceOrEqualApproximations() {
	}
	public String[] getApproximations() {
		return TSE07CollapsableInheritanceOrEqualApproximations.APPROXIMATIONS;
	}
}
