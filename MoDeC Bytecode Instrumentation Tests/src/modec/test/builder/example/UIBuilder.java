package modec.test.builder.example;

import javax.swing.JPanel;

public abstract class UIBuilder {
  protected JPanel searchUI;

  public abstract void addUIControls();
  public abstract void initialize();
  public abstract String getSQL();

  public JPanel getSearchUI() {
    return this.searchUI;
  }
}

