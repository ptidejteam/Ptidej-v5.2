package ptidej.example.chainOfResponsibility1;

public class Client {
	public static void main(final String[] args) {

		// Declaration.

		Document document;
		Title title;
		Paragraph paragraph;
		IndentedParagraph indentedParagraph;

		// Construction.

		document = new Document();

		title = new Title(document, HelpHandler.WHAT_IS_HELP_TOPIC);
		document.addElement(title);
		paragraph = new Paragraph(title, HelpHandler.WHAT_IS_HELP_TOPIC);
		document.addElement(paragraph);

		title = new Title(document, HelpHandler.WHAT_IS_HELP_TOPIC);
		document.addElement(title);
		paragraph = new Paragraph(document, HelpHandler.WHAT_IS_HELP_TOPIC);
		document.addElement(paragraph);
		indentedParagraph =
			new IndentedParagraph(paragraph, HelpHandler.WHAT_IS_HELP_TOPIC);
		document.addElement(indentedParagraph);
		indentedParagraph =
			new IndentedParagraph(paragraph, HelpHandler.WHAT_IS_HELP_TOPIC);
		document.addElement(indentedParagraph);

		// Use.

		System.out
			.println("-- HELP -----------------------------------------------");
		indentedParagraph.handleHelp();
		title.handleHelp();
		document.handleHelp();
		System.out
			.println("-- PRINT ----------------------------------------------");
		document.printAll();
	}
}
