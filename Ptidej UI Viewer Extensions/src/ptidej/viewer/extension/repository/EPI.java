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

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import padl.kernel.IAbstractModel;
import ptidej.viewer.IRepresentation;
import ptidej.viewer.event.SourceAndGraphModelEvent;
import ptidej.viewer.extension.IViewerExtension;
import util.help.IHelpURL;

/**
 * @author OlivierK
 */
public final class EPI extends Frame implements IViewerExtension, IHelpURL {
	private static final long serialVersionUID = 1L;
	public EPI() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosed(final WindowEvent e) {
				EPI.this.setVisible(false);
			}
			public void windowClosing(final WindowEvent e) {
				this.windowClosed(e);
			}
		});
	}
	public void sourceModelChanged(final SourceAndGraphModelEvent sourceEvent) {
	}
	public String getName() {
		return "Efficient Patterns Identification";
	}
	public void invoke(final IRepresentation representation) {
		//		// Positioning and display.
		//		this.setSize(new Dimension(300, 500));
		//		this.setLocation(this.getX() + 50, this.getY() + 50);
		//		this.setTitle(this.getName());
		//
		//		// I build the ScrollPane into which all the buttons and labels
		//		// will be displayed.
		//		final ScrollPane scrollPane = new ScrollPane();
		//		scrollPane.setBackground(Color.lightGray);
		//		scrollPane.getHAdjustable().setUnitIncrement(20);
		//		scrollPane.getVAdjustable().setUnitIncrement(20);
		//		this.add(scrollPane);
		//
		//		this.textArea = new TextArea();
		//		scrollPane.add(this.textArea);

		this.launchEPIInterface(representation.getSourceModel());
	}
	private void launchEPIInterface(final IAbstractModel model) {
		// final EPIInterface inst = new EPIInterface(model);
		// inst.setVisible(true);
	}
	public void sourceModelAvailable(SourceAndGraphModelEvent aViewerEvent) {
	}
	public void sourceModelUnavailable() {
	}
	public String getHelpURL() {
		return "http://www.ptidej.net/publications/documents/IST09.doc.pdf";
	}
}
