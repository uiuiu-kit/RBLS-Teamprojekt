package modell.formel;

/**
 * Unterklasse des Bikonnektors. Impliziert logisch aus dem linken
 * Formelnachbarn den rechten Formelnachbarn. Seine String Repraesentation ist
 * "f".
 * 
 * @author Flo
 *
 */
public class Implikation extends BiKonnektor {

  /**
   * Kostruktor. Setzt uebergebene Werte und seine String Rep.
   * 
   * @param rechts Die rechte Formel.
   * @param links  Die linke Formel.
   */
  public Implikation(Formel links, Formel rechts) {
    this.rechts = rechts;
    this.links = links;
    this.rep = "\u2192";
    this.bindungsstaerke = 1;
    this.zeichen = "f";
  }

  @Override
  public boolean auswerten(boolean[] werte) {
    if (links.auswerten(werte) == true && rechts.auswerten(werte) == false) {
      return false;
    }
    return true;
  }

  @Override
  public String gibStringRep() {
    String ausdruck;
    ausdruck = this.rep;
    if (bindungsstaerke >= links.bindungsstaerke) {
      ausdruck = "(" + links.gibStringRep() + ")" + ausdruck;
    } else {
      ausdruck = links.gibStringRep() + ausdruck;
    }
    if (bindungsstaerke >= rechts.bindungsstaerke) {
      ausdruck = ausdruck + "(" + rechts.gibStringRep() + ")";
    } else {
      ausdruck = ausdruck + rechts.gibStringRep();
    }
    return ausdruck;
  }

  @Override
  public String gibParsable() {
    String ausdruck;
    ausdruck = this.zeichen;
    if (bindungsstaerke >= links.bindungsstaerke) {
      ausdruck = "(" + links.gibParsable() + ")" + ausdruck;
    } else {
      ausdruck = links.gibParsable() + ausdruck;
    }
    if (bindungsstaerke >= rechts.bindungsstaerke) {
      ausdruck = ausdruck + "(" + rechts.gibParsable() + ")";
    } else {
      ausdruck = ausdruck + rechts.gibParsable();
    }
    return ausdruck;
  }

}
