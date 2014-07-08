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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import padl.kernel.IAbstractModel;
import ptidej.viewer.IRepresentation;
import ptidej.viewer.event.SourceAndGraphModelEvent;
import ptidej.viewer.extension.IViewerExtension;
import util.help.IHelpURL;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/03/23
 */
public final class POM extends Frame implements IViewerExtension, IHelpURL {
	private static final long serialVersionUID = 1L;
	private static final POMCalculator pomCalculator = new POMCalculator();
	private TextArea textArea;

	public POM() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosed(final WindowEvent e) {
				POM.this.setVisible(false);
			}
			public void windowClosing(final WindowEvent e) {
				this.windowClosed(e);
			}
		});
	}
	private void computeMetrics(final IAbstractModel model) {
		model.walk(pomCalculator);
		this.textArea.setText((String) pomCalculator.getResult());
	}
	public String getName() {
		return "POM-based metrics";
	}
	public void invoke(final IRepresentation aRepresentation) {
		// Positioning and display.
		this.setSize(new Dimension(300, 500));
		this.setLocation(this.getX() + 50, this.getY() + 50);
		this.setTitle(this.getName());

		// I build the ScrollPane into which all the buttons and labels
		// will be displayed.
		final ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBackground(Color.lightGray);
		scrollPane.getHAdjustable().setUnitIncrement(20);
		scrollPane.getVAdjustable().setUnitIncrement(20);
		this.add(scrollPane);

		this.textArea = new TextArea();
		scrollPane.add(this.textArea);

		this.computeMetrics(aRepresentation.getSourceModel());

		this.setVisible(true);
	}
	public void sourceModelAvailable(SourceAndGraphModelEvent aViewerEvent) {
		if (this.isVisible()) {
			this.computeMetrics(aViewerEvent
				.getRepresentation()
				.getSourceModel());

			// I must refresh the window.
			this.validate();
			this.repaint();
		}
	}
	public void sourceModelChanged(final SourceAndGraphModelEvent aViewerEvent) {
		this.sourceModelAvailable(aViewerEvent);
	}
	public void sourceModelUnavailable() {
		// Nothing to do...
	}
	public String getHelpURL() {
		return "http://www.ptidej.net/publications/documents/WCRE04.doc.pdf";
	}
}
