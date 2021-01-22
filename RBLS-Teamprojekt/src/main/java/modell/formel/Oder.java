package modell.formel;

public class Oder extends BiKonnektor {

  /** Kostruktor. Setzt übergebene Werte und seine String Rep.
   * @param rechts rechte Formel
   * @param links linke Formel
   */
  public Oder(Formel rechts, Formel links) {
    this.rechts = rechts;
    this.links = links;
  }
  
  @Override
  public boolean auswerten(boolean[] werte) {
    return (links.auswerten(werte) || rechts.auswerten(werte));
  }

}
