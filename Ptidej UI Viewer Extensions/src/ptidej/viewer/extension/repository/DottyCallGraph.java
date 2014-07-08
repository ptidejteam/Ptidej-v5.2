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
package ptidej.viewer.extension.repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.bcel.Repository;
import ptidej.viewer.IRepresentation;
import ptidej.viewer.event.SourceAndGraphModelEvent;
import ptidej.viewer.extension.IViewerExtension;
import ptidej.viewer.extension.repository.callgraph.CallGraphGenerator;
import ptidej.viewer.extension.repository.callgraph.model.Method;
import ptidej.viewer.extension.repository.callgraph.visitor.DottyGenerator;
import util.awt.NameDialog;
import util.help.IHelpURL;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/10/06
 */
public final class DottyCallGraph extends Dotty implements IViewerExtension,
		IHelpURL {

	private static final long serialVersionUID = 1L;
	public String getHelpURL() {
		return "http://www.graphviz.org/";
	}
	public String getName() {
		return "Dotty on Call Graph";
	}
	public void invoke(final IRepresentation aRepresentation) {
		this.callDotty(aRepresentation);
	}
	public void sourceModelAvailable(final SourceAndGraphModelEvent aViewerEvent) {
		if (this.isVisible()) {
			this.callDotty(aViewerEvent.getRepresentation());
		}
	}
	public void sourceModelChanged(final SourceAndGraphModelEvent aViewerEvent) {
		this.sourceModelAvailable(aViewerEvent);
	}
	public void sourceModelUnavailable() {
		// Nothing to do...
	}
	private void callDotty(final IRepresentation aRepresentation) {
		final NameDialog classNameDialog =
			new NameDialog(this, "Name of the Class?");
		final String className = classNameDialog.getName();
		final NameDialog methodNameDialog =
			new NameDialog(this, "Name of the Method?");
		final String methodName = methodNameDialog.getName();

		try {
			final CallGraphGenerator generator =
				CallGraphGenerator.getInstance();
			final Method method =
				generator.create(Repository.lookupClass(className), methodName);
			final DottyGenerator builder =
				new DottyGenerator(aRepresentation.getSourceModel());
			method.accept(builder);

			final String path =
				System.getProperty("user.dir") + File.separatorChar
						+ "Temp.dot";
			// TODO Use ProxyDisk 
			final FileWriter writer = new FileWriter(path);
			writer.write(builder.getResult().toString());
			writer.close();

			this.callDotty(aRepresentation, path);
		}
		catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
