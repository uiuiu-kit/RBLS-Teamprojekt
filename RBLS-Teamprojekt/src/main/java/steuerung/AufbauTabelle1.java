package steuerung;

import java.util.ArrayList;
import java.util.List;
import modell.Fassade;
import modell.formel.Formel;

public class AufbauTabelle1 extends AufbauTabelle {

  private List<String> noetigeFormelnS;

  /**
   * Kostruktor für AufbauTabelle1 der die Ausführung des Befehls anstößt.
   * 
   * @param model die SteuerungFassade auf die bei der Ausführung zugegriffen
   *              wird.
   */
  public AufbauTabelle1(Fassade model) {
    super(model);
    hohleDaten();
    setzeDaten();
  }

  @Override
  public void hohleDaten() {
    noetigeFormelnS = model.gibNoetigeFormel();
    atomareAussagen = model.gibAtomareAussage();
  }

  @Override
  public void setzeDaten() {
    List<Formel> noetigeFormelnF = new ArrayList<Formel>();
    for (int i = 0; i < noetigeFormelnS.size(); i++) {
      noetigeFormelnF.add(FormelParser.pars(noetigeFormelnS.get(i), model));
    }
    for (int i = 0; i < noetigeFormelnF.size(); i++) {
      model.spalteHinzufuegen();
      model.setzeFormel(noetigeFormelnF.get(i), i + atomareAussagen.size());
    }
  }
}
