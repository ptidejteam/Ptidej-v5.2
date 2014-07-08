package ptidej.example.mediator1;

public class TextPane extends Widget {
	public TextPane(final ViewManager viewManager) {
		super(viewManager);
	}
	public void callback() {
		// Some operation on the ViewManager.
		final ViewManager viewManager = this.getViewManager();
		viewManager.operation();
	}
}
