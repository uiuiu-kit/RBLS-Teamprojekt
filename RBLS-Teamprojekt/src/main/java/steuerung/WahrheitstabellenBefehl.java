package steuerung;

import modell.Fassade;

public abstract class WahrheitstabellenBefehl {
  protected Fassade model;

  /**
   * Konstruktor f�r WahrheitstabellenBefehl.

   * @param model die Fassde von der Daten geholt und bei der Daten gesetzt
   *              werden.
   */
  public WahrheitstabellenBefehl(Fassade model) {
    this.model = model;
  }

  /**
   * hohlt alle Daten die f�r den Befehl ben�tigt werden, von der Fassade.
   */
  public abstract void hohleDaten();

  /**
   * Setzt in der Fassade alle Daten die der Befehl ver�ndert.
   */
  public abstract void setzeDaten();
}