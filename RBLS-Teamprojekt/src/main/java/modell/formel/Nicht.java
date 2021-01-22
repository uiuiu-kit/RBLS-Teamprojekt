package modell.formel;

public class Nicht extends Konnektor {
  
 
  /** Konstruktor. setzt die rechte Formel.
   * @param rechts rechte Formel
   */
  public Nicht(Formel rechts) {
    this.rechts = rechts;
    this.rep = "n";
  }
  
  @Override
  public boolean auswerten(boolean[] werte) {
    return !(rechts.auswerten(werte));
  }

  @Override
  public String gibStringRep() {
    return this.rep + this.rechts.gibStringRep();
  }

}