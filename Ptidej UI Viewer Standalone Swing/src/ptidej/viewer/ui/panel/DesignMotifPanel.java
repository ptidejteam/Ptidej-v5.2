package ptidej.viewer.ui.panel;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import ptidej.viewer.utils.Resources;
import ptidej.viewer.widget.EmbeddedPanel;

public class DesignMotifPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public DesignMotifPanel() {
		final EmbeddedPanel panel = new EmbeddedPanel();
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.NORTH);

		panel.addCollapsablePanel(
			Resources.DESIGN_MOTIFS,
			DesignMotifPanel.class,
			new DesignMotifChoicePanel());
		panel.addCollapsablePanel(
			Resources.PTIDEJ_SOLVERS,
			DesignMotifPanel.class,
			new SolverGeneralPanel());
		panel.addCollapsablePanel(
			Resources.PTIDEJ_SOLVER_3,
			DesignMotifPanel.class,
			new Solver3Panel());
		panel.addCollapsablePanel(
			Resources.PTIDEJ_SOLVER_4,
			DesignMotifPanel.class,
			new Solver4Panel());
		panel.addCollapsablePanel(
			Resources.PTIDEJ_METRICAL_SOLVER_4,
			DesignMotifPanel.class,
			new MetricalSolver4Panel());
		panel.addCollapsablePanel(
			Resources.PTIDEJ_EPI,
			DesignMotifPanel.class,
			new EPIPanel());
	}
}