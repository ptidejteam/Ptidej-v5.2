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
package padl.creator.cppfile.eclipse.misc;

import java.io.IOException;
import util.io.OutputMonitor;
import util.io.ProxyConsole;

public class StarterManual {
	public static void main(final String[] args) {
		// http://stackoverflow.com/questions/2502518/eclipse-export-running-configuration
		//	final String commandLine =
		//		"\"C:/Program Files (x86)/Java/jdk1.7.0_21/bin/javaw.exe\" -Declipse.pde.launch=true -Dfile.encoding=Cp1252 -classpath \"C:/Program Files (x86)/eclipse/plugins/org.eclipse.equinox.launcher_1.2.0.v20110502.jar\" org.eclipse.equinox.launcher.Main -application PADL_Creator_Cpp_Eclipse.Launcher -data \"D:/Software/Ptidej 5 Workspace 3/PADL Creator C++ (Eclipse) Helper/Runtime Workspace\" -configuration \"file:D:/Software/Ptidej 5 Workspace 3/.metadata/.plugins/org.eclipse.pde.core/PADL Creator C++ (Eclipse)/\" -dev \"file:D:/Software/Ptidej 5 Workspace 3/.metadata/.plugins/org.eclipse.pde.core/PADL Creator C++ (Eclipse)/dev.properties\" -os win32 -ws win32 -arch x86 -nl en_CA -consoleLog";
		final String[] brokenCommandLine =
			new String[] {
					"C:/Program Files (x86)/Java/jre7/bin/javaw.exe",
					"-Declipse.pde.launch=true",
					"-Dfile.encoding=Cp1252",
					"-classpath",
					"\"C:/Program Files (x86)/eclipse/plugins/org.eclipse.equinox.launcher_1.3.0.v20120522-1813.jar\"",
					"org.eclipse.equinox.launcher.Main",
					"-application",
					"PADL_Creator_Cpp_Eclipse.Launcher",
					"-data",
					"\"D:/Software/Ptidej 5 Workspace 3/PADL Creator C++ (Eclipse) Helper/Runtime Workspace\"",
					"-configuration",
					"\"file:D:/Software/Ptidej 5 Workspace 3/.metadata/.plugins/org.eclipse.pde.core/PADL Creator C++ (Eclipse)/\"",
					"-dev",
					"\"file:D:/Software/Ptidej 5 Workspace 3/.metadata/.plugins/org.eclipse.pde.core/PADL Creator C++ (Eclipse)/dev.properties\"",
					"-os", "win32", "-ws", "win32", "-arch", "x86", "-nl",
					"en_CA", "-consoleLog" };
		final ProcessBuilder processBuilder =
			new ProcessBuilder(brokenCommandLine);
		try {
			final Process process = processBuilder.start();
			final OutputMonitor outMonitor =
				new OutputMonitor(
					"Out Monitor",
					"",
					process.getInputStream(),
					ProxyConsole.getInstance().debugOutput());
			outMonitor.start();
			final OutputMonitor errMonitor =
				new OutputMonitor(
					"Err Monitor",
					"",
					process.getErrorStream(),
					ProxyConsole.getInstance().errorOutput());
			errMonitor.start();
			process.waitFor();
			ProxyConsole
				.getInstance()
				.debugOutput()
				.println(process.exitValue());
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
		catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}
}
