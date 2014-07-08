package ptidej.viewer.ui.splash;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import ptidej.viewer.utils.Resources;
import ptidej.viewer.utils.Utils;

public class JSplashScreen {
	private final JFrame frame;
	public JSplashScreen(final String strImageKey) {
		this.frame = new JFrame("Display Image");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JPanel panel = (JPanel) this.frame.getContentPane();

		final JLabel label = new JLabel();
		label.setIcon(Utils.getImageIcon(strImageKey, SplashScreen.class));
		panel.add(label);

		this.frame.setLocationRelativeTo(null);
		this.frame.pack();
	}
	public void setVisible(boolean isVisible) {
		this.frame.setVisible(isVisible);
	}
	public static void main(final String[] args) {
		final JSplashScreen splashScreen =
			new JSplashScreen(Resources.PTIDEJ_LOGO);
		splashScreen.setVisible(true);
	}
}
