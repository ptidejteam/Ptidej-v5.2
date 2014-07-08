package padl.example.annotator;

public class C {
	public static void staticMethod() {
		System.out.println();

	}
	String s;

	public C() {
		super();
		this.s = new String("ttttt");
	}

	public void nonStaticMethod() {
		C.staticMethod();
		C.staticMethod();
		this.s.toString();
		super.toString();
		this.s.toString();
		this.s = "";
	}

}
