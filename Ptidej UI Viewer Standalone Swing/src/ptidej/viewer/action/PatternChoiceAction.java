package ptidej.viewer.action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import padl.motif.DesignMotifsRepository;
import padl.motif.IDesignMotifModel;
import ptidej.viewer.ui.DesktopPane;

public class PatternChoiceAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private static PatternChoiceAction UniqueInstance;
	public static PatternChoiceAction getInstance() {
		if (PatternChoiceAction.UniqueInstance == null) {
			PatternChoiceAction.UniqueInstance = new PatternChoiceAction();
		}
		return PatternChoiceAction.UniqueInstance;
	}
	private PatternChoiceAction() {
	}
	public void actionPerformed(final ActionEvent e) {
		final String patternName = e.getActionCommand();
		DesktopPane.getInstance().setPatternName(patternName.toCharArray());

		final DesignMotifsRepository patternRepository =
			DesignMotifsRepository.getInstance();
		final IDesignMotifModel[] patterns =
			patternRepository.getDesignMotifs();
		boolean found = false;
		for (int i = 0; i < patterns.length && !found; i++) {
			final IDesignMotifModel model = patterns[i];
			if (model.getName().equals(patternName)) {
				DesktopPane.getInstance().setPattern(model);
				found = true;
			}
		}
	}
}