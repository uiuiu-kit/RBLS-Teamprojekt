package praesentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import modell.PraesentationFassade;

public class StufenRaetselFenster extends RaetselFenster {

  private String frage = "Rätsel #24: \n"
  		+ "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor\n"
  		+ " invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et \n"
  		+ "justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lore\n"
  		+ "m ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy \neirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.\n Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.\n"  		+ "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor\n"
  		+ " invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.";
  private JTextArea frageFeld;
  private JPanel antwortAnsicht;
  private Schaltflaeche tipp;
  
  private Fensterverwaltung fv;
  private PraesentationFassade modell;

  public StufenRaetselFenster(Fensterverwaltung fensterverwaltung, PraesentationFassade modell) {
    this.fv = fensterverwaltung;
    this.modell = modell;
    
    ansicht = new JFrame();
    ansicht.getContentPane().setLayout(new BoxLayout(ansicht.getContentPane(), BoxLayout.Y_AXIS));
    ansicht.getContentPane().setBackground(Color.WHITE);
    
    JPanel fragePanel = new JPanel();
    JPanel tabellenPanel = new JPanel();
    JPanel antwortPanel = new JPanel();
    
    //FragePanel//
    fragePanel.setLayout(new BorderLayout());
    fragePanel.setBackground(Color.WHITE);
    
    menueKnopf = new Schaltflaeche("Menü", 2);
    menueKnopf.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          geheZuRaetselwahlMenue();
        }
      });
    JPanel menuePanel = new JPanel();
    menuePanel.setLayout(new FlowLayout());
    menuePanel.add(menueKnopf);
    menuePanel.setBackground(Color.WHITE);
    
    tipp = new Schaltflaeche("Tipp", 2);
    tipp.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          zeigeTippAn();
        }
      });
    JPanel tippPanel = new JPanel();
    tippPanel.setLayout(new FlowLayout());
    tippPanel.add(tipp);
    tippPanel.setBackground(Color.WHITE);
    
    frageFeld = new JTextArea(frage);
    
    JPanel frageFeldPanel = new JPanel();
    frageFeldPanel.setLayout(new BoxLayout(frageFeldPanel,BoxLayout.X_AXIS));
    Border frageBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    frageFeldPanel.setBorder(frageBorder);
    frageFeldPanel.add(frageFeld);
    frageFeldPanel.setBackground(new Color(255,102,0));
    
    JPanel frageFeldPanelPanel = new JPanel();
    frageFeldPanelPanel.setLayout(new BoxLayout(frageFeldPanelPanel,BoxLayout.X_AXIS));
    Border fragePanelBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    frageFeldPanelPanel.setBorder(fragePanelBorder);
    frageFeldPanelPanel.add(frageFeldPanel);
    frageFeldPanelPanel.setBackground(Color.WHITE);
    
    fragePanel.add(menuePanel, BorderLayout.WEST);
    fragePanel.add(frageFeldPanelPanel, BorderLayout.CENTER);
    fragePanel.add(tippPanel, BorderLayout.EAST);
    
    //WahrheitstabellenPanel//
    tabellenPanel.setLayout(new BorderLayout());
    tabellenPanel.setBackground(Color.DARK_GRAY);
    tabellenPanel.add(new javax.swing.JLabel("TABELLE", SwingConstants.CENTER));
    
    //AntwortfeldPanel//
    antwortPanel.setLayout(new BorderLayout());
    antwortPanel.setBackground(Color.WHITE);
    antwortPanel.add(new javax.swing.JLabel("[Hier könnte Ihre Antwort stehen]", SwingConstants.CENTER));
    
    //Ansicht zusammenfügen//
    ansicht.getContentPane().add(fragePanel, 0);
    ansicht.getContentPane().add(tabellenPanel, 1);
    ansicht.getContentPane().add(antwortPanel, 2);
  }

  private void zeigeTippAn() {
    
  }

  private void geheZuRaetselwahlMenue() {
    //fv.oeffneRaetselwahl(modell.gibAktuelleStufe());
    fv.oeffneMenue();
  }

  public void schliesseRaetselAb() {
    fv.erledigeRaetsel();
  }

}
