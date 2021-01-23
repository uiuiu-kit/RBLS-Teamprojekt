package modell;

import java.util.List;

public class Tabelle {

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
  
  public void setzeZelle(int[] zelle, boolean ww) {
    Zelle.setzeZelle();
  }
  
  public boolean gibZellenWert(int[] zelle) {
    
    return Zelle.gibZustand();
  }
  
  public int gibSpaltenAnz() {
  
}
