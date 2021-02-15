package modell;

import java.util.List;
import modell.formel.Atom;
import modell.formel.Formel;
import modell.formel.Und;
import modell.raetsel.Memento;
import modell.raetsel.Raetsel;
import modell.raetsel.RaetselZustand;
import modell.raetsel.Raetselinterpret;
import modell.tabelle.Tabelle;

/**
 * Dies ist die Klasse fuer die Fassade, die mit der Steuerung zusammenarbeitet.
 * Ueber sie werden die Anfragen der Steuerung im Modell verwaltet.
 * 
 * @author janne
 *
 */
public class Fassade {

  private static Fassade steuFa = null;
  private Raetselinterpret interpret;
  private RaetselZustand raetselZustand;
  private Raetsel raetsel;
  private Tabelle tabelle;
  private int abgeschlosseneStufe;

  /**
   * Einzelstueckmethode, die dafuer sorgt, dass die Klasse nur einmal erstellt,
   * aber jederzeit von allen Klassen genutzt werden kann.
   * 
   * @return Objekt der Klasse SteuerungFassade.
   */
  public static Fassade gibSteuFa() {
    if (steuFa == null) {
      steuFa = new Fassade();
    }
    return steuFa;
  }

  private void aktualisiere() { // Präs aktuallisieren.

  }

  /**
   * Initialisiert die Erstellung aller Modell-Objekte zu Beginn des Programms,
   * die ohne weitere Eingaben des Benutzers erstellt werden können Diese sind der
   * Raetselinterpret, Memento und die Praesentationsfassade.
   * 
   */
  public void init() {
    this.interpret = new Raetselinterpret();
  }

  /**
   * Gibt eine Liste aller im Raetsel verwendeter Atome aus.
   * 
   * @return Liste der Atomobjekte.
   */
  public List<String> gibAtomareAussage() {
    this.aktualisiere();
    return this.raetsel.gibAtomareAussage();
  }

  //zum Testen benoetigt
  public String gibRaetselString() {
    aktualisiere();
    return this.raetsel.gibName();
  }

  /**
   * Aktualisiert das Raetsel, indem es den erhaltenen Raetselnamen dem
   * RInterpreten übergibt, der ein neues Raetselobjekt zurückgibt. Dieses wird
   * hier gesetzt und daraus eine neue Tabelle erzeugt.
   * 
   * @param raetselname Name des neuen Raetsels
   */
  public void setzeRaetsel(String raetselname) {
    aktualisiere();
    this.raetsel = this.interpret.liesRaetsel(raetselname);
    this.tabelle = new Tabelle(raetsel.gibAtome());
  }

  /**
   * Gibt eine Liste aller Raetselnamen zurück, deren Stufe angefordert wurde.
   * 
   * @param i Raetselstufe, nach der gesucht wird.
   * @return Liste der Raetselnamen der entsprechenden Stufe.
   */
  public List<String> gibRaetselListe(int i) {
    aktualisiere();
    return interpret.liesOrdner(i);
  }

  public String gibAktivenRaetselnamen() {
    aktualisiere();
    return raetsel.gibName();
  }

  protected void setzeAktivenZustand(RaetselZustand zustand) {
    aktualisiere();
    this.raetselZustand = zustand;
  }

  public String gibFragestellung() {
    aktualisiere();
    return raetsel.gibRaetselText();
  }

  public String gibAntwortText() {
    aktualisiere();
    return raetsel.gibAntworttext();
  }

  public String[] gibAntwortmoeglichkeiten() {
    aktualisiere();
    return raetsel.gibAntwort();
  }

  public String gibLoesung() {
    aktualisiere();
    return raetsel.gibLoesung();
  }

  /**
   * Holt die String-Repraesentation der Zelle und aktualisiert die konkrete
   * Tabellenansicht.
   * 
   * @return Die String-Repraesentation der Zelle.
   */
  public String gibZelle(int[] zelle) {
    aktualisiere();
    return tabelle.gibZelle(zelle);
  }

  public void erstelleRaetsel(List<String> atome) {
    aktualisiere();
    this.interpret.erstelleFR(atome);
  }

  public void setzeAbgeschlosseneStufe(int abgeschlosseneStufe) {
    aktualisiere();
    this.abgeschlosseneStufe = abgeschlosseneStufe;
  }

  public int gibAbgeschlosseneStufe() {
    aktualisiere();
    return this.abgeschlosseneStufe;
  }

  /**
   * Gibt eine Liste aller Formeln zurück, die in der Tabelle Verwendung finden.
   * 
   * @return Liste der Formeln.
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
   * Speichert das aktuelle Raetsel als den aktuellen Spielstand in Form eines
   * Memento.
   * 
   * @return Ein Memento-Objekt.
   */
  public Memento fuehreSicherungAus() {
    this.aktualisiere();
    Memento memento = new Memento(raetsel);
    setzeAbgeschlosseneStufe(memento.gibSicherung().gibStufe());
    setzeAktivenZustand(memento.gibSicherung());
    return memento;
  }

  public Formel gibFormel(int spalte) {
    this.aktualisiere();
    return tabelle.gibFormel(spalte);
  }

  /**
   * Ermoeglicht der Steuerung Formeln zu setzen.
   * 
   * @param formel Die zu setzende Formel.
   * @param spalte Die Position der Formel (Zelle).
   */
  public void setzeFormel(Formel formel, int spalte) {
    this.aktualisiere();
    tabelle.setzeFormel(formel, spalte);
  }

  public String gibFormelText(int spalte) {
    this.aktualisiere();
    return tabelle.gibFormelText(spalte);
  }

  public int gibStufe() {
    this.aktualisiere();
    return this.raetsel.gibStufe();
  }

  /**
   * Klasse zur Initialisierung einer Testumgebung, benoetigt zum Test für
   * Steuerung und Steuerungsfassade.
   * 
   * @param test Instanz des Testinterpreten.
   */
  public void erstelleTestUmgebung(Raetselinterpret test) {
    this.interpret = test;
    setzeRaetsel("Raetseldummy");
  }

  public Raetsel gibRaetsel() {
    return raetsel;
  }

  public Tabelle gibTabelle() {
    return tabelle;
  }
  
  public List<String> gibGelösteRaetsel(int stufe) {
    return null;
  }
}
