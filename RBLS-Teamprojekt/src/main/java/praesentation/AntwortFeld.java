package praesentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AntwortFeld {
  private JPanel ansicht;
  private Schaltflaeche pruefeKnopf;
  private JComboBox antwortWahl;

  private List<String> antwortMoeglichkeiten;
  private String text;
  private String loesung;

  public AntwortFeld(List<String> antworten, String text, String loesung) {
    this.text = text;
    this.antwortMoeglichkeiten = antworten;
    this.loesung = loesung;
    
    ansicht = new JPanel();
    ansicht.setLayout(new BorderLayout());
    ansicht.setBackground(Color.WHITE);
    
    ansicht.add(new javax.swing.JLabel("[Hier könnte Ihre Antwort stehen]", SwingConstants.CENTER));
  }

  public JPanel gibAnsicht() {
    return ansicht;
  }

  public boolean pruefeAntwort() {
    return true;  //Platzhalter!!
  }
}
