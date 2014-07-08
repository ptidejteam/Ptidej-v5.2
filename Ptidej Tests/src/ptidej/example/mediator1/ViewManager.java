package ptidej.example.mediator1;

import java.util.Vector;

public class ViewManager {
	private final Vector elements;
	public ViewManager() {
		this.elements = new Vector();
	}
	public void add(final Widget widget) {
		this.elements.addElement(widget);
	}
	public Widget get(final int index) {
		return (Widget) this.elements.elementAt(index);
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (12/04/2001 15:25:28)
	 */
	public void operation() {
	}
}
