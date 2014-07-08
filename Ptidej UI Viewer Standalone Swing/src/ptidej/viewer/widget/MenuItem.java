package ptidej.viewer.widget;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenuItem;
import ptidej.viewer.ui.DesktopFrame;

public class MenuItem extends JMenuItem {
	private static final long serialVersionUID = 1L;

	public MenuItem() {
	}

	public MenuItem(Icon icon) {
		super(icon);
	}

	public MenuItem(String text) {
		super(text);
	}

	public MenuItem(Action a) {
		super(a);
	}

	public MenuItem(String text, Icon icon) {
		super(text, icon);
	}

	public MenuItem(String text, int mnemonic) {
		super(text, mnemonic);
	}

	protected void fireActionPerformed(final ActionEvent anEvent) {
		DesktopFrame.getInstance().setCursor(
			Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		super.fireActionPerformed(anEvent);

		DesktopFrame.getInstance().setCursor(Cursor.getDefaultCursor());
	}
}