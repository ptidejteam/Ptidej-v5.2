package ptidej.example.composite1;

import java.util.Enumeration;
import java.util.Vector;

public class Document {
	private final Vector elements = new Vector();
	public void addComponent(final Element e) {
		this.elements.addElement(e);
	}
	public Element getComponent(final int pos) {
		return (Element) this.elements.elementAt(pos);
	}
	public void printAll() {
		final Enumeration e = this.elements.elements();
		while (e.hasMoreElements()) {
			((AbstractDocument) e.nextElement()).print();
		}
	}
	public Element removeComponent(final Element e) {
		return null;
	}
}
