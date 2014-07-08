package padl.micropatterns.examples;


public class CompoundBox {
	private NonPrimitive instanceAttribute;
	private int fieldOne;
	private int fieldTwo;
	
	// private constructor
	private CompoundBox() {
	}
	
	private void methodOne () {	
		this.instanceAttribute = null;
		this.fieldOne = 0;
		this.fieldTwo = 0;
	}
	
	public void methodTwo () {		
	}
}
