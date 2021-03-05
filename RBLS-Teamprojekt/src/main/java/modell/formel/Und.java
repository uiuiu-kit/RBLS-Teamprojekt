package modell.formel;

/**Unterklasse des Bikonnektors.
 * Verknuepft seinen linken und rechten Formelnachbarn mit einem logischen UND beim Auswerten.
 * Seine String Repraesentation ist "u".

 * @author Flo
 *
 */
public class Und extends BiKonnektor {

  /** Kostruktor. Setzt uebergebene Werte und seine String Rep.

   * @param rechts Die rechte Formel.
   * @param links Die linke Formel.
   */
  public Und(Formel links, Formel rechts) {
    this.rechts = rechts;
    this.links = links;
    this.rep = "\u2227";
    this.bindungsstaerke = 4;
    this.zeichen = "u";
  }
  
  @Override
  public boolean auswerten(boolean[] werte) {
    return (links.auswerten(werte) && rechts.auswerten(werte));
  }
  
}
