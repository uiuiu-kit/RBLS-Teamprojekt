package steuerung;

import modell.SteuerungFassade;

public class SpalteHinzufuegen extends WahrheitstabellenBefehl {

  private final int maxSpaltenAnz = 12;
  private int spaltenAnz;

  public SpalteHinzufuegen(SteuerungFassade model) {
    super(model);
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
