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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import modell.Fassade;
import steuerung.Hauptsteuerung;
import steuerung.WahrheitstabellenSteuerungen;

/**
 * Grafische Ansicht des freien Modus. Zeigt eine
 * Wahrheitstabelle an und st��t beim Klicken des Men�-Buttons den Wechsel der
 * Ansicht zum Hauptmen� an.
 * @author Nick
 */
public class FreiesRaetselFenster extends RaetselFenster {

  private List<String> aussagenListe = new ArrayList<String>();
  private JDialog atomareAussagen;
  private JTextField[] aussagen;
  
  private Fensterverwaltung fv;
  private Fassade modell;
  private Hauptsteuerung strg;

  /**
   * Erstellt die grafische Ansicht eines FreiesRaetselFenster mit n�tigen Buttons und Tabelle.
   * @param fensterverwaltung Fensterverwaltung zum Wechseln des Fensters
   * @param modell Praesentationsfassade
   *     zum Setzen und Erhalten von Informationen �ber atomare Aussagen und Status
   */
  public FreiesRaetselFenster(Fensterverwaltung fensterverwaltung,
      Fassade modell, Hauptsteuerung strg) {
    this.fv = fensterverwaltung;
    this.modell = modell;
    this.strg = strg;
    //this.tabelle = new KonkreteTabellenAnsicht(modell, wstrg);
    
    //Dialogfenster//
    atomareAussagen = new JDialog();
    atomareAussagen.getContentPane().setLayout(
        new BoxLayout(atomareAussagen.getContentPane(), BoxLayout.Y_AXIS));
    atomareAussagen.getContentPane().setBackground(Color.WHITE);
    
    aussagen = new JTextField[5];
    ((JComponent) atomareAussagen.getContentPane()).setBorder(
        BorderFactory.createEmptyBorder(10, 10, 10, 10));
    JLabel aussagenHinweis = new JLabel("W�hle die Namen f�r bis zu 5 atomare Aussagen:");
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
              aussagenListe.add(aussagen[j].getText());
            }
          }
          if (!aussagenListe.isEmpty()) {
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
    
    ansicht = new JFrame();
    ansicht.getContentPane().setLayout(new BoxLayout(ansicht.getContentPane(), BoxLayout.Y_AXIS));
    ansicht.getContentPane().setBackground(Color.WHITE);
    
    //oberes Panel//
    JPanel oben = new JPanel();
    oben.setLayout(new BorderLayout());
    oben.setBackground(Color.WHITE);
    
    menueKnopf = new Schaltflaeche("Men�", 2);
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
    tabellenPanel.add(tabelle.gibAnsicht());
    tabellenPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE,1000));
    tabellenPanel.setBackground(Color.WHITE);
    
    //Ansicht zusammenf�gen//
    ansicht.getContentPane().add(oben, 0);
    ansicht.getContentPane().add(tabellenPanel, 1);
    

  }

  private void initTabelle() {
    modell.erstelleRaetsel(aussagenListe);
    
    WahrheitstabellenSteuerungen wstrg;
    wstrg = strg.raetselFensterInit();
    tabelle = new KonkreteTabellenAnsicht(modell, wstrg);
  }

  private void geheZuMenue() {
    fv.oeffneMenue();
  }
  
}