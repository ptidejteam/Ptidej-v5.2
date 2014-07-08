package padl.micropatterns.examples;

public class Immutable {
	// Not modified outside of the constructor
	private int anImmutableAttribute;
	private static int aMutableAttribute;
	
	public Immutable() {
		this.anImmutableAttribute = 0;
	}
	
	public void methodOne () {
		Immutable.aMutableAttribute = 0;
	}
	
	private void methodTwo () {
	}
}
