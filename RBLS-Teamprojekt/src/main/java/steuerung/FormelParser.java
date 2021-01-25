package steuerung;

import java.util.ArrayList;
import java.util.List;
import modell.SteuerungFassade;
import modell.formel.*;

public class FormelParser {

  List<String> klammerAusdruecke = new ArrayList<String>();

  /**
   * Baut eine Formel die als String vorliegt in eine Baumstruktur um.
   * 
   * @param formelS die gegebene Formel als String
   * @return Das Wurzelelement der Formel
   */
  public Formel pars(String formelS, SteuerungFassade fassade) {
    Formel formelF = null;
    if (formelS.length() < 2) {
      int num = Integer.parseInt(formelS);
      String aussage = fassade.gibAtomareAussage().get(num).getAussage();
      String repraesentation = aussage.substring(0, 0);
      formelF = new Atom(aussage, repraesentation, num);
    }
    String[] formleSplit;
    klammerAusdrueckeErsetzen(formelS);
    formleSplit = formelS.split("f", 2);
    if (formleSplit.length > 1) {
      Formel rechts = pars(klammerAusdrueckeWiederherstellen(formleSplit[0]), fassade);
      Formel links = pars(klammerAusdrueckeWiederherstellen(formleSplit[1]), fassade);
      formelF = new Implikation(rechts, links);
    } else {
      formleSplit = formelS.split("x", 2);
      if (formleSplit.length > 1) {
        Formel rechts = pars(klammerAusdrueckeWiederherstellen(formleSplit[0]), fassade);
        Formel links = pars(klammerAusdrueckeWiederherstellen(formleSplit[1]), fassade);
        formelF = new Oder(rechts, links);
      } else {
        formleSplit = formelS.split("o", 2);
        if (formleSplit.length > 1) {
          Formel rechts = pars(klammerAusdrueckeWiederherstellen(formleSplit[0]), fassade);
          Formel links = pars(klammerAusdrueckeWiederherstellen(formleSplit[1]), fassade);
          formelF = new Oder(rechts, links);
        } else {
          formleSplit = formelS.split("u", 2);
          if (formleSplit.length > 1) {
            Formel rechts = pars(klammerAusdrueckeWiederherstellen(formleSplit[0]), fassade);
            Formel links = pars(klammerAusdrueckeWiederherstellen(formleSplit[1]), fassade);
            formelF = new Und(rechts, links);
          } else {
            formleSplit = formelS.split("n", 1);
            if (formleSplit.length > 1) {
              Formel rechts = pars(klammerAusdrueckeWiederherstellen(formleSplit[0]), fassade);
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
  private String klammerAusdrueckeErsetzen(String formelS) {
    int i = 0;
    while (formelS.matches("...\\(...\\)...")) {
      i++;
      klammerAusdruecke.add((formelS.substring(formelS.indexOf("("), formelS.indexOf(")"))));
      formelS.replaceFirst("\\(...\\)", "k" + i);
    }
    return formelS;
  }

  /**
   * Stellt Klammerausdrücke wieder her.
   * 
   * @param formelS Formel in der k(Index) durch Klammerausdrücke zurück ersetzt
   *                werden.
   * @return Formel mit Rückersetzungen.
   */
  private String klammerAusdrueckeWiederherstellen(String formelS) {
    int i = 0;
    while (formelS.matches("k.")) {
      i++;
      formelS.replaceFirst("k.", klammerAusdruecke.get(i));
    }
    return formelS;
  }
}
