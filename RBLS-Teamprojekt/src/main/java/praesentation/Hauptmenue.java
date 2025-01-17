package praesentation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import modell.Fassade;

/**
 * Grafische Ansicht des Hauptmenues.
 * Stoesst bei Mausklick eines Buttons das Oeffnen des Raetselwahl-Bildschirms oder des freien Modus
 * bzw. das Beenden des Programms ueber die Fensterverwaltung an.

 * @author Nick
 */
public class Hauptmenue extends javax.swing.JPanel {

  /**
   * auto-generierte ID.
   */
  private static final long serialVersionUID = -1940385720982920614L;

  private Fensterverwaltung fw;

  private Schaltflaeche stufe1;
  private Schaltflaeche stufe2;
  private Schaltflaeche stufe3;
  private Schaltflaeche stufe4;
  private Schaltflaeche freierModus;
  private Schaltflaeche beenden;

  public Hauptmenue(Fensterverwaltung fw) {
    this.fw = fw;
    init();
  }
    
  /**
   * initialiert GUI und Buttonaktionen.
   */
  private void init() {
    stufe1 = new Schaltflaeche(3);
    stufe2 = new Schaltflaeche(3);
    stufe3 = new Schaltflaeche(3);
    stufe4 = new Schaltflaeche(3);
    freierModus = new Schaltflaeche("FREIER MODUS");
    beenden = new Schaltflaeche("BEENDEN");
    JPanel startFeld = new JPanel(new java.awt.FlowLayout());
    startFeld.setBackground(Color.WHITE);
    JPanel mitte = new JPanel(new java.awt.BorderLayout());
    mitte.setBackground(Color.WHITE);
    mitte.add(startFeld, java.awt.BorderLayout.SOUTH);
    JLabel starttext = new javax.swing.JLabel("Start", SwingConstants.CENTER);
    mitte.add(starttext, java.awt.BorderLayout.NORTH);

    this.setLayout(new java.awt.GridBagLayout());
    this.setBackground(Color.WHITE);
        
    //Stufenbuttons//
    stufe1.setText("Stufe 1");
    stufe1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeStart(1);
      }
    });
    startFeld.add(stufe1);
        
    stufe2.setText("Stufe 2");
    stufe2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeStart(2);
      }
    });
    startFeld.add(stufe2);
        
    stufe3.setText("Stufe 3");
    stufe3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeStart(3);
      }
    });
    startFeld.add(stufe3);
        
    stufe4.setText("Stufe 4");
    stufe4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeStart(4);
      }
    });
    startFeld.add(stufe4);
    
    int stufe = Fassade.gibFa().gibAbgeschlosseneStufe();
    if (stufe < 1) {
      stufe2.setEnabled(false);
    }
    if (stufe < 2) {
      stufe3.setEnabled(false);
    }
    if (stufe < 3) {
      stufe4.setEnabled(false);
      freierModus.setEnabled(false);
    }
        
    GridBagConstraints c = new GridBagConstraints();
    c.weightx = 0.5;
    c.weighty = 0.5;
    c.gridx = 3;
    c.gridy = 2;
    this.add(mitte, c);
        
    //weitere Buttons//
    freierModus.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeFreienModus();
      }
    });
    beenden.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeBeenden();
      }
    });
    c.insets = new Insets(74, 50, 74, 50);
    c.fill = java.awt.GridBagConstraints.BOTH;
    c.gridx = 0;
    c.ipadx = 80;
    c.ipady = 60;
    this.add(beenden, c);
    c.gridx = 4;
    this.add(freierModus, c);
    
    //Titel//
    c.gridx = 3;
    c.gridy = 1;
    c.ipady = 2;
    c.insets = new Insets(120, 50, 0, 50);
    JLabel titel1 = new JLabel("RÄTSEL-BASIERTE", SwingConstants.CENTER);
    JLabel titel2 = new JLabel("LOGIK-SOFTWARE", SwingConstants.CENTER);
    JPanel titel = new JPanel();
    titel.setLayout(new BoxLayout(titel, BoxLayout.Y_AXIS));
    titel1.setAlignmentX(Component.CENTER_ALIGNMENT);
    titel2.setAlignmentX(Component.CENTER_ALIGNMENT);
    titel1.setFont(new javax.swing.plaf.FontUIResource("Arial Unicode MS", Font.BOLD, 48));
    titel2.setFont(new javax.swing.plaf.FontUIResource("Arial Unicode MS", Font.BOLD, 48));
    titel.add(titel1, 0);
    titel.add(titel2, 1);
    titel.setBackground(Color.WHITE);
    this.add(titel, c);

    JPanel leer = new JPanel();
    leer.setLayout(new BoxLayout(leer, BoxLayout.Y_AXIS));
    leer.setBackground(Color.WHITE);
    c.gridy = 3;
    c.ipady = 40;
    this.add(leer, c);

  }

  /**
   * Stoesst Oeffnen von Raetselwahl an.

   * @param stufe Stufe der Raetsel
   */
  private void klickeStart(int stufe) {
    fw.oeffneRaetselwahl(stufe);
  }

  /**
   * stoesst Beenden an.
   */
  private void klickeBeenden() {
    fw.beende();
  }

  /**
   * stoesst Starten des freien Modus an.
   */
  private void klickeFreienModus() {
    fw.starteFreienModus();
  }
  
}