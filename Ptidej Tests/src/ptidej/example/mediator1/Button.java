package ptidej.example.mediator1;

public class Button extends Widget {
	public Button(final ViewManager viewManager) {
		super(viewManager);
	}
	public void callback() {
		// Some operation on the ViewManager.
		final ViewManager viewManager = this.getViewManager();
		viewManager.operation();
	}
}
