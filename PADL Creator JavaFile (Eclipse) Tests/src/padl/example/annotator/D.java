package padl.example.annotator;

public class D {

	C nonStaticField;
	static C staticField;

	static void staticCallingMethod(final C parameter) {
		final C localVariable = new C();
		C localVariable1;
		localVariable1 = new C();
		C.staticMethod();
		localVariable.nonStaticMethod();
		C.staticMethod();
		parameter.nonStaticMethod();
		C.staticMethod();
		D.staticField.nonStaticMethod();
		C.staticMethod();

	}

	void nonstaticCallingMethod(final C parameter) {
		final C localVariable = new C();
		C.staticMethod();
		localVariable.nonStaticMethod();
		C.staticMethod();
		parameter.nonStaticMethod();
		C.staticMethod();
		D.staticField.nonStaticMethod();
		C.staticMethod();
		this.nonStaticField.nonStaticMethod();
		C.staticMethod();
	}
}
