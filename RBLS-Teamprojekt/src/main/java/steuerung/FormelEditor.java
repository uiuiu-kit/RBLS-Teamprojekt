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

  public FormelEditor(List<Atom> atomareAussagen) {
    this.atomareAussagenA = atomareAussagen;
  }

  public String gibNeueFormel(String formel) {
    formel_alt = formel;
    FormelAnsicht ansicht = new FormelAnsicht(atomZuString(atomareAussageA));
    ansicht.getFormel();
    return this.formel;
  }

  private List<String> atomZuString(List<Atom> atomareAussageA) {
    List<String> atomareAussageS = new ArrayList<String>();
    for (int i = 0; i < atomareAussageA.size(); i++) {
      atomareAussageS.add(atomareAussageA.get(i).getAussage());
    }
    return atomareAussageS;
  }

  public void setzeZeichen(char zeichen) {
    formel = formel + zeichen;
  }

  public void entferneletzesZeichen() {
    if (formel.length() > 0) {
      formel = formel.substring(0, formel.length() - 1);
    }
  }

  public void brecheAb() {
    formel = formel_alt;
  }

  public void bestätige(String formel) throws Exception {
    char letzerCh = formel.charAt(formel.length());
    if (!klammerOffen(formel) && (letzerCh == '0' || letzerCh == '1' || letzerCh == '2' || letzerCh == '3' || letzerCh == ')')) {
      this.formel = formel;
    } else {
      throw new IllegalFormatFlagsException("Keine gültige Logische Forme");
    }
  }

  public boolean zeichenErlaubt(String formel,char naechsterCh) {
    switch (formel.charAt(formel.length())) {
    case '0':
    case '1':
    case '2':
    case '3':
    case ')':
      return naechsterCh == 'o' || naechsterCh == 'u' || naechsterCh == 'x' || naechsterCh == 'f' || klammerOffen(formel) && naechsterCh == ')';
    case 'o':
    case 'u':
    case 'x':
    case 'f':
    case 'n':
    case '(':
      return naechsterCh == '(' || naechsterCh == 'n' || naechsterCh == '0' || naechsterCh == '1' || naechsterCh == '2' || naechsterCh == '3';
    default:
      return false;
    }
    
  }
  
  private boolean klammerOffen(String formel) {
    int i = 0;
    while(formel.indexOf('(', i) > 0) {
      i = formel.indexOf('(', i);
      if(formel.indexOf(')', i) < 0) {
        return true;
      }
    }
    return false;
  }
}
