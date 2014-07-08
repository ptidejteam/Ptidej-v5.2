/*
 * (c) Copyright 2001-2004 Yann-Gaël Guéhéneuc,
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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import padl.kernel.IAbstractModel;
import ptidej.viewer.IRepresentation;
import ptidej.viewer.event.SourceAndGraphModelEvent;
import ptidej.viewer.extension.IViewerExtension;

/**
 * @author Foutse Khomh
 * @since  2007/03/01
 */
public final class QMOOD implements IViewerExtension {
	private static final QMOODMetrics qmoodCalculator = new QMOODMetrics();
	public QMOOD() {

	}
	public String getName() {
		return "QMOOD model";
	}
	private void computeMetrics(final IAbstractModel model) {
		try {
			// TODO Use ProxyDisk
			final FileWriter fw = new FileWriter("QMOOD.values");
			final BufferedWriter out = new BufferedWriter(fw);

			model.walk(qmoodCalculator);

			out.write((qmoodCalculator.getResult()).toString());
			out.write('\n');
			out.flush();
			out.close();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
	}
	public void invoke(final IRepresentation aRepresentation) {
		this.computeMetrics(aRepresentation.getSourceModel());
	}
	public boolean isVisible() {
		return false;
	}
	public void setVisible(final boolean aVisibility) {
	}
	public void sourceModelAvailable(
		final SourceAndGraphModelEvent aSourceModelEvent) {
	}
	public void sourceModelChanged(
		final SourceAndGraphModelEvent aSourceModelEvent) {
	}
	public void sourceModelUnavailable() {
	}
}
