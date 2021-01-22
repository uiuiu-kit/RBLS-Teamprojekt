package modell.formel;

public class Und extends BiKonnektor {

  /** Kostruktor. Setzt übergebene Werte und seine String Rep.
   * @param rechts rechte Formel
   * @param links linke Formel
   */
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
