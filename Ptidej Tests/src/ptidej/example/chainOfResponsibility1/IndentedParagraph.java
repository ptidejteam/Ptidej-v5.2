package ptidej.example.chainOfResponsibility1;

public class IndentedParagraph extends Paragraph {
	public IndentedParagraph(final Element parent, final int topic) {
		super(parent, topic);
	}
	public void handleHelp() {
		if (this.hasHelp()) {
			System.out.println("Display IndentedParagraph help here!");
		}
		else {
			((HelpHandler) this).handleHelp();
		}
	}
}
