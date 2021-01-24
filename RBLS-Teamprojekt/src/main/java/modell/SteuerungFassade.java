package modell;

import java.util.List;
import modell.formel.Atom;
import modell.formel.Formel;
import modell.raetsel.Memento;
import modell.raetsel.Raetsel;
import modell.raetsel.Raetselinterpret;
import modell.tabelle.Tabelle;

public class SteuerungFassade {
  
  private Raetselinterpret interpret;
  private Memento memento = new Memento();
  private Raetsel raetsel;
  private Tabelle tabelle;

  public void init() {
    this.interpret = new Raetselinterpret();
    interpret.erstelleRaetsel();
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
  
  public void setzeZelleWW(int[] i, boolean ww) {
    tabelle.setzeZelle(i, ww);
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
  
  public void fuehreSicherungAus(Raetsel r) {
    memento.sichern(r);
  }
  
  public Formel gibFormel(int spalte) {
    return tabelle.gibFormel(spalte);
  }
  
  public void setzeFormel(Formel f, int spalte) {
    tabelle.setzeFormel(f, spalte);
  }
  
  public String gibFormelText(int spalte) {
    return tabelle.gibFormelText(spalte);
  }
  
  public void setzeAktRaetsel(String raetselname) {
    this.raetsel = this.interpret.liesRaetsel(raetselname);
    this.tabelle = new Tabelle(raetsel.gibZeilenAnz(),
        raetsel.gibSpaltenAnz(), raetsel.gibAtomAnz());
    PraesentationFassade praesFassade = new PraesentationFassade();
    praesFassade.setzeRaetsel(raetsel, tabelle, interpret);
  }
}
