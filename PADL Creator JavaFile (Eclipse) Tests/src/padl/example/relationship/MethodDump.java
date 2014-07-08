/*
 * Created on 2004-03-23
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package padl.example.relationship;

/**
 * @author Farouk ZAIDI - 2004-03-23
 * PADL Tests
 *
 */
public class MethodDump {
	private int counter = 0;
	private float f = 4f;
	private float[] myTab;
	private Integer p;
	private String str;
	public void analyzeDup() {
		this.myTab[this.counter++] = 3f;
		this.myTab[--this.counter] /= 5f;
	}
	public void controlIfWhileForSwitch() {

		final Integer aLocalInt = new Integer(0);
		for (final int i = 0; i < this.p.intValue();) {
			System.out.println(this.p.floatValue() + ":" + this.p);

			if (this.p != aLocalInt) {
				this.p.byteValue();

				switch (this.p.intValue()) {
					case 1 :
						final int myInt = this.p.intValue();
						final float r = this.p.floatValue() + this.f;
						System.out.println(myInt + r);
						break;
					case 3 :
					case 2 :
						this.p = null;
						break;
					default :

						{
							this.p = Integer.getInteger(this.str, 2);
							while (this.f > 0 || this.p != null) {
								this.f = this.f - 100;
							}
							break;
						}
				}
			}
		}
	}
	public void detectOperators() {
		this.f = this.p.floatValue() + 15f;
		int aLocalInt = this.p.intValue() << 7;
		aLocalInt >>= this.p.intValue() / 100 + (int) this.f;
	}
	public void dynamicMethod(
		final Integer anInteger,
		final String aString,
		final String anotherString,
		final int anInt) {
		this.str.substring(this.p.intValue(), this.str.length());
		this.str.charAt(15);
		final String myLocalStr = "My PADL is very strong!!!";
		myLocalStr.compareTo(this.str);
		this.dynamicMethod(this.p, myLocalStr, this.str, myLocalStr.length());
	}

	public void fooDup() {
		Integer a1 = null, a2 = null, a3 = null;
		new Integer(this.p.compareTo(a3 =
			a2 = a1 = new Integer(this.p.intValue())));
		System.out.println(" " + a1 + a2 + a3);
	}
	public void fooNew() {
		this.p = new Integer(54);
		this.p.compareTo(new Integer(this.p.intValue()));
		this.p.compareTo(this.p = new Integer(this.p.intValue()));
	}
	public void instanceOf() {
		final Integer localInteger = null;
		if (this.p instanceof Object) {
			final boolean b = this.str instanceof String;
			final boolean c = localInteger instanceof Number;
			System.out.println(" " + b + c);
		}
	}
	public void loadAndStoreInArray() {
		this.myTab = new float[15];
		this.myTab[5] = this.f;
		final float aLocalFloat = this.myTab[this.p.intValue()] + this.f;
		System.out.println(aLocalFloat);
	}
	public void method3aryOperator(final Integer aLocalParameter) {
		final float lp =
			this.p == aLocalParameter ? aLocalParameter.floatValue()
					: (float) Math.random();
		System.out.println(lp);
	}
	public void methodPrintln(final short s, final Integer aLocalParameter) {
		System.out.println(this.p.floatValue() + ":" + s
				+ aLocalParameter.compareTo((Integer) new Object()));
	}
	public void staticInvocations() {
		final String myLocalStr = "My PADL is stronger!!!";
		Math.random();
		Integer.parseInt("5");
		Integer.getInteger(myLocalStr, this.p);
		System.load(this.str);
	}
	public void storeToField() {
		this.p = new Integer(this.str);
		this.str = this.p.toString();
		final String myLocalStr = this.str;
		final Integer aLocalInt = this.p = Integer.getInteger(myLocalStr);
		System.out.println(aLocalInt);
	}
}