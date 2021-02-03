package steuerung;

import java.util.ArrayList;
import java.util.List;
import modell.SteuerungFassade;
import modell.formel.Atom;
import modell.formel.ExklusivOder;
import modell.formel.Formel;
import modell.formel.Implikation;
import modell.formel.Nicht;
import modell.formel.Oder;
import modell.formel.Und;

public class FormelParser {

  /**
   * Baut eine Formel die als String vorliegt in eine Baumstruktur um.
   * 
   * @param formelS die gegebene Formel als String
   * @return Das Wurzelelement der Formel
   */
  public static Formel pars(String formelS, SteuerungFassade fassade) {
    Formel formelF = null;
    List<String> klammerAusdruecke = klammerAusdrueckeErsetzen(formelS);
    formelS = klammerAusdruecke.get(klammerAusdruecke.size() - 1);
    if (formelS.length() < 2) {
      int num = Integer.parseInt(formelS);
      String aussage = fassade.gibAtomareAussage().get(num).getAussage();
      String repraesentation = aussage.substring(0, 1);
      formelF = new Atom(aussage, repraesentation, num);
    }
    String[] formleSplit;
    formleSplit = formelS.split("f", 2);
    if (formleSplit.length > 1) {
      Formel rechts = pars(klammerAusdrueckeWiederherstellen(formleSplit[0], klammerAusdruecke),
          fassade);
      Formel links = pars(klammerAusdrueckeWiederherstellen(formleSplit[1], klammerAusdruecke),
          fassade);
      formelF = new Implikation(rechts, links);
    } else {
      formleSplit = formelS.split("o", 2);
      if (formleSplit.length > 1) {
        Formel rechts = pars(klammerAusdrueckeWiederherstellen(formleSplit[0], klammerAusdruecke),
            fassade);
        Formel links = pars(klammerAusdrueckeWiederherstellen(formleSplit[1], klammerAusdruecke),
            fassade);
        formelF = new Oder(rechts, links);
      } else {
        formleSplit = formelS.split("x", 2);
        if (formleSplit.length > 1) {
          Formel rechts = pars(klammerAusdrueckeWiederherstellen(formleSplit[0], klammerAusdruecke),
              fassade);
          Formel links = pars(klammerAusdrueckeWiederherstellen(formleSplit[1], klammerAusdruecke),
              fassade);
          formelF = new ExklusivOder(rechts, links);
        } else {
          formleSplit = formelS.split("u", 2);
          if (formleSplit.length > 1) {
            Formel rechts = pars(
                klammerAusdrueckeWiederherstellen(formleSplit[0], klammerAusdruecke), fassade);
            Formel links = pars(
                klammerAusdrueckeWiederherstellen(formleSplit[1], klammerAusdruecke), fassade);
            formelF = new Und(rechts, links);
          } else {
            formleSplit = formelS.split("n", 1);
            if (formleSplit.length > 1) {
              Formel rechts = pars(
                  klammerAusdrueckeWiederherstellen(formleSplit[0], klammerAusdruecke), fassade);
              formelF = new Nicht(rechts);
            }
          }
        }
      }
    }
    return formelF;
  }

  /**
   * Ersetzt Klammerausdrücke.
   * 
   * @param formelS Formel in der Klammernasudrücke durch k(Index) ersetzt werden.
   * @return Formel mit Ersetzungen
   */
  private static List<String> klammerAusdrueckeErsetzen(String formelS) {
    List<String> klammerAusdruecke = new ArrayList<String>();
    int i = 0;
    while (formelS.matches("...\\(...\\)...")) {
      klammerAusdruecke.add((formelS.substring(formelS.lastIndexOf("("),
          formelS.indexOf(")", formelS.lastIndexOf("(")))));
      formelS.replace("\\([a-z,0-3]*\\)", "k" + i);
      i++;
    }
    klammerAusdruecke.add(formelS);
    return klammerAusdruecke;
  }

  /**
   * Stellt Klammerausdrücke wieder her.
   * 
   * @param formelS Formel in der k(Index) durch Klammerausdrücke zurück ersetzt
   *                werden.
   * @return Formel mit Rückersetzungen.
   */
  private static String klammerAusdrueckeWiederherstellen(String formelS,
      List<String> klammerAusdruecke) {
    int i = 0;
    while (formelS.matches("k" + i)) {
      formelS.replaceFirst("k" + i, klammerAusdruecke.get(i));
      i++;
    }
    return formelS;
  }
}
