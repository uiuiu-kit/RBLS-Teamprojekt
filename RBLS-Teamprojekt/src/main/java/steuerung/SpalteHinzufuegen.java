package steuerung;

import modell.Fassade;

public class SpalteHinzufuegen extends WahrheitstabellenBefehl {

  private final int maxSpaltenAnz = 12;
  private int spaltenAnz;

  /**
   * Kostruktor für SpalteEntfernen der die Ausführung des Befehls anstößt.
   * 
   * @param model die SteuerungFassade auf die bei der Ausführung zugegriffen
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
