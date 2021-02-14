package modell;

import java.util.List;
import modell.raetsel.Raetsel;
import modell.raetsel.RaetselZustand;
import modell.raetsel.Raetselinterpret;
import modell.tabelle.Tabelle;

/**
 * Dies ist die Klasse fuer die Fassade, die mit der Praesentation
 * zusammenarbeitet. Ueber sie wird zwischen Modell und Praesentation
 * vermittelt.
 * 
 * @author janne
 *
 */
public class PraesentationFassade {
  private static PraesentationFassade praFa = null;

  private Raetselinterpret interpret;
  private RaetselZustand raetselZustand;
  private Raetsel raetsel;
  private Tabelle tabelle;
  private int abgeschlosseneStufe;
  private static SteuerungFassade steuFa;

  protected PraesentationFassade(Raetselinterpret interpret, SteuerungFassade steuFa) {
    this.interpret = interpret;
    this.steuFa = steuFa;
    PraesentationFassade.praFa = this;
  }

  private void aktualisiere() {
    this.raetsel = steuFa.gibRaetsel();
    this.tabelle = steuFa.gibTabelle();
  }

  /**
   * Gibt das Objekt PraesentationFassade als Einzelstueck zurueck.
   * 
   * @return PraesentationFassade.
   */
  public static PraesentationFassade gibPraFa() {
    return praFa;
  }

  public Raetsel gibRaetsel() {
    return this.raetsel;
  }

  // zum Testen benoetigt
  public String gibRaetselString() {
    aktualisiere();
    return this.raetsel.gibName();
  }

  protected Tabelle gibTabelle() {
    return this.tabelle;
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
    return raetselZustand.gibRaetselname();
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

  public List<String> gibAntwortmoeglichkeiten() {
    aktualisiere();
    return raetsel.gibAntwort();
  }

  public String gibLoesung() {
    aktualisiere();
    return raetsel.gibLoesung();
  }

  public int gibAktuelleStufe() {
    aktualisiere();
    return raetsel.gibStufe();
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

  public boolean gibZellenWert(int[] zelle) {
    aktualisiere();
    return tabelle.gibZellenWert(zelle);
  }

  public void erstelleRaetsel(List<String> atome) {
    aktualisiere();
    this.interpret.erstelleFR(atome);
  }

  public int gibZeilenAnz() {
    aktualisiere();
    return tabelle.gibZeilenAnz();
  }

  public int gibSpaltenAnz() {
    aktualisiere();
    return tabelle.gibSpaltenAnz();
  }

  public String gibFormelText(int spalte) {
    aktualisiere();
    return tabelle.gibFormelText(spalte);
  }

  public void setzeAbgeschlosseneStufe(int abgeschlosseneStufe) {
    aktualisiere();
    this.abgeschlosseneStufe = abgeschlosseneStufe;
  }

  public int gibAbgeschlosseneStufe() {
    aktualisiere();
    return this.abgeschlosseneStufe;
  }
}
