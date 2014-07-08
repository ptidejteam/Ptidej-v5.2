package ptidej.example.composite22.corrected;

public class Main {
	public static void main(final String[] args) {
		final Document document = new Document();
		document.addElement(new Title());
		document.addElement(new Paragraph());
		document.addElement(new Title());
		document.addElement(new ParaIndent());
		document.addElement(new ParaIndent());

		document.printAll();
	}
}
