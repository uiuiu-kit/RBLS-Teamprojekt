package steuerung;

import modell.Fassade;

public class AufbauTabelle24 extends AufbauTabelle {
  private int anzAtome;

  /**
   * Kostruktor f�r AufbauTabelle24 der die Ausf�hrung des Befehls anst��t.

   * @param model die SteuerungFassade auf die bei der Ausf�hrung zugegriffen
   *              wird.
   */
  public AufbauTabelle24(Fassade model) {
    super(model);
    hohleDaten();
    setzeDaten();
  }

  @Override
  public void hohleDaten() {
    anzAtome = model.gibAtomareAussage().size();
    atomareAussagen = model.gibAtomareAussage();
  }

  @Override
  public void setzeDaten() {
    boolean[] akFall;
    int[] koordinaten = new int[2];
    boolean[][] faelle = new boolean[(int) Math.pow(2, anzAtome)][anzAtome];
    Berechner.faelleBerechnen(anzAtome, faelle, 0);
    for (int i = 0; i < faelle.length; i++) {
      akFall = faelle[i];
      koordinaten[0] = i + 1;
      for (int j = 0; j < akFall.length; j++) {
        koordinaten[1] = j;
        model.setzeZelleWaWe(koordinaten, akFall[j]);
      }
    }
  }
}
