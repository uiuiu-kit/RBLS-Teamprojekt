package praesentation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Hauptmenue extends javax.swing.JFrame{
	private Fensterverwaltung fw;

	private JButton Stufe1;
    private JButton Stufe2;
    private JButton Stufe3;
    private JButton Stufe4;
    private JButton freierModus;
    private JButton beenden;
	
	public Hauptmenue(Fensterverwaltung fw) {	//Fassade fehlt
		this.fw = fw;
		init();}
    
	private void init() {
        Stufe1 = new javax.swing.JButton();
        Stufe2 = new javax.swing.JButton();
        Stufe3 = new javax.swing.JButton();
        Stufe4 = new javax.swing.JButton();
        freierModus = new javax.swing.JButton();
        beenden = new javax.swing.JButton();
 
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.FlowLayout(200,200,150));
        getContentPane().setBackground(Color.WHITE);
        
        freierModus.setText("FREIER MODUS");
        getContentPane().add(freierModus);
        
        Stufe1.setText("Stufe 1");
        Stufe1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fw.oeffneRaetselwahl(1);
            }
        });
        Stufe1.setBackground(new Color(255,102,0));
        Stufe1.setForeground(Color.WHITE);
        getContentPane().add(Stufe1);
        
        Stufe2.setText("Stufe 2");
        getContentPane().add(Stufe2);
 
        Stufe3.setText("Stufe 3");
        getContentPane().add(Stufe3);
 
        Stufe4.setText("Stufe 4");
        getContentPane().add(Stufe4);
 
        beenden.setText("BEENDEN");
        getContentPane().add(beenden);
 
        pack();
    }
}