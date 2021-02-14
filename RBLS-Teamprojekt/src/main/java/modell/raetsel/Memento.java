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
  
  public static final String PATH = "/src/main/resources/Sicherung/Sicherung.txt";
  private Writer fw = null;
  private RaetselZustand zustand;
  
  /**
   * Konstruktor fuer ein Memento. Dieses speichert den aktuellen Spielstand,
   * also welches R�tsel zuletzt geloest wurde.
   * @param r Das Raetsel, welches gespeichert werden soll.
   */
  public Memento (Raetsel r) {
    this.zustand = new RaetselZustand(r);
    erstelleMementoDatei(zustand.gibStufe(), zustand.gibRaetselname());
  }
  
  public RaetselZustand gibSicherung() {
    return this.zustand;
  }
  
  public void loesche() {
    this.zustand = null;
  }
  
  public boolean erstelleMementoDatei(int stufe, String name) {
    try {
      fw = new FileWriter("src/main/resources/Sicherung/Sicherung.txt");
      fw.write(stufe + "\n");
      fw.write("##\n");
      fw.write(name + "\n");
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
