package praesentation;

import java.awt.Font;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.UIManager;

import modell.PraesentationFassade;
import modell.raetsel.RaetselZustand;
import steuerung.Hauptsteuerung;

/**Startet zu Beginn das Hauptmenü, wechselt die sichtbare Ansicht
 * und stößt das Beenden des Programms durch die Hauptsteuerung an.
 */
public class Fensterverwaltung {

  private Hauptsteuerung strg;
  private JFrame aktivesFenster;
  private PraesentationFassade modell;

  public Fensterverwaltung(Hauptsteuerung strg, PraesentationFassade fsd) {
    this.strg = strg;
    this.modell = fsd;
  }

  /**
   * erstellt Hauptmenue und setzt Variablen.
   */
  public void init() {
    UIManager.put("Button.font", new javax.swing.plaf.FontUIResource("Arial",Font.BOLD,20));
    UIManager.put("Label.font", new javax.swing.plaf.FontUIResource("Arial",Font.BOLD,40));
	
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
    aktivesFenster.setTitle("RBLS");
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
    aktivesFenster = new Raetselwahl(this, modell.gibRaetselListe(stufe));
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
    strg.raetselFensterInit(name);
    aktivesFenster.setVisible(false);
    aktivesFenster = new StufenRaetselFenster(this, modell).ansicht;
    aktivesFenster.setTitle("RBLS");
    aktivesFenster.setSize(1000, 620);
    aktivesFenster.setResizable(true);
    aktivesFenster.setLocation(50, 50);
    aktivesFenster.setVisible(true);
    
  }

  /**
   * startet zufaelliges Raetsel aktueller Stufe.
   */
  public void starteZufaelligesRaetsel() {
    List<RaetselZustand> liste = modell.gibRaetselListe(modell.gibAktuelleStufe());
    for (Iterator<RaetselZustand> i = liste.iterator(); i.hasNext(); ) {
      RaetselZustand raetsel = i.next();
      if (raetsel.geloest == false) {
        starteRaetsel(raetsel.raetselname);
        return;
      }
    }
  }

  /**
   * startet Freien Modus.
   */
  public void starteFreienModus() {
    strg.raetselFensterInit("").ansicht;
    aktivesFenster.setVisible(false);
    aktivesFenster = new FreiesRaetselFenster(this, modell);
    aktivesFenster.setTitle("RBLS");
    aktivesFenster.setSize(1000, 620);
    aktivesFenster.setResizable(true);
    aktivesFenster.setLocation(50, 50);
    aktivesFenster.setVisible(true);
  }

  /**
   * stoeßt Beenden an.
   */
  public void beende() {
    aktivesFenster.setVisible(false);
    strg.beende();
    System.exit(0);
  }
  
  /**
   * teilt Hauptsteuerung das Lösen des aktiven Raetsels mit.
   */
  public void erledigeRaetsel() {
    strg.raetselGeloest();
  }
  
}
