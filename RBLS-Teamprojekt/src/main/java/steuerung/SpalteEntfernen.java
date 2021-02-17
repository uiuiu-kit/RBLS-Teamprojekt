package steuerung;

import modell.Fassade;

public class SpalteEntfernen extends WahrheitstabellenBefehl {

  private int spalte;
  private int spaltenAnz;
  private int atomAnz;

  /**
   * Kostruktor f�r SpalteEntfernen der die Ausf�hrung des Befehls anst��t.

   * @param model  die SteuerungFassade auf die bei der Ausf�hrung zugegriffen
   *               wird.
   * @param spalte die spalte die entfernt werden soll
   */
  public SpalteEntfernen(Fassade model, int spalte) {
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
    if (spalte < spaltenAnz && spalte >= atomAnz) {
      model.spalteEntfernen(spalte);
    }
  }
}
