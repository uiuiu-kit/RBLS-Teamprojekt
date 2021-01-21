package praesentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Zeigt zwei Buttons an, durch die das Wechseln zum Hauptmen�
 * bzw. zu einem zuf�lligen ungel�sten R�tsel derselben Stufe
 * �ber die Fensterverwaltung angesto�en wird.
 *
 */
public class Abschlussfenster extends javax.swing.JFrame {

  /**
   * auto-generierte ID.
   */
  private static final long serialVersionUID = 6076675565632852903L;
  private Fensterverwaltung fw;
  private JButton naechstesRaetsel;
  private JButton menue;
  private JLabel textLabel;
  private String[] texte = new String[] {
    "R�tsel gel�st!", "Geschafft!", "[GENERISCHER ABSCHLUSSTEXT]", 
      "du hast das R�tsel gel�st. Klasse.", "Richtig gel�st!"};

  /**
   * Konstruktor.
   * @param fw Fensterverwaltung zum Wechseln der Ansichten.
   */
  public Abschlussfenster(Fensterverwaltung fw) {
    this.fw = fw;

    naechstesRaetsel = new javax.swing.JButton("n�chstes R�tsel");
    naechstesRaetsel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeNaechstesRaetsel();
      }
    });
    menue = new javax.swing.JButton("zum Men�");
    menue.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeMenue();
      }
    });
    JPanel buttons = new JPanel(new java.awt.BorderLayout());
    buttons.setBackground(new Color(255,102,0));
    naechstesRaetsel.setPreferredSize(new Dimension(200,100));
    naechstesRaetsel.setBackground(Color.WHITE);
    naechstesRaetsel.setForeground(new Color(255,102,0));
    buttons.add(naechstesRaetsel, java.awt.BorderLayout.EAST);
    menue.setPreferredSize(new Dimension(200,100));
    menue.setBackground(Color.WHITE);
    menue.setForeground(new Color(255,102,0));
    buttons.add(menue, java.awt.BorderLayout.WEST);
        
    textLabel = new JLabel(texte[new Random().nextInt(texte.length)], SwingConstants.CENTER);
    textLabel.setForeground(Color.WHITE);
    
    getContentPane().setLayout(new java.awt.BorderLayout());
    getContentPane().setBackground(new Color(255,102,0));
    getContentPane().add(buttons, java.awt.BorderLayout.SOUTH);
    getContentPane().add(textLabel, java.awt.BorderLayout.CENTER);
  }
  
  /**
   * fuehrt zum Hauptmenue.
   */
  private void klickeMenue() {
    fw.oeffneMenue();
  }

  /**
  * fuehrt zu zufaelligem Raetsel ueber Fensterverwaltung.
  */
  private void klickeNaechstesRaetsel() {
    fw.starteZufaelligesRaetsel();
  }
}
