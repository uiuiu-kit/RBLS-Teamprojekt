package modell;

import java.util.List;
import modell.raetsel.Raetsel;
import modell.raetsel.RaetselZustand;
import modell.raetsel.Raetselinterpret;
import modell.tabelle.Tabelle;

/**
 * Dies ist die Klasse fuer die Fassade, die mit der Praesentation zusammenarbeitet. Ueber sie
 * wird zwischen Modell und Praesentation vermittelt.
 * @author janne
 *
 */
public class PraesentationFassade {
  private static PraesentationFassade praFa = null;
  
  private Raetselinterpret interpret;
  //private RaetselZustand raetselZustand = new RaetselZustand();
  private Raetsel raetsel;
  private Tabelle tabelle;
  private int abgeschlosseneStufe;
  
  
  public PraesentationFassade(Raetselinterpret interpret) {
    this.interpret = interpret;
    PraesentationFassade.praFa = this;
  }
  
  /**
   * Gibt das Objekt PraesentationFassade als Einzelstueck zurueck. Wenn es noch nicht existiert,
   * wird es erzeugt.
   * @return Das Einzelstueckobjekt PraesentationFassade.
   */
  public static PraesentationFassade gibPraFa() {
    return praFa;
  }
  

  
  /**Aktualisiert das Raetsel, indem es den erhaltenen Raetselnamen dem RInterpreten übergibt, 
   * der ein neues Raetselobjekt zurückgibt. Dieses wird hier gesetzt 
   * und daraus eine neue Tabelle erzeugt.
   * @param raetselname Name des neuen Raetsels
   */
  public void setzeRaetsel(String raetselname) {
    this.raetsel = this.interpret.liesRaetsel(raetselname);
    this.tabelle = new Tabelle(raetsel.gibZeilenAnz(),
        raetsel.gibSpaltenAnz(), raetsel.gibAtomAnz());
  }
  
  /** Gibt eine Liste aller Raetselnamen zurück, deren Stufe angefordert wurde.
   * @param i Raetselstufe, nach der gesucht wird.
   * @return Liste der Raetselnamen der entsprechenden Stufe.
   */
  public List<String> gibRaetselListe(int i) {
    return interpret.liesOrdner(i);
  }

  /* Wird von niemandem gebraucht
  public String gibAktivenRaetselnamen() {
    return raetselZustand.raetselname;
  }*/
  
  public String gibFragestellung() {
    return raetsel.gibRaetselText();
  }
  
  public String gibAntwortText() {
    return raetsel.gibAntworttext();
  }

  public List<String> gibAntwortmoeglichkeiten() {
    return raetsel.gibAntwort();
  }
  
  public String gibLoesung() {
    return raetsel.gibLoesung();
  }
  
  public int gibAktuelleStufe() {
    return raetsel.gibStufe();
  }
  
  /**
   * Holt die String-Repraesentation der Zelle und aktualisiert die konkrete Tabellenansicht.
   * @return Die String-Repraesentation der Zelle.
   */
  public String gibZelle(int[] zelle) {
    return tabelle.gibZelle(zelle);
  }
  
  public boolean gibZellenWert(int[] zelle) {
    return tabelle.gibZellenWert(zelle);
  }
  
  public void erstelleRaetsel(List<String> atome) {
    this.interpret.erstelleFR(atome);
  }
  
  public int gibZeilenAnz() {
    return tabelle.gibZeilenAnz();
  }
  
  public int gibSpaltenAnz() {
    return tabelle.gibSpaltenAnz();
  }
  
  public String gibFormelText(int spalte) {
    return tabelle.gibFormelText(spalte);
  }
  
  public void setzeAbgeschlosseneStufe(int abgeschlosseneStufe) {
    this.abgeschlosseneStufe = abgeschlosseneStufe;
  }
  
  public int gibAbgeschlosseneStufe() {
    return this.abgeschlosseneStufe;
  }
}
