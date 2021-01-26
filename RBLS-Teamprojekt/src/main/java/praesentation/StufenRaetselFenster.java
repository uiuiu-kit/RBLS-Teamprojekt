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

import modell.PraesentationFassade;

public class StufenRaetselFenster extends RaetselFenster {

  private String frage = "Rätsel #24: \n"
  		+ "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor\n"
  		+ " invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et \n"
  		+ "justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lore\n"
  		+ "m ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."  		+ "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor\n"
  		+ " invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et \n"
  		+ "justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum do\n"
  		+ "lor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."
  		+ "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor\n"
  		+ " invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et \n"
  		+ "justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. \n"
  		+ "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
  private JTextArea frageFeld;
  private JPanel antwortAnsicht;
  private Schaltflaeche tipp;
  
  private Fensterverwaltung fv;
  private PraesentationFassade modell;

  public StufenRaetselFenster(Fensterverwaltung fensterverwaltung, PraesentationFassade modell) {
    ansicht = new JFrame();
    ansicht.getContentPane().setLayout(new BoxLayout(ansicht.getContentPane(), BoxLayout.Y_AXIS));
    ansicht.getContentPane().setBackground(Color.WHITE);
    
    JPanel fragePanel = new JPanel();
    JPanel tabellenPanel = new JPanel();
    JPanel antwortPanel = new JPanel();
    
    fragePanel.setLayout(new BorderLayout());
    fragePanel.setBackground(Color.WHITE);
    menueKnopf = new Schaltflaeche("Menü", 2);
    menueKnopf.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          geheZuRaetselwahlMenue();
        }
      });
    tipp = new Schaltflaeche("Tipp", 2);
    frageFeld = new JTextArea(frage);
    fragePanel.add(menueKnopf, BorderLayout.WEST);
    fragePanel.add(frageFeld, BorderLayout.CENTER);
    fragePanel.add(tipp, BorderLayout.EAST);
    
    tabellenPanel.setLayout(new BorderLayout());
    tabellenPanel.setBackground(Color.CYAN);
    tabellenPanel.add(new javax.swing.JLabel("[HIER IST EINE WAHRHEITSTABELLE]", SwingConstants.CENTER));
    
    antwortPanel.setLayout(new BorderLayout());
    antwortPanel.setBackground(Color.YELLOW);
    
    ansicht.getContentPane().add(fragePanel, 0);
    ansicht.getContentPane().add(tabellenPanel, 1);
    ansicht.getContentPane().add(antwortPanel, 2);
  }

  private void zeigeTippAn() {
    
  }

  private void geheZuRaetselwahlMenue() {
    fv.oeffneRaetselwahl(modell.gibAktuelleStufe());
  }

  public void schliesseRaetselAb() {
    
  }

}
