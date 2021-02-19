package steuerung;

import java.util.ArrayList;
import java.util.List;
import modell.Fassade;
import modell.formel.Aequivalenz;
import modell.formel.Atom;
import modell.formel.ExklusivOder;
import modell.formel.Formel;
import modell.formel.Implikation;
import modell.formel.Nicht;
import modell.formel.Oder;
import modell.formel.Und;

public class FormelParser {

  /**
   * Baut eine Formel, die als String vorliegt, in die Formel-Baumstruktur um.
   * 
   * @param formelS die gegebene Formel als String
   * @return Das Wurzelelement der Formel
   */
  public static Formel pars(String formelS, Fassade fassade) {
    Formel formelF = null;
    List<String> klammerAusdruecke = new ArrayList<String>();
    if (formelS.length() > 1) {
      if (umfassendeKlammer(formelS)) {
        formelS = formelS.substring(1, formelS.length() - 1);
      }
    }
    klammerAusdruecke = klammerAusdrueckeErsetzen(formelS);
    formelS = klammerAusdruecke.get(klammerAusdruecke.size() - 1);
    if (formelS.length() < 2) {
      int num = Integer.parseInt(formelS);
      String aussage = fassade.gibAtomareAussage().get(num);
      formelF = new Atom(aussage, num);
    }
    String[] formleSplit;
    formleSplit = formelS.split("a", 2);
    if (formleSplit.length > 1) {
      Formel links = pars(klammerAusdrueckeWiederherstellen(formleSplit[0], klammerAusdruecke),
          fassade);
      Formel rechts = pars(klammerAusdrueckeWiederherstellen(formleSplit[1], klammerAusdruecke),
          fassade);
      formelF = new Aequivalenz(links, rechts);
    } else {
      formleSplit = formelS.split("f", 2);
      if (formleSplit.length > 1) {
        Formel links = pars(klammerAusdrueckeWiederherstellen(formleSplit[0], klammerAusdruecke),
            fassade);
        Formel rechts = pars(klammerAusdrueckeWiederherstellen(formleSplit[1], klammerAusdruecke),
            fassade);
        formelF = new Implikation(links, rechts);
      } else {
        formleSplit = formelS.split("o", 2);
        if (formleSplit.length > 1) {
          Formel links = pars(klammerAusdrueckeWiederherstellen(formleSplit[0], klammerAusdruecke),
              fassade);
          Formel rechts = pars(klammerAusdrueckeWiederherstellen(formleSplit[1], klammerAusdruecke),
              fassade);
          formelF = new Oder(links, rechts);
        } else {
          formleSplit = formelS.split("x", 2);
          if (formleSplit.length > 1) {
            Formel links = pars(
                klammerAusdrueckeWiederherstellen(formleSplit[0], klammerAusdruecke), fassade);
            Formel rechts = pars(
                klammerAusdrueckeWiederherstellen(formleSplit[1], klammerAusdruecke), fassade);
            formelF = new ExklusivOder(links, rechts);
          } else {
            formleSplit = formelS.split("u", 2);
            if (formleSplit.length > 1) {
              Formel links = pars(
                  klammerAusdrueckeWiederherstellen(formleSplit[0], klammerAusdruecke), fassade);
              Formel rechts = pars(
                  klammerAusdrueckeWiederherstellen(formleSplit[1], klammerAusdruecke), fassade);
              formelF = new Und(links, rechts);
            } else {
              if (formelS.charAt(0) == 'n') {
                Formel links = pars(
                    klammerAusdrueckeWiederherstellen(formelS.substring(1), klammerAusdruecke),
                    fassade);
                formelF = new Nicht(links);
              }
            }
          }
        }
      }
    }
    return formelF;
  }

  private static boolean umfassendeKlammer(String formelS) {
    if (formelS.charAt(0) == '(' && formelS.charAt(formelS.length() - 1) == ')') {
      int offene = 0;
      for (int i = 1; i < formelS.length() - 2; i++) {
        if (formelS.charAt(i) == '(') {
          offene = offene + 1;
        }
        if (formelS.charAt(i) == ')') {
          offene = offene - 1;
        }
        if (offene == -1) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  /**
   * Ersetzt Klammerausdr�cke.
   * 
   * @param formelS Formel in der Klammerausdr�cke jeweils durch k(Index) ersetzt
   *                werden.
   * @return Formel mit ersetzten Klammerausdr�cke
   */
  private static List<String> klammerAusdrueckeErsetzen(String formelS) {
    List<String> klammerAusdruecke = new ArrayList<String>();
    int i = 0;
    int open;
    int close;
    String formelSn = formelS;
    while (formelS.matches(".*\\(.*\\).*")) {
      open = formelS.lastIndexOf("(");
      close = formelS.indexOf(")", open);
      klammerAusdruecke.add((formelS.substring(open, close + 1)));
      formelSn = "k" + i;
      if (0 < open) {
        formelSn = formelS.substring(0, open) + formelSn;
      }
      if (close < formelS.length() - 1) {
        formelSn = formelSn + formelS.substring(close + 1, formelS.length());
      }
      formelS = formelSn;
      i++;
    }
    klammerAusdruecke.add(formelS);
    return klammerAusdruecke;
  }

  /**
   * Stellt Klammerausdr�cke wieder her.
   * 
   * @param formelS Formel in der k(Index) durch Klammerausdr�cke zur�ck ersetzt
   *                werden sollen.
   * @return Formel mit R�ckersetzungen.
   */
  private static String klammerAusdrueckeWiederherstellen(String formelS,
      List<String> klammerAusdruecke) {
    int i = klammerAusdruecke.size() - 2;
    while (formelS.matches(".*k[0-9]*.*")) {
      formelS = formelS.replaceFirst("k[0-9]*", klammerAusdruecke.get(i));
      klammerAusdruecke.remove(i);
      i = klammerAusdruecke.size() - 2;
    }
    return formelS;
  }
}
