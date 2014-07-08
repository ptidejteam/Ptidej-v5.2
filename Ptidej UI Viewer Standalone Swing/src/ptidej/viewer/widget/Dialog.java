package ptidej.viewer.widget;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.HeadlessException;
import javax.swing.JDialog;

//import ptidej.swing.custom.panels.PtidejPanel;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Dialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private int width, height;
	private Frame owner;

	public Dialog(
		Frame owner,
		String title,
		boolean modal,
		int width,
		int height) throws HeadlessException {
		super(owner, title, modal);
		this.width = width;
		this.height = height;
		this.owner = owner;
		this.init();
	}
	private void init() {
		this.getContentPane().setLayout(new BorderLayout());
		this.setResizable(false);
		this.setSize(this.width, this.height);
		this.setLocationRelativeTo(this.owner);
	}
}
