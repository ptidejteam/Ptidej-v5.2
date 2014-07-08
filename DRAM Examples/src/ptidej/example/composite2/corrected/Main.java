package ptidej.example.composite2.corrected;

public class Main {
public static void main(String[] args) {
	Document document = new Document();
	document.addElement(new Title());
	document.addElement(new Paragraph());
	document.addElement(new Title());
	document.addElement(new ParaIndent());
	document.addElement(new ParaIndent());

	document.printAll();
}
}
