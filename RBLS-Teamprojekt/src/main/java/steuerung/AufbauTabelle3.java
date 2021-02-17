package steuerung;

import modell.Fassade;

public class AufbauTabelle3 extends AufbauTabelle {

  AufbauTabelle1 aufbautabelle1;
  AufbauTabelle24 aufbauTabelle24;

  /**
   * Kostruktor f�r AufbauTabelle3 der die Ausf�hrung des Befehls anst��t.

   * @param model die SteuerungFassade auf die bei der Ausf�hrung zugegriffen
   *              wird.
   * 
   */
  public AufbauTabelle3(Fassade model) {
    super(model);
    hohleDaten();
    setzeDaten();
  }

  @Override
  public void hohleDaten() {
    // keine Daten ben�tigt
  }

  @Override
  public void setzeDaten() {
    aufbautabelle1 = new AufbauTabelle1(model);
    aufbauTabelle24 = new AufbauTabelle24(model);
  }
}
