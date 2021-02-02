package steuerung;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatFlagsException;
import java.util.List;

import modell.SteuerungFassade;
import modell.formel.Atom;
import modell.formel.Formel;

public class FormelEditor {
  private List<Atom> atomareAussagenA;
  private String formel_alt;
  private String formel;

  /**
   * Konstruktor für den FormelEditor.
   * 
   * @param atomareAussagen die atomaren Aussagen die in den Aussagenlogischen
   *                        Formel vorkommen kommen.
   */
  public FormelEditor(List<Atom> atomareAussagen) {
    this.atomareAussagenA = atomareAussagen;
  }

  /**
   * Übergibt eine Formel zu der die FormelAnsicht zur Bearbeitung geöffnet wird.
   * Sobald die Formel bestätigt oder abgeleht wurden wird die Formel zurück
   * gegeben.
   * 
   * @param formel_alt die Formel die bearbeitet werden soll und zurück gegeben
   *                   wird falls abgebrochen wird.
   * @return die neue Formel.
   */
  public String gibNeueFormel(String formel_alt) {
    this.formel_alt = formel_alt;
    FormelAnsicht ansicht = new FormelAnsicht(atomZuString(atomareAussageA));
    ansicht.getFormel();
    return this.formel;
  }

  /**
   * Wandelt die Atom Liste in ein String Liste um, sodass sie vom Formeleditor
   * verarbeitet werden kann.
   * 
   * @param atomareAussageA.
   * @return die Aussagen der Atome.
   */
  private List<String> atomZuString(List<Atom> atomareAussageA) {
    List<String> atomareAussageS = new ArrayList<String>();
    for (int i = 0; i < atomareAussageA.size(); i++) {
      atomareAussageS.add(atomareAussageA.get(i).getAussage());
    }
    return atomareAussageS;
  }

  /**
   * setzt ein weiteres Zeichen an das Ende der aktuellen Formel
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
    formel = formel_alt;
  }

  /**
   * gibt zurück ob diese Formel bestätig ist. Das heißt keine Klammern mehr offen
   * und es nicht auf einen offenen Konnektor endet.
   */
  public boolean bestätige() {
    char letzerCh = formel.charAt(formel.length());
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
    switch (formel.charAt(formel.length())) {
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
      return false;
    }

  }

  /**
   * gibt zurück ob es in der aktuelle Formel noch offene Klammern gibt.
   * 
   * @return gibt es noch offene Klammern?
   */
  private boolean klammerOffen() {
    int i = 0;
    while (formel.indexOf('(', i) > 0) {
      i = formel.indexOf('(', i);
      if (formel.indexOf(')', i) < 0) {
        return true;
      }
    }
    return false;
  }
}
