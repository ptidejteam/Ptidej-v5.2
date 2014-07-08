package modec.test.visitor.example;

//package src.VISITOR;

public class NonCaliforniaOrder implements Order {
  private double orderAmount;

  public NonCaliforniaOrder() {
  }
  public NonCaliforniaOrder(double inp_orderAmount) {
    this.orderAmount = inp_orderAmount;
  }
  public double getOrderAmount() {
    return this.orderAmount;
  }
  public void accept(OrderVisitor v) {
    v.visit(this);
  }
}
