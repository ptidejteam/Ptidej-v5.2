package ptidej.viewer.ui.panel;

import ptidej.viewer.utils.Resources;
import ptidej.viewer.utils.Controls;
import ptidej.viewer.widget.EmbeddedPanel;
import util.help.IHelpURL;

public class AntiPatternActionPanel extends EmbeddedPanel {
	private static final long serialVersionUID = 1L;

	public AntiPatternActionPanel() {
		super();

		this.addButton(
			Resources.NEW_RULECARD,
			AntiPatternActionPanel.class,
			Resources.DESIGN_SMELLS,
			Controls.getInstance().canModifyRuleCards(),
			false,
			false);

		this.addButton(
			Resources.DELETE_RULECARD,
			AntiPatternActionPanel.class,
			Resources.DESIGN_SMELLS,
			Controls.getInstance().canModifyRuleCards(),
			false,
			false);

		this.addButton(
			Resources.DETECT_DESIGN_SMELLS,
			AntiPatternActionPanel.class,
			Resources.DESIGN_SMELLS,
			false,
			false,
			Controls.getInstance().areAntiPatternsListening(),
			new IHelpURL() {
				public String getHelpURL() {
					return "http://www.ptidej.net/publications/documents/TSE09.doc.pdf";
				}
			});
	}
}