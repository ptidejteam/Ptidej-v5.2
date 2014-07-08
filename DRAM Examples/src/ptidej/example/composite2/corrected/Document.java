package ptidej.example.composite2.corrected;

public class Document extends Element {
	private Element[] elements;
	private int nbOfElements;
	public void addElement(Element e) {
		if (this.elements == null) {
			this.elements = new Element[4];
		}
		else if (this.nbOfElements == this.elements.length) {
			Element[] temp = new Element[this.nbOfElements * 2];
			System.arraycopy(this.elements, 0, temp, 0, this.nbOfElements);
			this.elements = temp;
		}
		this.elements[this.nbOfElements] = e;
		this.nbOfElements++;
	}
	public Element getElement(int pos) {
		return this.elements[pos];
	}
	public void printAll() {
		for (int i = 0; i < this.nbOfElements; i++) {
			this.elements[i].print();
		}
	}
	public Element removeElement(Element e) {
		return null;
	}
}
