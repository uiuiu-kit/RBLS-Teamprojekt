package modell.tabelle;

import java.util.ArrayList;
import java.util.List;

import modell.formel.Atom;
import modell.formel.Formel;

/**
 * Diese Klasse enthält alle Zellenobjekte und hält diese in einer 2dimensionalen Liste. 
 * Die Zellen werden über die Tabelle angesprochen, verwaltet und abgefragt.
 * Die Zeilen- und Spaltenanzahl ist abhaengig vom Raetsel. Sie enthaelt außerdem Zellenobjekte vom
 * speziellen Typ Wahrheitswert- oder Formelzelle.
 * @author janne
 *
 */
public class Tabelle {
  
  private List<List<Wahrheitswertzelle>> wwTabelle;
  private List<Formelzelle> formelTabelle;
  private int atomAnz;
  private static final String UNDEFINED_F = "formel hinzufügen";
  
  /**
   * Konstruktor für die Tabelle eines Raetels. Konstruiert anhand der uebergebenen Parameter.
   * @param atom Anzahl der Atomaren Aussagen.
   */
  public Tabelle(List<Atom> atom) {
    this.atomAnz = atom.size();
    formelTabelle = new ArrayList<Formelzelle>();
    wwTabelle = new ArrayList<List<Wahrheitswertzelle>>();
    for (int i = 0; i < atom.size(); i++) {
      this.spalteHinzufuegen();
    }
    for (int i = 0; i < atom.size(); i++) {
      this.formelTabelle.get(i).setzeZelle(atom.get(i));
    }
  }
  
  public void setzeZelle(int[] z, boolean ww) {
    gibWZ(z).setzeZelle(ww);
  }
  
  public void setzeFormel(Formel f, int spalte) {
    this.formelTabelle.get(spalte).setzeZelle(f);
  }
  
  /** Gibt der Fassade den WW der angepingten Zelle zurück.
   * @param i Zellenposition 
   * @return WW
   */
  public boolean gibZellenWert(int[] i) {
    if (i[0] > 0) {
      return this.gibWZ(i).gibZustand();
    }
    return false;
  }
  
  /**
   * Gibt die String-Repraesentation von Wahrheitswertzelle oder Formelzelle wieder.
   * @param i Array der Laenge 2, welches die Zelle definiert.
   * @return String-Repraesentation der ausgewaehlten Zelle.
   */
  public String gibZelle(int[] i) {
    if (i[0] == 0) {
      return this.gibFormelText(i[1]);
    }
    return gibWZ(i).toString();
  }
  
  /** Sucht das Zellenobjekt im 2dimensionalen Listenfeld.
   * @param i Array der größe 2, gibt Zeile und Spalte der Zellenposition an.
   * @return Zellenobjekt.
   */
  private Wahrheitswertzelle gibWZ(int[] i) {
    if (i[0] != 0) {
      return this.wwTabelle.get(i[0] - 1).get(i[1]);
    }
    return null;
  }
  
  /** Liefert der Fassade die gewählte Aussagenlogische Formel der angegebenen Zelle.
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
  
  /** Erlaubt es der Fassade eine neue Spalte mit einer neuen Formel zur Tabelle hinzuzufügen.
   * 
   */
  public void spalteHinzufuegen() {
    List<Wahrheitswertzelle> row = new ArrayList<Wahrheitswertzelle>();
    for (int h = 1; h < (int) Math.pow(2, atomAnz) + 2; h++) {
      Wahrheitswertzelle temp = new Wahrheitswertzelle();
      temp.setzeZelle(new Atom(UNDEFINED_F, 5));
      row.add(temp);
    }
    this.wwTabelle.add(row);
    this.formelTabelle.add(new Formelzelle());
  }
  
  /** Erlaubt das gezielte Löschen von Spalten.
   * @param spalte zu löschende Spalte.
   */
  public void spalteEntfernen(int spalte) {
    if ((this.gibSpaltenAnz()) > this.atomAnz) {
      this.wwTabelle.remove(spalte);
      this.formelTabelle.remove(spalte);
    } 
  }
  
  /** Gibt die Stringrepräsentation der genannten Zelle an.
   * @param z Zelle
   * @return String
   */
  public String gibAtomareAussage(int[] z) {
    if (z[0] > 0) {
      return this.gibWZ(z).toString();
    }
    return null;
  }
  
  /** Ermittelt die Wahrheitswertbelegung einer kompletten Zeile.
   * @param i gewünschte Zeilennummer
   * @return Booleanarray mit Wahrheitswerten.
   */
  public boolean[] gibZeileFall(int i) {
    boolean[] out = new boolean[this.atomAnz];
    if (i != 0) {
      for (int h = 0; h < this.atomAnz; h++) {
        int[] pos = {i, h};
        out[h] = this.gibWZ(pos).gibZustand();
      } 
    }
    return out;
  }
  
  
  /** Gibt der Fassade die Formel der entsprechenden Zelle zurück.
   * @param s Spalte der Formelzelle.
   * @return Formel der Zelle
   */
  public Formel gibFormel(int s) {
    return this.formelTabelle.get(s).gibZustand();
  }
  
  
  
  /** Gibt die Formel als Text wieder.
   * @param s Spalte, in der die Formel zu finden ist.
   * @return String der Formel.
   */
  public String gibFormelText(int s) {
    int[] temp = {0, s};
    return this.gibAussagenlogischeFormel(temp).gibStringRep();
  }

}
