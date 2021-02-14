package steuerung;

import modell.SteuerungFassade;

public class SpalteEntfernen extends WahrheitstabellenBefehl {

  private int spalte;
  private int spaltenAnz;
  private int atomAnz;

  /**
   * Kostruktor für SpalteEntfernen der die Ausführung des Befehls anstößt.
   * 
   * @param model  die SteuerungFassade auf die bei der Ausführung zugegriffen
   *               wird.
   * @param spalte die spalte die entfernt werden soll
   */
  public SpalteEntfernen(SteuerungFassade model, int spalte) {
    super(model);
    this.spalte = spalte;
    hohleDaten();
    setzeDaten();
  }

  @Override
  public void hohleDaten() {
    spaltenAnz = model.gibSpaltenAnz();
    atomAnz = model.gibAtomareAussage().size();
  }

  @Override
  public void setzeDaten() {
    if (spalte < spaltenAnz && spalte > atomAnz) {
      model.spalteEntfernen(spalte);
    }
  }
}
