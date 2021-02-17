package praesentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Grafische Ansicht eines Antwortfeldes. Zeigt einen Antworttext und eine
 * Auswahl der Antwortm�glichkeiten durch eine JComboBox an und vergleicht beim
 * Klicken des Pr�fe-Buttons den ausgew�hlten Wert mit der tats�chlichen L�sung.

 * @author Nick
 */
public class AntwortFeld {
  private JPanel ansicht;
  private Schaltflaeche pruefeKnopf;
  private JComboBox<String> antwortWahl;
  private StufenRaetselFenster fenster;

  private String[] antwortMoeglichkeiten;
  private String text = "[Antworttext]";
  private String loesung;

  /**
   * Konstruktor, erstellt ein Antwortfeld mit den angegebenen Inhalten.

   * @param antworten Antwortmoeglichkeiten als Liste
   * @param text      Text des Antwortsatzes
   * @param loesung   richtige Antwortmoeglichkeit, soll in der Liste enthalten
   *                  sein
   * @param fenster   StufenRaetselFenster, welches das Antwortfeld benoetigt
   */
  public AntwortFeld(String[] antworten, 
      String text, String loesung, StufenRaetselFenster fenster) {

    this.text = text;
    this.antwortMoeglichkeiten = antworten;
    this.loesung = loesung;

    this.fenster = fenster;
    JPanel p = new JPanel();
    p.setLayout(new FlowLayout());
    p.setBackground(Color.WHITE);

    JLabel textLabel = new JLabel(this.text, SwingConstants.CENTER);
    textLabel.setFont(new javax.swing.plaf.FontUIResource("Arial", Font.PLAIN, 18));

    antwortWahl = new JComboBox<String>(antwortMoeglichkeiten);
    antwortWahl.setSelectedIndex(0);
    antwortWahl.setBackground(Color.WHITE);
    antwortWahl.setFont(new javax.swing.plaf.FontUIResource("Arial", Font.BOLD, 18));

    p.add(antwortWahl);
    p.add(textLabel);

    pruefeKnopf = new Schaltflaeche("Pr�fen");
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

  private void pruefeAntwort() {
    if (antwortWahl.getSelectedItem().equals(loesung)) {
      fenster.schliesseRaetselAb();
      antwortWahl.setEnabled(false);
    } else {
      // TODO evtl Dialogfenster
    }
  }

}