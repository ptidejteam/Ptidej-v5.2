package modec.test.visitor.example;

//package src.VISITOR;
public interface Order {
  public void accept(OrderVisitor v);
}
