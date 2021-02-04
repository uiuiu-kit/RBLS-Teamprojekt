package praesentation;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Grafische Formelansicht zum Eingeben von Formeln als Pop-Up-Fenster.
 * Zeigt Buttons mit Zeichen und Buttons mit atomaren Aussagen an.
 * @author Nick
 */
public class FormelAnsicht {
  JFrame ansicht;
  JLabel formelAnzeige;
  Schaltflaeche bestaetige;
  Schaltflaeche abbruch;
  
  String alteFormel;
  String formel;
  
  Schaltflaeche[] atomareAussagen;
  Schaltflaeche und = new Schaltflaeche(3);
  Schaltflaeche oder = new Schaltflaeche(3);
  Schaltflaeche nicht = new Schaltflaeche(3);
  Schaltflaeche impliziert = new Schaltflaeche(3);
  Schaltflaeche xor = new Schaltflaeche(3);
  Schaltflaeche klammerAuf = new Schaltflaeche(3);
  Schaltflaeche klammerZu = new Schaltflaeche(3);
  
  public FormelAnsicht(String[] atomareAussagen) {
    
  }
  
  public String getFormel() {
    return formel;
  }
  
  private void fuegeHinzu(String zeichen) {
    
  }
  
  private void bestaetige() {
    
  }
  
  private void brecheAb() {
    
  }
}
