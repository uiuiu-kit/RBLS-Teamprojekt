package steuerung;

import modell.Fassade;

public class SpalteHinzufuegen extends WahrheitstabellenBefehl {

  private final int maxSpaltenAnz = 12;
  private int spaltenAnz;

  /**
   * Kostruktor f�r SpalteEntfernen der die Ausf�hrung des Befehls anst��t.

   * @param model die SteuerungFassade auf die bei der Ausf�hrung zugegriffen
   *              wird.
   * 
   */
  public SpalteHinzufuegen(Fassade model) {
    super(model);
    hohleDaten();
    setzeDaten();
  }

  @Override
  public void hohleDaten() {
    spaltenAnz = model.gibSpaltenAnz();
  }

  @Override
  public void setzeDaten() {
    if (spaltenAnz < maxSpaltenAnz) {
      model.spalteHinzufuegen();
    }
  }
}
