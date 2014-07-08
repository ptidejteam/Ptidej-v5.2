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
package ptidej.solver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2006/02/06
 */
public class OccurrenceComparator {
	private static OccurrenceComparator UniqueInstance;
	public boolean belongsTo(
		final Occurrence aSolution,
		final Occurrence[] solutions) {

		boolean belongsTo = false;
		for (int i = 0; i < solutions.length && !belongsTo; i++) {
			if (aSolution.equals(solutions[i])) {
				belongsTo = true;
			}
		}
		return belongsTo;
	}
	public static OccurrenceComparator getInstance() {
		if (OccurrenceComparator.UniqueInstance == null) {
			OccurrenceComparator.UniqueInstance = new OccurrenceComparator();
		}
		return OccurrenceComparator.UniqueInstance;
	}
	public static void main(final String[] args) {
	}

	/**
	 * Compute the difference between the first array of solutions with
	 * the second array of solutions if |someSolutionA| >= |someSolutionB|
	 * or the other way around if |someSolutionB| > |someSolutionA|.
	 */
	public Occurrence[] difference(
		final Occurrence[] someSolutionsA,
		final Occurrence[] someSolutionsB) {

		final Occurrence[] longerArray;
		final Occurrence[] shorterArray;
		if (someSolutionsB.length > someSolutionsA.length) {
			longerArray = someSolutionsB;
			shorterArray = someSolutionsA;
		}
		else {
			longerArray = someSolutionsA;
			shorterArray = someSolutionsB;
		}

		final List list = new ArrayList();
		for (int i = 0; i < longerArray.length; i++) {
			if (!this.belongsTo(longerArray[i], shorterArray)) {
				list.add(longerArray[i]);
			}
		}

		final Occurrence[] differentSolutions = new Occurrence[list.size()];
		list.toArray(differentSolutions);
		return differentSolutions;
	}
	/**
	 * Compute the difference between the first array of solutions with
	 * the second array of solutions if |someSolutionA| >= |someSolutionB|
	 * or the other way around if |someSolutionB| > |someSolutionA|.
	 */
	public Occurrence[] intersection(
		final Occurrence[] someSolutionsA,
		final Occurrence[] someSolutionsB) {

		final Occurrence[] longerArray;
		final Occurrence[] shorterArray;
		if (someSolutionsB.length > someSolutionsA.length) {
			longerArray = someSolutionsB;
			shorterArray = someSolutionsA;
		}
		else {
			longerArray = someSolutionsA;
			shorterArray = someSolutionsB;
		}

		final List list = new ArrayList();
		for (int i = 0; i < longerArray.length; i++) {
			if (this.belongsTo(longerArray[i], shorterArray)) {
				list.add(longerArray[i]);
			}
		}

		final Occurrence[] differentSolutions = new Occurrence[list.size()];
		list.toArray(differentSolutions);
		return differentSolutions;
	}
}
