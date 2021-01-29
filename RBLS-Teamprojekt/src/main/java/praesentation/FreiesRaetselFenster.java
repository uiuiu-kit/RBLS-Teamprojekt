package praesentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import modell.PraesentationFassade;

public class FreiesRaetselFenster extends RaetselFenster {

  private List<String> aussagenListe;
  private JFrame atomareAussagen;
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
  }

  private void geheZuMenue() {
    fv.oeffneMenue();
  }
  
}
