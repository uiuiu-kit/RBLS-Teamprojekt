package praesentation;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Grafische Ansicht des Hauptmenüs.
 * Stößt bei Mausklick eines Buttons das Öffnen des Rätselwahl-Bildschirms oder des freien Modus
 * bzw. das Beenden des Programms über die Fensterverwaltung an.
 * 
 */
public class Hauptmenue extends javax.swing.JFrame {

  /**
   * auto-generierte ID.
   */
  private static final long serialVersionUID = -1940385720982920614L;

  private Fensterverwaltung fw;

  private JButton stufe1;
  private JButton stufe2;
  private JButton stufe3;
  private JButton stufe4;
  private JButton freierModus;
  private JButton beenden;

  public Hauptmenue(Fensterverwaltung fw) { //Fassade fehlt
    this.fw = fw;
    init();
  }
    
  /**
   * initialiert GUI und Buttonaktionen.
   */
  private void init() {
    stufe1 = new javax.swing.JButton();
    stufe2 = new javax.swing.JButton();
    stufe3 = new javax.swing.JButton();
    stufe4 = new javax.swing.JButton();
    freierModus = new javax.swing.JButton();
    beenden = new javax.swing.JButton();
    JPanel startFeld = new JPanel(new java.awt.FlowLayout());
    startFeld.setBackground(Color.WHITE);
    JPanel mitte = new JPanel(new java.awt.BorderLayout());
    mitte.setBackground(Color.WHITE);
    mitte.add(startFeld, java.awt.BorderLayout.SOUTH);
    JLabel starttext = new javax.swing.JLabel("Start", SwingConstants.CENTER);
    mitte.add(starttext, java.awt.BorderLayout.NORTH);
 
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    getContentPane().setLayout(new java.awt.GridBagLayout());
    getContentPane().setBackground(Color.WHITE);
        
    //Stufenbuttons//
    stufe1.setText("Stufe 1");
    stufe1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeStart(1);
      }
    });
    stufe1.setBackground(Color.WHITE);
    stufe1.setForeground(new Color(255,102,0));
    startFeld.add(stufe1);
        
    stufe2.setText("Stufe 2");
    stufe2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeStart(2);
      }
    });
    stufe2.setBackground(Color.LIGHT_GRAY);
    stufe2.setForeground(Color.DARK_GRAY);
    startFeld.add(stufe2);
        
    stufe3.setText("Stufe 3");
    stufe3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeStart(3);
      }
    });
    stufe3.setBackground(Color.LIGHT_GRAY);
    stufe3.setForeground(Color.DARK_GRAY);
    startFeld.add(stufe3);
        
    stufe4.setText("Stufe 4");
    stufe4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeStart(4);
      }
    });
    stufe4.setBackground(Color.LIGHT_GRAY);
    stufe4.setForeground(Color.DARK_GRAY);
    startFeld.add(stufe4);
        
    GridBagConstraints c = new GridBagConstraints();
    c.weightx = 0.5;
    c.gridx = 3;
    c.gridy = 1;
    getContentPane().add(mitte, c);
        
    //weitere Buttons//
    freierModus.setText("FREIER MODUS");
    freierModus.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeFreienModus();
      }
    });
    beenden.setText("BEENDEN");
    beenden.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeBeenden();
      }
    });
    freierModus.setBackground(new Color(255,102,0));
    freierModus.setForeground(Color.WHITE);
    beenden.setBackground(new Color(255,102,0));
    beenden.setForeground(Color.WHITE);
    c.weightx = 0.5;
    c.gridx = 0;
    c.gridy = 1;
    c.ipadx = 80;
    c.ipady = 60;
    getContentPane().add(beenden, c);
    c.gridx = 4;
    c.gridy = 1;
    getContentPane().add(freierModus, c);
        
    pack();
  }

  /**
   * stoesst Oeffnen von Raetselwahl an.
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