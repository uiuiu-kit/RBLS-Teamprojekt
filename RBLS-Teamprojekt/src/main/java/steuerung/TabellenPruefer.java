package steuerung;

import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.text.DefaultEditorKit.BeepAction;

import modell.SteuerungFassade;
import modell.formel.Formel;

public class TabellenPruefer {
  private SteuerungFassade model;
  boolean vollstaendig;
  List<int[]> fehlerhafteWW;
  List<Integer> fehlerhafteFaelle;
  List<boolean[]> noetigeFaelle;
  List<boolean[]> nochNoetigeFaelle;

  /**
   * Konstruktor des TabellenPruefers der die Startvoraussetzunge und die globalen
   * Variablen initialisiert und.
   * 
   * @param model die Fassade hinter der die Tabelle ist die getestet werden soll.
   */
  public TabellenPruefer(SteuerungFassade model) {
    this.model = model;
    int anzAtom = model.gibAtomareAussage().size() + 1;
    boolean[][] faelle = new boolean[(int) Math.pow(2, anzAtom)][2];
    faelle = Berechner.faelleBerechnen(anzAtom, faelle, 0);
    noetigeFaelle = Arrays.asList(faelle);
    nochNoetigeFaelle = noetigeFaelle;
    vollstaendig = false;
    fehlerhafteWW = new ArrayList<int[]>();
  }

  /**
   * Testet ob ein Fall noch nötig war. Wenn nicht wird getestet ob der Fall der
   * vorher in der Zeile stand nötig war. Je nachdem wir die Liste der noch
   * nötigen Fälle aktualisiert. Je nachdem ob der Fall nun fehlerhaft ist oder
   * nicht wird auch die Liste der fehlerhaften Fälle aktuallisiert.
   * 
   * @param koordinaten die Koordinate die geändert wurde
   * @return die Liste an fehlerhaften Fällen
   */
  public List<Integer> ueberpuefeFaelle(int[] koordinaten) {
    boolean[] akFall = model.gibZeileFall(koordinaten[1]);
    if (nochNoetigeFaelle.contains(akFall)) {
      nochNoetigeFaelle.remove(akFall);
      fehlerhafteFaelle.remove(koordinaten[1]);
    } else {
      akFall[koordinaten[1]] = !akFall[koordinaten[1]];
      if (noetigeFaelle.contains(akFall)) {
        fehlerhafteFaelle.add(koordinaten[1]);
        nochNoetigeFaelle.add(akFall);
      }
    }
    return fehlerhafteFaelle;
  }

  /**
   * überprüft ob alle nötigen Formeln vorhanden sind.
   * 
   * @return sind alle nötigen Formel vorhanden.
   */
  public boolean ueberpuefeFormeln() {
    boolean vollstaendig = true;
    boolean enthaelt;
    int j;
    int anzAtom = model.gibAtomareAussage().size() + 1;
    int anzSpalten = model.gibSpaltenAnz();
    List<String> noetigeFormelnS = model.gibNoetigeFormel();
    List<Formel> noetigeFormelnF = new ArrayList<Formel>();
    for (int i = 0; i < noetigeFormelnS.size(); i++) {
      noetigeFormelnF.add(FormelParser.pars(noetigeFormelnS.get(i), model));
    }
    for (int i = 0; i < noetigeFormelnF.size(); i++) {
      enthaelt = false;
      j = anzAtom;
      while (!enthaelt && j < anzSpalten) {
        Formel vergleichsformel = model.gibFormel(j);
        enthaelt = Berechner.vergleicheFormel(noetigeFormelnF.get(i), vergleichsformel, anzAtom);
        j++;
      }
      if (!enthaelt) {
        return false;
      }
    }
    return vollstaendig;

  }

  /**
   * gibt eine Liste von fehlerhaften Koordinaten aus.
   * 
   * @param koordinate die Koordinate die geändert wurde
   * @return die Liste an fehlerhaften Koordinaten
   */
  public List<int[]> ueberpuefeWW(int[] koordinate) {
    if (fehlerhafteWW.contains(koordinate)) {
      fehlerhafteWW.remove(koordinate);
    } else {
      Formel akFormel = model.gibFormel(koordinate[1]);
      boolean[] akFall = model.gibZeileFall(koordinate[0]);
      if (!akFormel.auswerten(akFall)) {
        fehlerhafteWW.add(koordinate);
      }
    }
    return fehlerhafteWW;
  }
}