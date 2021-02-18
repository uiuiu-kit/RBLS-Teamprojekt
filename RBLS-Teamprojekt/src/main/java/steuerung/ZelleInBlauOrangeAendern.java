package steuerung;

import modell.Fassade;

public class ZelleInBlauOrangeAendern extends WahrheitstabellenBefehl {
  private int[] zellenKoordinaten;
  private boolean zelleWaWe;

  /**
   * �ndert den Wahrheitswert in einer Wahrheitswert Zelle.
   * 
   * @param model  die SteuerungFassade auf der der Befehl ausgef�hrt wird.
   * @param spalte die Spalte in der der Wert ge�ndert wird
   * @param zeile  die Zeile in der der Wert ge�ndert wird
   */
  public ZelleInBlauOrangeAendern(Fassade model, int zeile, int spalte) {
    super(model);
    zellenKoordinaten = new int[2];
    zellenKoordinaten[0] = zeile;
    zellenKoordinaten[1] = spalte;
    hohleDaten();
    setzeDaten();
  }

  @Override
  public void hohleDaten() {
    zelleWaWe = model.gibZelleWaWe(zellenKoordinaten);
  }

  @Override
  public void setzeDaten() {
    model.setzeZelleWaWe(zellenKoordinaten, !zelleWaWe);
  }
}
