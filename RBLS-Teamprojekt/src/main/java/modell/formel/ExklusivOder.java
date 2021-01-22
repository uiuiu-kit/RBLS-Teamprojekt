package modell.formel;

public class ExklusivOder extends BiKonnektor{

  public ExklusivOder(Formel rechts, Formel links) {
    this.rechts = rechts;
    this.links = links;
  }

  @Override
  public boolean auswerten(boolean[] werte) {
    return links.auswerten(werte) ^ rechts.auswerten(werte);
  }
}
