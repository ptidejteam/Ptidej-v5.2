package modec.test.visitor.example;

//package src.VISITOR;
public class CaliforniaOrder implements Order {
  private double orderAmount;
  private double additionalTax;

  public CaliforniaOrder() {
  }
  public CaliforniaOrder(double inp_orderAmount,
      double inp_additionalTax) {
    this.orderAmount = inp_orderAmount;
    this.additionalTax = inp_additionalTax;
  }
  public double getOrderAmount() {
    return this.orderAmount;
  }
  public double getAdditionalTax() {
    return this.additionalTax;
  }
  public void accept(OrderVisitor v) {
    v.visit(this);
  }
}

