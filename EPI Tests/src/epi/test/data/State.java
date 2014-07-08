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
public final class State implements BitVectorPattern {
	private static State UniqueInstance;
	public static State getInstance() {
		if (State.UniqueInstance == null) {
			State.UniqueInstance = new State();
		}
		return State.UniqueInstance;
	}

	private static final String STATE_NAME = "BridgeStateStrategy";
	private static final String STATE_STRING_NONE =
		"Context aggregation EPI_Abstract_State inheritance ConcreteStateA dummyRelationship EPI_Abstract_State inheritance ConcreteStateB dummyRelationship EPI_Abstract_State ignorance Context dummyRelationship ConcreteStateA ignorance Context dummyRelationship ConcreteStateB ignorance Context";
	private static final String STATE_STRING_1 =
		"Context aggregation EPI_Abstract_State inheritance ConcreteStateA dummyRelationship EPI_Abstract_State inheritance ConcreteStateB dummyRelationship EPI_Abstract_State ignorance Context dummyRelationship ConcreteStateA ignorance Context dummyRelationship ConcreteStateB ignorance Context";
	private static final String STATE_STRING_2 =
		"Context aggregation EPI_Abstract_State inheritance ConcreteStateA dummyRelationship EPI_Abstract_State inheritance ConcreteStateB dummyRelationship EPI_Abstract_State ignorance Context dummyRelationship ConcreteStateA ignorance Context dummyRelationship ConcreteStateB ignorance Context";
	private static final String STATE_STRING_3 =
		"Context aggregation State inheritance ConcreteStateA dummyRelationship State inheritance ConcreteStateB dummyRelationship State ignorance Context dummyRelationship ConcreteStateA ignorance Context dummyRelationship ConcreteStateB ignorance Context";
	private static final String STATE_STRING_4 = "";
	// Same as NONE because all roles must be played.
	private static final String STATE_STRING_1_AND_2 =
		"Context aggregation EPI_Abstract_State inheritance ConcreteStateA dummyRelationship EPI_Abstract_State inheritance ConcreteStateB dummyRelationship EPI_Abstract_State ignorance Context dummyRelationship ConcreteStateA ignorance Context dummyRelationship ConcreteStateB ignorance Context";
	private static final String STATE_STRING_1_AND_3 =
		"Context aggregation State inheritance ConcreteStateA dummyRelationship State inheritance ConcreteStateB dummyRelationship State ignorance Context dummyRelationship ConcreteStateA ignorance Context dummyRelationship ConcreteStateB ignorance Context";
	private static final String STATE_STRING_1_AND_4 = ""; // Same as 1
	private static final String STATE_STRING_2_AND_3 =
		"Context aggregation State inheritance ConcreteStateA dummyRelationship State inheritance ConcreteStateB dummyRelationship State ignorance Context dummyRelationship ConcreteStateA ignorance Context dummyRelationship ConcreteStateB ignorance Context";
	private static final String STATE_STRING_2_AND_4 = "";
	// Same as 2 and 4
	private static final String STATE_STRING_3_AND_4 = "";
	// Same as 3 and 4
	private static final String STATE_STRING_1_AND_2_AND_3 =
		"Context aggregation State inheritance ConcreteStateA dummyRelationship State inheritance ConcreteStateB dummyRelationship State ignorance Context dummyRelationship ConcreteStateA ignorance Context dummyRelationship ConcreteStateB ignorance Context";
	private static final String STATE_STRING_ALL = "";
	// Same as 1 and 2 and 3
	private static final String[] STATE_STRINGS =
		new String[] {
			STATE_STRING_NONE,
			STATE_STRING_1,
			STATE_STRING_2,
			STATE_STRING_3,
			STATE_STRING_4,
			STATE_STRING_1_AND_2,
			STATE_STRING_1_AND_3,
			STATE_STRING_1_AND_4,
			STATE_STRING_2_AND_3,
			STATE_STRING_2_AND_4,
			STATE_STRING_3_AND_4,
			STATE_STRING_1_AND_2_AND_3,
			STATE_STRING_ALL };

	public String getName(){
		return STATE_NAME;
	}
	public String[] getStrings(){
		return STATE_STRINGS;
	}
}
