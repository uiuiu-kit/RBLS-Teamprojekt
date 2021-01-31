package praesentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import modell.raetsel.RaetselZustand;

/**
 * Grafische Ansicht des Rätselauswahlbildschirms einer Stufe.
 * @author Nick
 */
public class Raetselwahl extends javax.swing.JFrame {

  /**
   * auto-generierte ID.
   */
  private static final long serialVersionUID = -1514024121223846041L;
  private Fensterverwaltung fw;
  private Schaltflaeche zurueck;
  private Schaltflaeche[] buttons;
  private List<RaetselZustand> raetsel;
  
  /**
   * Konstruktor.
   * @param fstr Fensterverwaltung zum Wechseln der Ansicht
   * @param liste Stufe der anzuzeigenden Rätsel
   */
  public Raetselwahl(Fensterverwaltung fstr, List<RaetselZustand> liste) {
    this.fw = fstr;
    this.raetsel = liste;
    init();
  }

  /**
   * initialisiert GUI und Buttonaktionen.
   */
  private void init() {
    buttons = new Schaltflaeche[raetsel.size()];
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    buttonPanel.setBackground(new Color(255,102,0));
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    for (int j = 0; j < buttons.length; j++) {
      if (raetsel.get(j).geloest) {
        buttons[j] = new Schaltflaeche(raetsel.get(j).raetselname, 5);
      } else {
        buttons[j] = new Schaltflaeche(raetsel.get(j).raetselname, 3);
      }
      buttons[j].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          waehleAus(e.getActionCommand());
        }
      });
      buttons[j].setMaximumSize(new Dimension(Integer.MAX_VALUE, 
          buttons[j].getMinimumSize().height * 2));
      buttonPanel.add(buttons[j], j);
      buttonPanel.setBackground(new Color(255,102,0));
    }
    
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(buttonPanel, BorderLayout.CENTER);
    
    zurueck = new Schaltflaeche("ZUM MENÜ", 2);
    zurueck.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeZurueck();
      }
    });
    getContentPane().add(zurueck, BorderLayout.WEST);
    
    pack();
  }

  /**
  * stoesst Starten eines Raetsels an.
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