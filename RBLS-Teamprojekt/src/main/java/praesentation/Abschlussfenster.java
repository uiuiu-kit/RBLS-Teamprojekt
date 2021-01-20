package praesentation;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Nick Terzer
 *
 */
public class Abschlussfenster extends javax.swing.JFrame{

  private Fensterverwaltung fw;
  private JButton naechstesRaetsel;
  private JButton menue;
  private JLabel textLabel;
  private String[] texte = new String[] {
    "Rätsel gelöst", "Geschafft", "[GENERISCHER ABSCHLUSSTEXT]"};
	
  public Abschlussfenster(Fensterverwaltung fw) {
    this.fw = fw;

    naechstesRaetsel = new javax.swing.JButton("nächstes Rätsel");
    naechstesRaetsel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeNaechstesRaetsel();
      }
    });
    menue = new javax.swing.JButton("zum Menü");
    menue.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeMenue();
      }
    });
    JPanel buttons = new JPanel(new java.awt.BorderLayout());
    buttons.setBackground(Color.WHITE);
    buttons.add(naechstesRaetsel, java.awt.BorderLayout.EAST);
    buttons.add(menue, java.awt.BorderLayout.WEST);
        
    textLabel = new JLabel(texte[new Random().nextInt(texte.length)]);
 
    getContentPane().setLayout(new java.awt.BorderLayout());
    getContentPane().setBackground(Color.WHITE);
    getContentPane().add(buttons, java.awt.BorderLayout.SOUTH);
    getContentPane().add(textLabel, java.awt.BorderLayout.NORTH);
  }
	
  /**
   * fuehrt zum Hauptmenue
   */
  private void klickeMenue() {
    fw.oeffneMenue();
  }
	
  /**
  * fuehrt zu zufaelligem Raetsel ueber Fensterverwaltung
  */
  private void klickeNaechstesRaetsel() {
    fw.starteZufaelligesRaetsel();
  }
}
