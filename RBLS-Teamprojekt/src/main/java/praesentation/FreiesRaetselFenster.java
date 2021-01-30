package praesentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import modell.PraesentationFassade;

public class FreiesRaetselFenster extends RaetselFenster {

  private List<String> aussagenListe = new ArrayList<String>();
  private JDialog atomareAussagen;
  private JTextField[] aussagen;
  
  private Fensterverwaltung fv;
  private PraesentationFassade modell;

  public FreiesRaetselFenster(Fensterverwaltung fensterverwaltung, PraesentationFassade modell) {
    this.fv = fensterverwaltung;
    this.modell = modell;
    this.tabelle = new KonkreteTabellenAnsicht(modell);
    
    ansicht = new JFrame();
    ansicht.getContentPane().setLayout(new BoxLayout(ansicht.getContentPane(), BoxLayout.Y_AXIS));
    ansicht.getContentPane().setBackground(Color.WHITE);
    
    //oberes Panel//
    JPanel oben = new JPanel();
    oben.setLayout(new BorderLayout());
    oben.setBackground(Color.WHITE);
    
    menueKnopf = new Schaltflaeche("Menü", 2);
    menueKnopf.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          geheZuMenue();
        }
      });
    JPanel menuePanel = new JPanel();
    menuePanel.setLayout(new FlowLayout());
    menuePanel.add(menueKnopf);
    menuePanel.setBackground(Color.WHITE);
    
    JLabel text = new JLabel("Freier Modus      ", SwingConstants.CENTER);
    
    oben.add(menuePanel, BorderLayout.WEST);
    oben.add(text, BorderLayout.CENTER);

    //WahrheitstabellenPanel//
    JPanel tabellenPanel = new JPanel();
    tabellenPanel.setLayout(new BorderLayout());
    tabellenPanel.setBackground(Color.DARK_GRAY);
    tabellenPanel.add(new javax.swing.JLabel("TABELLE", SwingConstants.CENTER));
    tabellenPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE,1000));
    
    //evtl AussagenPanel(?)//

    //Ansicht zusammenfügen//
    ansicht.getContentPane().add(oben, 0);
    ansicht.getContentPane().add(tabellenPanel, 1);
    
    //Dialogfenster//
    atomareAussagen = new JDialog();
    atomareAussagen.getContentPane().setLayout(new BoxLayout(atomareAussagen.getContentPane(), BoxLayout.Y_AXIS));
    atomareAussagen.getContentPane().setBackground(Color.WHITE);
    
    aussagen = new JTextField[5];
    ((JComponent) atomareAussagen.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    JLabel aussagenHinweis = new JLabel("Wähle die Namen für bis zu 5 atomare Aussagen:");
    aussagenHinweis.setFont(new javax.swing.plaf.FontUIResource("Arial",Font.BOLD,18));
    aussagenHinweis.setForeground(Color.WHITE);
    atomareAussagen.add(aussagenHinweis, 0);
    for (int j = 0; j < aussagen.length; j++) {
      aussagen[j] = new JTextField("");
      atomareAussagen.getContentPane().add(aussagen[j],j + 1);
    }
    Schaltflaeche okButton = new Schaltflaeche("OK", Schaltflaeche.WEISS);
    okButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          for (int j = 0; j < aussagen.length; j++) {
            if (!aussagen[j].getText().equals("")) {
              aussagenListe.add(aussagen[j].getText());    //Was wenn nichts eingegeben (?)
            }
            initTabelle();
            atomareAussagen.dispose();
          }
        }
      });
    atomareAussagen.add(okButton);
    atomareAussagen.setTitle("Atomare Aussagen");
    atomareAussagen.setSize(600, 400);
    atomareAussagen.setResizable(true);
    atomareAussagen.setLocation(50, 50);
    atomareAussagen.setAlwaysOnTop(true);
    atomareAussagen.setModal(true);
    atomareAussagen.setModalityType(ModalityType.APPLICATION_MODAL);
    atomareAussagen.getContentPane().setBackground(new Color(255,102,0));
    atomareAussagen.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    atomareAussagen.setVisible(true);
  }

  private void initTabelle() {
    //TODO// Tabelle integrieren!!
    //modell.erstelleRaetsel(aussagenListe);    Verfügbar wenn ausführbar!!
  }

  private void geheZuMenue() {
    fv.oeffneMenue();
  }
  
}
