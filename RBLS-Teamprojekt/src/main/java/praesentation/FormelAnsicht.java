package praesentation;

import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
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
  private JLabel formelAnzeige = new JLabel();
  private Schaltflaeche bestaetige = new Schaltflaeche("Bestätige");
  private Schaltflaeche abbruch = new Schaltflaeche("Abbruch", 2);
  private Schaltflaeche entferne = new Schaltflaeche("Entferne", 3);
  
  private String formel = "";
  
  private Schaltflaeche[] atomareAussagen;
  private Schaltflaeche und = new Schaltflaeche("\u2227", 3);
  private Schaltflaeche oder = new Schaltflaeche("\u2228", 3);
  private Schaltflaeche nicht = new Schaltflaeche("\u00AC", 3);
  private Schaltflaeche impliziert = new Schaltflaeche("\u2192", 3);
  //private Schaltflaeche aequivalent = new Schaltflaeche("\u2194", 3);  //TODO Aequivalenz
  private Schaltflaeche xor = new Schaltflaeche("\u2295", 3);
  private Schaltflaeche klammerAuf = new Schaltflaeche("(", 3);
  private Schaltflaeche klammerZu = new Schaltflaeche(")", 3);
  private ArrayList<Schaltflaeche> zeichen = new ArrayList<Schaltflaeche>(
      Arrays.asList(new Schaltflaeche[] {und, oder, nicht, impliziert,
      /* aequivalent,*/ xor, klammerAuf, klammerZu}));
  private ArrayList<Character> symbole = new ArrayList<Character>(
      Arrays.asList(new Character[] {'u', 'o', 'n', 'f',/* 'a',*/ 'x', '(', ')'}));
  
  /**
   * Erzeugt eine Ansicht für den Formeleditor als Fenster.
   * @param aussagen atomare Aussagen, die als Schaltflächen verfügbar sein sollen
   * @param strg Formeleditor zur Kommunikation
   */
  public FormelAnsicht(String[] aussagen, FormelEditor strg) {
    this.strg = strg;
    JPanel aussagenPanel = new JPanel();
    aussagenPanel.setLayout(new FlowLayout());
    atomareAussagen = new Schaltflaeche[aussagen.length];
    for (int j = 0; j < aussagen.length; j++) {
      atomareAussagen[j] = new Schaltflaeche(aussagen[j], 3);
      atomareAussagen[j].addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            schreibe(e.getActionCommand().substring(0, 1));
            for (int i = 0; i < atomareAussagen.length; i++) {
              if (atomareAussagen[i].getActionCommand() == e.getActionCommand()) {
                fuegeHinzu((char) (i + '0'));
              }
            }
          }
        });
      zeichen.add(atomareAussagen[j]);
      symbole.add((char) (j + '0'));
      aussagenPanel.add(atomareAussagen[j]);
    }
    
    JPanel operatorPanel1 = new JPanel();
    operatorPanel1.setLayout(new FlowLayout());
    und.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          schreibe(und.getText());
          fuegeHinzu('u');
        }
      });
    operatorPanel1.add(und);
    oder.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          schreibe(oder.getText());
          fuegeHinzu('o');
        }
      });
    operatorPanel1.add(oder);
    xor.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          schreibe(xor.getText());
          fuegeHinzu('x');
        }
      });
    operatorPanel1.add(xor);

    nicht.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          schreibe(nicht.getText());
          fuegeHinzu('n');
        }
      });
    operatorPanel1.add(nicht);
    
    JPanel operatorPanel2 = new JPanel();
    operatorPanel2.setLayout(new FlowLayout());
    impliziert.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          schreibe(impliziert.getText());
          fuegeHinzu('f');
        }
      });
    operatorPanel2.add(impliziert);
    //TODO Aequivalenz
    /* aequivalent.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          schreibe(aequivalent.getText());
          fuegeHinzu('a');
        }
      });
    operatorPanel2.add(aequivalent); */
    klammerAuf.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          schreibe("(");
          fuegeHinzu('(');
        }
      });
    operatorPanel2.add(klammerAuf);
    klammerZu.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          schreibe(")");
          fuegeHinzu(')');
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
    pruefeErlaubteZeichen();
    ansicht.setVisible(true);
    
  }
  
  public String getFormel() {
    return formel;
  }
  
  private void fuegeHinzu(char zeichen) {
    strg.setzeZeichen(zeichen);
    pruefeErlaubteZeichen();
  }
  
  private void schreibe(String zeichen) {
    formel = formel + "" + zeichen;
    formelAnzeige.setVisible(false);
    formelAnzeige.setText(formel);
    formelAnzeige.setVisible(true);
  }
  
  private void pruefeErlaubteZeichen() {
    int j = 0;
    for (Iterator<Schaltflaeche> iter = zeichen.iterator(); iter.hasNext(); ) {
      Schaltflaeche element = iter.next();
      if (strg.zeichenErlaubt(symbole.get(j))) {
        element.setEnabled(true);
      } else {
        element.setEnabled(false);
      }
      j++; 
    }
    if (formel.equals("")) {
      entferne.setEnabled(false);
    } else {
      entferne.setEnabled(true);
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
    if (strg.bestaetige()) {
      ansicht.dispose();
    } else {
      //TODO evtl Dialogfenster, falls Antwort nicht korrekt ist
    }
  }
  
  private void brecheAb() {
    strg.brecheAb();
    ansicht.dispose();
  }
}
