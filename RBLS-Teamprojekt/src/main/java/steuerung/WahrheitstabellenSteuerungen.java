package steuerung;

import modell.SteuerungFassade;

public class WahrheitstabellenSteuerungen {
  /**
   * Konstruktor für die WahrheitstabellenSteuerungen.
   * 
   * @param modell die Fassade die von Befehlen angesprochen werden soll
   */
  public WahrheitstabellenSteuerungen(SteuerungFassade modell) {
    // TODO Auto-generated constructor stub
  }

  /**
   * löst Befehl von der Präsentation auf und konstruiert das passenden
   * Befehls-Objekt und stößt damit dessen Ausführung an.
   * 
   * @param befehl der auszuführende Befehl
   */
  public void befehl(String befehl) {
    ueberpruefeTabelle();
  }

  /**
   * überprüft ob der Inhalt der Tabelle korrekt ist.
   * 
   * @return Korrektheit
   */
  private boolean ueberpruefeTabelle() {
    return false;
  }
}