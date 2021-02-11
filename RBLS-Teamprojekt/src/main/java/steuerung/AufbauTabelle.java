package steuerung;

import java.util.List;
import modell.SteuerungFassade;

public abstract class AufbauTabelle extends WahrheitstabellenBefehl {

  protected List<String> atomareAussagen;

  /**
   * AufbauTabelle ist der Konstruktor für den Befehle vom Typ AufbauTabelle.
   * 
   * @param model die Fassde von der Daten geholt und bei der Daten gesetzt
   *              werden.
   */
  public AufbauTabelle(SteuerungFassade model) {
    super(model);
    holeDaten();
  }

  /**
   * Holt die atomaren Aussagen von der Fassade, da sie in jedem Befehl vom Typ
   * AufbauTabelle verwendet werden.
   */
  private void holeDaten() {
    atomareAussagen = model.gibAtomareAussage();
  }
}
