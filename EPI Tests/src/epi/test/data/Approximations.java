/*
 * (c) Copyright 2001-2007 Yann-Gaël Guéhéneuc,
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
package epi.test.data;

import java.util.ArrayList;

import epi.solver.Approximation;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  08/01/15
 */
public abstract class Approximations {
	private static final Approximation APPROXIMATION_NONE =
		new Approximation();
	static {
		ArrayList containerComposition = new ArrayList();
		containerComposition.add("containerComposition");
		// containerComposition.add("containerAggregation");
		// containerComposition.add("composition");
		// containerComposition.add("aggregation");
		// containerComposition.add("useRelationship");
		// containerComposition.add("association");
		APPROXIMATION_NONE.setApproximationList(
			"containerComposition",
			containerComposition);
		ArrayList containerAggregation = new ArrayList();
		containerAggregation.add("containerAggregation");
		// containerAggregation.add("aggregation");
		APPROXIMATION_NONE.setApproximationList(
			"containerAggregation",
			containerAggregation);
		ArrayList composition = new ArrayList();
		composition.add("composition");
		// composition.add("aggregation");
		APPROXIMATION_NONE.setApproximationList("composition", composition);
		ArrayList aggregation = new ArrayList();
		aggregation.add("aggregation");
		APPROXIMATION_NONE.setApproximationList("aggregation", aggregation);
		ArrayList association = new ArrayList();
		association.add("association");
		APPROXIMATION_NONE.setApproximationList("association", association);
		ArrayList useRelationship = new ArrayList();
		useRelationship.add("useRelationship");
		APPROXIMATION_NONE.setApproximationList(
			"useRelationship",
			useRelationship);
		ArrayList inheritance = new ArrayList();
		inheritance.add("inheritance");
		// inheritance.add("pathInheritance");
		// inheritance.add("null");
		APPROXIMATION_NONE.setApproximationList("inheritance", inheritance);
		ArrayList creation = new ArrayList();
		creation.add("creation");
		APPROXIMATION_NONE.setApproximationList("creation", creation);
		ArrayList inheritance2 = new ArrayList();
		inheritance2.add("inheritance2");
		inheritance2.add("inheritance");
		// inheritance2.add("pathInheritance");
		APPROXIMATION_NONE.setApproximationList("inheritance2", inheritance2);
	}
	private static final Approximation APPROXIMATION_1 = new Approximation();
	static {
		ArrayList containerComposition = new ArrayList();
		containerComposition.add("containerComposition");
		containerComposition.add("containerAggregation");
		containerComposition.add("composition");
		containerComposition.add("aggregation");
		// containerComposition.add("association");
		// containerComposition.add("useRelationship");
		APPROXIMATION_1.setApproximationList(
			"containerComposition",
			containerComposition);

		ArrayList containerAggregation = new ArrayList();
		containerAggregation.add("containerAggregation");
		containerAggregation.add("aggregation");
		APPROXIMATION_1.setApproximationList(
			"containerAggregation",
			containerAggregation);

		ArrayList composition = new ArrayList();
		composition.add("composition");
		composition.add("aggregation");
		APPROXIMATION_1.setApproximationList("composition", composition);

		ArrayList aggregation = new ArrayList();
		aggregation.add("aggregation");
		APPROXIMATION_1.setApproximationList("aggregation", aggregation);

		ArrayList association = new ArrayList();
		association.add("association");
		APPROXIMATION_1.setApproximationList("association", association);

		ArrayList useRelationship = new ArrayList();
		useRelationship.add("useRelationship");
		APPROXIMATION_1.setApproximationList(
			"useRelationship",
			useRelationship);

		ArrayList inheritance = new ArrayList();
		inheritance.add("inheritance");
		// inheritance.add("pathInheritance");
		// inheritance.add("null");
		APPROXIMATION_1.setApproximationList("inheritance", inheritance);
		ArrayList creation = new ArrayList();
		creation.add("creation");
		APPROXIMATION_1.setApproximationList("creation", creation);
		ArrayList inheritance2 = new ArrayList();
		inheritance2.add("inheritance2");
		// inheritance2.add("inheritance");
		// inheritance2.add("pathInheritance");
		APPROXIMATION_1.setApproximationList("inheritance2", inheritance2);
	}
	private static final Approximation APPROXIMATION_2 = new Approximation();
	static {
		ArrayList containerComposition = new ArrayList();
		containerComposition.add("containerComposition");
		// containerComposition.add("containerAggregation");
		// containerComposition.add("composition");
		// containerComposition.add("aggregation");
		// containerComposition.add("useRelationship");
		// containerComposition.add("association");
		APPROXIMATION_2.setApproximationList(
			"containerComposition",
			containerComposition);
		ArrayList containerAggregation = new ArrayList();
		containerAggregation.add("containerAggregation");
		containerAggregation.add("aggregation");
		APPROXIMATION_2.setApproximationList(
			"containerAggregation",
			containerAggregation);
		ArrayList composition = new ArrayList();
		composition.add("composition");
		composition.add("aggregation");
		APPROXIMATION_2.setApproximationList("composition", composition);
		ArrayList aggregation = new ArrayList();
		aggregation.add("aggregation");
		APPROXIMATION_2.setApproximationList("aggregation", aggregation);
		ArrayList association = new ArrayList();
		association.add("association");
		APPROXIMATION_2.setApproximationList("association", association);
		ArrayList useRelationship = new ArrayList();
		useRelationship.add("useRelationship");
		APPROXIMATION_2.setApproximationList(
			"useRelationship",
			useRelationship);
		ArrayList inheritance = new ArrayList();
		inheritance.add("inheritance");
		inheritance.add("pathInheritance");
		// inheritance.add("null");
		APPROXIMATION_2.setApproximationList("inheritance", inheritance);
		ArrayList creation = new ArrayList();
		creation.add("creation");
		APPROXIMATION_2.setApproximationList("creation", creation);
		ArrayList inheritance2 = new ArrayList();
		inheritance2.add("inheritance");
		inheritance2.add("inheritance2");
		// inheritance2.add("pathInheritance");
		APPROXIMATION_2.setApproximationList("inheritance2", inheritance2);
	}
	private static final Approximation APPROXIMATION_3 = new Approximation();
	static {
		ArrayList containerComposition = new ArrayList();
		containerComposition.add("containerComposition");
		// containerComposition.add("containerAggregation");
		// containerComposition.add("composition");
		// containerComposition.add("aggregation");
		// containerComposition.add("useRelationship");
		// containerComposition.add("association");
		APPROXIMATION_3.setApproximationList(
			"containerComposition",
			containerComposition);
		ArrayList containerAggregation = new ArrayList();
		containerAggregation.add("containerAggregation");
		// containerAggregation.add("aggregation");
		APPROXIMATION_3.setApproximationList(
			"containerAggregation",
			containerAggregation);
		ArrayList composition = new ArrayList();
		composition.add("composition");
		// composition.add("aggregation");
		APPROXIMATION_3.setApproximationList("composition", composition);
		ArrayList aggregation = new ArrayList();
		aggregation.add("aggregation");
		APPROXIMATION_3.setApproximationList("aggregation", aggregation);
		ArrayList association = new ArrayList();
		association.add("association");
		APPROXIMATION_3.setApproximationList("association", association);
		ArrayList useRelationship = new ArrayList();
		useRelationship.add("useRelationship");
		APPROXIMATION_3.setApproximationList(
			"useRelationship",
			useRelationship);
		ArrayList inheritance = new ArrayList();
		inheritance.add("inheritance");
		// inheritance.add("pathInheritance");
		// inheritance.add("null");
		APPROXIMATION_3.setApproximationList("inheritance", inheritance);
		ArrayList creation = new ArrayList();
		creation.add("creation");
		APPROXIMATION_3.setApproximationList("creation", creation);
		ArrayList inheritance2 = new ArrayList();
		inheritance2.add("inheritance2");
		// inheritance2.add("inheritance");
		// inheritance2.add("pathInheritance");
		APPROXIMATION_3.setApproximationList("inheritance2", inheritance2);
	}
	private static final Approximation APPROXIMATION_4 = new Approximation();
	static {
		ArrayList containerComposition = new ArrayList();
		containerComposition.add("containerComposition");
		// containerComposition.add("containerAggregation");
		// containerComposition.add("composition");
		// containerComposition.add("aggregation");
		// containerComposition.add("useRelationship");
		// containerComposition.add("association");
		APPROXIMATION_4.setApproximationList(
			"containerComposition",
			containerComposition);
		ArrayList containerAggregation = new ArrayList();
		containerAggregation.add("containerAggregation");
		// containerAggregation.add("aggregation");
		APPROXIMATION_4.setApproximationList(
			"containerAggregation",
			containerAggregation);
		ArrayList composition = new ArrayList();
		composition.add("composition");
		// composition.add("aggregation");
		APPROXIMATION_4.setApproximationList("composition", composition);
		ArrayList aggregation = new ArrayList();
		aggregation.add("aggregation");
		APPROXIMATION_4.setApproximationList("aggregation", aggregation);
		ArrayList association = new ArrayList();
		association.add("association");
		APPROXIMATION_4.setApproximationList("association", association);
		ArrayList useRelationship = new ArrayList();
		useRelationship.add("useRelationship");
		APPROXIMATION_4.setApproximationList(
			"useRelationship",
			useRelationship);
		ArrayList inheritance = new ArrayList();
		inheritance.add("inheritance");
		// inheritance.add("pathInheritance");
		inheritance.add("null");
		APPROXIMATION_4.setApproximationList("inheritance", inheritance);
		ArrayList creation = new ArrayList();
		creation.add("creation");
		APPROXIMATION_4.setApproximationList("creation", creation);
		ArrayList inheritance2 = new ArrayList();
		inheritance2.add("inheritance2");
		inheritance2.add("inheritance");
		// inheritance2.add("pathInheritance");
		APPROXIMATION_4.setApproximationList("inheritance2", inheritance2);
	}
	private static final Approximation APPROXIMATION_1_AND_2 =
		new Approximation();
	static {
		ArrayList containerComposition = new ArrayList();
		containerComposition.add("containerComposition");
		containerComposition.add("containerAggregation");
		containerComposition.add("composition");
		containerComposition.add("aggregation");
		// containerComposition.add("association");
		// containerComposition.add("useRelationship");
		APPROXIMATION_1_AND_2.setApproximationList(
			"containerComposition",
			containerComposition);
		ArrayList containerAggregation = new ArrayList();
		containerAggregation.add("containerAggregation");
		// containerAggregation.add("aggregation");
		APPROXIMATION_1_AND_2.setApproximationList(
			"containerAggregation",
			containerAggregation);
		ArrayList composition = new ArrayList();
		composition.add("composition");
		// composition.add("aggregation");
		APPROXIMATION_1_AND_2.setApproximationList(
			"composition",
			composition);
		ArrayList aggregation = new ArrayList();
		aggregation.add("aggregation");
		APPROXIMATION_1_AND_2.setApproximationList(
			"aggregation",
			aggregation);
		ArrayList association = new ArrayList();
		association.add("association");
		APPROXIMATION_1_AND_2.setApproximationList(
			"association",
			association);
		ArrayList useRelationship = new ArrayList();
		useRelationship.add("useRelationship");
		APPROXIMATION_1_AND_2.setApproximationList(
			"useRelationship",
			useRelationship);
		ArrayList inheritance = new ArrayList();
		inheritance.add("inheritance");
		inheritance.add("pathInheritance");
		// inheritance.add("null");
		APPROXIMATION_1_AND_2.setApproximationList(
			"inheritance",
			inheritance);
		ArrayList creation = new ArrayList();
		creation.add("creation");
		APPROXIMATION_1_AND_2.setApproximationList("creation", creation);
		ArrayList inheritance2 = new ArrayList();
		inheritance2.add("inheritance2");
		inheritance2.add("inheritance");
		inheritance2.add("pathInheritance");
		APPROXIMATION_1_AND_2.setApproximationList(
			"inheritance2",
			inheritance2);
	}
	private static final Approximation APPROXIMATION_1_AND_3 =
		new Approximation();
	static {
		ArrayList containerComposition = new ArrayList();
		containerComposition.add("containerComposition");
		containerComposition.add("containerAggregation");
		containerComposition.add("composition");
		containerComposition.add("aggregation");
		// containerComposition.add("association");
		// containerComposition.add("useRelationship");
		APPROXIMATION_1_AND_3.setApproximationList(
			"containerComposition",
			containerComposition);
		ArrayList containerAggregation = new ArrayList();
		containerAggregation.add("containerAggregation");
		// containerAggregation.add("aggregation");
		APPROXIMATION_1_AND_3.setApproximationList(
			"containerAggregation",
			containerAggregation);
		ArrayList composition = new ArrayList();
		composition.add("composition");
		// composition.add("aggregation");
		APPROXIMATION_1_AND_3.setApproximationList(
			"composition",
			composition);
		ArrayList aggregation = new ArrayList();
		aggregation.add("aggregation");
		APPROXIMATION_1_AND_3.setApproximationList(
			"aggregation",
			aggregation);
		ArrayList association = new ArrayList();
		association.add("association");
		APPROXIMATION_1_AND_3.setApproximationList(
			"association",
			association);
		ArrayList useRelationship = new ArrayList();
		useRelationship.add("useRelationship");
		APPROXIMATION_1_AND_3.setApproximationList(
			"useRelationship",
			useRelationship);
		ArrayList inheritance = new ArrayList();
		inheritance.add("inheritance");
		// inheritance.add("pathInheritance");
		// inheritance.add("null");
		APPROXIMATION_1_AND_3.setApproximationList(
			"inheritance",
			inheritance);
		ArrayList creation = new ArrayList();
		creation.add("creation");
		APPROXIMATION_1_AND_3.setApproximationList("creation", creation);
		ArrayList inheritance2 = new ArrayList();
		inheritance2.add("inheritance2");
		inheritance2.add("inheritance");
		// inheritance2.add("pathInheritance");
		APPROXIMATION_1_AND_3.setApproximationList(
			"inheritance2",
			inheritance2);
	}
	private static final Approximation APPROXIMATION_1_AND_4 =
		new Approximation();
	static {
		ArrayList containerComposition = new ArrayList();
		containerComposition.add("containerComposition");
		containerComposition.add("containerAggregation");
		containerComposition.add("composition");
		containerComposition.add("aggregation");
		// containerComposition.add("association");
		// containerComposition.add("useRelationship");
		APPROXIMATION_1_AND_4.setApproximationList(
			"containerComposition",
			containerComposition);
		ArrayList containerAggregation = new ArrayList();
		containerAggregation.add("containerAggregation");
		// containerAggregation.add("aggregation");
		APPROXIMATION_1_AND_4.setApproximationList(
			"containerAggregation",
			containerAggregation);
		ArrayList composition = new ArrayList();
		composition.add("composition");
		// composition.add("aggregation");
		APPROXIMATION_1_AND_4.setApproximationList(
			"composition",
			composition);
		ArrayList aggregation = new ArrayList();
		aggregation.add("aggregation");
		APPROXIMATION_1_AND_4.setApproximationList(
			"aggregation",
			aggregation);
		ArrayList association = new ArrayList();
		association.add("association");
		APPROXIMATION_1_AND_4.setApproximationList(
			"association",
			association);
		ArrayList useRelationship = new ArrayList();
		useRelationship.add("useRelationship");
		APPROXIMATION_1_AND_4.setApproximationList(
			"useRelationship",
			useRelationship);
		ArrayList inheritance = new ArrayList();
		inheritance.add("inheritance");
		// inheritance.add("pathInheritance");
		inheritance.add("null");
		APPROXIMATION_1_AND_4.setApproximationList(
			"inheritance",
			inheritance);
		ArrayList creation = new ArrayList();
		creation.add("creation");
		APPROXIMATION_1_AND_4.setApproximationList("creation", creation);
		ArrayList inheritance2 = new ArrayList();
		inheritance2.add("inheritance2");
		inheritance2.add("inheritance");
		// inheritance2.add("pathInheritance");
		APPROXIMATION_1_AND_4.setApproximationList(
			"inheritance2",
			inheritance2);
	}
	private static final Approximation APPROXIMATION_2_AND_3 =
		new Approximation();
	static {
		ArrayList containerComposition = new ArrayList();
		containerComposition.add("containerComposition");
		// containerComposition.add("containerAggregation");
		// containerComposition.add("composition");
		// containerComposition.add("aggregation");
		// containerComposition.add("useRelationship");
		// containerComposition.add("association");
		APPROXIMATION_2_AND_3.setApproximationList(
			"containerComposition",
			containerComposition);
		ArrayList containerAggregation = new ArrayList();
		containerAggregation.add("containerAggregation");
		// containerAggregation.add("aggregation");
		APPROXIMATION_2_AND_3.setApproximationList(
			"containerAggregation",
			containerAggregation);
		ArrayList composition = new ArrayList();
		composition.add("composition");
		// composition.add("aggregation");
		APPROXIMATION_2_AND_3.setApproximationList(
			"composition",
			composition);
		ArrayList aggregation = new ArrayList();
		aggregation.add("aggregation");
		APPROXIMATION_2_AND_3.setApproximationList(
			"aggregation",
			aggregation);
		ArrayList association = new ArrayList();
		association.add("association");
		APPROXIMATION_2_AND_3.setApproximationList(
			"association",
			association);
		ArrayList useRelationship = new ArrayList();
		useRelationship.add("useRelationship");
		APPROXIMATION_2_AND_3.setApproximationList(
			"useRelationship",
			useRelationship);
		ArrayList inheritance = new ArrayList();
		inheritance.add("inheritance");
		inheritance.add("pathInheritance");
		// inheritance.add("null");
		APPROXIMATION_2_AND_3.setApproximationList(
			"inheritance",
			inheritance);
		ArrayList creation = new ArrayList();
		creation.add("creation");
		APPROXIMATION_2_AND_3.setApproximationList("creation", creation);
		ArrayList inheritance2 = new ArrayList();
		inheritance2.add("inheritance2");
		inheritance2.add("inheritance");
		inheritance2.add("pathInheritance");
		APPROXIMATION_2_AND_3.setApproximationList(
			"inheritance2",
			inheritance2);
	}
	private static final Approximation APPROXIMATION_2_AND_4 =
		new Approximation();
	static {
		ArrayList containerComposition = new ArrayList();
		containerComposition.add("containerComposition");
		// containerComposition.add("containerAggregation");
		// containerComposition.add("composition");
		// containerComposition.add("aggregation");
		// containerComposition.add("useRelationship");
		// containerComposition.add("association");
		APPROXIMATION_2_AND_4.setApproximationList(
			"containerComposition",
			containerComposition);
		ArrayList containerAggregation = new ArrayList();
		containerAggregation.add("containerAggregation");
		// containerAggregation.add("aggregation");
		APPROXIMATION_2_AND_4.setApproximationList(
			"containerAggregation",
			containerAggregation);
		ArrayList composition = new ArrayList();
		composition.add("composition");
		// composition.add("aggregation");
		APPROXIMATION_2_AND_4.setApproximationList(
			"composition",
			composition);
		ArrayList aggregation = new ArrayList();
		aggregation.add("aggregation");
		APPROXIMATION_2_AND_4.setApproximationList(
			"aggregation",
			aggregation);
		ArrayList association = new ArrayList();
		association.add("association");
		APPROXIMATION_2_AND_4.setApproximationList(
			"association",
			association);
		ArrayList useRelationship = new ArrayList();
		useRelationship.add("useRelationship");
		APPROXIMATION_2_AND_4.setApproximationList(
			"useRelationship",
			useRelationship);
		ArrayList inheritance = new ArrayList();
		inheritance.add("inheritance");
		inheritance.add("pathInheritance");
		inheritance.add("null");
		APPROXIMATION_2_AND_4.setApproximationList(
			"inheritance",
			inheritance);
		ArrayList creation = new ArrayList();
		creation.add("creation");
		APPROXIMATION_2_AND_4.setApproximationList("creation", creation);
		ArrayList inheritance2 = new ArrayList();
		inheritance2.add("inheritance2");
		inheritance2.add("inheritance");
		inheritance2.add("pathInheritance");
		APPROXIMATION_2_AND_4.setApproximationList(
			"inheritance2",
			inheritance2);
	}
	private static final Approximation APPROXIMATION_3_AND_4 =
		new Approximation();
	static {
		ArrayList containerComposition = new ArrayList();
		containerComposition.add("containerComposition");
		// containerComposition.add("containerAggregation");
		// containerComposition.add("composition");
		// containerComposition.add("aggregation");
		// containerComposition.add("useRelationship");
		// containerComposition.add("association");
		APPROXIMATION_3_AND_4.setApproximationList(
			"containerComposition",
			containerComposition);
		ArrayList containerAggregation = new ArrayList();
		containerAggregation.add("containerAggregation");
		// containerAggregation.add("aggregation");
		APPROXIMATION_3_AND_4.setApproximationList(
			"containerAggregation",
			containerAggregation);
		ArrayList composition = new ArrayList();
		composition.add("composition");
		// composition.add("aggregation");
		APPROXIMATION_3_AND_4.setApproximationList(
			"composition",
			composition);
		ArrayList aggregation = new ArrayList();
		aggregation.add("aggregation");
		APPROXIMATION_3_AND_4.setApproximationList(
			"aggregation",
			aggregation);
		ArrayList association = new ArrayList();
		association.add("association");
		APPROXIMATION_3_AND_4.setApproximationList(
			"association",
			association);
		ArrayList useRelationship = new ArrayList();
		useRelationship.add("useRelationship");
		APPROXIMATION_3_AND_4.setApproximationList(
			"useRelationship",
			useRelationship);
		ArrayList inheritance = new ArrayList();
		inheritance.add("inheritance");
		// inheritance.add("pathInheritance");
		inheritance.add("null");
		APPROXIMATION_3_AND_4.setApproximationList(
			"inheritance",
			inheritance);
		ArrayList creation = new ArrayList();
		creation.add("creation");
		APPROXIMATION_3_AND_4.setApproximationList("creation", creation);
		ArrayList inheritance2 = new ArrayList();
		inheritance2.add("inheritance2");
		inheritance2.add("inheritance");
		// inheritance2.add("pathInheritance");
		APPROXIMATION_3_AND_4.setApproximationList(
			"inheritance2",
			inheritance2);
	}
	private static final Approximation APPROXIMATION_1_AND_2_AND_3 =
		new Approximation();
	static {
		ArrayList containerComposition = new ArrayList();
		containerComposition.add("containerComposition");
		containerComposition.add("containerAggregation");
		containerComposition.add("composition");
		containerComposition.add("aggregation");
		// containerComposition.add("association");
		// containerComposition.add("useRelationship");
		APPROXIMATION_1_AND_2_AND_3.setApproximationList(
			"containerComposition",
			containerComposition);

		ArrayList containerAggregation = new ArrayList();
		containerAggregation.add("containerAggregation");
		// containerAggregation.add("aggregation");
		APPROXIMATION_1_AND_2_AND_3.setApproximationList(
			"containerAggregation",
			containerAggregation);

		ArrayList composition = new ArrayList();
		composition.add("composition");
		// composition.add("aggregation");
		APPROXIMATION_1_AND_2_AND_3.setApproximationList(
			"composition",
			composition);

		ArrayList aggregation = new ArrayList();
		aggregation.add("aggregation");
		APPROXIMATION_1_AND_2_AND_3.setApproximationList(
			"aggregation",
			aggregation);

		ArrayList association = new ArrayList();
		association.add("association");
		APPROXIMATION_1_AND_2_AND_3.setApproximationList(
			"association",
			association);

		ArrayList useRelationship = new ArrayList();
		useRelationship.add("useRelationship");
		APPROXIMATION_1_AND_2_AND_3.setApproximationList(
			"useRelationship",
			useRelationship);

		ArrayList inheritance = new ArrayList();
		inheritance.add("inheritance");
		inheritance.add("pathInheritance");
		// inheritance.add("null");
		APPROXIMATION_1_AND_2_AND_3.setApproximationList(
			"inheritance",
			inheritance);

		ArrayList creation = new ArrayList();
		creation.add("creation");
		APPROXIMATION_1_AND_2_AND_3.setApproximationList(
			"creation",
			creation);

		ArrayList inheritance2 = new ArrayList();
		inheritance2.add("inheritance2");
		// inheritance2.add("inheritance");
		// inheritance2.add("pathInheritance");
		APPROXIMATION_1_AND_2_AND_3.setApproximationList(
			"inheritance2",
			inheritance2);
	}
	private static final Approximation APPROXIMATION_ALL =
		new Approximation();
	static {
		ArrayList containerComposition = new ArrayList();
		containerComposition.add("containerComposition");
		containerComposition.add("containerAggregation");
		containerComposition.add("composition");
		containerComposition.add("aggregation");
		// containerComposition.add("association");
		// containerComposition.add("useRelationship");
		APPROXIMATION_ALL.setApproximationList(
			"containerComposition",
			containerComposition);
		ArrayList containerAggregation = new ArrayList();
		containerAggregation.add("containerAggregation");
		containerAggregation.add("aggregation");
		APPROXIMATION_ALL.setApproximationList(
			"containerAggregation",
			containerAggregation);
		ArrayList composition = new ArrayList();
		composition.add("composition");
		composition.add("aggregation");
		APPROXIMATION_ALL.setApproximationList("composition", composition);
		ArrayList aggregation = new ArrayList();
		aggregation.add("aggregation");
		APPROXIMATION_ALL.setApproximationList("aggregation", aggregation);
		ArrayList association = new ArrayList();
		association.add("association");
		APPROXIMATION_ALL.setApproximationList("association", association);
		ArrayList useRelationship = new ArrayList();
		useRelationship.add("useRelationship");
		APPROXIMATION_ALL.setApproximationList(
			"useRelationship",
			useRelationship);
		ArrayList inheritance = new ArrayList();
		inheritance.add("inheritance");
		inheritance.add("pathInheritance");
		inheritance.add("null");
		APPROXIMATION_ALL.setApproximationList("inheritance", inheritance);
		ArrayList creation = new ArrayList();
		creation.add("creation");
		APPROXIMATION_ALL.setApproximationList("creation", creation);
		ArrayList inheritance2 = new ArrayList();
		inheritance2.add("inheritance2");
		inheritance2.add("inheritance");
		inheritance2.add("pathInheritance");
		APPROXIMATION_ALL.setApproximationList("inheritance2", inheritance2);
	}
	public static final Approximation[] APPROXIMATIONS =
		new Approximation[] {
			APPROXIMATION_NONE,
			APPROXIMATION_1,
			APPROXIMATION_2,
		// APPROXIMATION_3,
		// APPROXIMATION_4,
		APPROXIMATION_1_AND_2,
		// APPROXIMATION_1_AND_3,
		// APPROXIMATION_1_AND_4,
		// APPROXIMATION_2_AND_3,
		// APPROXIMATION_2_AND_4,
		// APPROXIMATION_3_AND_4,
		// APPROXIMATION_1_AND_2_AND_3,
		// APPROXIMATION_ALL 
	};
	public static final String[] APPROXIMATIONS_NAMES =
		new String[] { "NONE", "1", "2",
		// "3",
		// "4",
		"1 and 2",
		//	"1 and 3",
		//	"1 and 4",
		//	"2 and 3",
		//	"2 and 4",
		//	"3 and 4",
		//	"1 and 2 and 3",
		//	"ALL" 
	};
}
