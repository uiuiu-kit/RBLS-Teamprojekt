package modell;

import java.util.List;
import modell.formel.Formel;
import modell.raetsel.Memento;
import modell.raetsel.Raetsel;
import modell.raetsel.Raetselinterpret;
import modell.tabelle.Tabelle;

/**
 * Dies ist die Klasse fuer die Fassade, die mit der Steuerung zusammenarbeitet.
 * Ueber sie werden die Anfragen der Steuerung im Modell verwaltet.

 * @author janne
 *
 */
public class Fassade {

  private static Fassade steuFa = null;
  private Raetselinterpret interpret;
  private Raetsel raetsel;
  private Tabelle tabelle;
  private Memento memento;

  /**
   * Einzelstueckmethode, die dafuer sorgt, dass die Klasse nur einmal erstellt,
   * aber jederzeit von allen Klassen genutzt werden kann.

   * @return Objekt der Klasse SteuerungFassade.
   */
  public static Fassade gibSteuFa() {
    if (steuFa == null) {
      steuFa = new Fassade();
    }
    return steuFa;
  }

  private void aktualisiere() { // Praes aktuallisieren.

  }

  /**
   * Initialisiert die Erstellung aller Modell-Objekte zu Beginn des Programms,
   * die ohne weitere Eingaben des Benutzers erstellt werden koennen. Diese sind der
   * Raetselinterpret, Memento und die Praesentationsfassade.
   * 
   */
  public void init() {
    this.interpret = new Raetselinterpret();
    this.memento = new Memento();
  }

  /**
   * Gibt eine Liste aller im Raetsel verwendeter Atome aus.

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
   * RInterpreten uebergibt, der ein neues Raetselobjekt zurueckgibt. Dieses wird
   * hier gesetzt und daraus eine neue Tabelle erzeugt.

   * @param raetselname Name des neuen Raetsels
   */
  public void setzeRaetsel(String raetselname) {
    aktualisiere();
    this.raetsel = this.interpret.liesRaetsel(raetselname);
    this.tabelle = new Tabelle(raetsel.gibAtome());
  }

  /**
   * Gibt eine Liste aller Raetselnamen zurueck, deren Stufe angefordert wurde.

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

   * @return Die String-Repraesentation der Zelle.
   */
  public String gibZelle(int[] zelle) {
    aktualisiere();
    return tabelle.gibZelle(zelle);
  }
  
  /**
   * Erstellt ein neues Raetsel im Interpreten.

   * @param atome
   */
  public void erstelleRaetsel(List<String> atome) {
    aktualisiere();
    raetsel = this.interpret.erstelleFR(atome);
  }

  /**
   * Gibt eine Liste aller Formeln zurueck, die in der Tabelle Verwendung finden.

   * @return Liste der Formeln.
   */
  public List<String> gibNoetigeFormel() {
    this.aktualisiere();
    return this.raetsel.gibFormeln();
  }
  
  /**
   * Gibt den Wahrheitswert der Zelle zurueck.

   * @param i Die Zelle als Spalten und Zeilen Angabe.
   * @return Den Wahreitswert der konkreten Zelle.
   */
  public boolean gibZelleWW(int[] i) {
    
    if (tabelle != null) {
      return tabelle.gibZellenWert(i);
    }
    return false;
  }

  public void setzeZelleWW(int[] i, boolean ww) {
    this.aktualisiere();
    if (tabelle != null) {
    tabelle.setzeZelle(i, ww);
    }
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

  public Formel gibFormel(int spalte) {
    this.aktualisiere();
    return tabelle.gibFormel(spalte);
  }

  /**
   * Ermoeglicht der Steuerung Formeln zu setzen.

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
   * Klasse zur Initialisierung einer Testumgebung, benoetigt zum Test fuer
   * Steuerung und Steuerungsfassade.

   * @param test Instanz des Testinterpreten.
   */
  public void erstelleTestUmgebung(Raetselinterpret test) {
    this.interpret = test;
    setzeRaetsel("Raetseldummy");
  }

  public List<String> gibGeloesteRaetsel(int stufe) {
    this.memento = new Memento();
    return memento.gibGeloesteRaetsel();
  }
  
  /**
   * Gibt die abgeschlossene Stufe zurueck.

   * @return Die zuletzt abgeschlossene Raetselstufe.
   */
  public int gibAbgeschlosseneStufe() {
    aktualisiere();
    this.memento = new Memento();
    return memento.gibStufenSicherung();
  }
  
  /**
   * Speichert das aktuelle Raetsel als den aktuellen Spielstand in Form eines
   * Memento.
   */
  public void fuehreSicherungAus() {
    this.aktualisiere();
    memento.erstelleMementoDatei(raetsel);
  }
}
