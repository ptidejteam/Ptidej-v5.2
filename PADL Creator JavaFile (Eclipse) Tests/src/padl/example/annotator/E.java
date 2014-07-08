package padl.example.annotator;

public class E {

	public static void main(final String args[]) {
		final E a = new E("ma");
		a.print();
		a.setM("mo");
		a.print();
		if (a instanceof E) {
			System.out.println();
		}
		else {
			System.out.println();
			for (int i = 0; i < 10; i++) {
				a.print();
				System.out.println(i);
			}
		}
	}

	String m;

	public E(final String _m) {

		this.setM(_m);
		this.setM(_m);
		this.setM(_m);
		this.setM(_m);

	}

	public void method1(final int k, final int j) {
		if (k == 4) {
			switch (j) {
				case 1 :
					System.out.println(j);
				case 2 :
					System.out.println(j);
				case 3 :
					System.out.println(j);
				case 4 :
					System.out.println(j);
				case 5 :
					System.out.println(j);
				default :
					System.out.println(j);
			}
		}
		else {
			switch (j) {
				case 1 :
					System.out.println(j);
				case 2 :
					System.out.println(j);
				case 3 :
					System.out.println(j);
				default :
					System.out.println(j);
			}
		}
	}

	public void print() {
		System.out.println(this.m);
	}

	public void setM(final String _m) {
		this.m = _m;
	}

}
