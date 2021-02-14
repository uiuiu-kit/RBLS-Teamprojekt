package steuerung;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import modell.Fassade;
import modell.formel.Formel;

public class TabellenPruefer {
  private Fassade model;
  boolean vollstaendig;
  private List<int[]> fehlerhafteWW;
  private List<Integer> fehlerhafteFaelle;
  private List<boolean[]> noetigeFaelle;
  private List<boolean[]> nochNoetigeFaelle;
  private int stufe;

  /**
   * Konstruktor des TabellenPruefers der die Startvoraussetzunge und die globalen
   * Variablen initialisiert und.
   * 
   * @param model die Fassade hinter der die Tabelle ist die getestet werden soll.
   */
  public TabellenPruefer(Fassade model, int stufe) {
    this.model = model;
    this.stufe = stufe;
    int anzAtom = model.gibAtomareAussage().size() + 1;
    boolean[][] faelle = new boolean[(int) Math.pow(2, anzAtom)][anzAtom];
    faelle = Berechner.faelleBerechnen(anzAtom, faelle, 0);
    noetigeFaelle = Arrays.asList(faelle);
    nochNoetigeFaelle = noetigeFaelle;
    vollstaendig = false;
    fehlerhafteWW = new ArrayList<int[]>();
    fehlerhafteFaelle = new ArrayList<Integer>();
    for (int i = 0; i < (int) Math.pow(2, anzAtom); i++) {
      fehlerhafteFaelle.add(i);
    }
  }

  /**
   * Testet ob ein Fall noch nötig war. Wenn nicht wird getestet ob der Fall der
   * vorher in der Zeile stand nötig war. Je nachdem wir die Liste der noch
   * nötigen Fälle aktualisiert. Je nachdem ob der Fall nun fehlerhaft ist oder
   * nicht wird auch die Liste der fehlerhaften Fälle aktuallisiert.
   * 
   * @param koordinate die Koordinate die geändert wurde
   * @return die Liste an fehlerhaften Fällen
   */
  public List<Integer> ueberpuefeFaelle(int[] koordinate) {
    boolean[] akFall = model.gibZeileFall(koordinate[0]);
    if (nochNoetigeFaelle.contains(akFall)) {
      nochNoetigeFaelle.remove(akFall);
      fehlerhafteFaelle.remove(koordinate[0]);
    } else {
      akFall[koordinate[0]] = !akFall[koordinate[0]];
      if (noetigeFaelle.contains(akFall)) {
        fehlerhafteFaelle.add(koordinate[0]);
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

  /**
   * gib die Koordinaten einer fehlerhaften Zelle zurück. Abhänig von der Stufe
   * wird entweder eine Koordinaten in den Fällen (1), eine Koordinate in den
   * Wahrheitswerden(3) oder null (2,4) zurück gegegben.
   * 
   * @return die Koordinaten einer Fehlerhaften Zelle
   */
  public int[] gibFehlerhafteZelle() {
    int pos;
    if (stufe == 1) {
      pos = ThreadLocalRandom.current().nextInt(0, fehlerhafteFaelle.size());
      int[] koordinaten = { 1, fehlerhafteFaelle.get(pos) };
      return koordinaten;
    }
    if (stufe == 3) {
      pos = ThreadLocalRandom.current().nextInt(0, fehlerhafteWW.size());
      return fehlerhafteWW.get(pos);
    }
    return null;
  }
}