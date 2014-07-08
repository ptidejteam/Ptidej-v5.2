package ptidej.example.memento2;

public abstract class Element implements AbstractDocument, AbstractElement {
	public void print() {
		System.out.println(this.getClass());
	}
}
