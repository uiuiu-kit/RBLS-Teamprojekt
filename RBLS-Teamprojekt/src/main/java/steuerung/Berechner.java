package steuerung;

public class Berechner {
  static boolean[][] faelleBerechnen(int anzAtom, boolean[][] faelle, int aktuellePos) {
    for (int i = 0; i < (2 ^ (anzAtom - 1)); i++) {
      faelle[i][(anzAtom - 1)] = true;
    }
    if (anzAtom > 1) {
      faelleBerechnen(anzAtom - 1, faelle, aktuellePos);
    }
    for (int i = 0; i < (2 ^ (anzAtom - 1)); i++) {
      faelle[i][(anzAtom - 1)] = false;
    }
    if (anzAtom > 1) {
      faelleBerechnen(anzAtom - 1, faelle, aktuellePos);
    }
    return faelle;
  }
}
