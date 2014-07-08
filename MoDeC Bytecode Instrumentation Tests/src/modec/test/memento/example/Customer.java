package modec.test.memento.example;

//package src.MEMENTO;
public class Customer {
  private String ID;
  private String firstName;
  private String lastName;
  private String cardNum;

  public Customer(String id, String fn, String ln,
                  String CCNum) {
    this.ID = id;
    this.firstName = fn;
    this.lastName = ln;
    this.cardNum = CCNum;
  }
  public String getSQL() {
    String str =
      "Insert into Customer(ID, fname, lname,ccnum)" +
      "values(" + this.ID + ",'" + this.firstName + "','" +
      this.lastName + "','" + this.cardNum + "');";
    return str;
  }
  public boolean isValid() {
    String validChars = "0123456789";
    boolean result = true;

    if (this.lastName.trim().length() == 0) {
      result = false;
    }
    for (int i = 0; i < this.cardNum.length(); i++) {
      if (validChars.indexOf(this.cardNum.substring(i, i + 1)) <
          0) {
        result = false;
        break;
      }
    }

    return result;
  }
}
