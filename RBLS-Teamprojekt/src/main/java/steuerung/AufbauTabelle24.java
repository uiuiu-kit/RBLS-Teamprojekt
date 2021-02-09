package steuerung;

import modell.SteuerungFassade;

public class AufbauTabelle24 extends AufbauTabelle {
  private int anzAtome;

  public AufbauTabelle24(SteuerungFassade model) {
    super(model);
  }

  @Override
  public void hohleDaten() {
    anzAtome = model.gibAtomareAussage().size();
  }

  @Override
  public void setzeDaten() {
    boolean[] akFall;
    int[] koordinaten = new int[2];
    boolean[][] faelle = new boolean[(int) Math.pow(2, anzAtome)][anzAtome];
    Berechner.faelleBerechnen(anzAtome, faelle, 0);
    for (int i = 0; i < faelle.length; i++) {
      akFall = faelle[i];
      koordinaten[0] = i;
      for (int j = 0; j < faelle.length; j++) {
        koordinaten[1] = j;
        model.setzeZelleWW(koordinaten, akFall[j]);
      }
    }
  }
}
