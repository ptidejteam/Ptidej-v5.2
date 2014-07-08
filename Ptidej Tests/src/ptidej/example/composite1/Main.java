package ptidej.example.composite1;

public class Main {
	public static void main(final String[] args) {
		final Document document = new Document();
		document.addComponent(new Title());
		document.addComponent(new Paragraph());
		document.addComponent(new Title());
		document.addComponent(new IndentedParagraph());
		document.addComponent(new IndentedParagraph());

		document.printAll();
	}
}
