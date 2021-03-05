package steuerung;

import java.util.List;
import praesentation.FormelAnsicht;

public class FormelEditor {
  private List<String> atomareAussagen;
  private String formelAlt;
  private String formel;
  private boolean betaetige;

  /**
   * Konstruktor f�r den FormelEditor.
   * 
   * @param atomareAussagen die atomaren Aussagen die in den aussagenlogischen
   *                        Formel vorkommen kommen.
   */
  public FormelEditor(List<String> atomareAussagen) {
    this.atomareAussagen = atomareAussagen;
    betaetige = false;
  }

  /**
   * �bergibt eine Formel zu der die FormelAnsicht, zur Bearbeitung, ge�ffnet
   * wird. Sobald die Formel best�tigt oder abgeleht wurden wird die Formel zur�ck
   * gegeben.
   * 
   * @param formelAlt die Formel die bearbeitet werden soll und zur�ck gegeben
   *                  wird falls abgebrochen wird.
   * @return die neue Formel.
   */
  public String gibNeueFormel(String formelAlt, String formelAltRep) {
    this.formelAlt = formelAlt;
    if (formelAlt.equals("-1")) {
      formelAltRep = "";
      formel = "";
    } else {
      formel = formelAlt;
    }
    new FormelAnsicht(listToArray(atomareAussagen), this, formelAltRep);
    if (betaetige) {
      return this.formel;
    } else {
      return formelAlt;
    }
  }

  /**
   * Setzt ein weiteres Zeichen an das Ende der aktuellen Formel.
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
   * setzt die Formel zur�ck.
   */
  public void brecheAb() {
    formel = formelAlt;
  }

  /**
   * gibt zur�ck ob diese Formel g�ltig ist, das hei�t, das keine Klammern mehr
   * offen ist und die Formel nicht mit einen Konnektor endet.
   */
  public boolean bestaetige() {
    if (formel.length() > 0) {
      char letzerCh = formel.charAt(formel.length() - 1);
      if (!klammerOffen() && (letzerCh == '0' || letzerCh == '1' || letzerCh == '2'
          || letzerCh == '3' || letzerCh == '4' || letzerCh == ')')) {
        betaetige = true;
        return true;
      }
    }
    return false;
  }

  /**
   * Gibt zur�ck ob das n�chste Zeichen erlaubt ist.
   * 
   * @param naechsterCh das n�chste Zeichen
   * @return ob es erlaubt ist
   */
  public boolean zeichenErlaubt(char naechsterCh) {
    if (formel.equals("")) {
      return naechsterCh == '(' || naechsterCh == 'n' || naechsterCh == '0' || naechsterCh == '1'
          || naechsterCh == '2' || naechsterCh == '3' || naechsterCh == '4';
    }
    switch (formel.charAt(formel.length() - 1)) {
      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case ')':
        return naechsterCh == 'o' || naechsterCh == 'u' || naechsterCh == 'x' || naechsterCh == 'f'
            || naechsterCh == 'a' || klammerOffen() && naechsterCh == ')';
      case 'o':
      case 'u':
      case 'x':
      case 'f':
      case 'a':
      case 'n':
      case '(':
        return naechsterCh == '(' || naechsterCh == 'n' || naechsterCh == '0' || naechsterCh == '1'
            || naechsterCh == '2' || naechsterCh == '3' || naechsterCh == '4';
      default:
        return naechsterCh == '(' || naechsterCh == 'n' || naechsterCh == '0' || naechsterCh == '1'
            || naechsterCh == '2' || naechsterCh == '3' || naechsterCh == '4';
    }

  }

  private String[] listToArray(List<String> atomareAussagenL) {
    String[] atomareAussagenA = new String[atomareAussagenL.size()];
    for (int i = 0; i < atomareAussagenL.size(); i++) {
      atomareAussagenA[i] = atomareAussagenL.get(i);
    }
    return atomareAussagenA;
  }

  /**
   * Gibt zur�ck ob es in der aktuelle Formel noch offene Klammern gibt.
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
