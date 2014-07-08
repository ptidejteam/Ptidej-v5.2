package ptidej.viewer.widget;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import ptidej.viewer.ui.DesktopFrame;
import ptidej.viewer.ui.about.AboutDialog;
import ptidej.viewer.utils.Resources;
import util.io.ProxyConsole;

public class LookAndFeelMenu extends JMenu implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static LookAndFeelMenu UniqueInstance;
	public static LookAndFeelMenu getUniqueInstance() {
		if (LookAndFeelMenu.UniqueInstance == null) {
			LookAndFeelMenu.UniqueInstance = new LookAndFeelMenu();
		}
		return LookAndFeelMenu.UniqueInstance;
	}

	private List components = new ArrayList();
	private UIManager.LookAndFeelInfo lf[] = UIManager
		.getInstalledLookAndFeels();
	private Hashtable lookAndFeels;

	private LookAndFeelMenu() {
		this.setText(Resources.getMenuText(
			Resources.LOOKANDFEELS,
			LookAndFeelMenu.class));
		this.setMnemonic(Resources.getMenuMnemonic(
			Resources.LOOKANDFEELS,
			LookAndFeelMenu.class).charAt(0));

		this.lookAndFeels = new Hashtable();

		for (int i = 0; i < this.lf.length; i++) {
			final String strName = this.lf[i].getName();
			final String strClassName = this.lf[i].getClassName();
			this.lookAndFeels.put(strName, strClassName);
			final JMenuItem itemLF = new JMenuItem(strName);
			itemLF.addActionListener(this);
			this.add(itemLF);
		}

		this.components.add(DesktopFrame.getInstance());
		this.components.add(AboutDialog.getUniqueInstance());
	}
	public void actionPerformed(final ActionEvent ae) {
		// Yann 2006/07/19: Understanding...
		// I am not sure that we really do need
		// the following piece of code, everything
		// seems to work fine without it! So, I
		// remove it...
		//	this.components.add(
		//		Desktop
		//			.getUniqueInstance()
		//			.getPtidejToolBar()
		//			.dropdown
		//			.getPopupMenu());
		final Object obj = this.lookAndFeels.get(ae.getActionCommand());
		if (obj != null)
			try {
				UIManager.setLookAndFeel((LookAndFeel) ((Class
					.forName((String) obj)).newInstance()));
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						for (int i = 0; i < LookAndFeelMenu.this.components
							.size(); i++) {
							SwingUtilities
								.updateComponentTreeUI((Component) (LookAndFeelMenu.this.components
									.get(i)));
						}
					}
				});
			}
			catch (final Exception e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
	}
}