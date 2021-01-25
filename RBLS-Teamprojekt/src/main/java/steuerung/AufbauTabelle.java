package steuerung;

import java.util.List;
import modell.SteuerungFassade;
import modell.formel.Atom;

public abstract class AufbauTabelle extends WahrheitstabellenBefehl {

  private List<Atom> atomareAussagen;

  /**
   * Konstruktor für den Befehle vom Typ AufbauTabelle.
   * 
   * @param model die Fassde von der Daten geholt und bei der Daten gesetzt
   *              werden.
   */
  public AufbauTabelle(SteuerungFassade model) {
    super(model);
    holeDaten();
  }

  /**
   * Holt die Atomaren Aussagen von der Fassade da sie in jedem Befehl vom Typ
   * AufbauTabelle verwendet werden.
   */
  private void holeDaten() {
    atomareAussagen = model.gibAtomareAussage();
  }
}
