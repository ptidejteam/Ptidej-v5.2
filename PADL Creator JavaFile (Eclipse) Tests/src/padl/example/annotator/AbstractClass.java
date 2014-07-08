package padl.example.annotator;

public abstract class AbstractClass {
	public abstract void abstractMethod();

	public void myMethod() {
		final int a = 0;
		final int b = 0;
		final int x = a + b;
		System.out.println(x);

	}

}
