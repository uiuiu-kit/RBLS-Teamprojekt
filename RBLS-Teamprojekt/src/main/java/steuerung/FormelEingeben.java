package steuerung;

import java.util.List;
import modell.SteuerungFassade;
import modell.formel.Atom;
import modell.formel.Formel;

public class FormelEingeben extends WahrheitstabellenBefehl {

  private int[] zellenKoordinaten;
  private String alteFromel;
  private List<Atom> atomareAussagen;

  /**
   * Der Konstruktor, für die Befehl, der den Befehl auch direkt ausführt.
   * 
   * @param model             die Fassade auf die der Befehl zugreift.
   * @param zellenKoordinaten die Koordinaten der Zelle dessen Formel geändert
   *                          werden soll.
   */
  public FormelEingeben(SteuerungFassade model, int[] zellenKoordinaten) {
    super(model);
    this.zellenKoordinaten = zellenKoordinaten;
    hohleDaten();
    setzeDaten();
  }

  /**
   * hohlt die alte Formel und die atomaren Aussagen von der Fasssade.
   */
  public void hohleDaten() {
    alteFromel = model.gibFormelText(zellenKoordinaten[0]);
    atomareAussagen = model.gibAtomareAussage();
  }

  /**
   * Ruf das Dialogfenster auf, in dem man die Formel editieren kann, und überträgt
   * die neue Formel, über die Fassade, zum Modell.
   */
  public void setzeDaten() {
    FormelEditor fe = new FormelEditor(atomareAussagen);
    String neueFormel = fe.gibNeueFormel(alteFromel);
    Formel neueFormelF = FormelParser.pars(neueFormel, model);
    model.setzeFormel(neueFormelF, zellenKoordinaten[0]);
  }
}
