package modell;

import java.util.List;
import modell.formel.Atom;
import modell.formel.Formel;
import modell.formel.Und;
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
  private Raetsel raetsel;
  private Tabelle tabelle;
  PraesentationFassade praesFassade;
  
  /**Einzelstückmethode, die dafür sorgt, dass die Klasse nur einmal erstellt, 
   * aber jederzeit von allen Klassen genutzt werden kann.
   * @return Objekt der Klasse SteuerungFassade.
   */
  public static SteuerungFassade gibSteuFa() {
    if (steuFa == null) {
      steuFa  = new SteuerungFassade();
    }
    return steuFa;
  }

  private void aktualisiere() {
    this.raetsel = praesFassade.gibRaetsel();
    this.tabelle = praesFassade.gibTabelle();
  }

  /** Initialisiert die Erstellung aller Modell-Objekte zu Beginn des Programms, 
   * die ohne weitere Eingaben des Benutzers erstellt werden können
   * Diese sind der Raetselinterpret, Memento und die Praesentationsfassade.
   * 
   */
  public void init() {
    this.interpret = new Raetselinterpret();
    praesFassade = new PraesentationFassade(interpret);
  }

  /** Gibt eine Liste aller im Raetsel verwendeter Atome aus.
   * @return Liste der Atomobjekte.
   */
  public List<String> gibAtomareAussage() {
    this.aktualisiere();
    return this.raetsel.gibAtomareAussage();
  }
  
  /** Gibt eine Liste aller Formeln zurück, die in der Tabelle Verwendung finden.
   * @return
   */
  public List<String> gibNoetigeFormel() {
    this.aktualisiere();
    return this.raetsel.gibFormeln();
  }
  
  public boolean gibZelleWW(int[] i) {
    this.aktualisiere();
    return tabelle.gibZellenWert(i);
  }
  
  public void setzeZelleWW(int[] i, boolean ww) {
    this.aktualisiere();
    tabelle.setzeZelle(i, ww);
  }
  
  public int gibZeilenAnz() {
    this.aktualisiere();
    return tabelle.gibZeilenAnz();
  }
  
  public int gibSpaltenAnz() {
    this.aktualisiere();
    return tabelle.gibSpaltenAnz();
  }
  
  public boolean[] gibZeileFall(int zeile) {
    return tabelle.gibZeileFall(zeile);
  }
  
  public void spalteHinzufuegen() {
    this.aktualisiere();
    tabelle.spalteHinzufuegen();
  }
  
  public void spalteEntfernen(int spalte) {
    this.aktualisiere();
    tabelle.spalteEntfernen(spalte);
  }
  
  /**
   * Speichert das aktuelle Raetsel als den aktuellen Spielstand in Form eines Memento.
   * @return Ein Memento-Objekt.
   */
  public Memento fuehreSicherungAus() {
    this.aktualisiere();
    Memento memento = new Memento(raetsel);
    this.praesFassade.setzeAbgeschlosseneStufe(memento.gibSicherung().gibStufe());
    return memento;
  }
  
  public Formel gibFormel(int spalte) {
    this.aktualisiere();
    return tabelle.gibFormel(spalte);
  }
  
  /** Ermöglicht der Steuerung Formeln zu setzen.
   * @param formel zu setzende Formel.
   * @param spalte position der Formel (Zelle)
   */
  public void setzeFormel(Formel formel, int spalte) {
    this.aktualisiere();
    tabelle.setzeFormel(formel, spalte);
    this.raetsel.addFormel(formel);
  }
  
  public String gibFormelText(int spalte) {
    this.aktualisiere();
    return tabelle.gibFormelText(spalte);
  }
  
  public int gibStufe() {
    this.aktualisiere();
    return this.raetsel.gibStufe();
  }
  
  /**Klasse zur Initialisierung einer Testumgebung, 
   * benötigt zum Test für Steuerung und Steuerungsfassade.
   * @param test  Instanz des Testinterpreten.
   */
  public PraesentationFassade erstelleTestUmgebung(Raetselinterpret test) {
    praesFassade = new PraesentationFassade(test);
    praesFassade.setzeRaetsel("Raetseldummy");
    this.setzeFormel(new Und(new Atom("A", "A", 1), new Atom("B", "B", 2)), 4);
    return praesFassade;
  }
}
