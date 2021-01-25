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
  PraesentationFassade praesFassade;

  /** Initialisiert die Erstellung aller Modell-Objekte zu Beginn des Programms, 
   * die ohne weitere Eingaben des Benutzers erstellt werden k�nnen
   * Diese sind der Raetselinterpret, Memento und die Praesentationsfassade.
   * 
   */
  public void init() {
    this.interpret = new Raetselinterpret();
    praesFassade = new PraesentationFassade(interpret);
    /* interpret.liesRaetsel(); hier kann noch kein Raetsel erstellt werden. 
    In der Initphase ist noch nicht bekannt, welches Reatsel ausgew�hlt wurde.
    Bitte nach dem Lesen diesen Kommentar l�schen
    */
  }
  
  /** Gibt eine Liste aller im Raetsel verwendeter Atome aus.
   * @return Liste der Atomobjekte.
   */
  public List<Atom> gibAtomareAussage() {
    return this.raetsel.gibAtomareAussage();
  }
  
  /** Gibt eine Liste aller Formeln zur�ck, die in der Tabelle verwendung finden.
   * @return
   */
  public List<String> gibNoetigeFormel() {
    return this.raetsel.gibFormeln();
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
  
  /** 
   * @param raetselname
   */
  public void setzeAktRaetsel(String raetselname) {
    this.raetsel = this.interpret.liesRaetsel(raetselname);
    this.tabelle = new Tabelle(raetsel.gibZeilenAnz(),
        raetsel.gibSpaltenAnz(), raetsel.gibAtomAnz());
    praesFassade.setzeRaetsel(raetsel, tabelle);
  }
}
