package padl.example.methodInvocation;

import padl.example.methodInvocation1.B;
import padl.example.methodInvocation1.C;
import padl.example.methodInvocation1.D;

public abstract class A extends B implements
		C, D {
	public boolean isEmpty() {
		size();
		return false;
	}

}
