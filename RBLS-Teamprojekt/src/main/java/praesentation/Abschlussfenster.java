package praesentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Zeigt zwei Buttons an, durch die das Wechseln zum Hauptmenü
 * bzw. zu einem zufälligen ungelösten Rätsel derselben Stufe
 * über die Fensterverwaltung angestoßen wird.
 * @author Nick
 */
public class Abschlussfenster extends javax.swing.JFrame {

  /**
   * auto-generierte ID.
   */
  private static final long serialVersionUID = 6076675565632852903L;
  private Fensterverwaltung fw;
  private Schaltflaeche naechstesRaetsel;
  private Schaltflaeche menue;
  private JLabel textLabel;
  private String[] texte = new String[] {
    "Rätsel gelöst!", "Geschafft!", 
      "Du hast das Rätsel gelöst. Klasse.", "Richtig gelöst!"};

  /**
   * Konstruktor.
   * @param fw Fensterverwaltung zum Wechseln der Ansichten.
   */
  public Abschlussfenster(Fensterverwaltung fw) {
    this.fw = fw;

    naechstesRaetsel = new Schaltflaeche("nächstes Rätsel", 3);
    naechstesRaetsel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeNaechstesRaetsel();
      }
    });
    menue = new Schaltflaeche("zum Menü", 3);
    menue.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeMenue();
      }
    });
    JPanel buttons = new JPanel(new java.awt.BorderLayout());
    buttons.setBackground(new Color(255,102,0));
    naechstesRaetsel.setPreferredSize(new Dimension(200,100));
    buttons.add(naechstesRaetsel, java.awt.BorderLayout.EAST);
    menue.setPreferredSize(new Dimension(200,100));
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