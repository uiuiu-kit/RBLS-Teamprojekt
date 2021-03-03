package modell.formel;

/**Unterklasse des Bikonnektors.
 * Verknuepft seinen linken und rechten Formelnachbarn mit einem logischen EXOR beim Auswerten.
 * Seine String Repraesentation ist "x".

 * @author Flo
 *
 */
public class ExklusivOder extends BiKonnektor {

  /** Kostruktor. Setzt uebergebene Werte und seine String Rep.

   * @param rechts Die rechte Formel.
   * @param links Die linke Formel.
   */
  public ExklusivOder(Formel links, Formel rechts) {
    this.rechts = rechts;
    this.links = links;
    this.rep = "\u2295";
    this.bindungsstaerke = 3;
  }

  @Override
  public boolean auswerten(boolean[] werte) {
    return links.auswerten(werte) ^ rechts.auswerten(werte);
  }
}
