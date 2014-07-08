/*
 * (c) Copyright 2000-2003 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Gaël Guéhéneuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package ptidej.viewer.extension;

import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.List;
import util.io.ProxyConsole;
import util.io.SubtypeLoader;
import util.multilingual.MultilingualManager;
import util.repository.FileAccessException;
import util.repository.IRepository;
import util.repository.impl.FileRepositoryFactory;
import com.ibm.toad.cfparse.ClassFile;

public class ViewerExtensionsRepository implements IRepository {
	private static ViewerExtensionsRepository UniqueInstance;
	public static ViewerExtensionsRepository getInstance() {
		if (ViewerExtensionsRepository.UniqueInstance == null) {
			ViewerExtensionsRepository.UniqueInstance =
				new ViewerExtensionsRepository();
		}
		return ViewerExtensionsRepository.UniqueInstance;
	}
	//	public static void main(final String args[]) {
	//		final Repository extensionManager =
	//			Repository.getCurrentExtensionManager();
	//		System.out.println(extensionManager);
	//	}

	private IViewerExtension[] viewerExtensions;
	private ViewerExtensionsRepository() {
		// Yann 2003/10/14: Demo!
		// I must catch the AccessControlException
		// thrown when attempting loading extensions
		// from the applet viewer.
		try {
			final ClassFile[] classFiles =
				SubtypeLoader.loadSubtypesFromStream(
					"ptidej.viewer.extension.IViewerExtension",
					FileRepositoryFactory
						.getInstance()
						.getFileRepository(this)
						.getFiles(),
					"ptidej.viewer.extension.repository",
					".class");
			final List listOfExtensions = new ArrayList(classFiles.length);
			for (int i = 0; i < classFiles.length; i++) {
				try {
					listOfExtensions.add((IViewerExtension) Class.forName(
						classFiles[i].getName()).newInstance());
				}
				// Yann 2003/10/07: Protection!
				// I want to make sure that any problem in this
				// section won't break the program...
				//	catch (final ClassNotFoundException cnfe) {
				//		// cnfe.printStackTrace();
				//	}
				//	catch (final InstantiationException ie) {
				//		// ie.printStackTrace();
				//	}
				//	catch (final IllegalAccessException iae) {
				//		// iae.printStackTrace();
				//	}
				//	catch (final NoClassDefFoundError ncdfe) {
				catch (final Throwable t) {
					ProxyConsole
						.getInstance()
						.errorOutput()
						.println(
							MultilingualManager.getString(
								"LOAD_EXTENSION",
								ViewerExtensionsRepository.class,
								new Object[] { classFiles[i].getName(),
										t.getMessage() }));
				}
			}

			this.viewerExtensions =
				new IViewerExtension[listOfExtensions.size()];
			listOfExtensions.toArray(this.viewerExtensions);
		}
		catch (final AccessControlException ace) {
			this.viewerExtensions = new IViewerExtension[0];
		}
		catch (final FileAccessException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			this.viewerExtensions = new IViewerExtension[0];
		}
	}
	public IViewerExtension[] getViewerExtensions() {
		return this.viewerExtensions;
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("Extension Repository:\n");
		for (int x = 0; x < this.getViewerExtensions().length; x++) {
			buffer.append('\t');
			buffer.append(this.getViewerExtensions()[x].getName());
			buffer.append('\n');
		}
		return buffer.toString();
	}
}
