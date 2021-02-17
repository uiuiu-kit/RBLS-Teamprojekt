package steuerung;

import java.util.List;
import modell.Fassade;
import modell.formel.Formel;


public class FormelEingeben extends WahrheitstabellenBefehl {

  private int spalte;
  private String alteFormel;
  private List<String> atomareAussagen;

  /**
   * Der Konstruktor, f�r die Befehl, der den Befehl auch direkt ausf�hrt.

   * @param model  die Fassade auf die der Befehl zugreift.
   * @param spalte die Spalte in der die Formel ge�ndert werden soll.
   */
  public FormelEingeben(Fassade model, int spalte) {
    super(model);
    this.spalte = spalte;
    hohleDaten();
    setzeDaten();
  }

  /**
   * Hohlt die alte Formel und die atomaren Aussagen von der Fasssade.
   */
  public void hohleDaten() {
    try {
      alteFormel = model.gibFormelText(spalte);
    } catch (NullPointerException e) {
      alteFormel = "";
    }
    atomareAussagen = model.gibAtomareAussage();
  }

  /**
   * Ruf das Dialogfenster auf, in dem man die Formel editieren kann, und
   * �bertr�gt die neue Formel, �ber die Fassade, zum Modell.
   */
  public void setzeDaten() {
    FormelEditor fe = new FormelEditor(atomareAussagen);
    String neueFormel = fe.gibNeueFormel(alteFormel);
    Formel neueFormelF = FormelParser.pars(neueFormel, model);
    model.setzeFormel(neueFormelF, spalte);
  }
}
