package praesentation;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Grafische Ansicht des Rätselauswahlbildschirms einer Stufe.
 */
public class Raetselwahl extends javax.swing.JFrame {

  /**
   * auto-generierte ID.
   */
  private static final long serialVersionUID = -1514024121223846041L;
  private Fensterverwaltung fw;
  private Schaltflaeche zurueck;
  private Schaltflaeche[] buttons;
  private int stufe = 1; //Platzhalter Stufe
  private int raetselAnzahl = 5; //Platzhalter
  
  private Schaltflaeche debugAbschluss = new Schaltflaeche("[Test Abschlussfenster]", 4); //MUSS WEG
  
  /**
   * Konstruktor.
   * @param fstr Fensterverwaltung zum Wechseln der Ansicht
   * @param stufe Stufe der anzuzeigenden Rätsel
   */
  public Raetselwahl(Fensterverwaltung fstr, int stufe) { //stattdessen Rätselnamenliste
    this.fw = fstr;
    this.stufe = stufe;
    init();
  }

  /**
   * initialisiert GUI und Buttonaktionen.
   */
  private void init() {
    buttons = new Schaltflaeche[raetselAnzahl]; //Platzhalter, stattdessen Liste
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    buttonPanel.setBackground(new Color(255,102,0));
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    for (int j = 0; j < buttons.length; j++) {
      buttons[j] = new Schaltflaeche("Rätsel " + (j + 1), 3); //Platzhalter Rätselname
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

    //kommt wieder raus (!)
    debugAbschluss.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fw.oeffneAbschlussFenster();
      }
    });
    getContentPane().add(debugAbschluss, BorderLayout.SOUTH);
    
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

