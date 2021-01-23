package steuerung;

public class Hauptsteuerung {
  public Hauptsteuerung() {
   
  }

  /**
   * initialisiert die Hauptsteuerung. Erstellt Kopfobjekte für die andere Teile
   * und stößt Initialisierung an
   */

  public void init() {
    modell.SteuerungFassade sf = new modell.SteuerungFassade(this);
    modell.PraesentationFassade pf = new modell.PraesentationFassade(); 
    praesentation.Fensterverwaltung fv = new praesentation.Fensterverwaltung(this, pf);
    sf.init();
    fv.init();
  }
}