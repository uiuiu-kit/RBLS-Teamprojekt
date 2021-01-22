package modell.formel;

public class Nicht extends Konnektor {
  
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
