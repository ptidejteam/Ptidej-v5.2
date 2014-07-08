package ptidej.example.facade1;

public class Main {
	public static void main(final String[] args) {
		final Main main = new Main();
		main.compiler = new Compiler();
		main.compiler.compile();
	}
	private Compiler compiler;
}
