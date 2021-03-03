package modell.formel;

/** Das Atom enthaelt den Wahrheitswert der atomaren Aussage 
 * und den Namen des repraesentierten Subjekts. 
 * Es erbt direkt von der Formel, ist also kein Konnektor 
 * und stellt damit das Ende jeder auswerten()-Kette dar.
 * Es wird als Großbuchstabe in der Formel dargestellt.

 * @author Flo
 *
 */
public class Atom extends Formel {

  private String aussage;
  private String rep;
  private int nummer;
    
  /** Konstruktor. Setzt nur seine Werte, die er uebergeben bekommt.

   * @param aussage Entspricht dem vollen Namen des Atoms (zB Herbert).
   * @param nummer Position im Werte-Array.
   */
  public Atom(String aussage, int nummer) {
    this.aussage = aussage + "(" + nummer + ")";
    if (nummer == -1) {
      this.rep = "Formel einfügen";
    } else {
      this.rep = "" + nummer;
    }
    this.nummer = nummer;
    this.bindungsstaerke = 6;
  }

  public String getAussage() {
    return aussage;
  }

  @Override
  public boolean auswerten(boolean[] werte) {
    return werte[nummer];
  }

  @Override
  public String gibStringRep() {
    return rep;
  }
}
