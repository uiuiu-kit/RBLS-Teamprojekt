package praesentation;

import java.awt.Color;

class Schaltflaeche extends javax.swing.JButton {

  /**
   * auto-generierte ID.
   */
  private static final long serialVersionUID = -6196848668908499618L;
  public final int ORANGE = 1;
  public final int GRAU = 2;
  public final int WEISS = 3;
  public final int WEISS_ALT = 4;

  public Schaltflaeche() {
    this.setBackground(new Color(255,102,0));
    this.setForeground(Color.WHITE);
  }

  public Schaltflaeche(int farbe) {
    setzeFarbDesign(farbe);
  }
  
  public Schaltflaeche(String text) {
    this.setBackground(new Color(255,102,0));
    this.setForeground(Color.WHITE);
    this.setText(text);
  }
  
  public Schaltflaeche(String text, int farbe) {
    this.setText(text);
    setzeFarbDesign(farbe);
  }
  
  private void setzeFarbDesign(int farbe) {
    switch (farbe) {
      case 1:
        this.setBackground(new Color(255,102,0));
        this.setForeground(Color.WHITE);
        break;
      case 2:
        this.setBackground(Color.LIGHT_GRAY);
        this.setForeground(Color.DARK_GRAY);
        break;
      case 3:
        this.setBackground(Color.WHITE);
        this.setForeground(new Color(255,102,0));
        break;
      case 4:
        this.setBackground(Color.WHITE);
        break;
      default:
        this.setBackground(Color.WHITE);
        break;
    }
  }
}