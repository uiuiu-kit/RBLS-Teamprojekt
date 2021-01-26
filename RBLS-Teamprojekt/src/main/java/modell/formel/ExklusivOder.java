package modell.formel;

/**Unterklasse des Bikonnektors.
 * Verknüft seinen linken und rechten Formelnachbarn mit einem logischen EXOR beim Auzswerten.
 * Seine String Repräsentation ist "x".
 * @author Flo
 *
 */
public class ExklusivOder extends BiKonnektor {

  /** Kostruktor. Setzt übergebene Werte und seine String Rep.
   * @param rechts rechte Formel
   * @param links linke Formel
   */
  public ExklusivOder(Formel rechts, Formel links) {
    this.rechts = rechts;
    this.links = links;
    this.rep = "x";
  }

  @Override
  public boolean auswerten(boolean[] werte) {
    return links.auswerten(werte) ^ rechts.auswerten(werte);
  }
}
