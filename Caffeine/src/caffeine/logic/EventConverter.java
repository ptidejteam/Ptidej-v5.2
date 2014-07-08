/*
 * (c) Copyright 2001-2003 Yann-Gaël Guéhéneuc,
 * École des Mines de Nantes and Object Technology International, Inc.
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
package caffeine.logic;

import java.util.HashMap;
import java.util.Map;

import caffeine.Constants;

/**
 * version	1.0
 * @author 	Yann-Gaël Guéhéneuc
 */
public final class EventConverter {
	//	Constants
	//		.class
	//		.getField(
	//			atomName
	//				.substring(0, 1)
	//				.toUpperCase()
	//				+ atomName.substring(1))
	//		.getInt(null)
	private static final Map MAP = new HashMap();
	static {
		EventConverter.MAP.put(
			"generateClassLoadEvent",
			new Integer(Constants.GENERATE_CLASS_LOAD_EVENT));
		EventConverter.MAP.put(
			"generateClassUnloadEvent",
			new Integer(Constants.GENERATE_CLASS_UNLOAD_EVENT));
		EventConverter.MAP.put(
			"generateCollectionEvent",
			new Integer(Constants.GENERATE_COLLECTION_EVENT));
		EventConverter.MAP.put(
			"generateConstructorEntryEvent",
			new Integer(Constants.GENERATE_CONSTRUCTOR_ENTRY_EVENT));
		EventConverter.MAP.put(
			"generateConstructorExitEvent",
			new Integer(Constants.GENERATE_CONSTRUCTOR_EXIT_EVENT));
		EventConverter.MAP.put(
			"generateFieldAccessEvent",
			new Integer(Constants.GENERATE_FIELD_ACCESS_EVENT));
		EventConverter.MAP.put(
			"generateFieldModificationEvent",
			new Integer(Constants.GENERATE_FIELD_MODIFICATION_EVENT));
		EventConverter.MAP.put(
			"generateFinalizerEntryEvent",
			new Integer(Constants.GENERATE_FINALIZER_ENTRY_EVENT));
		EventConverter.MAP.put(
			"generateFinalizerExitEvent",
			new Integer(Constants.GENERATE_FINALIZER_EXIT_EVENT));
		EventConverter.MAP.put(
			"generateMethodEntryEvent",
			new Integer(Constants.GENERATE_METHOD_ENTRY_EVENT));
		EventConverter.MAP.put(
			"generateMethodExitEvent",
			new Integer(Constants.GENERATE_METHOD_EXIT_EVENT));
		EventConverter.MAP.put(
			"generateMethodReturnedValueEvent",
			new Integer(Constants.GENERATE_METHOD_RETURNED_VALUE_EVENT));
		EventConverter.MAP.put(
			"generateProgramEndEvent",
			new Integer(Constants.GENERATE_PROGRAM_END_EVENT));
	}
	public static int convertRequiredEvent(final String prologEventName) {
		try {
			final int value =
				((Integer) EventConverter.MAP.get(prologEventName))
					.intValue();
			return value;
		}
		catch (final Exception e) {
			System.err.print("Warning! Unknown required event: ");
			System.err.println(prologEventName);
			return 0;
		}
	}
}
