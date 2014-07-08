package ptidej.example.chainOfResponsibility1;

public class HelpHandler {
	public static int NO_HELP_TOPIC = -1;
	public static int WHAT_IS_HELP_TOPIC = 0;

	private HelpHandler successor = null;
	private int topic = HelpHandler.NO_HELP_TOPIC;
	public HelpHandler(final HelpHandler successor, final int topic) {
		this.successor = successor;
		this.topic = topic;
	}
	public void handleHelp() {
		if (this.successor != null) {
			this.successor.handleHelp();
		}
	}
	public boolean hasHelp() {
		return this.topic != HelpHandler.NO_HELP_TOPIC;
	}
	public void setHandler(final HelpHandler handler, final int topic) {
		this.successor = handler;
		this.topic = topic;
	}
}
