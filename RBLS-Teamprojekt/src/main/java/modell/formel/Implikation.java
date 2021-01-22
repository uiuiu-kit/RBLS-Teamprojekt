package modell.formel;

public class Implikation extends BiKonnektor {

  public Implikation(Formel rechts, Formel links) {
    this.rechts = rechts;
    this.links = links;
  }
  
  @Override
  public boolean auswerten(boolean[] werte) {
    if (links.auswerten(werte) == true && rechts.auswerten(werte) == false) {
      return false;
    }
    return true;
  }
}
