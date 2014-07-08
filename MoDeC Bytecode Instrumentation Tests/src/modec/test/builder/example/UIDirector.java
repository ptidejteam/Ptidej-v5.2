package modec.test.builder.example;

public class UIDirector {
  private UIBuilder builder;

  public UIDirector(UIBuilder bldr) {
    this.builder = bldr;
  }
  public void build() {
    this.builder.addUIControls();
    this.builder.initialize();
  }

}

