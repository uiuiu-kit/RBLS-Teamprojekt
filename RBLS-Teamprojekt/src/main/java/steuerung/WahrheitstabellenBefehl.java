package steuerung;

import modell.SteuerungFassade;

public abstract class WahrheitstabellenBefehl {
  protected SteuerungFassade model;

  /**
   * Konstruktor für WahrheitstabellenBefehl.
   * 
   * @param model die Fassde von der Daten geholt und bei der Daten gesetzt
   *              werden.
   */
  public WahrheitstabellenBefehl(SteuerungFassade model) {
    this.model = model;
  }

  /**
   * hohlt alle Daten die für den Befehl benötigt werden von der Fassade.
   */
  public abstract void hohleDaten();

  /**
   * Stezt in der Fassade alle Daten die der Befehl verändert hat.
   */
  public abstract void setzeDaten();
}