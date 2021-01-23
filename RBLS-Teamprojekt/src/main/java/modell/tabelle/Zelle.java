package modell.tabelle;

import modell.formel.Formel;

public abstract class Zelle {

  //public abstract void setzeZelle();
  
  private void benachrichtige() {
    //TODO
  }
  
  public abstract String toString();
  
  public abstract boolean gibZustand();

  public void setzeZelle(boolean w) {
  }
  
  public void setzeZelle(Formel f) {
  }
  
}
