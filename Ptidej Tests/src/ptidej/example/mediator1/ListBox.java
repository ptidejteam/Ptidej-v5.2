package ptidej.example.mediator1;

public class ListBox extends Widget {
	public ListBox(final ViewManager viewManager) {
		super(viewManager);
	}
	public void callback() {
		// Some operation on the ViewManager.
		final ViewManager viewManager = this.getViewManager();
		viewManager.operation();
	}
}
