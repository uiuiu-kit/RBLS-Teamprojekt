package modell.tabelle;

import java.util.ArrayList;
import java.util.List;
import modell.formel.Atom;
import modell.formel.Formel;

/**
 * Diese Klasse enthaelt alle Zellenobjekte und haelt diese in einer
 * 2-dimensionalen Liste. Die Zellen werden ueber die Tabelle angesprochen,
 * verwaltet und abgefragt. Die Zeilen- und Spaltenanzahl ist abhaengig vom
 * Raetsel. Sie enthaelt au�erdem Zellenobjekte vom speziellen Typ
 * Wahrheitswert- oder Formelzelle.
 * 
 * @author janne
 *
 */
public class Tabelle {

  private List<List<Wahrheitswertzelle>> wwTabelle;
  private List<Formelzelle> formelTabelle;
  private int atomAnz;

  /**
   * Konstruktor fuer die Tabelle eines Raetels. Konstruiert anhand der
   * uebergebenen Parameter.
   * 
   * @param atom Anzahl der Atomaren Aussagen.
   */
  public Tabelle(List<Atom> atom) {
    this.atomAnz = atom.size();
    formelTabelle = new ArrayList<Formelzelle>();
    wwTabelle = new ArrayList<List<Wahrheitswertzelle>>();
    for (int i = 1; i <= atom.size(); i++) {
      this.spalteHinzufuegen();
    }
    for (int i = 0; i < atom.size(); i++) {
      this.formelTabelle.get(i).setzeZelle(atom.get(i));
    }
  }

  public void setzeZelle(int[] z, boolean ww) {
    gibWaZe(z).setzeZelle(ww);
  }

  public void setzeFormel(Formel f, int spalte) {
    this.formelTabelle.get(spalte).setzeZelle(f);
  }

  /**
   * Gibt der Fassade den WW der angepingten Zelle zurueck.
   * 
   * @param i Zellenposition
   * @return WW
   */
  public boolean gibZellenWert(int[] i) {
    if (i[0] > 0) {
      return this.gibWaZe(i).gibZustand();
    }
    return false;
  }

  /**
   * Gibt die String-Repraesentation von Wahrheitswertzelle oder Formelzelle
   * wieder.
   * 
   * @param i Array der Laenge 2, welches die Zelle definiert.
   * @return String-Repraesentation der ausgewaehlten Zelle.
   */
  public String gibZelle(int[] i) {
    if (i[0] == 0) {
      return this.gibFormelText(i[1]);
    }
    return gibWaZe(i).toString();
  }

  /**
   * Sucht das Zellenobjekt im 2-dimensionalen Listenfeld.
   * 
   * @param i Array der Groe�e 2, gibt Zeile und Spalte der Zellenposition an.
   * @return Zellenobjekt.
   */
  private Wahrheitswertzelle gibWaZe(int[] i) {
    if (i[0] > 0) {
      return this.wwTabelle.get(i[1]).get(i[0] - 1);
    }
    return null;
  }

  /**
   * Liefert der Fassade die gewaehlte aussagenlogische Formel der angegebenen
   * Zelle.
   * 
   * @param i Position der Zelle
   * @return Formel der angegeben Zelle.
   */
  public Formel gibAussagenlogischeFormel(int[] i) {
    if (i[0] == 0) {
      return this.formelTabelle.get(i[1]).gibZustand();
    }
    return null;
  }

  public int gibSpaltenAnz() {
    return this.formelTabelle.size();
  }

  public int gibZeilenAnz() {
    return this.wwTabelle.get(0).size();
  }

  /**
   * Erlaubt es der Fassade eine neue Spalte mit einer neuen Formel zur Tabelle
   * hinzuzuf�gen.
   * 
   */
  public void spalteHinzufuegen() {
    List<Wahrheitswertzelle> row = new ArrayList<Wahrheitswertzelle>();
    for (int h = 0; h <= (int) Math.pow(2, atomAnz); h++) {
      row.add(new Wahrheitswertzelle());
    }
    this.wwTabelle.add(row);
    Formelzelle temp = new Formelzelle();
    temp.setzeZelle(new Atom("leer", -1));
    this.formelTabelle.add(temp);
  }

  /**
   * Erlaubt das gezielte Loeschen von Spalten.
   * 
   * @param spalte zu loeschende Spalte.
   */
  public void spalteEntfernen(int spalte) {
    if ((this.gibSpaltenAnz()) > this.atomAnz) {
      this.wwTabelle.remove(spalte);
      this.formelTabelle.remove(spalte);
    }
  }

  /**
   * Gibt die Stringrepraesentation der genannten Zelle an.
   * 
   * @param z Zelle
   * @return String
   */
  public String gibAtomareAussage(int[] z) {
    if (z[0] > 0) {
      return this.gibWaZe(z).toString();
    }
    return null;
  }

  /**
   * Ermittelt die Wahrheitswertbelegung einer kompletten Zeile.
   * 
   * @param i gewuenschte Zeilennummer
   * @return Booleanarray mit Wahrheitswerten.
   */
  public boolean[] gibZeileFall(int i) {
    boolean[] out = new boolean[this.atomAnz];
    if (i != 0) {
      for (int h = 0; h < this.atomAnz; h++) {
        int[] pos = { i, h };
        out[h] = this.gibWaZe(pos).gibZustand();
      }
    }
    return out;
  }

  /**
   * Gibt der Fassade die Formel der entsprechenden Zelle zurueck.
   * 
   * @param s Spalte der Formelzelle.
   * @return Formel der Zelle
   */
  public Formel gibFormel(int s) {
    return this.formelTabelle.get(s).gibZustand();
  }

  /**
   * Gibt die Formel als Text wieder.
   * 
   * @param s Spalte, in der die Formel zu finden ist.
   * @return String der Formel.
   */
  public String gibFormelText(int s) {
    int[] temp = { 0, s };
    if (s < atomAnz) {
      Atom a = (Atom) this.gibAussagenlogischeFormel(temp);
      return a.getAussage();
    }
    return this.gibAussagenlogischeFormel(temp).gibStringRep();
  }

  /**
   * Gibt die Formel als Text wieder.
   * 
   * @param s Spalte, in der die Formel zu finden ist.
   * @return String der Formel.
   */
  public String gibFormelParsabel(int s) {
    int[] temp = { 0, s };
    if (s < atomAnz) {
      return null;
    }
    return this.gibAussagenlogischeFormel(temp).gibParsable();
  }

}
