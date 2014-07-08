/*
 * @(#)NervousText.java	1.4 98/06/29
 *
 * Copyright (c) 1997, 1998 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

package caffeine.analysis.nervoustext;

import java.applet.Applet;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * An applet that displays jittering text on the screen.
 *
 * @author Daniel Wyszynski 04/12/95
 * @version 1.10, 02/05/97
 * @modified 05/09/95 kwalrath Changed string; added thread suspension
 * @modified 02/06/98 madbot removed use of suspend and resume and cleaned up
 * @modified 27/03/01 Yann-Gaël Guéhéneuc transformed the applet into an application (very unclean!)
 */

public class NervousText extends Applet implements Runnable, MouseListener {
	private static final long serialVersionUID = 8490652697797528457L;

	private static int WIDTH = 600;
	private static int HEIGHT = 80;
	private Graphics g;

	public static void main(final String[] args) {
		final Frame frame = new Frame();

		frame.setSize(NervousText.WIDTH, NervousText.HEIGHT);
		frame.setLocation(200, 200);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		frame.setVisible(true);

		NervousText nervousText = new NervousText();
		nervousText.init(frame.getGraphics());
		nervousText.start();
	}

	String banner; // The text to be displayed
	char bannerChars[]; // The same text as an array of characters
	Thread runner = null; // The thread that is displaying the text
	boolean threadSuspended; // True when thread suspended (via mouse click)

	public void destroy() {
		removeMouseListener(this);
	}
	public String getAppletInfo() {
		return "Title: NervousText\nAuthor: Daniel Wyszynski\nDisplays a text banner that jitters.";
	}
	public String[][] getParameterInfo() {
		String pinfo[][] = { { "text", "string", "Text to display" }, };
		return pinfo;
	}
	public void init(final Graphics g) {
		this.g = g;

		// banner = getParameter("text");
		if (this.banner == null) {
			this.banner = "Caffeine, the very essence of Java!";
		}
		int bannerLength = this.banner.length();
		this.bannerChars = new char[bannerLength];
		this.banner.getChars(0, this.banner.length(), this.bannerChars, 0);

		this.threadSuspended = false;

		resize(15 * (bannerLength + 1), 100);
		setFont(new Font("TimesRoman", Font.BOLD, 36));
		addMouseListener(this);
	}
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public synchronized void mousePressed(MouseEvent e) {
		e.consume();
		this.threadSuspended = !this.threadSuspended;
		if (!this.threadSuspended)
			notify();
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void paint(Graphics g) {
		for (int i = 0, length = this.banner.length(); i < length; i++) {
			int x = (int) (10 * Math.random() + 15 * i + 10);
			int y = (int) (10 * Math.random() + 40);
			g.drawChars(this.bannerChars, i, 1, x, y);
		}
	}
	public void run() {
		Thread me = Thread.currentThread();
		while (this.runner == me) {
			try {
				Thread.sleep(100);
				synchronized (this) {
					while (this.threadSuspended) {
						wait();
					}
				}
			}
			catch (InterruptedException e) {
			}
			this.g.clearRect(0, 0, NervousText.WIDTH, NervousText.HEIGHT);
			paint(this.g);
		}
	}
	public void start() {
		this.runner = new Thread(this);
		this.runner.start();
	}
	public synchronized void stop() {
		this.runner = null;
		if (this.threadSuspended) {
			this.threadSuspended = false;
			notify();
		}
	}
}