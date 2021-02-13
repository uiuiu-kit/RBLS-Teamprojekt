package praesentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import modell.PraesentationFassade;
import modell.SteuerungFassade;
import steuerung.FormelEditor;
import steuerung.WahrheitstabellenSteuerungen;

/**
 * Grafische Ansicht einer Wahrheitstabelle. Über das
 * Befehlsmuster werden der Wahrheitstabellensteuerung Aktionen mitgeteilt.
 * Außerdem wird die Ansicht einer Zelle mit den Informationen der
 * Modell-Fassade aktuell gehalten.
 * @author Nick
 */
public class KonkreteTabellenAnsicht extends TabellenAnsicht {

  private WahrheitstabellenSteuerungen strg;
  private PraesentationFassade modell;
  private JTable tabelle;
  private Schaltflaeche ausfuellen = new Schaltflaeche("<html>&nbsp Fülle<br />Tabelle</html>", 5);
  private Schaltflaeche mehrSpalten = new Schaltflaeche("+", 6);
  private Schaltflaeche wenigerSpalten = new Schaltflaeche("-", 6);
  private Schaltflaeche zeileMarkieren = new Schaltflaeche("Markieren", 6);
  private boolean[][] wahrheitswerte;
  private String[][] inhalt;
  private int zeilenzahl = 9;
  private int spaltenzahl = 5;
  private boolean[] markierteZeilen;
  
  private enum Modus { standard,  entfernen, markieren
  }
  
  private enum ZellenStatus { standard, wahr, falsch, markiert, tipp
  }
  
  private Modus modus = Modus.standard;
  private int stufe = 4;
  private JPanel panel;
  private JPanel tabellenRahmen = new JPanel();

  public KonkreteTabellenAnsicht(PraesentationFassade modell) {
    this.modell = modell;
    init();
  }
  
  private void init() {
    //this.strg = new WahrheitstabellenSteuerungen(SteuerungFassade.gibSteuFa());  //TODO Wie?
    
    //TODO unkommentieren wenn fertig
    //zeilenzahl = modell.gibZeilenAnz();
    //spaltenzahl = modell.gibSpaltenAnz();
    //stufe = modell.gibAktuelleStufe();
    markierteZeilen = new boolean[zeilenzahl];
    Arrays.fill(markierteZeilen, false);
    initTabelle();
    
    //SchaltflaechenPanel//
    JPanel schaltflaechenPanel = new JPanel();
    schaltflaechenPanel.setLayout(new BoxLayout(schaltflaechenPanel, BoxLayout.Y_AXIS));
    schaltflaechenPanel.setBackground(Color.WHITE);
    schaltflaechenPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    
    mehrSpalten.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fuegeSpalteHinzu();
      }
    });
    if (stufe == 2 || stufe == 4) { 
      schaltflaechenPanel.add(mehrSpalten);
    }
    schaltflaechenPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    wenigerSpalten.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        wechseleModus(wenigerSpalten, Modus.entfernen);
      }
    });
    if (stufe == 2 || stufe == 4) { 
      schaltflaechenPanel.add(wenigerSpalten);
    }
    schaltflaechenPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    zeileMarkieren.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        wechseleModus(zeileMarkieren, Modus.markieren);
      }
    });
    schaltflaechenPanel.add(zeileMarkieren);
    schaltflaechenPanel.add(Box.createRigidArea(
        new Dimension(0, (int) (mehrSpalten.getMaximumSize().height * 1))));
    ausfuellen.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fuelleAus();
      }
    });
    if (stufe != 3) { 
      schaltflaechenPanel.add(ausfuellen);
    }
    
    //Tabellenrahmen//
    tabellenRahmen.setLayout(new BorderLayout());
    tabellenRahmen.add(tabelle, BorderLayout.CENTER);
    tabellenRahmen.setBackground(Color.GRAY);
    tabellenRahmen.setBorder(BorderFactory.createEmptyBorder(1, 1, 0, 0));
    
    //Panel//
    panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    panel.add(tabellenRahmen, BorderLayout.CENTER);
    panel.add(schaltflaechenPanel, BorderLayout.EAST);
    tabelle.setFillsViewportHeight(true);    
  }
  
  private void initTabelle() {
    //Modelldaten//
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
          inhalt[i][j] = "wahr";
        } else if (i > 0 && wahrheitswerte[i - 1][j]) {
          inhalt[i][j] = "falsch";
        } else {
          inhalt[i][j] = "" + j + "" + i;
        }
      }
    }
    
    //JTable//
    tabelle = new JTable(inhalt, inhalt[0]);
    FarbModell tm = new FarbModell(inhalt, inhalt[0]);
    tabelle.setModel((FarbModell) tm);
    for (int j = 0; j < spaltenzahl; j++) {
      tabelle.getColumnModel().getColumn(j).setCellRenderer(new FarbRenderer());
    }
    
    tabelle.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        int i = tabelle.rowAtPoint(evt.getPoint());
        int j = tabelle.columnAtPoint(evt.getPoint());
        klickeZelle(i, j);
      }
    });
    tabelle.setRowHeight((int) (tabelle.getRowHeight() * 1.5));
    
    for (int i = 0; i < inhalt.length; i++) {
      for (int j = 0; j < inhalt[0].length; j++) {
        if (i > 0 && inhalt[i][j].equals("wahr")) {
          ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.wahr);
        } else if (i > 0 && inhalt[i][j].equals("falsch")) {
          ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.falsch);
        }
      }
    }
  }
  
  class FarbModell extends DefaultTableModel {
    ZellenStatus[][] status = new ZellenStatus[zeilenzahl][spaltenzahl];
    
    public FarbModell(String[][] inhalt, String[] inhalt2) {
      this.setDataVector(inhalt, inhalt2);
      for (int i = 0; i < inhalt.length; i++) {
        for (int j = 0; j < inhalt[0].length; j++) {
          status[i][j] = ZellenStatus.standard;
        }
      }    
    }
    
    public boolean isCellEditable(int row, int column) {
      tabelle.setFocusable(false);
      tabelle.setRowSelectionAllowed(false);
      return false;
    }
 
    public void setzeStatus(int i, int j, ZellenStatus s) {
      status[i][j] = s;
    }
      
    public ZellenStatus gibStatus(int i, int j) {
      return status[i][j];
    }
  }
  
  class FarbRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(
        JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
      JLabel l = (JLabel) super.getTableCellRendererComponent(
          table, value, isSelected, hasFocus, row, col);
      FarbModell fm = (FarbModell) tabelle.getModel();
      switch (fm.gibStatus(row, col)) {
        case standard:l.setBackground(Color.WHITE);
        break;
        case wahr:l.setBackground(Color.GREEN);
        break;
        case falsch:l.setBackground(Color.RED);
        break;
        case markiert:l.setBackground(Color.LIGHT_GRAY);
        break;
        case tipp:l.setBackground(Color.DARK_GRAY);
        break;
        default: l.setBackground(Color.WHITE);
      }
      setHorizontalAlignment(javax.swing.JLabel.CENTER);
      return l;
    }
  }
  
  private void klickeZelle(int i, int j) {
    if (i >= 0 && j >= 0 && modus == Modus.entfernen) {
      entferneSpalte(j);
      return;
    }
    if (i > 0 && j >= 0 && modus == Modus.markieren) {
      markiereZeile(i);
      return;
    }
    if (i > 0 && j >= 0) {
      wahrheitswerte[i - 1][j] = !wahrheitswerte[i - 1][j];
      if (wahrheitswerte[i - 1][j]) {
        inhalt[i][j] = "true";
        ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.wahr);
        tabelle.getModel().setValueAt("wahr", i, j);
      } else {
        inhalt[i][j] = "false";
        ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.falsch);
        tabelle.getModel().setValueAt("falsch", i, j);
      }
      //strg.befehl("zelleAendern(" + i + "," + j + ")");  //TODO noch auskommentiert
    } else if (i == 0 && j >= 0) {
      klickeFormel(j);
      return;
    }
    ((FarbModell) tabelle.getModel()).fireTableCellUpdated(i, j);
  }
  
  private void klickeFormel(int zeile) {
    //strg.befehl("formelEingeben(" + zeile + ")");  //TODO Kommentar entfernen
  }
  
  private void fuegeSpalteHinzu() {
    spaltenzahl++;
    //strg.befehl("SpalteHinzufuegen");  //TODO Kommentarzeichen entfernen
    tabelle.setVisible(false);
    initTabelle();
    tabelle.setVisible(true);
    tabellenRahmen.add(tabelle);
  }
  
  private void entferneSpalte(int j) {
    //strg.befehl("SpalteEntfernen(" + j + ")");  //TODO Kommentarzeichen entfernen
    spaltenzahl--;
    tabelle.setVisible(false);
    initTabelle();
    tabelle.setVisible(true);
    tabellenRahmen.add(tabelle);
    wechseleModus(wenigerSpalten, Modus.entfernen);
  }
  
  private void markiereZeile(int i) {
    markierteZeilen[i] = !markierteZeilen[i];
    for (int j = 0; j < spaltenzahl; j++) {
      if (markierteZeilen[i]) {
        ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.markiert);
      } else if (inhalt[i][j].equals("wahr")) {
        ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.wahr);
      } else if (inhalt[i][j].equals("falsch")) {
        ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.falsch);
      }
      ((FarbModell) tabelle.getModel()).fireTableCellUpdated(i, j);
    }
    wechseleModus(zeileMarkieren, Modus.markieren);
  }
  
  private void wechseleModus(Schaltflaeche s, Modus m) {
    if (modus == Modus.standard) {
      modus = m;
      s.setBackground(Color.DARK_GRAY);
      s.setForeground(Color.LIGHT_GRAY);
    } else if (modus == m) {
      modus = Modus.standard;
      s.setBackground(Color.LIGHT_GRAY);
      s.setForeground(Color.DARK_GRAY);
    }
  }
  
  public void zeigeTippAn() {
    
  }
  
  private void fuelleAus() {
    strg.befehl("fuelleAus");
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