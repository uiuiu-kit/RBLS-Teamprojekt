package modell.formel;

public abstract class Formel {
    
  protected String rep;
  
  public abstract boolean auswerten(boolean[] werte);
    
  public abstract String gibStringRep();
}
