package modell.raetsel;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Diese Klasse symbolisiert ein Memento. Sie verwaltet die Speicherung und das Abrufen der Raetsel
 * sowie deren Zustaende.
 * @author janne
 *
 */
public class Memento {
  
  public static final String PATH = "/src/main/resources/Sicherung";
  private Writer fw = null;
  private RaetselZustand zustand;
  
  /**
   * Konstruktor fuer ein Memento. Dieses speichert den aktuellen Spielstand,
   * also welches Rätsel zuletzt geloest wurde.
   * @param r Das Raetsel, welches gespeichert werden soll.
   */
  public Memento (Raetsel r) {
    this.zustand = new RaetselZustand(r);
  }
  
  public RaetselZustand gibSicherung() {
    return this.zustand;
  }
  
  public void loesche() {
    this.zustand = null;
  }
  
  public boolean erstelleMementoDatei(Raetsel r) {
    try {
      fw = new FileWriter("Sicherung.txt");
      fw.write(r.gibName());
      fw.append(System.getProperty("line.seperator"));
    }
    catch (IOException e) {
      System.err.println("Sicherung konnte nicht erstellt werden.");
    }
    finally {
      if (fw != null) {
        try {
          fw.close();
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return true;
  }
}
