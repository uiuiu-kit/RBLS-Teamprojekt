package modell.tabelle;

import java.util.List;

import modell.formel.Formel;

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
    zelle.setzeZelle(ww);
  }
  
  public boolean gibZellenWert(int[] z) {
    //TODO
    return false;
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
  }
  
  public void spalteEntfernen(int spalte) {
    /* if ((this.spalte - spalte) < 1) {
     * return;
     * } else {
     * this.spalte - spalte;
     * } 
     */
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

}
