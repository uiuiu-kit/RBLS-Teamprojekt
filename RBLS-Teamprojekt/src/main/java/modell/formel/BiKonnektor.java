package modell.formel;

/** Abstrakte Unterklasse des Konnektors, von ihr erben UND, ODER, EXOR und ImplikatorBokonnektor.
 * @author Flo
 *
 */
public abstract class BiKonnektor extends Konnektor {
  
  protected Formel rechts;
  protected Formel links;
  
  @Override
  public String gibStringRep() {
    return this.links.gibStringRep() + this.rep + this.rechts.gibStringRep();
  }
}
