package modell.raetsel;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse symbolisiert ein Memento. Sie verwaltet die Speicherung und das Abrufen der Raetsel
 * sowie deren Zustaende und erstellt eine Sicherungs-Textdatei.
 * @author janne
 *
 */
public class Memento {
  
  private Writer fw = null;
  private List<String> memento = null;
  private List<String> namenGeloesterRaetsel = null;
  private RaetselZustand zustand;
  
  /**
   * Konstruktor fuer ein Memento. Dieses speichert den aktuellen Spielstand,
   * also welches Rätsel zuletzt geloest wurde.
   * @param r Das Raetsel, welches gespeichert werden soll.
   */
  public Memento(Raetsel r) {
    this.zustand = new RaetselZustand(r);
    erstelleMementoDatei(zustand.gibStufe(), zustand.gibRaetselname());
  }
  
  public RaetselZustand gibSicherung() {
    return this.zustand;
  }
  
  public void loesche() {
    this.zustand = null;
  }
  
  /**
   * Quelle: http://openbook.rheinwerk-verlag.de/javainsel9/
   * javainsel_17_001.htm#mj87f7ea8c7b8051417049399df2c5782a
   * 
   * Erstellt eine Textdatei, welche den aktuellen Spielstand, sprich die höchste geloeste Stufe
   * und den Raetselnamen enthaelt.
   * @param stufe Die Stufe des geloesten Raetsels.
   * @param name Der Name des geloesten Raetsels.
   * @return True, wenn die Datei erfolgreich erstellt wurde.
   */
  public boolean erstelleMementoDatei(int stufe, String name) {
    liesMementoDatei();
    try {
      if (this.namenGeloesterRaetsel.size() > 0) {
        fw = new FileWriter("src/main/resources/Sicherung/Sicherung.txt");
        fw.write(stufe + "\n");       //die neue hoechste Stufe
        fw.write("##\n");             
        fw.write(name + "\n");        //der neu hinzugekommene Raetselname
        for (int i = 0; i <= namenGeloesterRaetsel.size(); i++) {
          fw.write(namenGeloesterRaetsel.get(i) + "\n"); //alle bisher geloesten Raetselnamen
        }
      } else {
        fw = new FileWriter("src/main/resources/Sicherung/Sicherung.txt");
        fw.write(stufe + "\n");
        fw.write("##\n");             
        fw.write(name + "\n");
      }
    } catch (IOException e) {
      System.err.println("Sicherung konnte nicht erstellt werden.");
    } finally {
      if (fw != null) {
        try {
          fw.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return true;
  }
  
  /**
   * Liest die Memento-Textdatei und schreibt den Inhalt in eine String-Liste. Erstellt außerdem
   * eine Liste nur mit den Namen der bisher geloesten Raetsel.
   */
  private void liesMementoDatei() {
    memento = new ArrayList<String>();      //Liste, mit allen Infos des Memento
    try {
      this.memento = Files.readAllLines(
          FileSystems.getDefault().getPath("src/main/resources/Sicherung/Sicherung.txt"), 
          StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }
    namenGeloesterRaetsel = new ArrayList<String>();
    if (memento.size() > 3) {
      for (int i = 3; i <= memento.size(); i++) {     //Liste nur mit den Raetselnamen
        this.namenGeloesterRaetsel.add(memento.get(i));
      }
    }
  }
}
