package padl.micropatterns.examples;

public class RestrictedCreation {
	private static RestrictedCreation uniqueInstance;
	private int intOne;
	
	// private constructor
	private RestrictedCreation() {
	}
	
	public void methodTwo () {		
		this.intOne = 0;
	}
}
