package ptidej.viewer.action;

import java.awt.event.ActionEvent;
import java.util.Properties;
import javax.swing.AbstractAction;
import ptidej.solver.OccurrenceGenerator;
import ptidej.viewer.ViewerCommons;
import ptidej.viewer.ui.DesktopPane;
import ptidej.viewer.utils.Resources;
import util.help.Browser;

public class Solver4Action extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private static Solver4Action UniqueInstance;
	public static Solver4Action getInstance() {
		return (Solver4Action.UniqueInstance == null) ? Solver4Action.UniqueInstance =
			new Solver4Action()
				: Solver4Action.UniqueInstance;
	}

	private Solver4Action() {
	}
	public void actionPerformed(final ActionEvent e) {
		final String action = e.getActionCommand();

		if (action
			.equals(Resources.PTIDEJ_SOLVER_4_FIND_SIMILAR_MICRO_ARCHITECTURE)) {

			final OccurrenceGenerator solutionGenerator =
				OccurrenceGenerator.getInstance();

			final Properties solutions =
				solutionGenerator
					.getOccurrences(
						DesktopPane.getInstance().getPatternName(),
						DesktopPane
							.getInstance()
							.getAbstractRepresentationWindow()
							.getSourceModel(),
						OccurrenceGenerator.VERSION_PTIDEJSOLVER4,
						DesktopPane.getInstance().getSolver(),
						DesktopPane.getInstance().getProblem());

			ViewerCommons.loadConstraintsData(DesktopPane
				.getInstance()
				.getAbstractRepresentationWindow(), solutions);
		}
		else if (action
			.equals(Resources.PTIDEJ_SOLVER_4_SIMILAR_MICRO_ARCHITECTURE_HELP)) {

			Browser
				.displayURL(Resources
					.getLink(Resources.PTIDEJ_SOLVER_4_SIMILAR_MICRO_ARCHITECTURE_HELP));
		}
	}
}