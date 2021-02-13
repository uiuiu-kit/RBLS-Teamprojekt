package praesentation;

import java.awt.Color;

/**
 * Design der Buttons ausgelagert in eine Unterklasse von JButton.
 * @author Nick
 */
class Schaltflaeche extends javax.swing.JButton {

  /**
   * auto-generierte ID.
   */
  private static final long serialVersionUID = -6196848668908499618L;
  public static final int ORANGE = 1;
  public static final int GRAU = 2;
  public static final int WEISS = 3;
  public static final int WEISS_ALT = 4;
  public static final int GRAU_ALT = 5;
  public static final int GRAU_ALT_2 = 6;

  public Schaltflaeche() {
    this.setBorderPainted(false);
    this.setBackground(new Color(255,102,0));
    this.setForeground(Color.WHITE);
  }

  public Schaltflaeche(int farbe) {
    setzeFarbDesign(farbe);
  }
  
  public Schaltflaeche(String text) {
    this.setBorderPainted(false);
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
        this.setBorderPainted(false);
        break;
      case 2:
        this.setBackground(Color.LIGHT_GRAY);
        this.setForeground(Color.DARK_GRAY);
        this.setBorderPainted(false);
        break;
      case 3:
        this.setBackground(Color.WHITE);
        this.setForeground(new Color(255,102,0));
        break;
      case 4:
        this.setBackground(Color.WHITE);
        break;
      case 5:
        this.setBackground(Color.LIGHT_GRAY);
        this.setForeground(new Color(255,102,0));
        break;
      case 6:
        this.setBackground(Color.LIGHT_GRAY);
        this.setForeground(Color.DARK_GRAY);
        break;
      default:
        this.setBackground(Color.WHITE);
        break;
    }
  }
  
}