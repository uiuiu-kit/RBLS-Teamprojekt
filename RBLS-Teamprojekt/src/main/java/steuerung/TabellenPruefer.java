package steuerung;

import java.util.ArrayList;
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
    int anzAtom = model.gibAtomareAussage().size();
    boolean[][] faelle = new boolean[(int) Math.pow(2, anzAtom)][anzAtom];
    faelle = Berechner.faelleBerechnen(anzAtom, faelle, 0);
    noetigeFaelle = new ArrayList<boolean[]>();
    for (int i = 0; i < faelle.length; i++) {
      noetigeFaelle.add(faelle[i]);
    }
    nochNoetigeFaelle = noetigeFaelle;
    vollstaendig = false;
    fehlerhafteWW = new ArrayList<int[]>();
    fehlerhafteFaelle = new ArrayList<Integer>();
    for (int i = 1; i < (int) Math.pow(2, anzAtom) + 1; i++) {
      fehlerhafteFaelle.add(i);
    }
  }

  /**
   * Testet ob ein Fall noch n�tig war. Wenn nicht wird getestet ob der Fall der
   * vorher in der Zeile stand n�tig war. Je nachdem wir die Liste der noch
   * n�tigen F�lle aktualisiert. Je nachdem ob der Fall nun fehlerhaft ist oder
   * nicht wird auch die Liste der fehlerhaften F�lle aktuallisiert.
   * 
   * @param koordinate die Koordinate die ge�ndert wurde
   */
  public void ueberpuefeFaelle(int zeile, int spalte) {
    boolean[] akFall = model.gibZeileFall(zeile);
    if (durchsucheFallListe(akFall) != -1) {
      nochNoetigeFaelle.remove(durchsucheFallListe(akFall));
      fehlerhafteFaelle.remove(zeile);
    } else {
      akFall[zeile] = !(akFall[zeile]);
      if (durchsucheFallListe(akFall) != -1) {
        fehlerhafteFaelle.add(zeile);
        nochNoetigeFaelle.add(akFall);
      }
    }
  }

  private int durchsucheFallListe(boolean[] akFall) {
    boolean gleich = false;
    for (int i = 0; i < nochNoetigeFaelle.size(); i++) {
      boolean[] listFall = nochNoetigeFaelle.get(i);
      for (int j = 0; j < listFall.length; j++) {
        if (listFall[j] == akFall[i]) {
          gleich = true;
        }
      }
      if (gleich) {
        return i;
      }
    }
    return -1;
  }

  /**
   * �berpr�ft ob alle n�tigen Formeln vorhanden sind.
   * 
   * @return sind alle n�tigen Formel vorhanden.
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
   * @param zeile  die zeile die überprüft wird.
   * @param spalte die spalte die überprüft wird.
   */
  public void ueberpuefeWW(int zeile, int spalte) {
    int i = enthaltenInFehlerhaft(zeile, spalte);
    if (i != 1) {
      fehlerhafteWW.remove(i);
    } else {
      Formel akFormel = model.gibFormel(spalte);
      boolean[] akFall = model.gibZeileFall(zeile);
      if (!akFormel.auswerten(akFall)) {
        fehlerhafteWW.add(new int[] { zeile, spalte });
      }
    }
  }

  private int enthaltenInFehlerhaft(int zeile, int spalte) {
    for (int i = 0; i < fehlerhafteWW.size(); i++) {
      if (fehlerhafteWW.get(i)[0] == zeile && fehlerhafteWW.get(i)[1] == spalte) {
        return i;
      }
    }
    return -1;
  }

  /**
   * gib die Koordinaten einer fehlerhaften Zelle zur�ck. Abh�nig von der Stufe
   * wird entweder eine Koordinaten in den F�llen (1), eine Koordinate in den
   * Wahrheitswerden(3) oder null (2,4) zur�ck gegegben.
   * 
   * @return die Koordinaten einer Fehlerhaften Zelle
   */
  public int[] gibFehlerhafteZelle() {
    int pos;
    if (stufe == 1) {
      pos = ThreadLocalRandom.current().nextInt(0, fehlerhafteFaelle.size());
      int[] koordinaten = { fehlerhafteFaelle.get(pos), 0 };
      return koordinaten;
    }
    if (stufe == 3 && fehlerhafteWW.size() != 0) {
      pos = ThreadLocalRandom.current().nextInt(0, fehlerhafteWW.size());
      return fehlerhafteWW.get(pos);
    }
    return null;
  }

  /**
   * gibt aus ob fuelleTabelle erlaubt ist.
   * 
   * @return ist fuelleTabelle erlaubt
   */
  public boolean tabelleFuellenErlaubt() {
    if (stufe == 1) {
      return fehlerhafteFaelle.isEmpty();
    }
    if (stufe == 2 || stufe == 4) {
      return ueberpuefeFormeln();
    }
    return false;
  }

}