package praesentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
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
  private PraesentationFassade modell;
  private JTable tabelle;
  private Schaltflaeche ausfuellen = new Schaltflaeche(2);
  private Schaltflaeche mehrSpalten = new Schaltflaeche(2);
  private Schaltflaeche wenigerSpalten = new Schaltflaeche(2);
  private Schaltflaeche zeileMarkieren = new Schaltflaeche(2);
  private boolean[][] wahrheitswerte;
  private String[][] inhalt;
  private int zeilenzahl = 9;
  private int spaltenzahl = 5;
  
  private int stufe;
  private JPanel panel;

  public KonkreteTabellenAnsicht(PraesentationFassade modell) {
    this.modell = modell;
    init();
  }
  
  private void init() {
    this.strg = new WahrheitstabellenSteuerungen(null);  //TODO Wie?
    
    //TODO unkommentieren wenn fertig
    //zeilenzahl = modell.gibZeilenAnz();
    //spaltenzahl = modell.gibSpaltenAnz();
    
    wahrheitswerte = new boolean[zeilenzahl - 1][spaltenzahl];
    for (int i = 0; i < wahrheitswerte.length; i++) {
      for (int j = 0; j < wahrheitswerte[0].length; j++) {
        //wahrheitswerte[i][j] = modell.gibZellenWert(new int[] {i,j});
        //TODO Platzhalter
        wahrheitswerte[i][j] = true;
      }
    }
    inhalt = new String[zeilenzahl][spaltenzahl];
    for (int i = 0; i < inhalt.length; i++) {
      for (int j = 0; j < inhalt[0].length; j++) {
        //inhalt[i][j] = modell.gibZelle(new int[] {i,j});
        //TODO Platzhalter
        if (i > 0 && wahrheitswerte[i - 1][j]) {
          inhalt[i][j] = "true";
        } else if (i > 0 && wahrheitswerte[i - 1][j]) {
          inhalt[i][j] = "false";
        } else {
          inhalt[i][j] = "" + j + "" + i;
        }
      }
    }
    
    //JTable//
    tabelle = new JTable(inhalt, inhalt[0]);
    DefaultTableModel tm = new DefaultTableModel(inhalt, inhalt[0]) {
        public boolean isCellEditable(int row, int column) {
          tabelle.setFocusable(false);
          tabelle.setRowSelectionAllowed(false);
          return false;
        }
      };
    tabelle.setModel(tm);
    tabelle.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        int i = tabelle.rowAtPoint(evt.getPoint());
        int j = tabelle.columnAtPoint(evt.getPoint());
        klickeZelle(i, j);
        if (i > 0 && j >= 0) {
          wahrheitswerte[i - 1][j] = !wahrheitswerte[i - 1][j];
          if (wahrheitswerte[i - 1][j]) {
              inhalt[i][j] = "true";
              tabelle.getModel().setValueAt("true", i, j);
              //((DefaultTableCellRenderer) tabelle.getCellRenderer(i, j)).setBackground(Color.GREEN);
            } else {
              inhalt[i][j] = "false";
              tabelle.getModel().setValueAt("false", i, j);
              //((DefaultTableCellRenderer) tabelle.getCellRenderer(i, j)).setBackground(Color.RED);
            }
        }
        ((AbstractTableModel) tabelle.getModel()).fireTableCellUpdated(i, j);
      }
    });
    
    tabelle.setRowHeight((int) (tabelle.getRowHeight() * 1.5));
    DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabelle.getDefaultRenderer(getClass());
    renderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

    
    //Panel//
    panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(tabelle, BorderLayout.CENTER);
    tabelle.setFillsViewportHeight(true);
    
    
    
  }
  
  private void fuelleAus() {
    
  }
  
  private void klickeZelle(int reihe, int spalte) {
    strg.befehl("zelleAendern," + reihe + "," + spalte);
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
    assert zelle.length == 2;
    inhalt[zelle[0]][zelle[1]] = modell.gibZelle(zelle);
    tabelle.getModel().setValueAt(inhalt[zelle[0]][zelle[1]], zelle[0], zelle[1]);
  }
  
  public JPanel gibAnsicht() {
    return panel;
  }
  
}