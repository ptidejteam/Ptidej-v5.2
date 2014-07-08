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

import org.eclipse.core.runtime.adaptor.EclipseStarter;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import util.io.ProxyConsole;

@SuppressWarnings("restriction")
public class StarterEclipsev2 {
	public static void main(final String[] args) {
		try {
			String theArgs[] =
				{
						"-application ",
						"PADL_Creator_Cpp_Eclipse.Launcher ",
						"-data ",
						"\"D:/Software/Ptidej 5 Workspace 3/PADL Creator C++ (Eclipse) Helper/Runtime Workspace\" ",
						"-configuration ",
						"\"file:D:/Software/Ptidej 5 Workspace 3/.metadata/.plugins/org.eclipse.pde.core/PADL Creator C++ (Eclipse)/\" ",
						"-dev ",
						"\"file:D:/Software/Ptidej 5 Workspace 3/.metadata/.plugins/org.eclipse.pde.core/PADL Creator C++ (Eclipse)/dev.properties\" ",
						"-os ", "win32 ", "-ws ", "win32 ", "-arch ", "x86 ",
						"-nl ", "en_CA ", "-consoleLog " };
			final BundleContext ctx = EclipseStarter.startup(theArgs, null);
			final Bundle b =
				ctx.installBundle("file://PADL Creator C++ (Eclipse)/");
			b.start();
			// ctx.getBundles();
			EclipseStarter.shutdown();
		}
		catch (final Exception e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
	}
}
