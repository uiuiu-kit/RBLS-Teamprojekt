package praesentation;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * GUI Raetselwahl
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
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
    buttonPanel.setBackground(new Color(255,102,0));
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    for (int j = 0; j < buttons.length; j++) {
      buttons[j] = new JButton();
      buttons[j].setText("Rätsel " + (j + 1)); //Platzhalter Rätselname
      buttons[j].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          waehleAus(e.getActionCommand());
        }
      });
      buttons[j].setMaximumSize(new Dimension(Integer.MAX_VALUE, buttons[j].getMinimumSize().height*2));
      buttons[j].setBackground(Color.WHITE);
      buttons[j].setForeground(new Color(255,102,0));
      buttonPanel.add(buttons[j], j);
      buttonPanel.setBackground(new Color(255,102,0));
    }
    
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(buttonPanel, BorderLayout.CENTER);
    
    zurueck = new JButton();
    zurueck.setText("ZUM MENÜ");
    zurueck.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        klickeZurueck();
      }
    });
    zurueck.setBackground(Color.LIGHT_GRAY);
    zurueck.setForeground(Color.DARK_GRAY);
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

