package steuerung;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import modell.Fassade;
import modell.formel.Formel;

public class TabellenPruefer {
  private Fassade model;
  boolean vollstaendig;
  private List<int[]> fehlerhafteWaWe;
  private List<Integer> fehlerhafteFaelle;
  private int anzAtom;
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
    anzAtom = model.gibAtomareAussage().size();
    vollstaendig = false;
  }

  /**
   * Testet ob ein Fall noch n�tig war. Wenn nicht wird getestet ob der Fall der
   * vorher in der Zeile stand n�tig war. Je nachdem wir die Liste der noch
   * n�tigen F�lle aktualisiert. Je nachdem ob der Fall nun fehlerhaft ist oder
   * nicht wird auch die Liste der fehlerhaften F�lle aktuallisiert.
   * 
   */
  public boolean ueberpuefeFaelle() {
    boolean[] akFall;
    boolean[] vergleichsFall;
    boolean gleich;
    fehlerhafteFaelle = new ArrayList<Integer>();
    for (int i = 1; i < Math.pow(2, anzAtom) + 1; i++) {
      akFall = model.gibZeileFall(i);
      for (int j = i + 1; j < Math.pow(2, anzAtom) + 1; j++) {
        vergleichsFall = model.gibZeileFall(j);
        gleich = true;
        for (int k = 0; k < vergleichsFall.length; k++) {
          if (!vergleichsFall[k] == akFall[k]) {
            gleich = false;
          }
        }
        if (gleich) {
          fehlerhafteFaelle.add(j);
        }
      }
    }
    return fehlerhafteFaelle.isEmpty();
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
      j = anzAtom - 1;
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
   * Gibt eine Liste von fehlerhaften Koordinaten aus.
   * 
   */
  public void ueberpruefeWaWe() {
    boolean[] akFall;
    Formel akFormen;
    fehlerhafteWaWe = new ArrayList<int[]>();
    for (int zeile = 1; zeile < model.gibZeilenAnz(); zeile++) {
      akFall = model.gibZeileFall(zeile);
      for (int spalte = model.gibAtomareAussage().size(); spalte < model
          .gibSpaltenAnz(); spalte++) {
        akFormen = model.gibFormel(spalte);
        if (akFormen.auswerten(akFall) != model.gibZelleWaWe(new int[] { zeile, spalte })) {
          fehlerhafteWaWe.add(new int[] { zeile, spalte });
        }
      }
    }
  }

  /**
   * Gibt die Koordinaten einer fehlerhaften Zelle zur�ck. Abh�nig von der Stufe
   * wird entweder eine Koordinaten in den F�llen (1), eine Koordinate in den
   * Wahrheitswerden(3) oder null (2,4) zur�ck gegegben.
   * 
   * @return die Koordinaten einer Fehlerhaften Zelle
   */
  public int[] gibFehlerhafteZelle() {
    int pos;
    if (stufe == 1) {
      ueberpuefeFaelle();
      if (fehlerhafteFaelle.isEmpty()) {
        return null;
      }
      pos = ThreadLocalRandom.current().nextInt(0, fehlerhafteFaelle.size());
      int[] koordinaten = { fehlerhafteFaelle.get(pos), 0 };
      return koordinaten;
    }
    if (stufe == 3) {
      ueberpruefeWaWe();
      if (fehlerhafteWaWe.isEmpty()) {
        return null;
      }
      pos = ThreadLocalRandom.current().nextInt(0, fehlerhafteWaWe.size());
      return fehlerhafteWaWe.get(pos);
    }
    return null;
  }

  /**
   * Gibt aus ob fuelleTabelle erlaubt ist.
   * 
   * @return ist fuelleTabelle erlaubt
   */
  public boolean tabelleFuellenErlaubt() {
    if (stufe == 1) {
      return ueberpuefeFaelle();
    }
    if (stufe == 2 || stufe == 4) {
      return ueberpuefeFormeln();
    }
    return false;
  }

}