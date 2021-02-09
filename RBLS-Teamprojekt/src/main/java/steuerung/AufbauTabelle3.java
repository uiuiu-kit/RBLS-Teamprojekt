package steuerung;

import modell.SteuerungFassade;

public class AufbauTabelle3 extends AufbauTabelle {

  AufbauTabelle1 aufbautabelle1;
  AufbauTabelle24 aufbauTabelle24;

  public AufbauTabelle3(SteuerungFassade model) {
    super(model);

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
