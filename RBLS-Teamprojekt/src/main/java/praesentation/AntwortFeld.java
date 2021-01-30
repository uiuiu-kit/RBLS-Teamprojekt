package praesentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AntwortFeld {
  private JPanel ansicht;
  private Schaltflaeche pruefeKnopf;
  private JComboBox antwortWahl;

  private List<String> antwortMoeglichkeiten;
  private String text = "[Hier könnte Ihre Antwort stehen]";
  private String loesung;

  public AntwortFeld(List<String> antworten, String text, String loesung) {
    
    /*Kommt wieder rein, sobald Programm ausführbar////////////
    this.text = text;  
    this.antwortMoeglichkeiten = antworten;
    this.loesung = loesung;
    */
    
    //Platzhalter//
    this.antwortMoeglichkeiten = Arrays.asList("foo", "bar");
    this.loesung = "bar";
    
    JPanel p = new JPanel();
    p.setLayout(new FlowLayout());
    p.setBackground(Color.WHITE);
    
    JLabel textLabel = new JLabel(this.text, SwingConstants.CENTER);
    textLabel.setFont(new javax.swing.plaf.FontUIResource("Arial",Font.PLAIN,18));
    
    antwortWahl = new JComboBox(antwortMoeglichkeiten.toArray());
    antwortWahl.setSelectedIndex(0);
    antwortWahl.setBackground(Color.WHITE);
    antwortWahl.setFont(new javax.swing.plaf.FontUIResource("Arial",Font.BOLD,18));
    
    p.add(antwortWahl);
    p.add(textLabel);
    
    pruefeKnopf = new Schaltflaeche("Prüfen");
    pruefeKnopf.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          pruefeAntwort();
        }
      });
    
    JPanel p2 = new JPanel();
    p2.setLayout(new FlowLayout());
    p2.setBackground(Color.WHITE);
    p2.add(pruefeKnopf);
    
    ansicht = new JPanel();
    ansicht.setLayout(new BorderLayout());
    ansicht.add(p, BorderLayout.CENTER);
    ansicht.add(p2, BorderLayout.EAST);
    
    
    
    
  }

  public JPanel gibAnsicht() {
    return ansicht;
  }

  public boolean pruefeAntwort() {
    return true;  //Platzhalter!!
  }
}
