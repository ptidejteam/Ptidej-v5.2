package ptidej.example.mediator1;

public abstract class Widget {
	private ViewManager viewManager;
	public Widget(final ViewManager viewManager) {
	}
	public abstract void callback();
	public ViewManager getViewManager() {
		return this.viewManager;
	}
}
