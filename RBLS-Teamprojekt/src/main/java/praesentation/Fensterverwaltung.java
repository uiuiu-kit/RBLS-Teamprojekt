package praesentation;

import java.awt.Font;
import java.util.Iterator;
import java.util.List;
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
  private int[] fensterMass = new int[]{1000, 620};

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
    aktivesFenster.setSize(fensterMass[0], fensterMass[1]);
    aktivesFenster.setResizable(true);
    aktivesFenster.setLocation(50, 50);
    aktivesFenster.setVisible(true);
  }

  /**
   * wechselt zu Hauptmenue.
   */
  public void oeffneMenue() {
    wechseleFenster(new Hauptmenue(this), "RBLS");
  }

  /**
   * wechselt zu Raetselwahlfenster.
   * @param stufe Stufe der Raetsel
   */
  public void oeffneRaetselwahl(int stufe) {
    wechseleFenster(new Raetselwahl(this, modell.gibRaetselListe(stufe)), "Rätselwahl");
  }

  /**
   * wechselt zu Abschlussfenster.
   */
  public void oeffneAbschlussFenster() {
    wechseleFenster(new Abschlussfenster(this), "Abschlussfenster");
  }

  /**
   * startet Raetsel.
   * @param name Raetselname bzw Name der Datei
   */
  public void starteRaetsel(String name) {
    strg.raetselFensterInit(name);
    wechseleFenster(new StufenRaetselFenster(this, modell).ansicht, "RBLS");    
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
    strg.raetselFensterInit("");
    wechseleFenster(new FreiesRaetselFenster(this, modell).ansicht, "RBLS");
  }

  /**
   * stoeßt Beenden an.
   */
  public void beende() {
    aktivesFenster.setVisible(false);
    strg.beenden();
    System.exit(0);
  }
  
  /**
   * teilt Hauptsteuerung das Lösen des aktiven Raetsels mit.
   */
  public void erledigeRaetsel() {
    strg.raetselGeloest();
  }
  
  private void wechseleFenster(JFrame fenster, String titel) {
    aktivesFenster.setVisible(false);
    aktivesFenster = fenster;
    aktivesFenster.setTitle("titel");
    aktivesFenster.setSize(fensterMass[0], fensterMass[1]);
    aktivesFenster.setResizable(true);
    aktivesFenster.setLocation(50, 50);
    aktivesFenster.setVisible(true);
  }
}
