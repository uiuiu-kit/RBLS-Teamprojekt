package praesentation;

import javax.swing.JFrame;
import steuerung.Hauptsteuerung;

/**Startet zu Beginn das Hauptmenü, wechselt die sichtbare Ansicht
 * und stößt das Beenden des Programms durch die Hauptsteuerung an.
 */
public class Fensterverwaltung {

  private Hauptsteuerung strg;
  private JFrame aktivesFenster;

  public Fensterverwaltung(Hauptsteuerung strg) { //braucht Fassade
    this.strg = strg;
  }

  /**
   * erstellt Hauptmenue und setzt Variablen.
   */
  public void init() {
    strg = new Hauptsteuerung();
    aktivesFenster = new Hauptmenue(this);
    aktivesFenster.setTitle("RBLS");
    aktivesFenster.setSize(1000, 620);
    aktivesFenster.setResizable(true);
    aktivesFenster.setLocation(50, 50);
    aktivesFenster.setVisible(true);
  }

  /**
   * wechselt zu Hauptmenue.
   */
  public void oeffneMenue() {
    aktivesFenster.setVisible(false);
    aktivesFenster = new Hauptmenue(this);
    aktivesFenster.setTitle("Hauptmenü");
    aktivesFenster.setSize(1000, 620);
    aktivesFenster.setResizable(true);
    aktivesFenster.setLocation(50, 50);
    aktivesFenster.setVisible(true);
  }

  /**
   * wechselt zu Raetselwahlfenster.
   * @param stufe Stufe der Raetsel
   */
  public void oeffneRaetselwahl(int stufe) {
    aktivesFenster.setVisible(false);
    aktivesFenster = new Raetselwahl(this, stufe); //Rätselliste aus Modell statt Stufe
    aktivesFenster.setTitle("Rätselwahl");
    aktivesFenster.setSize(1000, 620);
    aktivesFenster.setResizable(true);
    aktivesFenster.setLocation(50, 50);
    aktivesFenster.setVisible(true);
  }

  /**
   * wechselt zu Abschlussfenster.
   */
  public void oeffneAbschlussFenster() {
    aktivesFenster.setVisible(false);
    aktivesFenster = new Abschlussfenster(this);  // + Stufe (?!)
    aktivesFenster.setTitle("Abschlussfenster");
    aktivesFenster.setSize(1000, 620);
    aktivesFenster.setResizable(true);
    aktivesFenster.setLocation(50, 50);
    aktivesFenster.setVisible(true);
  }

  /**
   * startet Raetsel.
   * @param name Raetselname bzw Name der Datei
   */
  public void starteRaetsel(String name) {
    
  }

  /**
   * startet zufaelliges Raetsel aktueller Stufe.
   */
  public void starteZufaelligesRaetsel() {
    
  }

  /**
   * startet Freien Modus.
   */
  public void starteFreienModus() {
    
  }

  /**
   * stoeßt Beenden an.
   */
  public void beende() {
    
  }
  
}
