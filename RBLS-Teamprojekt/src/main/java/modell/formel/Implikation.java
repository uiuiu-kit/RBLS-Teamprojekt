package modell.formel;

/**Unterklasse des Bikonnektors.
 * Impliziert logisch aus dem linken Formelnachbarn den rechten Formelnachbarn.
 * Seine String Repräsentation ist "f".
 * @author Flo
 *
 */
public class Implikation extends BiKonnektor {

  /** Kostruktor. Setzt übergebene Werte und seine String Rep.
   * @param rechts rechte Formel
   * @param links linke Formel
   */
  public Implikation(Formel rechts, Formel links) {
    this.rechts = rechts;
    this.links = links;
    this.rep = "f";
  }
  
  @Override
  public boolean auswerten(boolean[] werte) {
    if (links.auswerten(werte) == true && rechts.auswerten(werte) == false) {
      return false;
    }
    return true;
  }
}
