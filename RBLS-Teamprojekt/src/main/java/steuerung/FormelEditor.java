package steuerung;

import java.util.List;
import praesentation.FormelAnsicht;

public class FormelEditor {
  private List<String> atomareAussagen;
  private String formelAlt;
  private String formel;

  /**
   * Konstruktor für den FormelEditor.
   * 
   * @param atomareAussagen die atomaren Aussagen die in den aussagenlogischen
   *                        Formel vorkommen kommen.
   */
  public FormelEditor(List<String> atomareAussagen) {
    this.atomareAussagen = atomareAussagen;
    formel = "";
  }

  /**
   * Übergibt eine Formel zu der die FormelAnsicht, zur Bearbeitung, geöffnet
   * wird. Sobald die Formel bestätigt oder abgeleht wurden wird die Formel zurück
   * gegeben.
   * 
   * @param formelAlt die Formel die bearbeitet werden soll und zurück gegeben
   *                  wird falls abgebrochen wird.
   * @return die neue Formel.
   */
  public String gibNeueFormel(String formelAlt) {
    this.formelAlt = formelAlt;
    FormelAnsicht ansicht = new FormelAnsicht((String[]) atomareAussagen.toArray(), this);
    ansicht.getFormel();
    return this.formel;
  }

  /**
   * setzt ein weiteres Zeichen an das Ende der aktuellen Formel.
   * 
   * @param zeichen das zu setzende Zeichen
   */
  public void setzeZeichen(char zeichen) {
    formel = formel + zeichen;
  }

  /**
   * entfernt das letzte Zeichen der aktuellen Formel.
   */
  public void entferneletzesZeichen() {
    if (formel.length() > 0) {
      formel = formel.substring(0, formel.length() - 1);
    }
  }

  /**
   * setzt die Formel zurück.
   */
  public void brecheAb() {
    formel = formelAlt;
  }

  /**
   * gibt zurück ob diese Formel gültig ist, das heißt, das keine Klammern mehr
   * offen ist und die Formel nicht mit einen Konnektor endet.
   */
  public boolean bestaetige() {
    char letzerCh = formel.charAt(formel.length() - 1);
    if (!klammerOffen() || (letzerCh == '0' || letzerCh == '1' || letzerCh == '2' || letzerCh == '3'
        || letzerCh == ')')) {
      return true;
    }
    return false;
  }

  /**
   * gibt zurück ob das nächste Zeichen erlaubt ist.
   * 
   * @param naechsterCh das nächste Zeichen
   * @return ob es erlaubt ist
   */
  public boolean zeichenErlaubt(char naechsterCh) {
    if (formel.equals("")) {
      return naechsterCh == '(' || naechsterCh == 'n' || naechsterCh == '0' || naechsterCh == '1'
          || naechsterCh == '2' || naechsterCh == '3';
    }
    switch (formel.charAt(formel.length() - 1)) {
      case '0':
      case '1':
      case '2':
      case '3':
      case ')':
        return naechsterCh == 'o' || naechsterCh == 'u' || naechsterCh == 'x' || naechsterCh == 'f'
            || klammerOffen() && naechsterCh == ')';
      case 'o':
      case 'u':
      case 'x':
      case 'f':
      case 'n':
      case '(':
        return naechsterCh == '(' || naechsterCh == 'n' || naechsterCh == '0' || naechsterCh == '1'
            || naechsterCh == '2' || naechsterCh == '3';
      default:
        return naechsterCh == '(' || naechsterCh == 'n' || naechsterCh == '0' || naechsterCh == '1'
            || naechsterCh == '2' || naechsterCh == '3';
    }

  }

  /**
   * gibt zurück ob es in der aktuelle Formel noch offene Klammern gibt.
   * 
   * @return ob es noch offene Klammern gibt
   */
  private boolean klammerOffen() {
    int offene = 0;
    for (int i = 0; i < formel.length(); i++) {
      if (formel.charAt(i) == '(') {
        offene = offene + 1;
      }
      if (formel.charAt(i) == ')') {
        offene = offene - 1;
      }
    }
    if (offene == 0) {
      return false;
    }
    return true;
  }
}
