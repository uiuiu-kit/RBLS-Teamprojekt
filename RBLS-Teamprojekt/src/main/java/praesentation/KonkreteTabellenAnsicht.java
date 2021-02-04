package praesentation;

import javax.swing.JTable;
import modell.PraesentationFassade;
import steuerung.WahrheitstabellenSteuerungen;

/**
 * Grafische Ansicht einer Wahrheitstabelle. ‹ber das
 * Befehlsmuster werden der Wahrheitstabellensteuerung Aktionen mitgeteilt.
 * Auﬂerdem wird die Ansicht einer Zelle mit den Informationen der
 * Modell-Fassade aktuell gehalten.
 * @author Nick
 */
public class KonkreteTabellenAnsicht extends TabellenAnsicht {

  private WahrheitstabellenSteuerungen strg;
  private JTable tabelle;
  private Schaltflaeche ausfuellen = new Schaltflaeche(2);
  private Schaltflaeche mehrSpalten = new Schaltflaeche(2);
  private Schaltflaeche wenigerSpalten = new Schaltflaeche(2);
  private Schaltflaeche zeileMarkieren = new Schaltflaeche(2);
  private boolean[][] wahrheitswerte;
  private String[][] inhalt;
  private int zeilenzahl;
  private int spaltenzahl;

  public KonkreteTabellenAnsicht(PraesentationFassade modell) {
    
  }
  
  private void init() {
    
  }
  
  private void fuelleAus() {
    
  }
  
  private void klickeZelle(int[] zelle) {
    
  }
  
  private void klickeFormel(int zeile) {
    
  }
  
  private void fuegeSpalteHinzu() {
    
  }
  
  private void entferneSpalte() {
    
  }
  
  public void zeigeTippAn() {
    
  }
  
  public void markiereZeile(int zeile) {
    
  }
  
  public void aktualisiere(int[] zelle) {
    
  }

}