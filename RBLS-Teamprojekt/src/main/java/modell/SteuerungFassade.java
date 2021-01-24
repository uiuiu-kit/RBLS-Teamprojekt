package modell;

import java.util.List;
import modell.formel.Atom;
import modell.formel.Formel;
import modell.tabelle.Tabelle;

public class SteuerungFassade {
  
  private Tabelle tabelle = new Tabelle(0, 0, 0);

  public void init() {
    //TODO
  }
  
  public List<Atom> gibAtomareAussage() {
    //TODO
    return null;
  }
  
  public List<String> gibNoetigeFormel() {
    //TODO
    return null;
  }
  
  public boolean gibZelleWW(int[] i) {
    return tabelle.gibZellenWert(i);
  }
  
  public void setzeZelleWW(int[] i) {
    //TODO
  }
  
  public int gibZeilenAnz() {
    return tabelle.gibZeilenAnz();
  }
  
  public int gibSpaltenAnz() {
    return tabelle.gibSpaltenAnz();
  }
  
  public boolean gibZeileFall(int zeile) {
    return tabelle.gibZeileFall(zeile);
  }
  
  public void spalteHinzufuegen() {
    tabelle.spalteHinzufuegen();
  }
  
  public void spalteEntfernen(int spalte) {
    tabelle.spalteEntfernen(spalte);
  }
  
  public void fuehreSicherungAus() {
    //TODO
  }
  
  public Formel gibFormel(int spalte) {
    return tabelle.gibFormel(spalte);
  }
  
  public void setzeFormel(Formel f, int spalte) {
    //TODO
  }
  
  public String gibFormelText(int spalte) {
    //TODO
    return null;
  }
  
  public void setzeAktRaetsel(String raetselname) {
    //TODO
  }
}
