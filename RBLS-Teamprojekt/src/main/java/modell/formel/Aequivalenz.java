package modell.formel;

/**
 * Unterklasse des Bikonnektors. Logisch Aequivalenz von dem linken
 * Formelnachbarn dem rechten Formelnachbarn. Seine String Repraesentation ist
 * "a".
 * 
 * @author Jonathan
 *
 */
public class Aequivalenz extends BiKonnektor {

  /**
   * Kostruktor. Setzt uebergebene Werte und seine String Rep.
   * 
   * @param rechts Die rechte Formel.
   * @param links  Die linke Formel.
   */
  public Aequivalenz(Formel links, Formel rechts) {
    this.rechts = rechts;
    this.links = links;
    this.rep = "\u2194";
    this.bindungsstaerke = 0;
    this.zeichen = "a";
  }

  @Override
  public boolean auswerten(boolean[] werte) {
    if (links.auswerten(werte) == rechts.auswerten(werte)) {
      return true;
    }
    return false;
  }
}
