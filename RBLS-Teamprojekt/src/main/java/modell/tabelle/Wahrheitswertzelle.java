package modell.tabelle;

public class Wahrheitswertzelle extends Zelle {
  
  private boolean wahrheitswert;

  public void setzeZelle(boolean w) {
    wahrheitswert = w;    
  }
  
  
  public boolean gibZustand() {
    return wahrheitswert;
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return null;
  }

}
