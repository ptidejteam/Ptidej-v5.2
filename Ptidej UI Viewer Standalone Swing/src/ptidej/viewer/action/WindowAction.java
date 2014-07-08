package ptidej.viewer.action;

import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

import javax.swing.AbstractAction;
import javax.swing.JInternalFrame;

import ptidej.viewer.ui.DesktopPane;
import ptidej.viewer.utils.Resources;
import util.io.ProxyConsole;

public class WindowAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static WindowAction UniqueInstance;
	public static WindowAction getInstance() {
		return (WindowAction.UniqueInstance == null)
			? WindowAction.UniqueInstance =
			new WindowAction() : WindowAction.UniqueInstance;
	}

	private WindowAction() {
	}
	public void actionPerformed(final ActionEvent e) {
		final String action = e.getActionCommand();

		if (action.equals(Resources.MINIMIZE_ALL)) {
			final JInternalFrame[] frames =
				DesktopPane.getInstance().getAllFramesInLayer(
					Resources.PROJECTS_LAYER);
			for (int i = 0; i < frames.length; i++) {
				try {
					frames[i].setIcon(true);
				}
				catch (final PropertyVetoException pve) {
					pve.printStackTrace(ProxyConsole.getInstance().errorOutput());
				}
			}
			DesktopPane.getInstance().revalidate();
		}
		else if (action.equals(Resources.CASCADE)) {
			// TODO: To implement.
		}
		else if (action.equals(Resources.TILE_VERTICALLY)) {
			final JInternalFrame[] frames =
				DesktopPane.getInstance().getAllFramesInLayer(
					Resources.PROJECTS_LAYER);
			final int numberOfWindows = frames.length;
			final int height = DesktopPane.getInstance().getHeight();
			final int width =
				DesktopPane.getInstance().getWidth() / numberOfWindows;
			for (int i = 0; i < numberOfWindows; i++) {
				frames[i].setLocation(width * i, 0);
				frames[i].setSize(width, height);
			}
			DesktopPane.getInstance().revalidate();
		}
		else if (action.equals(Resources.TILE_HORIZONTALLY)) {
			final JInternalFrame[] frames =
				DesktopPane.getInstance().getAllFramesInLayer(
					Resources.PROJECTS_LAYER);
			final int numberOfWindows = frames.length;
			final int height =
				DesktopPane.getInstance().getHeight() / numberOfWindows;
			final int width = DesktopPane.getInstance().getWidth();
			for (int i = 0; i < numberOfWindows; i++) {
				frames[i].setLocation(0, height * i);
				frames[i].setSize(width, height);
			}
			DesktopPane.getInstance().revalidate();
		}
	}
}