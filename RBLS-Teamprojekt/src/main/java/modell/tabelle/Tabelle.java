package modell.tabelle;

import java.util.List;

import modell.formel.Formel;

/**
 * Diese Klasse symbolisiert ein Tabellenobjekt.
 * Die Zeilen- und Spaltenanzahl ist abhaengig vom Raetsel. Sie enthaelt außerdem Zellenobjekte vom
 * speziellen Typ Wahrheitswert- oder Formelzelle.
 * @author janne
 *
 */
public class Tabelle {
  
  private Zelle zelle;
  private List<List<Zelle>> tabelle;
  private int zeile;
  private int spalte;
  private int atomAnz;
  
  /**
   * Konstruktor für die Tabelle eines Raetels. Konstruiert anhand der uebergebenen Parameter.
   * @param zeilenAnz Entspricht der Zeilenanzahl, die fuer das Raetsel benoetigt werden.
   * @param spaltenAnz Entspricht der benoetigten Spaltenanzahl des Raetsels.
   * @param atomAnz
   */
  public Tabelle(int zeilenAnz, int spaltenAnz, int atomAnz) {
    this.zeile = zeilenAnz;
    this.spalte = spaltenAnz;
    this.atomAnz = atomAnz;
  }
  
  public void setzeZelle(int[] z, boolean ww) {
    //TODO
    zelle.setzeZelle(ww);
  }
  
  public void setzeFormel(Formel f, int spalte) {
    //TODO
    zelle.setzeZelle(f);
  }
  
  public boolean gibZellenWert(int[] z) {
    //TODO
    return false;
  }
  
  /**
   * Gibt die String-Repraesentation von Wahrheitswertzelle oder Formelzelle wieder.
   * @param i Array der Laenge 2, welches die Zelle definiert.
   * @return String-Repraesentation der ausgewaehlten Zelle.
   */
  public String gibZelle(int[] i) {
    //TODO
    return null;
  }
  
  public Formel gibAussagenlogischeFormel(int[] z) {
    //TODO
    return null;
  }
  
  public int gibSpaltenAnz() {
    return this.spalte;
  }
  
  public int gibZeilenAnz() {
    return this.zeile;
  }
  
  public void spalteHinzufuegen() {
    //TODO
    //if (this.spalte < spalteMax)
    this.spalte += 1;
  }
  
  public void spalteEntfernen(int spalte) {
    /* if ((this.spalte - spalte) < 1) {
     * return;
     * } else {
     * this.spalte - spalte;
     * } 
     */
    //TODO
  }
  
  public String gibAtomareAussage(int[] z) {
    //TODO
    return null;
  }
  
  public boolean gibZeileFall(int z) {
    //TODO
    return false;
  }
  
  public Formel gibFormel(int s) {
    //TODO
    return null;
  }
  
  //oder soll es auch die WWZelle wiedergeben koennen?
  public String gibFormelText(int s) {
    //TODO
    //Formelzelle.toString()
    return null;
  }

}
