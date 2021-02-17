package praesentation;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import modell.Fassade;
import steuerung.Hauptsteuerung;
import steuerung.WahrheitstabellenSteuerungen;

/**Startet zu Beginn das Hauptmen�, wechselt die sichtbare Ansicht
 * und st��t das Beenden des Programms durch die Hauptsteuerung an.

 * @author Nick
 */
public class Fensterverwaltung {

  private Hauptsteuerung strg;
  private JFrame aktivesFenster;
  private Fassade modell;
  private int[] fensterMass = new int[]{1280, 720};
  private int[] minimumMass = new int[]{1152, 648};
  ImageIcon img = new ImageIcon(getClass().getResource("/Icon/testIcon.png"));

  public Fensterverwaltung(Hauptsteuerung strg, Fassade fsd) {
    this.strg = strg;
    this.modell = fsd;
  }

  /**
   * Erstellt ein Hauptmenue und setzt Variablen.
   */
  public void init() {
    UIManager.put("Button.font", 
        new javax.swing.plaf.FontUIResource("Arial Unicode MS", Font.BOLD, 20));
    UIManager.put("Label.font", 
        new javax.swing.plaf.FontUIResource("Arial Unicode MS", Font.BOLD, 40));
    UIManager.put("TextArea.font", 
        new javax.swing.plaf.FontUIResource("Arial Unicode MS", Font.PLAIN, 18));
    UIManager.put("Table.font", new Font("Arial Unicode MS", Font.BOLD, 20));

    aktivesFenster = new Hauptmenue(this);
    aktivesFenster.setTitle("RBLS");
    aktivesFenster.setSize(fensterMass[0], fensterMass[1]);
    aktivesFenster.setMinimumSize(new Dimension(minimumMass[0], minimumMass[1]));
    aktivesFenster.setResizable(true);
    aktivesFenster.setLocation(50, 50);
    aktivesFenster.setVisible(true);
    aktivesFenster.setIconImage(img.getImage());
  }

  /**
   * Wechselt zum Hauptmenue.
   */
  public void oeffneMenue() {
    wechseleFenster(new Hauptmenue(this), "RBLS");
  }

  /**
   * Wechselt zum Raetselwahlfenster.

   * @param stufe Stufe der Raetsel
   */
  public void oeffneRaetselwahl(int stufe) {
    wechseleFenster(new Raetselwahl(this, modell.gibRaetselListe(stufe), 
        modell.gibGeloesteRaetsel(stufe)), "Rätselwahl");
  }

  /**
   * Wechselt zum Abschlussfenster.
   */
  public void oeffneAbschlussFenster() {
    wechseleFenster(new Abschlussfenster(this), "Abschlussfenster");
  }

  /**
   * Startet ein Raetsel.

   * @param name Raetselname bzw Name der Datei
   */
  public void starteRaetsel(String name) {
    modell.setzeRaetsel(name);
    WahrheitstabellenSteuerungen wstrg;
    wstrg = strg.raetselFensterInit();
    wechseleFenster(new StufenRaetselFenster(this, modell, wstrg).ansicht, "RBLS");    
  }

  /**
   * Startet ein zufaelliges Raetsel der aktuellen Stufe.
   */
  public void starteZufaelligesRaetsel() {
    List<String> liste = modell.gibRaetselListe(modell.gibStufe());
    for (Iterator<String> i = liste.iterator(); i.hasNext(); ) {
      String raetsel = i.next();
      if (modell.gibGeloesteRaetsel(modell.gibStufe()).contains(raetsel) == false) {
        starteRaetsel(raetsel);
        return;
      } else {
        oeffneRaetselwahl(modell.gibStufe());
      }
    }
  }

  /**
   * Startet den Freien Modus.
   */
  public void starteFreienModus() {
    wechseleFenster(new FreiesRaetselFenster(this, modell, strg).ansicht, "RBLS");
  }

  /**
   * Stoesst das Beenden an.
   */
  public void beende() {
    aktivesFenster.setVisible(false);
    strg.beenden();
    System.exit(0);
  }
  
  /**
   * Teilt der Hauptsteuerung das Loesen des aktiven Raetsels mit und oeffnet ein Abschlussfenster.
   */
  public void erledigeRaetsel() {
    strg.raetselGeloest();
    oeffneAbschlussFenster();
  }
  
  private void wechseleFenster(JFrame fenster, String titel) {
    aktivesFenster.setVisible(false);
    aktivesFenster = fenster;
    aktivesFenster.setTitle(titel);
    aktivesFenster.setSize(fensterMass[0], fensterMass[1]);
    aktivesFenster.setMinimumSize(new Dimension(minimumMass[0], minimumMass[1]));
    aktivesFenster.setResizable(true);
    aktivesFenster.setLocation(50, 50);
    aktivesFenster.setIconImage(img.getImage());
    aktivesFenster.setVisible(true);
  }
  
}