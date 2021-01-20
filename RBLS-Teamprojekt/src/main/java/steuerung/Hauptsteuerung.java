package steuerung;

public class Hauptsteuerung {
  public Hauptsteuerung() {
    // TODO Auto-generated constructor stub
  }

  /**
   * initialisiert die Hauptsteuerung. Erstellt Kopfobjekte für die andere Teile
   * und stößt Initialisierung an
   */

  public void init() {
    praesentation.Fensterverwaltung fv = new praesentation.Fensterverwaltung(this);
    /*//Zu Testzwecken auskommentiert, kann wieder rückgängig gemacht werden...
    modell.Fassade fs = new modell.Fassade(this);
    fs.init();
    */
    fv.init();
  }
}
