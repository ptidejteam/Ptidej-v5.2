package ptidej.viewer.ui.panel;

import ptidej.viewer.utils.Resources;
import ptidej.viewer.utils.Controls;
import ptidej.viewer.widget.EmbeddedPanel;
import util.help.IHelpURL;

public class EPIPanel extends EmbeddedPanel {
	private static final long serialVersionUID = 1L;

	public EPIPanel() {
		this.addButton(
			Resources.EPI_FIND_SIMILAR_MICRO_ARCHITECTURE,
			EPIPanel.class,
			Resources.PTIDEJ_EPI,
			false,
			false,
			Controls.getInstance().areDesignPatternsListening(),
			new IHelpURL() {
				public String getHelpURL() {
					return "http://www.ptidej.net/publications/documents/IST09.doc.pdf";
				}
			});
	}
}
