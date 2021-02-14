package steuerung;

import modell.Fassade;

public abstract class WahrheitstabellenBefehl {
  protected Fassade model;

  /**
   * Konstruktor für WahrheitstabellenBefehl.
   * 
   * @param model die Fassde von der Daten geholt und bei der Daten gesetzt
   *              werden.
   */
  public WahrheitstabellenBefehl(Fassade model) {
    this.model = model;
  }

  /**
   * hohlt alle Daten die für den Befehl benötigt werden, von der Fassade.
   */
  public abstract void hohleDaten();

  /**
   * Setzt in der Fassade alle Daten die der Befehl verändert.
   */
  public abstract void setzeDaten();
}