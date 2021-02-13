package steuerung;

import modell.SteuerungFassade;

public class AufbauTabelle3 extends AufbauTabelle {

  AufbauTabelle1 aufbautabelle1;
  AufbauTabelle24 aufbauTabelle24;

  /**
   * Kostruktor für AufbauTabelle3 der die Ausführung des Befehls anstößt.
   * 
   * @param model die SteuerungFassade auf die bei der Ausführung zugegriffen
   *              wird.
   * 
   */
  public AufbauTabelle3(SteuerungFassade model) {
    super(model);
    hohleDaten();
    setzeDaten();
  }

  @Override
  public void hohleDaten() {
    // keine Daten benötigt
  }

  @Override
  public void setzeDaten() {
    aufbautabelle1 = new AufbauTabelle1(model);
    aufbauTabelle24 = new AufbauTabelle24(model);
  }
}
