package modell.tabelle;

import modell.formel.Formel;

/**
 * Abstrakte Oberklasse Zelle. Die Wahrheitswertzelle und die Formelzelle erben von dieser Klasse.

 * @author janne
 *
 */
public abstract class Zelle {
  
  /**
   * Gibt die String-Repraesentation der Zelle zurueck.
   */
  public abstract String toString();

  /**
   * Setzt den Wert der Zelle auf den uebergebenen Wahrheitswert.

   * @param w der neue Wahrheitswert, den die Zelle beinhalten soll.
   */
  public void setzeZelle(boolean w) {
  }
  
  /**
   * Uebernimmt die uebergebene Formel als neuen Inhalt der Zelle.

   * @param f neue Formel, die die Zelle beinhalten soll.
   */
  public void setzeZelle(Formel f) {
  }
  
}
