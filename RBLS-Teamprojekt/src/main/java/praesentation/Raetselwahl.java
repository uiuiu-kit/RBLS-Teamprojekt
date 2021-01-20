package praesentation;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * 
 * @author Nick Terzer
 *
 */
public class Raetselwahl extends javax.swing.JFrame {
	
  private Fensterverwaltung fw;
  private JButton zurueck;
  private JButton[] buttons;
  private int stufe = 1; //Platzhalter Stufe
  private int raetselAnzahl = 5; //Platzhalter
  
  private JButton debugAbschluss = new JButton("debug Test Abschlussfenster"); //MUSS WEG
	
  public Raetselwahl(Fensterverwaltung fstr, int stufe) {	//stattdessen Rätselnamenliste
    this.fw = fstr;
    this.stufe = stufe;
    init();
  }
	
  /**
   * initialisiert GUI und Buttonaktionen
   */
  private void init() {

    buttons = new JButton[raetselAnzahl];	//Platzhalter, stattdessen Liste
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    getContentPane().setLayout(new java.awt.FlowLayout());
    getContentPane().setBackground(new Color(255,102,0));
    for (int j = 0; j < buttons.length; j++) {
      buttons[j] = new JButton();
      buttons[j].setText("Rätsel " + (j + 1)); //Platzhalter Rätselname
      buttons[j].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          waehleAus(e.getActionCommand());
        }
      });
      getContentPane().add(buttons[j]);
    }
        
    zurueck = new JButton();
    zurueck.setText("ZUM MENÜ");
    zurueck.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeZurueck();
      }
    });
    zurueck.setBackground(Color.LIGHT_GRAY);
    zurueck.setForeground(Color.DARK_GRAY);
    getContentPane().add(zurueck);

    //kommt wieder raus (!)
    debugAbschluss.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fw.oeffneAbschlussFenster();
      }
    });
    getContentPane().add(debugAbschluss);
        
    pack();
  }
	
  /**
  * stoesst Starten eines Raetsels an
  * @param name Raetselname
  */
  private void waehleAus(String name) {
    fw.starteRaetsel(name);
  }
	
  /**
   * fuehrt zurueck zum Hauptmenue
   */
  private void klickeZurueck() {
    fw.oeffneMenue();
  }
	
}

