package ptidej.example.chainOfResponsibility1;

public abstract class Element extends HelpHandler implements AbstractDocument,
		AbstractElement {
	public Element(final Element parent, final int topic) {
		super(parent, topic);
	}
	public void print() {
		System.out.println(this.getClass());
	}
}
