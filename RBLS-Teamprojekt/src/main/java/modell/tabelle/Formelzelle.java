package modell.tabelle;

import modell.formel.Formel;

public class Formelzelle extends Zelle {
  
  private Formel aussagenlogischeFormel;
  
  public void setzeZelle(Formel f) {
    aussagenlogischeFormel = f;
  }
  
  public Formel gibZustand() {
    return aussagenlogischeFormel;
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return null;
  }

}
