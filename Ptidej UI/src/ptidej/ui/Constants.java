/*
 * (c) Copyright 2001, 2002 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Gaël Guéhéneuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package ptidej.ui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public interface Constants {
	// TODO: The rounding from float to int makes necessary to use
	// this constant: improve the calculations to remove it.
	int FACTOR = 100000;
	int ROUNDING = 2;
	int DOT_LENGTH = 3;
	int TAB_LENGTH = 4;

	Dimension SCREEN_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();

	Point NULL_POSITION = new Point(0, 0);
	Point MAX_POSITION = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);

	Dimension NULL_DIMENSION = new Dimension(0, 0);
	Dimension MAX_DIMENSION =
		new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);

	Point DEFAULT_POSITION = new Point(10, 10);
	Dimension DEFAULT_DIMENSION = new Dimension(10, 10);

	RGB BACKGROUND_COLOR = new RGB(255, 255, 255);
	RGB FOREGROUND_COLOR = new RGB(0, 0, 0);

	Dimension CANVAS_INNER_GAP = new Dimension(10, 10);
	Dimension GAP_BETWEEN_ENTITIES = new Dimension(25, 25);

	int INHERITANCE_SYMBOL_GAP = 8;
	Dimension INHERITANCE_SYMBOL_DIMENSION = new Dimension(10, 10);
	Dimension AAC_SYMBOL_DIMENSION = new Dimension(8, 16);
	Dimension ARROW_SYMBOL_DIMENSION = new Dimension(8, 16);

	RGB IMPLEMENTATION_ELEMENT_DISPLAY_COLOR = new RGB(127, 127, 127);
	RGB SPECIALIZATION_ELEMENT_DISPLAY_COLOR = new RGB(0, 0, 0);
	RGB GHOST_ENTITY_DISPLAY_COLOR = new RGB(192, 192, 192);

	Dimension SOLUTION_HANDLE_DIMENSION = new Dimension(15, 15);
	Dimension SOLUTION_FRAME_GAP = new Dimension(5, 5);
	Dimension TIP_GAP = new Dimension(10, 7);
	RGB TIP_BACKGROUND_COLOR = new RGB(255, 255, 215);
	double MINIMUM_PERCENTAGE = 15 / 100d;

	int FONT_DESCENT = 4;
	int FONT_HEIGHT = 11;
	int FONT_WIDTH = 6;
	int FONT_HEIGHT_SWT_SHIFT = 2;
}
