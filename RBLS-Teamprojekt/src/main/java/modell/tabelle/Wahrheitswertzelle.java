package modell.tabelle;

/**
 * Symbolisiert den speziellen Typ Zelle Wahrheitswertzelle.
 * Sie erbt von der Zelle und beinhaltet nur Wahrheitswerte.
 * @author janne
 *
 */
public class Wahrheitswertzelle extends Zelle {
  
  private boolean wahrheitswert;

  public void setzeZelle(boolean w) {
    this.wahrheitswert = w;    
  }
  
  /**
   * Gibt den momentanen Wahrheitswert der Zelle zurueck.
   * @return true, wenn die Zelle den Wahrheitswert "true" enthaelt, sonst false.
   */
  public boolean gibZustand() {
    return wahrheitswert;
  }

  @Override
  public String toString() {
    return (wahrheitswert) ? "true" : "false";
  }

}
