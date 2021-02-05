package steuerung;

import modell.SteuerungFassade;

public class SpalteEntfernen extends WahrheitstabellenBefehl {

  private int spalte;
  private int spaltenAnz;

  public SpalteEntfernen(SteuerungFassade model, int spalte) {
    super(model);
    this.spalte = spalte;
  }

  @Override
  public void hohleDaten() {
    spaltenAnz = model.gibSpaltenAnz();
  }

  @Override
  public void setzeDaten() {
    if (spalte < spaltenAnz) {
      model.spalteEntfernen(spalte);
    }
  }
}
