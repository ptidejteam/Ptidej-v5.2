package ptidej.example.chainOfResponsibility1;

public class Document extends Element {
	private Element[] elements;
	private int nbOfElements;
	public Document() {
		super(null, HelpHandler.NO_HELP_TOPIC);
	}
	public Document(final Element parent, final int topic) {
		super(parent, topic);
	}
	public void addElement(final Element e) {
		if (this.elements == null) {
			this.elements = new Element[4];
		}
		else if (this.nbOfElements == this.elements.length) {
			final Element[] temp = new Element[this.nbOfElements * 2];
			System.arraycopy(this.elements, 0, temp, 0, this.nbOfElements);
			this.elements = temp;
		}
		this.elements[this.nbOfElements] = e;
		this.nbOfElements++;
	}
	public Element getElement(final int pos) {
		return this.elements[pos];
	}
	public void printAll() {
		for (int i = 0; i < this.nbOfElements; i++) {
			this.elements[i].print();
		}
	}
	public Element removeElement(final Element e) {
		return null;
	}
}
