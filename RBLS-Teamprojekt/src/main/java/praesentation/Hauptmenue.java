package praesentation;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * GUI Hauptmenue
 *
 */
public class Hauptmenue extends javax.swing.JFrame {
  private Fensterverwaltung fw;

  private JButton Stufe1;
  private JButton Stufe2;
  private JButton Stufe3;
  private JButton Stufe4;
  private JButton freierModus;
  private JButton beenden;

  public Hauptmenue(Fensterverwaltung fw) {	//Fassade fehlt
    this.fw = fw;
    init();
  }
    
  /**
   * initialiert GUI und Buttonaktionen
   */
  private void init() {
    Stufe1 = new javax.swing.JButton();
    Stufe2 = new javax.swing.JButton();
    Stufe3 = new javax.swing.JButton();
    Stufe4 = new javax.swing.JButton();
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
    GridBagConstraints c = new GridBagConstraints();
    getContentPane().setBackground(Color.WHITE);
        
    //Stufenbuttons//
    Stufe1.setText("Stufe 1");
    Stufe1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeStart(1);
      }
    });
    Stufe1.setBackground(Color.WHITE);
    Stufe1.setForeground(new Color(255,102,0));
    startFeld.add(Stufe1);
        
    Stufe2.setText("Stufe 2");
    Stufe2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeStart(2);
      }
    });
    Stufe2.setBackground(Color.LIGHT_GRAY);
    Stufe2.setForeground(Color.DARK_GRAY);
    startFeld.add(Stufe2);
        
    Stufe3.setText("Stufe 3");
    Stufe3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeStart(3);
      }
    });
    Stufe3.setBackground(Color.LIGHT_GRAY);
    Stufe3.setForeground(Color.DARK_GRAY);
    startFeld.add(Stufe3);
        
    Stufe4.setText("Stufe 4");
    Stufe4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeStart(4);
      }
    });
    Stufe4.setBackground(Color.LIGHT_GRAY);
    Stufe4.setForeground(Color.DARK_GRAY);
    startFeld.add(Stufe4);
        
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
   * stoesst Oeffnen von Raetselwahl an
   * @param stufe Stufe der Raetsel
   */
  private void klickeStart(int stufe) {
    fw.oeffneRaetselwahl(stufe);
  }

  /**
   * stoesst Beenden an
   */
  private void klickeBeenden() {
    fw.beende();
  }

  /**
   * stoesst Starten des freien Modus an
   */
  private void klickeFreienModus() {
    fw.starteFreienModus();
  }
}