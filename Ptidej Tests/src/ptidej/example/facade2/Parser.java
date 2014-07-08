package ptidej.example.facade2;

public class Parser {
	private Scanner scanner;
	public void parse() {
		this.scanner = new Scanner();
		final int length = 5;
		for (int i = 0; i < length; i++) {
			this.scanner.nextToken();
		}
	}
}
