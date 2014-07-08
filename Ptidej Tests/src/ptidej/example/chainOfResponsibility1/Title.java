package ptidej.example.chainOfResponsibility1;

public class Title extends Element {
	public Title(final Element parent, final int topic) {
		super(parent, topic);
	}
	public void handleHelp() {
		if (this.hasHelp()) {
			System.out.println("Display Title help here!");
		}
		else {
			((HelpHandler) this).handleHelp();
		}
	}
}
