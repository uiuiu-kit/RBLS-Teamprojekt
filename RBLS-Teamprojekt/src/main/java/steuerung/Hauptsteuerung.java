package steuerung;

public class Hauptsteuerung {

  private modell.SteuerungFassade sf;
  private modell.PraesentationFassade pf;
  private praesentation.Fensterverwaltung fv;
  private WahrheitstabellenSteuerungen ws;

  public Hauptsteuerung() {

  }

  /**
   * initialisiert die Hauptsteuerung. Erstellt Kopfobjekte für die andere Teile
   * und stößt Initialisierung an
   */

  public void init() {
    sf = new modell.SteuerungFassade();
    sf.init();
    pf = modell.PraesentationFassade.gibPraFa();
    fv = new praesentation.Fensterverwaltung(this, pf);
    fv.init();
  }

  /**
   * erstellt Objekte die für das Rätselfenster nötig sind.
   * 
   * @param raetselname der Name des Rätsel das gestartet werden soll.
   */
  public void raetselFensterInit(String raetselname) {
    ws = new WahrheitstabellenSteuerungen(sf);
    ws.befehl("AufbauTabelle");
  }

  /**
   * beendet das Rätsel und setst das Rätsel auf gelöst.
   */
  public void raetselGeloest() {
    // ToDo
  }

  /**
   * stößt die Erstellung der Sicherungsdatei und beendet das Programm.
   */
  public void beenden() {
    System.exit(0);
  }
}