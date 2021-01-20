package praesentation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Raetselwahl extends javax.swing.JFrame {
	
	private Fensterverwaltung fw;
	private JButton zurueck;
	private JButton[] buttons;
	private int stufe = 1; //Platzhalter Stufe
	private int raetselAnzahl = 5; //Platzhalter
	
	public Raetselwahl(Fensterverwaltung fstr, int stufe) {	//stattdessen Rätselnamenliste
		this.fw = fstr;
		this.stufe = stufe;
		init();
	}
	
	private void init() {
		buttons = new JButton[raetselAnzahl];
		
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.FlowLayout());
        getContentPane().setBackground(new Color(255,102,0));
 
        for (int j = 0; j < buttons.length; j++) {
        	buttons[j] = new JButton();
        	buttons[j].setText("Rätsel " + (j+1)); //Platzhalter Rätselname
        	buttons[j].addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		fw.starteRaetsel(e.getActionCommand());
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
        
        pack();
    }
	
	private void klickeZurueck() {
		fw.oeffneMenue();
	}
	
}
