package modell.formel;

public abstract class BiKonnektor extends Konnektor {
  
  protected Formel rechts;
  protected Formel links;
  
  @Override
  public String gibStringRep() {
    return this.links.gibStringRep() + this.rep + this.rechts.gibStringRep();
  }
}
