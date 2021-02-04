package praesentation;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import steuerung.FormelEditor;

/**
 * Grafische Formelansicht zum Eingeben von Formeln als Pop-Up-Fenster.
 * Zeigt Buttons mit Zeichen und Buttons mit atomaren Aussagen an.
 * @author Nick
 */
public class FormelAnsicht {
  private FormelEditor strg;
  private JDialog ansicht;
  private JLabel formelAnzeige;
  private Schaltflaeche bestaetige = new Schaltflaeche("Bestätige");
  private Schaltflaeche abbruch = new Schaltflaeche("Abbruch", 2);
  
  private String alteFormel;
  private String formel;
  
  private Schaltflaeche[] atomareAussagen;
  private Schaltflaeche und = new Schaltflaeche("und", 3);
  private Schaltflaeche oder = new Schaltflaeche("oder", 3);
  private Schaltflaeche nicht = new Schaltflaeche("nicht", 3);
  private Schaltflaeche impliziert = new Schaltflaeche("impl", 3);
  private Schaltflaeche xor = new Schaltflaeche("xor", 3);
  private Schaltflaeche klammerAuf = new Schaltflaeche("(", 3);
  private Schaltflaeche klammerZu = new Schaltflaeche(")", 3);
  
  public FormelAnsicht(String[] aussagen) {
    
    JPanel aussagenPanel = new JPanel();
    aussagenPanel.setLayout(new FlowLayout());
    atomareAussagen = new Schaltflaeche[aussagen.length];
    for (int j = 0; j < aussagen.length; j++) {
      atomareAussagen[j] = new Schaltflaeche(aussagen[j], 3);
      atomareAussagen[j].addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            fuegeHinzu(e.getActionCommand());
          }
        });
      aussagenPanel.add(atomareAussagen[j]);
    }
    
    JPanel operatorPanel1 = new JPanel();
    operatorPanel1.setLayout(new FlowLayout());
    und.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          fuegeHinzu("u");
        }
      });
    operatorPanel1.add(und);
    und.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          fuegeHinzu("u");
        }
      });
    operatorPanel1.add(oder);
    oder.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          fuegeHinzu("o");
        }
      });
    operatorPanel1.add(xor);
    xor.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          fuegeHinzu("x");
        }
      });
    operatorPanel1.add(impliziert);
    impliziert.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          fuegeHinzu("i");
        }
      });
    operatorPanel1.add(impliziert);
    
    JPanel operatorPanel2 = new JPanel();
    operatorPanel2.setLayout(new FlowLayout());
    nicht.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          fuegeHinzu("n");
        }
      });
    operatorPanel2.add(nicht);
    klammerAuf.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          fuegeHinzu("(");
        }
      });
    operatorPanel2.add(klammerAuf);
    klammerZu.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          fuegeHinzu(")");
        }
      });
    operatorPanel2.add(klammerZu);
    
    JPanel menuePanel = new JPanel();
    menuePanel.setLayout(new FlowLayout());
    abbruch.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          brecheAb();
        }
      });
    menuePanel.add(abbruch);
    bestaetige.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          bestaetige();
        }
      });
    menuePanel.add(bestaetige);
    
    formelAnzeige = new JLabel("Ihre Formel hier", SwingConstants.CENTER);
    
    aussagenPanel.setBackground(Color.WHITE);
    operatorPanel1.setBackground(Color.WHITE);
    operatorPanel2.setBackground(Color.WHITE);
    menuePanel.setBackground(Color.WHITE);
    formelAnzeige.setBackground(Color.WHITE);
    
    ansicht = new JDialog();
    ansicht.getContentPane().setLayout(new BoxLayout(ansicht.getContentPane(), BoxLayout.Y_AXIS));
    ansicht.getContentPane().add(formelAnzeige);
    ansicht.getContentPane().add(aussagenPanel);
    ansicht.getContentPane().add(operatorPanel1);
    ansicht.getContentPane().add(operatorPanel2);
    ansicht.getContentPane().add(menuePanel);
    
    ansicht.setTitle("Atomare Aussagen");
    ansicht.setSize(600, 400);
    ansicht.setResizable(true);
    ansicht.setLocation(50, 50);
    ansicht.setAlwaysOnTop(true);
    ansicht.setModal(true);
    ansicht.setModalityType(ModalityType.APPLICATION_MODAL);
    ansicht.getContentPane().setBackground(Color.LIGHT_GRAY);
    ansicht.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    ansicht.setVisible(true);
    
  }
  
  public String getFormel() {
    return formel;
  }
  
  private void fuegeHinzu(String zeichen) {
    strg.setzeZeichen(zeichen);
  }
  
  private void bestaetige() {
    
  }
  
  private void brecheAb() {
    ansicht.dispose();
  }
}
