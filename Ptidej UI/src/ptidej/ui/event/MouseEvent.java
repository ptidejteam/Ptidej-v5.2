/*
 * (c) Copyright 2001-2004 Yann-Gaël Guéhéneuc,
 * University of Montréal.
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
package ptidej.ui.event;

/*
 * I use my own MouseEvent. There is a good reason to this.
 * I want my framework to be graphic-framework independent.
 * Thus, I don't want the class ptidej.ui.Canvas to depend on
 * a specific implementation of AWT or SWT MouseEvent.
 * What happens is that an implementation specific Canvas
 * class (see for instance ptidej.viewer.AWTCanvas) wraps the
 * ptidej.ui.Canvas class and creates an instance of
 * ptidej.ui.event.MouseEvent from an instance of
 * java.awt.event.MouseEvent (resiprocally for SWT).
 */
public final class MouseEvent {
	public static final int MOUSE_CLICKED = 1;
	public static final int MOUSE_PRESSED = 2;
	public static final int MOUSE_RELEASED = 3;
	public static final int MOUSE_MOVED = 4;
	public static final int MOUSE_ENTERED = 5;
	public static final int MOUSE_EXITED = 6;
	public static final int MOUSE_DRAGGED = 7;

	private final Object source;
	private final int id;
	private final int x;
	private final int y;
	public MouseEvent(
		final Object aSource,
		final int anID,
		final int x,
		final int y) {

		this.source = aSource;
		this.id = anID;
		this.x = x;
		this.y = y;
	}
	public int getID() {
		return this.id;
	}
	public Object getSource() {
		return this.source;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
}
