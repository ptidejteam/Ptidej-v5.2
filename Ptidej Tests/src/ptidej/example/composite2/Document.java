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
package ptidej.example.composite2;

import java.util.Vector;

public class Document {
	//	private Element[] elements;
	//	private int nbOfElements;
	//
	//	public void addElement(final Element e) {
	//		if (elements == null) {
	//			elements = new Element[4];
	//		}
	//		else if (nbOfElements == elements.length) {
	//			final Element[] temp = new Element[nbOfElements * 2];
	//			System.arraycopy(elements, 0, temp, 0, nbOfElements);
	//			elements = temp;
	//		}
	//		elements[nbOfElements] = e;
	//		nbOfElements++;
	//	}
	//	public Element getElement(final int pos) {
	//		return elements[pos];
	//	}
	//	public void printAll() {
	//		for (int i = 0; i < nbOfElements; i++) {
	//			elements[i].print();
	//		}
	//	}
	//	public Element removeElement(final Element e) {
	//		return null;
	//	}

	private final Vector elements = new Vector();

	public void addElement(final Element e) {
		this.elements.add(e);
	}
	public Element getElement(final int pos) {
		return (Element) this.elements.get(pos);
	}
	public void printAll() {
		for (int i = 0; i < this.elements.size(); i++) {
			((Element) this.elements.get(i)).print();
		}
	}
	public Element removeElement(final Element e) {
		this.elements.remove(e);
		return e;
	}
}
