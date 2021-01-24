package steuerung;

public class Hauptsteuerung {

  modell.SteuerungFassade sf;
  modell.PraesentationFassade pf;
  praesentation.Fensterverwaltung fv;
  WahrheitstabellenSteuerungen ws;

  public Hauptsteuerung() {

  }

  /**
   * initialisiert die Hauptsteuerung. Erstellt Kopfobjekte für die andere Teile
   * und stößt Initialisierung an
   */

  public void init() {
    sf = new modell.SteuerungFassade(this);
    pf = new modell.PraesentationFassade();
    fv = new praesentation.Fensterverwaltung(this, pf);
    sf.init();
    fv.init();
  }

  /**
   * erstellt Objekte die für das Rätselfenster nötig sind.
   * 
   * @param raetselname der Name des Rätsel das gestartet werden soll.
   */
  public void raetselFensterInit(String raetselname) {
    sf.setzeRaetsel(raetselname);
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
    // ToDo
  }
}