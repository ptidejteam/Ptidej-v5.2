package ptidej.viewer.ui.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import ptidej.viewer.utils.Resources;
import ptidej.viewer.widget.EmbeddedPanel;

public class AntiPatternPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public AntiPatternPanel() {
		final EmbeddedPanel panel = new EmbeddedPanel();

		panel.addCollapsablePanel(
			Resources.DESIGN_SMELLS,
			AntiPatternPanel.class,
			new DesignSmellChoicePanel());
		
		panel.addCollapsablePanel(
			Resources.DESIGN_SMELLS_ACTION,
			AntiPatternPanel.class,
			new AntiPatternActionPanel());
		
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.NORTH);
	}
}