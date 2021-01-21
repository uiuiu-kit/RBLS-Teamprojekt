package steuerung;

public class Berechner {
  /**
   * berechnet alle möglichen Fälle die möglich sind.
   * 
   * @param anzAtom     Anzahl der Atome die wahr oder falsch sein können
   * @param faelle      Das Array in dem alle Fälle einzeln als Array gespeichert
   *                    sind
   * @param aktuellePos Die aktuelle Position im Array.
   * @return die Liste an Fällen.
   */
  public static boolean[][] faelleBerechnen(int anzAtom, boolean[][] faelle, int aktuellePos) {
    int anzFall = (int) Math.pow(2, anzAtom);
    for (int i = aktuellePos; i < aktuellePos + anzFall / 2; i++) {
      faelle[i][(anzAtom - 1)] = true;
    }
    if (anzAtom > 1) {
      faelleBerechnen(anzAtom - 1, faelle, aktuellePos);
    }
    for (int i = aktuellePos + anzFall / 2; i < aktuellePos + anzFall; i++) {
      faelle[i][(anzAtom - 1)] = false;
    }
    if (anzAtom > 1) {
      faelleBerechnen(anzAtom - 1, faelle, aktuellePos + anzFall / 2);
    }
    return faelle;
  }

  /**
   * Prüft zwei Formel aus Äquivalenz.
   * 
   * @param formel1 erste Formel
   * @param formel2 zweite Formel
   * @return ob die Formeln gleich sind.
   */
  public static boolean vergleicheFormel(modell.Formel formel1, modell.Formel formel2, int anzAtom) {
    boolean equal = true;
    boolean[][] faelle = new boolean[(int) (Math.pow(2, anzAtom))][anzAtom];
    faelle = faelleBerechnen(anzAtom, faelle, 0);
    for (int i = 0; i < faelle.length; i++) {
      if (formel1.auswerten(faelle[i]) != formel2.auswerten(faelle[i])) {
        equal = false;
      }
    }
    return equal;
  }
}
