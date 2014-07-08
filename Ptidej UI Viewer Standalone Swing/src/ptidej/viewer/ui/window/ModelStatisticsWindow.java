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
package ptidej.viewer.ui.window;

import ptidej.viewer.awt.entities.StatisticsPanel;
import ptidej.viewer.event.SourceAndGraphModelEvent;
import ptidej.viewer.ui.AbstractExternalWindow;
import ptidej.viewer.ui.DesktopPane;
import ptidej.viewer.utils.Resources;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/06/13
 */
public final class ModelStatisticsWindow extends AbstractExternalWindow {
	private static final long serialVersionUID = 1L;
	private final StatisticsPanel statisticsPanel;

	public ModelStatisticsWindow() {
		super("Model Statistics");

		this.setResizable(true);
		this.setBounds(50, 50, 500, 300);
		DesktopPane.getInstance().setLayer(this, Resources.PROJECTS_LAYER);

		this.statisticsPanel = StatisticsPanel.getInstance();
		this.getContentPane().add(this.statisticsPanel);
	}
	public void sourceModelAvailable(
		final SourceAndGraphModelEvent aSourceModelEvent) {

		this.statisticsPanel.sourceModelAvailable(aSourceModelEvent);
	}
	public void sourceModelChanged(
		final SourceAndGraphModelEvent aSourceModelEvent) {

		this.statisticsPanel.sourceModelChanged(aSourceModelEvent);
	}
	public void sourceModelUnavailable() {
		this.statisticsPanel.sourceModelUnavailable();
	}
}