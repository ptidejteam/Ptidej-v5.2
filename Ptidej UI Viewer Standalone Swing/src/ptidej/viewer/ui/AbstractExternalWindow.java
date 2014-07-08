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
package ptidej.viewer.ui;

import javax.swing.JFrame;
import ptidej.ui.canvas.event.CanvasEvent;
import ptidej.ui.event.GraphEvent;
import ptidej.viewer.event.SourceAndGraphModelEvent;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/08/19
 */
public abstract class AbstractExternalWindow extends JFrame implements
		IListeningWindow {

	private static final long serialVersionUID = 4965228064275712442L;
	protected AbstractExternalWindow(final String aTitle) {
		this.setIconImage(ptidej.ui.Utils.getImage("PtidejIcon.png"));
		this.setTitle(aTitle);

		DesktopPane.getInstance().addCanvasListener(this);
		DesktopPane.getInstance().addGraphModelListener(this);
		DesktopPane.getInstance().addSourceModelListener(this);
	}
	public void backgroundElementSelected(final CanvasEvent aCanvasEvent) {
	}
	public void backgroundElementUnSelected(final CanvasEvent aCanvasEvent) {
	}
	public void constituentSelected(final GraphEvent aGraphEvent) {
	}
	public void constituentUnSelected(final GraphEvent aGraphEvent) {
	}
	public void foregroundElementSelected(final CanvasEvent aCanvasEvent) {
	}
	public void foregroundElementUnSelected(final CanvasEvent aCanvasEvent) {
	}
	public void graphModelAvailable(
		final SourceAndGraphModelEvent aSourceModelEvent) {
	}
	public void graphModelChanged(
		final SourceAndGraphModelEvent aSourceModelEvent) {
	}
	public void graphModelUnavailable() {
	}
	public void setVisible(final boolean isVisible) {
		super.setVisible(isVisible);

		if (isVisible) {
			// Yann 2007/08/19: First update!
			// After adding a IListeningWindow, I request
			// the DesktopPane to request the current 
			// AbstractRepresentationWindow (if any) to
			// let this IListeningWindow about any interesting
			// event as a bootstrap mechanism.
			final AbstractRepresentationWindow window =
				DesktopPane.getInstance().getAbstractRepresentationWindow();
			if (window != null) {
				window.bootstrapListeningWindows();
			}
		}
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
