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
package ptidej.viewer.ui.window;

import ptidej.ui.event.GraphEvent;
import ptidej.viewer.awt.entities.ListPanel;
import ptidej.viewer.event.SourceAndGraphModelEvent;
import ptidej.viewer.ui.AbstractExternalWindow;
import ptidej.viewer.ui.DesktopPane;
import ptidej.viewer.utils.Resources;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/06/29
 */
public final class ListEntitiesWindow extends AbstractExternalWindow {
	private static final long serialVersionUID = 1L;
	private final ListPanel listPanel;

	public ListEntitiesWindow() {
		super("List of Entities");

		this.setResizable(true);
		this.setBounds(50, 50, 500, 500);
		DesktopPane.getInstance().setLayer(this, Resources.PROJECTS_LAYER);

		this.listPanel = ListPanel.getInstance();
		this.getContentPane().add(this.listPanel);
	}
	public void graphModelAvailable(
		final SourceAndGraphModelEvent aSourceModelEvent) {

		this.listPanel.graphModelAvailable(aSourceModelEvent);
	}
	public void graphModelChanged(
		final SourceAndGraphModelEvent aSourceModelEvent) {

		this.listPanel.graphModelChanged(aSourceModelEvent);
	}
	public void graphModelUnavailable() {
		this.listPanel.graphModelUnavailable();
	}
	public void constituentSelected(final GraphEvent aGraphEvent) {
		this.listPanel.constituentSelected(aGraphEvent);
	}
	public void constituentUnSelected(final GraphEvent aGraphEvent) {
		this.listPanel.constituentUnSelected(aGraphEvent);
	}
}
