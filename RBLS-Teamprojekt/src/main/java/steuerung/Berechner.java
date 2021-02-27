package steuerung;

import modell.formel.Formel;

public class Berechner {
  /**
   * berechnet alle m�glichen F�lle abh�ngig von der Anzahl der atomaren Aussagen.
   * 
   * @param anzAtom     Anzahl der Atome die wahr oder falsch sein k�nnen.
   * @param faelle      Das Array in dem alle F�lle einzeln, als Array,
   *                    gespeichert sind.
   * @param aktuellePos Die aktuelle Position im Array das alle F�lle beinhaltet.
   * @return die Liste der F�lle.
   */
  public static boolean[][] faelleBerechnen(int anzAtom, boolean[][] faelle, int aktuellePos) {
    int anzFall = (int) Math.pow(2, anzAtom);
    for (int i = aktuellePos; i < aktuellePos + anzFall / 2; i++) {
      faelle[i][(faelle[i].length - (anzAtom))] = false;
    }
    if (anzAtom > 1) {
      faelleBerechnen(anzAtom - 1, faelle, aktuellePos);
    }
    for (int i = aktuellePos + anzFall / 2; i < aktuellePos + anzFall; i++) {
      faelle[i][(faelle[i].length - (anzAtom))] = true;
    }
    if (anzAtom > 1) {
      faelleBerechnen(anzAtom - 1, faelle, aktuellePos + anzFall / 2);
    }
    return faelle;
  }

  /**
   * Pr�ft zwei Formel auf �quivalenz.
   * 
   * @param formel1 erste Formel
   * @param formel2 zweite Formel
   * @return ob die Formeln gleich sind.
   */
  public static boolean vergleicheFormel(Formel formel1, Formel formel2, int anzAtom) {
    boolean gleich = true;
    boolean[][] faelle = new boolean[(int) (Math.pow(2, anzAtom))][anzAtom];
    faelle = faelleBerechnen(anzAtom, faelle, 0);
    for (int i = 0; i < faelle.length; i++) {
      try {
        if (formel1.auswerten(faelle[i]) != formel2.auswerten(faelle[i])) {
          gleich = false;
          break;
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        gleich = false;
        break;
      }
    }
    return gleich;
  }
}
