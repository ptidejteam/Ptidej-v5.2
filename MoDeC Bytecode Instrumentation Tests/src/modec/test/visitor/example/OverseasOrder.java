package modec.test.visitor.example;

//package src.VISITOR;
public class OverseasOrder implements Order {
  private double orderAmount;
  private double additionalSH;

  public OverseasOrder() {
  }
  public OverseasOrder(double inp_orderAmount,
      double inp_additionalSH) {
    this.orderAmount = inp_orderAmount;
    this.additionalSH = inp_additionalSH;
  }
  public double getOrderAmount() {
    return this.orderAmount;
  }
  public double getAdditionalSH() {
    return this.additionalSH;
  }
  public void accept(OrderVisitor v) {
    v.visit(this);
  }
}
