package steuerung;

public class Hauptsteuerung {

  private modell.Fassade sf;
  private praesentation.Fensterverwaltung fv;
  private WahrheitstabellenSteuerungen ws;

  public Hauptsteuerung() {

  }

  /**
   * initialisiert die Hauptsteuerung. Erstellt Kopfobjekte für Modell und
   * Präsentation und stößt deren Initialisierung an.
   */

  public void init() {
    sf = new modell.Fassade();
    sf.init();
    fv = new praesentation.Fensterverwaltung(this, sf);
    fv.init();
  }

  /**
   * erstellt die WahrheitstabellenSteuerungen die für das Rätselfenster.
   * 
   */
  public WahrheitstabellenSteuerungen raetselFensterInit() {
    ws = new WahrheitstabellenSteuerungen(sf);
    ws.befehl("AufbauTabelle");
    return ws;
  }

  /**
   * beendet das Rätsel und setzt das Rätsel auf gelöst.
   */
  public void raetselGeloest() {
    sf.fuehreSicherungAus();
  }

  /**
   * stößt die Erstellung der Sicherungsdatei an und beendet das Programm.
   */
  public void beenden() {
    System.exit(0);
  }
}