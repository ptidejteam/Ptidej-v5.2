package ptidej.solver.test.data.composite;

import java.util.Vector;

public class B implements A {

	private final Vector children = new Vector();

	public void Add(final A anA) {
		this.children.add(anA);
	}

	public void Operation() {
		for (int i = 0; i < this.children.size(); i++) {
			((A) this.children.get(i)).Operation();
		}
	}

	public void Remove(final A anA) {
		this.children.remove(anA);
	}
}

class BB extends B {
	public void foo() {

	}
}
