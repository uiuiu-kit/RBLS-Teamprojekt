package modell.formel;

/**
 * Abstrakte Unterklasse des Konnektors, von ihr erben UND, ODER, EXOR und
 * Implikator.
 * 
 * @author Flo
 *
 */
public abstract class BiKonnektor extends Konnektor {

  protected Formel rechts;
  protected Formel links;

  @Override
  public String gibStringRep() {
    String ausdruck;
    ausdruck = this.rep;
    if (bindungsstaerke > links.bindungsstaerke) {
      ausdruck = "(" + links.gibStringRep() + ")" + ausdruck;
    } else {
      ausdruck = links.gibStringRep() + ausdruck;
    }
    if (bindungsstaerke > rechts.bindungsstaerke) {
      ausdruck = ausdruck + "(" + rechts.gibStringRep() + ")";
    } else {
      ausdruck = ausdruck + rechts.gibStringRep();
    }
    return ausdruck;
  }
}
