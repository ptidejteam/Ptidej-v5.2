package ptidej.viewer.ui.panel;

import java.util.Enumeration;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import padl.motif.DesignMotifsRepository;
import padl.motif.IDesignMotifModel;
import ptidej.viewer.utils.Resources;
import ptidej.viewer.widget.EmbeddedPanel;

public class DesignMotifChoicePanel extends EmbeddedPanel {
	private static final long serialVersionUID = 1L;

	public DesignMotifChoicePanel() {
		super();

		final DesignMotifsRepository patternRepository = DesignMotifsRepository.getInstance();
		final IDesignMotifModel[] patterns =
			patternRepository.getDesignMotifs();

		final ButtonGroup bgPatterns = new ButtonGroup();
		for (int i = 0; i < patterns.length; i++) {
			this.addRadioButton(
				patterns[i].getDisplayName(),
				DesignMotifChoicePanel.class,
				Resources.DESIGN_MOTIFS,
				bgPatterns,
				true,
				false,
				false);
		}

		// Yann 2006/07/19: Good start!
		// I make sure at least one of the 
		// radio-button is selected.
		final Enumeration enumeration = bgPatterns.getElements();
		if (enumeration.hasMoreElements()) {
			final JRadioButton button =
				(JRadioButton) enumeration.nextElement();
			button.doClick();
		}
	}
}