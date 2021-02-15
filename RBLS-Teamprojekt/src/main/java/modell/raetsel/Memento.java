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
 * Diese Klasse symbolisiert ein Memento. Sie verwaltet die Speicherung und das
 * Abrufen der Raetsel sowie deren Zustaende und erstellt eine
 * Sicherungs-Textdatei.
 * 
 * @author janne
 *
 */
public class Memento {

  private Writer fw = null;
  private List<String> memento;
  private RaetselZustand zustand;
  private int abschlussStufe = 0;

  /**
   * Konstruktor fuer ein Memento. Dieses speichert den aktuellen Spielstand, also
   * welches Rätsel zuletzt geloest wurde.
   * 
   * @param r Das Raetsel, welches gespeichert werden soll.
   */
  public Memento(Raetsel r) {
    this.zustand = new RaetselZustand(r);
    erstelleMementoDatei(zustand.gibStufe(), zustand.gibRaetselname());
  }

  public RaetselZustand gibSicherung() {
    this.liesMementoDatei();
    // TODO umändern zu Rückgabewett Liste<Raetselzustand>
    return this.zustand;
  }

  public int gibStufenSicherung() {
    this.liesMementoDatei();
    return this.abschlussStufe;
  }

  public void loesche() {
    this.zustand = null;
  }

  /**
   * Quelle: http://openbook.rheinwerk-verlag.de/javainsel9/
   * javainsel_17_001.htm#mj87f7ea8c7b8051417049399df2c5782a
   * 
   * Erstellt eine Textdatei, welche den aktuellen Spielstand, sprich die höchste
   * geloeste Stufe und den Raetselnamen enthaelt.
   * 
   * @param stufe Die Stufe des geloesten Raetsels.
   * @param name  Der Name des geloesten Raetsels.
   * @return True, wenn die Datei erfolgreich erstellt wurde.
   */
  public boolean erstelleMementoDatei(int stufe, String name) {
    liesMementoDatei();
    if (istNeu(name)) {
      memento.add(name);
    }
    try {
      fw = new FileWriter("src/main/resources/Sicherung/Sicherung.txt");
      if (stufe > this.abschlussStufe) {
        fw.write(stufe + "\n");  
      } else {
        fw.write(abschlussStufe + "\n");  
      }
      fw.write("##\n");             
      for (int i = 0; i <= memento.size(); i++) {
        fw.write(memento.get(i) + "\n"); //alle bisher geloesten Raetselnamen
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
  
  private boolean istNeu(String name) {
    boolean output = true;
    for (String temp : memento) {
      if (temp.equals(name)) {
        output = false;
      }
    }
    return output;
  }

  /**
   * Liest die Memento-Textdatei und schreibt den Inhalt in eine String-Liste.
   * Erstellt außerdem eine Liste nur mit den Namen der bisher geloesten Raetsel.
   */
  private void liesMementoDatei() {
    // memento = new ArrayList<String>(); unnötig,
    // da memento 2 Zeilen weiter gleich wieder gesetzt wird.
    try {
      memento = extrahiere(Files.readAllLines(
          FileSystems.getDefault().getPath("src/main/resources/Sicherung/Sicherung.txt"),
          StandardCharsets.UTF_8));
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.abschlussStufe = Integer.parseInt(memento.get(1));
    // namenGeloesterRaetsel = new ArrayList<String>();
    memento.remove(1);
    memento.remove(0);
    // if (memento.size() > 3) {
    // for (int i = 3; i <= memento.size(); i++) { //Liste nur mit den Raetselnamen
    // this.namenGeloesterRaetsel.add(memento.get(i));
    // }
    // }
  }

  private List<String> extrahiere(List<String> input) {
    List<String> output = new ArrayList<String>();
    for (String temp : input) {
      if (!temp.equals("##")) {
        output.add(temp);
      }
    }
    return output;
  }
}
