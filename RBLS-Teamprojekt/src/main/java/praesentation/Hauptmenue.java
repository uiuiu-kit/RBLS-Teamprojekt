package praesentation;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

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
        JPanel startFeld = new JPanel(new java.awt.FlowLayout());
        startFeld.setBackground(Color.WHITE);
        JPanel mitte = new JPanel(new java.awt.BorderLayout());
        mitte.setBackground(Color.WHITE);
        mitte.add(startFeld, java.awt.BorderLayout.SOUTH);
        mitte.add(new javax.swing.JLabel("Start"), java.awt.BorderLayout.NORTH);
 
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //c.fill = GridBagConstraints.HORIZONTAL;
        getContentPane().setBackground(Color.WHITE);
        
        Stufe1.setText("Stufe 1");
        Stufe1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fw.oeffneRaetselwahl(1);
            }
        });
        Stufe1.setBackground(Color.WHITE);
        Stufe1.setForeground(new Color(255,102,0));
        startFeld.add(Stufe1);
        
        Stufe2.setText("Stufe 2");
        Stufe2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fw.oeffneRaetselwahl(2);
            }
        });
        Stufe2.setBackground(Color.LIGHT_GRAY);
        Stufe2.setForeground(Color.DARK_GRAY);
        startFeld.add(Stufe2);
        
        Stufe3.setText("Stufe 3");
        Stufe3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fw.oeffneRaetselwahl(3);
            }
        });
        Stufe3.setBackground(Color.LIGHT_GRAY);
        Stufe3.setForeground(Color.DARK_GRAY);
        startFeld.add(Stufe3);
        
        Stufe4.setText("Stufe 4");
        Stufe4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fw.oeffneRaetselwahl(4);
            }
        });
        Stufe4.setBackground(Color.LIGHT_GRAY);
        Stufe4.setForeground(Color.DARK_GRAY);
        startFeld.add(Stufe4);
        
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 1;
        //c.fill = GridBagConstraints.HORIZONTAL;
        getContentPane().add(mitte, c);
        
        freierModus.setText("FREIER MODUS");
        beenden.setText("BEENDEN");
        freierModus.setBackground(new Color(255,102,0));
        freierModus.setForeground(Color.WHITE);
        beenden.setBackground(new Color(255,102,0));
        beenden.setForeground(Color.WHITE);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 80;
        c.ipady = 60;
        getContentPane().add(beenden, c);
        c.gridx = 4;
        c.gridy = 1;
        getContentPane().add(freierModus, c);
        

        
        pack();
    }
}