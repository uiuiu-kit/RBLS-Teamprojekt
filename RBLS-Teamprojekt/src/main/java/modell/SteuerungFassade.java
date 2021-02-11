package modell;

import java.util.List;

import modell.formel.Formel;
import modell.raetsel.Memento;
import modell.raetsel.Raetsel;
import modell.raetsel.Raetselinterpret;
import modell.tabelle.Tabelle;

/**
 * Dies ist die Klasse fuer die Fassade, die mit der Steuerung zusammenarbeitet. Ueber sie werden
 * die Anfragen der Steuerung im Modell verwaltet.
 * @author janne
 *
 */
public class SteuerungFassade {
  
  private static SteuerungFassade steuFa = null;
  private Raetselinterpret interpret;
  private Memento memento = new Memento();
  private Raetsel raetsel;
  private Tabelle tabelle;
  PraesentationFassade praesFassade;
  
  /**Einzelstückmethode, die dafür sorgt, dass die Klasse nur einmaql erstellt, 
   * aber jederzeit von allen Klassen genutzt werden kann.
   * @return Objekt der Klasse SteuerungFassade.
   */
  public static SteuerungFassade gibSteuFa() {
    if (steuFa == null) {
      steuFa  = new SteuerungFassade();
    }
    return steuFa;
  }

  /** Initialisiert die Erstellung aller Modell-Objekte zu Beginn des Programms, 
   * die ohne weitere Eingaben des Benutzers erstellt werden können
   * Diese sind der Raetselinterpret, Memento und die Praesentationsfassade.
   * 
   */
  public void init() {
    this.interpret = new Raetselinterpret();
    praesFassade = new PraesentationFassade(interpret);
    /* interpret.liesRaetsel(); hier kann noch kein Raetsel erstellt werden. 
    In der Initphase ist noch nicht bekannt, welches Reatsel ausgewählt wurde.
    Bitte nach dem Lesen diesen Kommentar löschen
    */
  }
  
  /** Gibt eine Liste aller im Raetsel verwendeter Atome aus.
   * @return Liste der Atomobjekte.
   */
  public List<String> gibAtomareAussage() {
    return this.raetsel.gibAtomareAussage();
  }
  
  /** Gibt eine Liste aller Formeln zurück, die in der Tabelle verwendung finden.
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
  
  public boolean[] gibZeileFall(int zeile) {
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
    this.raetsel.addFormel(f);
  }
  
  public String gibFormelText(int spalte) {
    return tabelle.gibFormelText(spalte);
  }
  
  public int gibStufe() {
    return this.raetsel.gibStufe();
  }
}
