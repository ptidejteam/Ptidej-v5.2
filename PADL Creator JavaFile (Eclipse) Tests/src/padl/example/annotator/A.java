package padl.example.annotator;

public class A {

	public static void main() {
		final A a = new A("ma");
		a.print();
		a.setM("mo");
		a.print();
	}

	String m;

	public A(final String _m) {

		this.setM(_m);
	}

	public void print() {
		System.out.println(this.m);
	}

	public void setM(final String _m) {
		this.m = _m;
	}
}
