package ptidej.example.mediator1;

public class Main {
	public static void main(final String[] args) {
		final ViewManager viewManager = new ViewManager();
		final Button button1 = new Button(viewManager);
		final Button button2 = new Button(viewManager);
		final ListBox listBox = new ListBox(viewManager);
		final TextPane textPane = new TextPane(viewManager);

		viewManager.add(button1);
		viewManager.add(button2);
		viewManager.add(listBox);
		viewManager.add(textPane);
	}
}
