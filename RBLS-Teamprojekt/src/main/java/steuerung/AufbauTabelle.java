package steuerung;

import java.util.List;
import modell.Fassade;

public abstract class AufbauTabelle extends WahrheitstabellenBefehl {

  protected List<String> atomareAussagen;

  /**
   * AufbauTabelle ist der Konstruktor f�r den Befehle vom Typ AufbauTabelle.

   * @param model die Fassde von der Daten geholt und bei der Daten gesetzt
   *              werden.
   */
  public AufbauTabelle(Fassade model) {
    super(model);
  }
}
