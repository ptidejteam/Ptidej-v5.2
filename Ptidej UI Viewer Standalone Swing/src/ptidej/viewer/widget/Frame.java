package ptidej.viewer.widget;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	private int frameWidth;
	private int frameHeight;
	private int frameX;
	private int frameY;
	private Dimension screenSize;
	private int screenSizeWidth;
	private int screenSizeHeight;

	public Frame() {
	}
	public Frame(
		final String titre,
		final int inFrameWidth,
		final int inFrameHeight) {

		this.setTitle(titre);

		this.frameWidth = inFrameWidth;
		this.frameHeight = inFrameHeight;
		this.screenSize = this.getToolkit().getScreenSize();
		this.screenSizeWidth = this.screenSize.width;
		this.screenSizeHeight = this.screenSize.height;
		this.frameX = (int) ((this.screenSizeWidth - this.frameWidth) / 2);
		this.frameY = (int) ((this.screenSizeHeight - this.frameHeight) / 2);
		this.checkAndMakeCentered(inFrameWidth, inFrameHeight);

		this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(final WindowEvent we) {
				System.exit(0);
			}
		});
	}

	/**
	 * Si le frame reçoit une largeur et/ou hauteur supérieures à celles de la
	 * résolution de l'écran, cette méthode les redéfinit pour qu'ils y soient
	 * inférieures.
	 * @param inTmpFrameWidth   la largeur passé en paramètre au constructeur
	 * @param inTmpFrameHeight  la hauteur passé en paramètre au constructeur
	 */
	private void checkAndMakeCentered(int inTmpFrameWidth, int inTmpFrameHeight) {
		boolean boolTooBig = false;
		if (this.frameHeight > this.screenSizeHeight) {
			this.frameHeight = this.screenSizeHeight - 50;
			this.frameY = 20;
			boolTooBig = true;
		}
		if (this.frameWidth > this.screenSizeWidth) {
			this.frameWidth = this.screenSizeWidth - 50;
			this.frameX = 25;
			boolTooBig = true;
		}
		this.setSize(this.frameWidth, this.frameHeight);
		this.setLocation(this.frameX, this.frameY);

		if (boolTooBig) {
			/**
			 * @todo make me international please please please ...
			 * I was born french and nobody translated me yet :(
			 */
			JOptionPane.showMessageDialog(
				this,
				"Pour une meilleur interface utilisateur,"
						+ " veillez augmenter votre résolution d'écran"
						+ "\nRésolution Actuelle :  " + this.screenSizeWidth
						+ " par " + this.screenSizeHeight + " pixels."
						+ "\nRésolution Minimale Préféré :  " + inTmpFrameWidth
						+ " par " + inTmpFrameHeight + " pixels.",
				"Information",
				JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
