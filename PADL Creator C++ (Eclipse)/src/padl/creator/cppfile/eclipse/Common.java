/* (c) Copyright 2008 and following years, Yann-Gaël Guéhéneuc,
 * École Polytechnique de Montréal.
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
package padl.creator.cppfile.eclipse;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import padl.cpp.kernel.event.CPPEventGenerator;
import padl.event.IModelListener;
import padl.kernel.ICodeLevelModel;
import util.io.ProxyConsole;
import util.io.ProxyDisk;
import com.thoughtworks.xstream.XStream;

public final class Common {
	public static final String ARGUMENT_DIRECTORY_PTIDEJ_WORKSPACE =
		"-directoryPtidej";
	public static final String ARGUMENT_DIRECTORY_TARGET_CPP_FILES =
		"-directoryCPPFiles";
	public static final String ARGUMENT_OSGi_RUNNING_IN_REMOTE_JVM =
		"-isRunningInRemoteJVM";
	public static final String EQUINOX_CONFIGURATION_CONFIG_FILE = "config.ini";
	public static final String EQUINOX_CONFIGURATION_DATA =
		"PADL Creator C++ (Eclipse) Helper/Configuration Area/";
	public static final String EQUINOX_CONFIGURATION_TEMPLATE_CONFIG_FILE =
		"Paths-placeholder config.ini";
	// The Launcher name is made of two part: the name of the plug-in
	// see in the Overview tab when opening the plugin.xml file and
	// the ID of the extension to "org.eclipse.core.runtime.applications"
	// see in the plugin.xml tab when opening the plugin.xml file.
	public static final String EQUINOX_LAUNCHER_NAME =
		"PADL_Creator_Cpp_Eclipse.Launcher";
	public static final String EQUINOX_RUNTIME_WORKSPACE =
		"PADL Creator C++ (Eclipse) Helper/Runtime Workspace/";
	public static final IProgressMonitor NULL_PROGRESS_MONITOR =
		new NullProgressMonitor();
	public static final String PLACEHOLDER_TAG = "@PLACEHOLDER@";
	public static final String SERIALISED_MODEL_FILENAME =
		"SerialisedCodeLevelModel.xml";

	public static void prepareForCodeLevelModel() {
		ProxyConsole.getInstance().debugOutput().print(Common.class.getName());
		ProxyConsole
			.getInstance()
			.debugOutput()
			.println(" is erasing any former serialised model");
		final File file =
			new File(ProxyDisk
				.getInstance()
				.directoryTempFile()
				.getAbsoluteFile(), Common.SERIALISED_MODEL_FILENAME);
		file.delete();

		ProxyConsole.getInstance().normalOutput().print(Common.class.getName());
		ProxyConsole.getInstance().normalOutput().print(" will use \"");
		ProxyConsole.getInstance().normalOutput().print(file.getAbsolutePath());
		ProxyConsole.getInstance().normalOutput().println('"');
	}
	public static void readCodeLevelModel(final ICodeLevelModel aCodeLevelModel) {
		// Yann 2013/05/23: Listeners!
		// I save the desired listener before "erasing" the model...
		final List<IModelListener> listeners = new ArrayList<IModelListener>();
		final Iterator<?> iterator =
			aCodeLevelModel.getIteratorOnModelListeners();
		while (iterator.hasNext()) {
			final IModelListener listener = (IModelListener) iterator.next();
			listeners.add(listener);
		}

		final XStream xstream = new XStream();
		xstream.setMode(XStream.ID_REFERENCES);
		xstream.fromXML(
			ProxyDisk.getInstance().fileTempInput(
				Common.SERIALISED_MODEL_FILENAME),
			aCodeLevelModel);

		// Yann 2013/05/23: Statistics!
		// Because I recreate the model from an XML file,
		// no event is generated during its "creation" so
		// any listener would not have the chance to hear
		// from it... Therefore, I visit each node and
		// pretend that it is being created ;-)
		aCodeLevelModel.addModelListeners(listeners);
		aCodeLevelModel.walk(CPPEventGenerator.getInstance());

		//	DB4OSerialiser.getInstance().deserialise(
		//		Utils.TEMPORARY_OUTPUT_FILE + ".db4o");
	}
	public static void writeCodeLevelModel(final ICodeLevelModel aCodeLevelModel) {
		ProxyConsole.getInstance().debugOutput().print(Common.class.getName());
		ProxyConsole
			.getInstance()
			.debugOutput()
			.println(" is serialising the model");
		final XStream xstream = new XStream();
		xstream.setMode(XStream.ID_REFERENCES);
		xstream.toXML(
			aCodeLevelModel,
			ProxyDisk.getInstance().fileTempOutput(
				Common.SERIALISED_MODEL_FILENAME));
		ProxyConsole.getInstance().debugOutput().print(Common.class.getName());
		ProxyConsole
			.getInstance()
			.debugOutput()
			.print(" serialised the mode in \"");
		ProxyConsole
			.getInstance()
			.debugOutput()
			.print(
				ProxyDisk.getInstance().directoryTempFile().getAbsolutePath());
		ProxyConsole.getInstance().debugOutput().println('\"');

		//	DB4OSerialiser.getInstance().serialise(
		//		aCodeLevelModel,
		//		Common.SERIALISED_MODEL_FILENAME + ".db4o");
	}
	private Common() {
	}
}
