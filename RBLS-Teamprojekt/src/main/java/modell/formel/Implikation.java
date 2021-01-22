package modell.formel;

public class Implikation extends BiKonnektor {

  /** Kostruktor. Setzt übergebene Werte und seine String Rep.
   * @param rechts rechte Formel
   * @param links linke Formel
   */
  public Implikation(Formel rechts, Formel links) {
    this.rechts = rechts;
    this.links = links;
    this.rep = "f";
  }
  
  @Override
  public boolean auswerten(boolean[] werte) {
    if (links.auswerten(werte) == true && rechts.auswerten(werte) == false) {
      return false;
    }
    return true;
  }
}
