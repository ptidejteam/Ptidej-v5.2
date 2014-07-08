package modec.test.visitor.example;

//package src.VISITOR;

class OrderVisitor implements VisitorInterface {
	private double orderTotal;

	public OrderVisitor() {
	}
	public void visit(NonCaliforniaOrder inp_order) {
		this.orderTotal = this.orderTotal + inp_order.getOrderAmount();
	}
	public void visit(CaliforniaOrder inp_order) {
		this.orderTotal =
			this.orderTotal + inp_order.getOrderAmount()
					+ inp_order.getAdditionalTax();
	}
	public void visit(OverseasOrder inp_order) {
		this.orderTotal =
			this.orderTotal + inp_order.getOrderAmount()
					+ inp_order.getAdditionalSH();
	}
	public double getOrderTotal() {
		return this.orderTotal;
	}
}
