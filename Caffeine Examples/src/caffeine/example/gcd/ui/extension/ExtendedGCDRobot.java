/*
 * (c) Copyright 2002-2003 Yann-Gaël Guéhéneuc,
 * École des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the author, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
 * ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHOR IS ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package caffeine.example.gcd.ui.extension;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import util.io.ProxyConsole;

public class ExtendedGCDRobot {
	public static int WINDOW_TITLE_HEIGHT = 20;
	public static int WINDOW_BORDER = 4;

	public ExtendedGCDRobot() {
		super();
	}
	public static void main(final String[] args) {
		// I run the application I want to test.
		ExtendedGCD.main(new String[0]);

		try {
			Robot robot = new Robot();
			robot.setAutoDelay(2000);

			// First, I initialize the mouse position.
			int mouse_x = 0;
			int mouse_y = 0;
			robot.mouseMove(mouse_x, mouse_y);

			// I add two GCD windows.
			mouse_x += ExtendedGCD.X_ORIGIN;
			mouse_y += ExtendedGCD.Y_ORIGIN;
			mouse_x += ExtendedGCDRobot.WINDOW_BORDER;
			mouse_y += ExtendedGCDRobot.WINDOW_BORDER;
			mouse_y += ExtendedGCDRobot.WINDOW_TITLE_HEIGHT;
			mouse_x += GCDController.X_ORIGIN;
			mouse_y += GCDController.Y_ORIGIN;
			mouse_x += ExtendedGCDRobot.WINDOW_BORDER;
			mouse_y += ExtendedGCDRobot.WINDOW_TITLE_HEIGHT;
			mouse_x += 10;
			mouse_y += 10;
			robot.mouseMove(mouse_x, mouse_y);

			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);

			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);

			// I close the GCDController window.
			mouse_y -= 10;
			mouse_x += GCDController.WIDTH;
			mouse_x -= 30;
			mouse_y -= ExtendedGCDRobot.WINDOW_TITLE_HEIGHT / 2;
			robot.mouseMove(mouse_x, mouse_y);

			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);

			// I initialize the mouse position.
			mouse_x = 0;
			mouse_y = 0;
			robot.mouseMove(mouse_x, mouse_y);

			// I close the first GCD window.
			mouse_x += ExtendedGCD.X_ORIGIN;
			mouse_x += ExtendedGCDRobot.WINDOW_BORDER;
			mouse_y += ExtendedGCD.Y_ORIGIN;
			mouse_y += ExtendedGCDRobot.WINDOW_BORDER;
			mouse_y += ExtendedGCDRobot.WINDOW_TITLE_HEIGHT;

			mouse_x += GCD.X_ORIGIN;
			mouse_y += GCD.Y_ORIGIN;

			mouse_x += GCD.WIDTH;
			mouse_x -= 20;
			mouse_y += ExtendedGCDRobot.WINDOW_TITLE_HEIGHT / 2;

			robot.mouseMove(mouse_x, mouse_y);

			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);

			// I initialize the mouse position.
			mouse_x = 0;
			mouse_y = 0;
			robot.mouseMove(mouse_x, mouse_y);

			// I close the application.
			mouse_x += ExtendedGCD.X_ORIGIN;
			mouse_x += ExtendedGCD.WIDTH;
			mouse_x -= ExtendedGCDRobot.WINDOW_BORDER;
			mouse_x -= 10;
			mouse_y += ExtendedGCD.Y_ORIGIN;
			mouse_y += ExtendedGCDRobot.WINDOW_BORDER;
			mouse_y += ExtendedGCDRobot.WINDOW_TITLE_HEIGHT / 2;
			robot.mouseMove(mouse_x, mouse_y);

			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		}
		catch (AWTException awte) {
			awte.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
	}
}