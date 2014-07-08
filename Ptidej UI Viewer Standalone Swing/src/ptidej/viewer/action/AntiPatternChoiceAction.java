package ptidej.viewer.action;

import java.awt.event.ActionEvent;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;

import ptidej.viewer.ui.DesktopPane;

public class AntiPatternChoiceAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static AntiPatternChoiceAction UniqueInstance;
	public static AntiPatternChoiceAction getInstance() {
		return (AntiPatternChoiceAction.UniqueInstance == null)
			? AntiPatternChoiceAction.UniqueInstance =
			new AntiPatternChoiceAction() : AntiPatternChoiceAction.UniqueInstance;
	}

	private AntiPatternChoiceAction() {
	}
	public void actionPerformed(final ActionEvent anActionEvent) {
		final JCheckBox source = (JCheckBox) anActionEvent.getSource();
		final Set designDefects =
			DesktopPane.getInstance().getDesignDefects();

		if (source.isSelected()) {
			designDefects.add(anActionEvent.getActionCommand());
		}
		else {
			designDefects.remove(anActionEvent.getActionCommand());
		}

		DesktopPane.getInstance().setDesignDefects(designDefects);
	}
}