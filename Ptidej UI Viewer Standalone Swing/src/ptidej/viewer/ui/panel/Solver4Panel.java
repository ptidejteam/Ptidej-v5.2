package ptidej.viewer.ui.panel;

import ptidej.viewer.utils.Resources;
import ptidej.viewer.utils.Controls;
import ptidej.viewer.widget.EmbeddedPanel;
import util.help.IHelpURL;

public class Solver4Panel extends EmbeddedPanel {
	private static final long serialVersionUID = 1L;

	public Solver4Panel() {
		super();

		this.addButton(
			Resources.PTIDEJ_SOLVER_4_FIND_SIMILAR_MICRO_ARCHITECTURE,
			Solver4Panel.class,
			Resources.PTIDEJ_SOLVER_4,
			false,
			false,
			Controls.getInstance().areDesignPatternsListening(),
			new IHelpURL() {
				public String getHelpURL() {
					return "http://www.ptidej.net/publications/documents/TSE08.doc.pdf";
				}
			});
	}
}
