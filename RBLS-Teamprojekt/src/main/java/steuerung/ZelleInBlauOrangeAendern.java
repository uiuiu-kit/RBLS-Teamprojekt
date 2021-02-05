package steuerung;

import modell.SteuerungFassade;

public class ZelleInBlauOrangeAendern extends WahrheitstabellenBefehl {
  private int[] zellenKoordinaten;
  private boolean zelleWW;

  public ZelleInBlauOrangeAendern(SteuerungFassade model, int[] koordinaten) {
    super(model);
    zellenKoordinaten = koordinaten;
  }

  @Override
  public void hohleDaten() {
    zelleWW = model.gibZelleWW(zellenKoordinaten);
  }

  @Override
  public void setzeDaten() {
    model.setzeZelleWW(zellenKoordinaten, !zelleWW);
  }
}
