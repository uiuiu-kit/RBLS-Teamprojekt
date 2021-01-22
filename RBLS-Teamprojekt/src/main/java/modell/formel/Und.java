package modell.formel;

public class Und extends BiKonnektor {

  public Und(Formel rechts, Formel links) {
    this.rechts = rechts;
    this.links = links;
    this.rep = "u";
  }
  
  @Override
  public boolean auswerten(boolean[] werte) {
    return (links.auswerten(werte) && rechts.auswerten(werte));
  }
  
}
