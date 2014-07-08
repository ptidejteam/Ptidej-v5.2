package padl.example.annotator;

public class B {
	A a;
	public B() {
		this.a = new A("m");
	}

	public void m() {
		this.a.print();
		this.a.setM("b");

	}

	public void m1(final A _a) {
		_a.print();
		_a.setM("k");

	}

}
