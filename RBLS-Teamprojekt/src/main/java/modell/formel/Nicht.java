package modell.formel;

/** Der Nicht-Konnektor erbt direkt vom Konnektor und besitzt ein Formelobjekt als rechten Nachbarn.
 * Dessen Wahrheitswert negiert es beim Auswerten. 
 * Seine String-Repraesentation ist "n".

 * @author Flo
 *
 */
public class Nicht extends Konnektor {
  
 
  /** Konstruktor. Setzt die rechte Formel.

   * @param rechts Die rechte Formel.
   */
  public Nicht(Formel rechts) {
    this.rechts = rechts;
    this.rep = "\u00AC";
  }
  
  @Override
  public boolean auswerten(boolean[] werte) {
    return !(rechts.auswerten(werte));
  }

  @Override
  public String gibStringRep() {
    return "(" + this.rep + this.rechts.gibStringRep() + ")";
  }

}
