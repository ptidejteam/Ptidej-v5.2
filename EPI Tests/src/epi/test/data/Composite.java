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

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  08/01/15
 */
public final class Composite implements BitVectorPattern {
	private static Composite UniqueInstance;
	public static Composite getInstance() {
		if (Composite.UniqueInstance == null) {
			Composite.UniqueInstance = new Composite();
		}
		return Composite.UniqueInstance;
	}

	private static final String COMPOSITE_NAME = "Composite";
	private static final String COMPOSITE_STRING_NONE =
		"Composite containerComposition EPI_Abstract_Component inheritance Composite dummyRelationship EPI_Abstract_Component inheritance Leaf";
	private static final String COMPOSITE_STRING_1 =
		"Composite containerComposition EPI_Abstract_Component inheritance Composite dummyRelationship EPI_Abstract_Component inheritance Leaf";
	private static final String COMPOSITE_STRING_2 =
		"Composite containerComposition EPI_Abstract_Component inheritance Composite dummyRelationship EPI_Abstract_Component inheritance Leaf";
	private static final String COMPOSITE_STRING_3 =
		"Composite containerComposition Component inheritance Composite dummyRelationship Component inheritance Leaf";
	private static final String COMPOSITE_STRING_4 =
		"Composite containerComposition EPI_Abstract_Component inheritance2 Composite dummyRelationship EPI_Abstract_Component";
	private static final String COMPOSITE_STRING_1_AND_2 =
		"Composite containerComposition EPI_Abstract_Component inheritance Composite dummyRelationship EPI_Abstract_Component inheritance Leaf";
	private static final String COMPOSITE_STRING_1_AND_3 =
		"Composite containerComposition Component inheritance Composite dummyRelationship Component inheritance Leaf";
	private static final String COMPOSITE_STRING_1_AND_4 =
		"Composite containerComposition EPI_Abstract_Component inheritance2 Composite dummyRelationship EPI_Abstract_Component";
	private static final String COMPOSITE_STRING_2_AND_3 =
		"Composite containerComposition Component inheritance Composite dummyRelationship Component inheritance Leaf";
	private static final String COMPOSITE_STRING_2_AND_4 =
		"Composite containerComposition EPI_Abstract_Component inheritance2 Composite dummyRelationship EPI_Abstract_Component";
	private static final String COMPOSITE_STRING_3_AND_4 =
		"Composite containerComposition Component inheritance2 Composite dummyRelationship Component";
	private static final String COMPOSITE_STRING_1_AND_2_AND_3 =
		"Composite containerComposition Component inheritance Composite dummyRelationship Component inheritance Leaf";
	private static final String COMPOSITE_STRING_ALL =
		"Composite containerComposition Component inheritance2 Composite dummyRelationship Component";
	private static final String[] COMPOSITE_STRINGS =
		new String[] {
			COMPOSITE_STRING_NONE,
			COMPOSITE_STRING_1,
			COMPOSITE_STRING_2,
			COMPOSITE_STRING_3,
			COMPOSITE_STRING_4,
			COMPOSITE_STRING_1_AND_2,
			COMPOSITE_STRING_1_AND_3,
			COMPOSITE_STRING_1_AND_4,
			COMPOSITE_STRING_2_AND_3,
			COMPOSITE_STRING_2_AND_4,
			COMPOSITE_STRING_3_AND_4,
			COMPOSITE_STRING_1_AND_2_AND_3,
			COMPOSITE_STRING_ALL };

	public String getName(){
		return COMPOSITE_NAME;
	}
	public String[] getStrings(){
		return COMPOSITE_STRINGS;
	}
}
