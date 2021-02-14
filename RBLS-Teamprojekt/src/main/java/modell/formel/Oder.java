package modell.formel;

/**Unterklasse des Bikonnektors.
 * Verknuepft seinen linken und rechten Formelnachbarn mit einem logischen ODER beim Auzswerten.
 * Seine String Repraesentation ist "o".
 * @author Flo
 *
 */
public class Oder extends BiKonnektor {

  /** Kostruktor. Setzt uebergebene Werte und seine String Rep.
   * @param rechts rechte Formel
   * @param links linke Formel
   */
  public Oder(Formel links, Formel rechts) {
    this.rechts = rechts;
    this.links = links;
    this.rep = "o";
  }
  
  @Override
  public boolean auswerten(boolean[] werte) {
    return (links.auswerten(werte) || rechts.auswerten(werte));
  }

}
