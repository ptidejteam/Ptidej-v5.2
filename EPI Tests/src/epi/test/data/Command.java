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
public final class Command implements BitVectorPattern {
	private static Command UniqueInstance;
	public static Command getInstance() {
		if (Command.UniqueInstance == null) {
			Command.UniqueInstance = new Command();
		}
		return Command.UniqueInstance;
	}

	private static final String COMMAND_NAME = "Command";
	private static final String COMMAND_STRING_NONE =
		"Invoker aggregation EPI_Abstract_Command inheritance ConcreteCommand association Receiver ignorance Invoker ignorance ConcreteCommand dummyRelationship Receiver ignorance EPI_Abstract_Command dummyRelationship Receiver ignorance ConcreteCommand";
	private static final String COMMAND_STRING_1 =
		"Invoker aggregation EPI_Abstract_Command inheritance ConcreteCommand association Receiver ignorance Invoker ignorance ConcreteCommand dummyRelationship Receiver ignorance EPI_Abstract_Command dummyRelationship Receiver ignorance ConcreteCommand";
	private static final String COMMAND_STRING_2 =
		"Invoker aggregation EPI_Abstract_Command inheritance ConcreteCommand association Receiver ignorance Invoker ignorance ConcreteCommand dummyRelationship Receiver ignorance EPI_Abstract_Command dummyRelationship Receiver ignorance ConcreteCommand";
	private static final String COMMAND_STRING_3 =
		"Invoker aggregation Command inheritance ConcreteCommand association Receiver ignorance Invoker ignorance ConcreteCommand dummyRelationship Receiver ignorance Command dummyRelationship Receiver ignorance ConcreteCommand";
	private static final String COMMAND_STRING_4 = "";
	// Same as NONE because all roles must be played.
	private static final String COMMAND_STRING_1_AND_2 =
		"Invoker aggregation EPI_Abstract_Command inheritance ConcreteCommand association Receiver ignorance Invoker ignorance ConcreteCommand dummyRelationship Receiver ignorance EPI_Abstract_Command dummyRelationship Receiver ignorance ConcreteCommand";
	private static final String COMMAND_STRING_1_AND_3 =
		"Invoker aggregation Command inheritance ConcreteCommand association Receiver ignorance Invoker ignorance ConcreteCommand dummyRelationship Receiver ignorance Command dummyRelationship Receiver ignorance ConcreteCommand";
	private static final String COMMAND_STRING_1_AND_4 = ""; // Same as 1
	private static final String COMMAND_STRING_2_AND_3 =
		"Invoker aggregation Command inheritance ConcreteCommand association Receiver ignorance Invoker ignorance ConcreteCommand dummyRelationship Receiver ignorance Command dummyRelationship Receiver ignorance ConcreteCommand";
	private static final String COMMAND_STRING_2_AND_4 = "";
	// Same as 2 and 4
	private static final String COMMAND_STRING_3_AND_4 = "";
	// Same as 3 and 4
	private static final String COMMAND_STRING_1_AND_2_AND_3 =
		"Invoker aggregation Command inheritance ConcreteCommand association Receiver ignorance Invoker ignorance ConcreteCommand dummyRelationship Receiver ignorance Command dummyRelationship Receiver ignorance ConcreteCommand";
	private static final String COMMAND_STRING_ALL = "";
	// Same as 1 and 2 and 3
	private static final String[] COMMAND_STRINGS =
		new String[] {
			COMMAND_STRING_NONE,
			COMMAND_STRING_1,
			COMMAND_STRING_2,
			COMMAND_STRING_3,
			COMMAND_STRING_4,
			COMMAND_STRING_1_AND_2,
			COMMAND_STRING_1_AND_3,
			COMMAND_STRING_1_AND_4,
			COMMAND_STRING_2_AND_3,
			COMMAND_STRING_2_AND_4,
			COMMAND_STRING_3_AND_4,
			COMMAND_STRING_1_AND_2_AND_3,
			COMMAND_STRING_ALL };

	public String getName(){
		return COMMAND_NAME;
	}
	public String[] getStrings(){
		return COMMAND_STRINGS;
	}
}
