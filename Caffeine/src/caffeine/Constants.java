/*
 * (c) Copyright 2002-2003 Yann-Gaël Guéhéneuc,
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
package caffeine;

public interface Constants {
	boolean DEBUG = false;
	boolean EXCEPTION_DEBUG = false;

	int GENERATE_CLASS_LOAD_EVENT = 1;
	int GENERATE_CLASS_UNLOAD_EVENT = 2;
	int GENERATE_CONSTRUCTOR_ENTRY_EVENT = 4;
	int GENERATE_CONSTRUCTOR_EXIT_EVENT = 8;
	int GENERATE_COLLECTION_EVENT = 16;
	int GENERATE_FIELD_ACCESS_EVENT = 32;
	int GENERATE_FIELD_MODIFICATION_EVENT = 64;
	int GENERATE_FINALIZER_ENTRY_EVENT = 128;
	int GENERATE_FINALIZER_EXIT_EVENT = 256;
	int GENERATE_METHOD_ENTRY_EVENT = 512;
	int GENERATE_METHOD_EXIT_EVENT = 1024;
	int GENERATE_METHOD_RETURNED_VALUE_EVENT = 2048;
	int GENERATE_PROGRAM_END_EVENT = 4096;
	int ANALYSIS_CONTROLLED_EVENTS = 0;

	String CLASS_LOAD_EVENT = "classLoad0";
	String CLASS_UNLOAD_EVENT = "classUnload0";
	String CONSTRUCTOR_ENTRY_EVENT = "constructorEntry0";
	String CONSTRUCTOR_EXIT_EVENT = "constructorExit0";
	String FIELD_ACCESS_EVENT = "fieldAccess0";
	String FIELD_MODIFICATION_EVENT = "fieldModification0";
	String FINALIZER_ENTRY_EVENT = "finalizerEntry0";
	String FINALIZER_EXIT_EVENT = "finalizerExit0";
	String METHOD_ENTRY_EVENT = "methodEntry0";
	String METHOD_EXIT_EVENT = "methodExit0";
	String PROGRAM_END_EVENT = "programEnd0";

	int NUMBER_OF_GC_CALLS = 10;
	int NUMBER_OF_OBJECTS = 100000;
	int VACUUM_CLEANER_WAKE_UP_TIME = 1000;
	int VACUUM_CLEANER_LATENT_PERIOD = 3;

	// This field is duplicated in the Loader class.
	String[] SYSTEM_CLASS_NAMES =
		new String[] {
			"caffeine.remote.CaffeineObject",
			"caffeine.remote.Loader",
			"caffeine.remote.VacuumCleaner",
			"caffeine.remote.WrapperMain",
			"com.ibm.toad.*",
			"javassist.*",
			"JIP.*",
			"com.sun.*",
			"java.applet.*",
			"java.awt.*",
			"java.beans.*",
			"java.io.*",
			"java.nio.*",
			"java.lang.*",
			"java.lang.ref.*",
			"java.lang.reflect.*",
			"java.math.*",
			"java.net.*",
			"java.rmi.*",
			"java.security.*",
			"java.sql.*",
			"java.text.*",
			"java.util.*",
			"javax.*",
			"org.omg.*",
			"sun.*" };

	String[] USER_SYSTEM_THREAD_NAMES = new String[] { "main", "AWT-Windows" };
	int NUMBER_OF_USER_SYSTEM_THREAD_NAMES = 2;

	String CAFFEINE_UNIQUE_EXIT_METHOD_NAME = "caffeineUniqueExit";
	String CAFFEINE_RETURNED_VALUE_WRAPPER_METHOD_NAME =
		"caffeineReturnedValueWrapper";
	String CAFFEINE_RETURNED_VALUE_WRAPPER_CLASS_NAME =
		"caffeine.remote.CaffeineWrapper";
}