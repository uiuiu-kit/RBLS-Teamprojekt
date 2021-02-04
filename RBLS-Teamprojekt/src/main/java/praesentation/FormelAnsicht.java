package praesentation;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

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
  
  public static final String UND = "\u2227";
  public static final String ODER = "\u2228";
  //TODO Rest

  private FormelEditor strg;
  private JDialog ansicht;
  private JLabel formelAnzeige = new JLabel();
  private Schaltflaeche bestaetige = new Schaltflaeche("Bestätige");
  private Schaltflaeche abbruch = new Schaltflaeche("Abbruch", 2);
  private Schaltflaeche entferne = new Schaltflaeche("Entferne", 3);
  
  private String alteFormel;
  private String formel = "";
  private List<Schaltflaeche> zeichen = new ArrayList<Schaltflaeche>();
  
  private Schaltflaeche[] atomareAussagen;
  private Schaltflaeche und = new Schaltflaeche("\u2227", 3);
  private Schaltflaeche oder = new Schaltflaeche("\u2228", 3);
  private Schaltflaeche nicht = new Schaltflaeche("\u00AC", 3);
  private Schaltflaeche impliziert = new Schaltflaeche("\u2192", 3);
  private Schaltflaeche aequivalent = new Schaltflaeche("\u2194", 3);
  private Schaltflaeche xor = new Schaltflaeche("\u2295", 3);
  private Schaltflaeche klammerAuf = new Schaltflaeche("(", 3);
  private Schaltflaeche klammerZu = new Schaltflaeche(")", 3);
  
  public FormelAnsicht(String[] aussagen, FormelEditor strg) {
    this.strg = strg;
    
    zeichen.add(und);
    zeichen.add(oder);
    zeichen.add(nicht);
    zeichen.add(impliziert);
    zeichen.add(aequivalent);
    zeichen.add(xor);
    zeichen.add(klammerAuf);
    zeichen.add(klammerZu);
    
    JPanel aussagenPanel = new JPanel();
    aussagenPanel.setLayout(new FlowLayout());
    atomareAussagen = new Schaltflaeche[aussagen.length];
    for (int j = 0; j < aussagen.length; j++) {
      atomareAussagen[j] = new Schaltflaeche(aussagen[j], 3);
      atomareAussagen[j].addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            fuegeHinzu(e.getActionCommand().substring(0, 1));
          }
        });
      aussagenPanel.add(atomareAussagen[j]);
    }
    
    JPanel operatorPanel1 = new JPanel();
    operatorPanel1.setLayout(new FlowLayout());
    und.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          fuegeHinzu(und.getText());
        }
      });
    operatorPanel1.add(und);
    oder.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          fuegeHinzu(oder.getText());
        }
      });
    operatorPanel1.add(oder);
    xor.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          fuegeHinzu(xor.getText());
        }
      });
    operatorPanel1.add(xor);

    nicht.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          fuegeHinzu(nicht.getText());
        }
      });
    operatorPanel1.add(nicht);
    
    JPanel operatorPanel2 = new JPanel();
    operatorPanel2.setLayout(new FlowLayout());
    impliziert.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          fuegeHinzu(impliziert.getText());
        }
      });
    operatorPanel2.add(impliziert);
    aequivalent.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          fuegeHinzu(aequivalent.getText());
        }
      });
    operatorPanel2.add(aequivalent);
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
    
    JPanel entfernePanel = new JPanel();
    entfernePanel.setLayout(new FlowLayout());
    entferne.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          loescheZeichen();
        }
      });
    entfernePanel.add(entferne);
    
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
    
    formelAnzeige = new JLabel(formel, SwingConstants.CENTER);
    JPanel formelPanel = new JPanel();
    formelPanel.setLayout(new FlowLayout());
    formelPanel.add(formelAnzeige);
    
    aussagenPanel.setBackground(Color.WHITE);
    operatorPanel1.setBackground(Color.WHITE);
    operatorPanel2.setBackground(Color.WHITE);
    entfernePanel.setBackground(Color.WHITE);
    menuePanel.setBackground(Color.WHITE);
    formelPanel.setBackground(Color.LIGHT_GRAY);
    formelAnzeige.setBackground(Color.LIGHT_GRAY);
    
    ansicht = new JDialog();
    ansicht.getContentPane().setLayout(new BoxLayout(ansicht.getContentPane(), BoxLayout.Y_AXIS));
    ansicht.getContentPane().add(formelPanel);
    ansicht.getContentPane().add(aussagenPanel);
    ansicht.getContentPane().add(operatorPanel1);
    ansicht.getContentPane().add(operatorPanel2);
    ansicht.getContentPane().add(entfernePanel);
    ansicht.getContentPane().add(menuePanel);
    
    ansicht.setTitle("Atomare Aussagen");
    ansicht.setSize(800, 400);
    ansicht.setResizable(true);
    ansicht.setLocation(50, 50);
    ansicht.setAlwaysOnTop(true);
    ansicht.setModal(true);
    ansicht.setModalityType(ModalityType.APPLICATION_MODAL);
    ansicht.getContentPane().setBackground(Color.LIGHT_GRAY);
    ansicht.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    ansicht.setVisible(true);
    
    pruefeErlaubteZeichen();
    
  }
  
  public String getFormel() {
    return formel;
  }
  
  private void fuegeHinzu(String zeichen) {
    formel = formel + "" + zeichen;
    formelAnzeige.setVisible(false);
    formelAnzeige.setText(formel);
    formelAnzeige.setVisible(true);
    strg.setzeZeichen(zeichen);
    pruefeErlaubteZeichen();
  }
  
  private void pruefeErlaubteZeichen() {
    for (Iterator<Schaltflaeche> iter = zeichen.iterator(); iter.hasNext(); ) {
      Schaltflaeche element = iter.next();
      if (strg.zeichenErlaubt(element.getActionCommand())) {
        element.setEnabled(true);
      } else {
        element.setEnabled(false);
      }
    }
  }

  private void loescheZeichen() {
    formel = formel.substring(0, formel.length() - 1);
    formelAnzeige.setVisible(false);
    formelAnzeige.setText(formel);
    formelAnzeige.setVisible(true);
    strg.entferneletzesZeichen();
    pruefeErlaubteZeichen();
  }
  
  private void bestaetige() {
    if (strg.bestätige()) {
      ansicht.dispose();
    } else {
      //TODO evtl Dialogfenster (?)
    }
  }
  
  private void brecheAb() {
    strg.brecheAb();
    ansicht.dispose();
  }
}
