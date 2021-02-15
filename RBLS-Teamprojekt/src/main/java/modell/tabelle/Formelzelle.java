package modell.tabelle;

import modell.formel.Formel;

/**
 * Symbolisiert eine Formelzelle. Sie erbt von der Zelle und beinhaltet ausschließlich Formeln.

 * @author janne
 *
 */
public class Formelzelle extends Zelle {
  
  private Formel aussagenlogischeFormel;
  
  public void setzeZelle(Formel f) {
    this.aussagenlogischeFormel = f;
  }
  
  /**
   * Gibt den aktuellen Inhalt der Zelle, also die Formel zurueck.

   * @return Ein Objekt des Typs Formel.
   */
  public Formel gibZustand() {
    return aussagenlogischeFormel;
  }

  @Override
  public String toString() {
    return aussagenlogischeFormel.gibStringRep();
  }

}
