package praesentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Grafische Ansicht des Raetselauswahlbildschirms einer Stufe.

 * @author Nick
 */
public class Raetselwahl extends javax.swing.JPanel {

  /**
   * auto-generierte ID.
   */
  private static final long serialVersionUID = -1514024121223846041L;
  private Fensterverwaltung fw;
  private Schaltflaeche zurueck;
  private Schaltflaeche[] buttons;
  private List<String> raetsel;
  private List<String> geloest;
  private int stufe;
  private String[] hinweise = new String[] { 
    "Dies ist die erste Stufe. Decke in der Wahrheitstabelle alle Fälle für die atomaren "
      + "Aussagen ab und klicke auf 'Fülle Tabelle'.",
    "Dies ist die zweite Stufe. Hier kannst Du die Wahrheitswerte nicht verändern,"
      + " aber neue Spalten mit Formeln hinzufügen.",
    "Dies ist die dritte Stufe. Hier füllt sich die Tabelle nicht selbst,"
      + " stattdessen gibst Du die Wahrheitswerte selbst ein.",
    "Dies ist die letzte Stufe. Hier gibt es verschiedene Arten"
      + " von Rätseln und die Wahrheitstabelle ist vollautomatisiert."
  };
  
  /**
   * Konstruktor.

   * @param fstr Fensterverwaltung zum Wechseln der Ansicht
   * @param liste Liste der anzuzeigenden Rätselnamen der Stufe
   * @param geloest Liste der Namen der gelösten Rätsel der Stufe
   */
  public Raetselwahl(Fensterverwaltung fstr, List<String> liste, List<String> geloest, int stufe) {
    this.fw = fstr;
    this.raetsel = liste;
    this.geloest = geloest;
    this.stufe = stufe;
    init();
  }

  /**
   * initialisiert GUI und Buttonaktionen.
   */
  private void init() {
    buttons = new Schaltflaeche[raetsel.size()];
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    buttonPanel.setBackground(new Color(255, 102, 0));
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    JLabel hinweis = new JLabel(hinweise[stufe - 1]);
    hinweis.setFont(new javax.swing.plaf.FontUIResource("Arial", Font.BOLD, 18));
    hinweis.setForeground(Color.WHITE);
    buttonPanel.add(hinweis, 0);
    buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    for (int j = 0; j < buttons.length; j++) {
      if (geloest.size() > 0 && geloest.contains(raetsel.get(j))) {
        buttons[j] = new Schaltflaeche(raetsel.get(j), 5);
      } else {
        buttons[j] = new Schaltflaeche(raetsel.get(j), 3);
      }
      buttons[j].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          waehleAus(e.getActionCommand());
        }
      });
      buttons[j].setMaximumSize(new Dimension(Integer.MAX_VALUE, 
          buttons[j].getMinimumSize().height * 2));
      buttonPanel.add(buttons[j], j + 2);
      buttonPanel.setBackground(new Color(255, 102, 0));
    }
    
    this.setLayout(new BorderLayout());
    this.add(buttonPanel, BorderLayout.CENTER);
    
    zurueck = new Schaltflaeche("ZUM MENÜ", 2);
    zurueck.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeZurueck();
      }
    });
    this.add(zurueck, BorderLayout.WEST);
  }

  /**
  * Stoesst Starten eines Raetsels an.

  * @param name Raetselname
  */
  private void waehleAus(String name) {
    fw.starteRaetsel(name);
  }

  /**
   * fuehrt zurueck zum Hauptmenue.
   */
  private void klickeZurueck() {
    fw.oeffneMenue();
  }

}