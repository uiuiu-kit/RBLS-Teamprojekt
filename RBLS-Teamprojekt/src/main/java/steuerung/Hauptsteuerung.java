package steuerung;

public class Hauptsteuerung {

  private modell.Fassade sf;
  private praesentation.Fensterverwaltung fv;
  private WahrheitstabellenSteuerungen ws;

  public Hauptsteuerung() {

  }

  /**
   * initialisiert die Hauptsteuerung. Erstellt Kopfobjekte fuer Modell und
   * Praesentation und stoesst deren Initialisierung an.
   */

  public void init() {
    sf = new modell.Fassade();
    sf.init();
    fv = new praesentation.Fensterverwaltung(this, sf);
    fv.init();
  }

  /**
   * erstellt die WahrheitstabellenSteuerungen die fuer das Raetselfenster.
   * 
   */
  public WahrheitstabellenSteuerungen raetselFensterInit() {
    ws = new WahrheitstabellenSteuerungen(sf);
    ws.befehl("AufbauTabelle");
    return ws;
  }

  /**
   * beendet das Raetsel und setzt das Raetsel auf geloest.
   */
  public void raetselGeloest() {
    sf.fuehreSicherungAus();
  }

  /**
   * stoesst die Erstellung der Sicherungsdatei an und beendet das Programm.
   */
  public void beenden() {
    System.exit(0);
  }
}