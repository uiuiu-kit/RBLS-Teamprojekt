package modell.formel;

/**Unterklasse des Bikonnektors.
 * Impliziert logisch aus dem linken Formelnachbarn den rechten Formelnachbarn.
 * Seine String Repraesentation ist "f".

 * @author Flo
 *
 */
public class Implikation extends BiKonnektor {

  /** Kostruktor. Setzt uebergebene Werte und seine String Rep.

   * @param rechts Die rechte Formel.
   * @param links Die linke Formel.
   */
  public Implikation(Formel links, Formel rechts) {
    this.rechts = rechts;
    this.links = links;
    this.rep = "\u2192";
    this.bindungsstaerke = 1;
  }
  
  @Override
  public boolean auswerten(boolean[] werte) {
    if (links.auswerten(werte) == true && rechts.auswerten(werte) == false) {
      return false;
    }
    return true;
  }
}
