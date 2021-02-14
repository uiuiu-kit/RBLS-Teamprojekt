package praesentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import modell.PraesentationFassade;
import praesentation.tabelle.FarbModell;
import praesentation.tabelle.ZellenStatus;
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
  private String[][] inhalt;
  private int zeilenzahl = 9;
  private int spaltenzahl = 5;
  private boolean[] markierteZeilen;
  
  private enum Modus { standard,  entfernen, markieren
  }

  private Modus modus = Modus.standard;
  private int stufe = 4;
  private JPanel panel;
  private JPanel tabellenRahmen = new JPanel();

  /**
   * Erstellt eine Wahrheitstabelle mit den Daten aus der Praesentationsfassade und
   * initialisiert die Schaltflaechen und die JTable.
   * @param modell Praesentationsfassade mit den Daten
   * @param strg Wahrheitstabellensteuerung fuer Weitergabe der Befehle
   */
  public KonkreteTabellenAnsicht(PraesentationFassade modell, WahrheitstabellenSteuerungen strg) {
    this.modell = modell;
    this.strg = strg;
    init();
  }
  
  private void init() {
    zeilenzahl = modell.gibZeilenAnz();
    spaltenzahl = modell.gibSpaltenAnz();
    //stufe = modell.gibAktuelleStufe();  //TODO unkommentieren!
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
    panel.setBackground(Color.WHITE);
    tabelle.setFillsViewportHeight(true);    
  }
  
  private void initTabelle() {
    //Modelldaten//
    inhalt = new String[zeilenzahl][spaltenzahl];
    for (int i = 0; i < inhalt.length; i++) {
      for (int j = 0; j < inhalt[0].length; j++) {
        inhalt[i][j] = modell.gibZelle(new int[] {i,j});
        if (i > 0 && inhalt[i][j].equals("true")) {
          inhalt[i][j] = "wahr";
        } else if (i > 0 && inhalt[i][j].equals("false")) {
          inhalt[i][j] = "falsch";
        }
      }
    }
    //JTable//
    tabelle = new JTable(inhalt, inhalt[0]);
    FarbModell tm = new FarbModell(inhalt, inhalt[0]);
    tabelle.setModel((FarbModell) tm);
    for (int j = 0; j < spaltenzahl; j++) {
      tabelle.getColumnModel().getColumn(j).setCellRenderer(
          new praesentation.tabelle.FarbRenderer());
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
    tabelle.setFocusable(false);
    tabelle.setRowSelectionAllowed(false);
    for (int i = 0; i < inhalt.length; i++) {
      for (int j = 0; j < inhalt[0].length; j++) {
        aktualisiere(new int[] {i, j});
      }  
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
      //strg.befehl("ZelleAendern(" + i + "," + j + ")");
      if (inhalt[i][j].equals("wahr")) {   // TODO
        inhalt[i][j] = "false";            //
      } else {                             //  KOMMT WEG SOBALD ZELLEÄNDERNBEFEHL GEHT
        inhalt[i][j] = "true";             //
      }                                    // TODO 
      aktualisiere(new int[] {i, j});
    } else if (i == 0 && j >= 0) {
      klickeFormel(j);
      return;
    }
    ((FarbModell) tabelle.getModel()).fireTableCellUpdated(i, j);
  }
  
  private void klickeFormel(int spalte) {
    strg.befehl("FormelEingeben(" + spalte + ")");
    ((FarbModell) tabelle.getModel()).setzeStatus(0, spalte, ZellenStatus.standard);
    ((FarbModell) tabelle.getModel()).fireTableCellUpdated(0, spalte); 
  }
  
  private void fuegeSpalteHinzu() {
    spaltenzahl = modell.gibSpaltenAnz();
    strg.befehl("SpalteHinzufuegen");
    tabelle.setVisible(false);
    initTabelle();
    tabelle.setVisible(true);
    tabellenRahmen.add(tabelle);
  }
  
  private void entferneSpalte(int j) {
    strg.befehl("SpalteEntfernen(" + j + ")");
    spaltenzahl = modell.gibSpaltenAnz();
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
      } else {
        System.out.println(inhalt[i][j]);
        ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.standard);
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
  
  /**
   * Holt Position einer fehlerhaft ausgefuellten Zelle aus der Steuerung und markiert diese.
   */
  public void zeigeTippAn() {
    int[] tipp = strg.gibTip();
    assert tipp.length == 2;
    ((FarbModell) tabelle.getModel()).setzeStatus(tipp[0], tipp[1], ZellenStatus.tipp);
    ((FarbModell) tabelle.getModel()).fireTableCellUpdated(tipp[0], tipp[1]);
  }
  
  private void fuelleAus() {
    strg.befehl("FuelleTabelle");
  }
  
  /**
   * Aktualisiert den Wert einer Zelle mit den Daten der Praesentationsfassade.
   */
  public void aktualisiere(int[] zelle) {
    assert zelle.length == 2;
    int i = zelle[0];
    int j = zelle[1];
    if (i > 0 && j >= 0) {
      if (inhalt[i][j].equals("true")) {
        inhalt[i][j] = "wahr";
        if (((FarbModell) tabelle.getModel()).gibStatus(i, j) != ZellenStatus.markiert) {
          ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.wahr);
        }
      } else {
        inhalt[i][j] = "falsch";
        if (((FarbModell) tabelle.getModel()).gibStatus(i, j) != ZellenStatus.markiert) {
          ((FarbModell) tabelle.getModel()).setzeStatus(i, j, ZellenStatus.falsch);
        }
      } 
    } else if (i >= 0 && j >= 0) {
      inhalt[zelle[0]][zelle[1]] = modell.gibZelle(zelle);
    }
    tabelle.getModel().setValueAt(inhalt[zelle[0]][zelle[1]], zelle[0], zelle[1]);
    ((FarbModell) tabelle.getModel()).fireTableCellUpdated(i, j); 
  }
  
  public JPanel gibAnsicht() {
    return panel;
  }
  
}