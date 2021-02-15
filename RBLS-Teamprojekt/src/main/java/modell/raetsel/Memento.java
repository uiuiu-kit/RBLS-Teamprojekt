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

  private List<String> memento;
  private int abschlussStufe = 0;

  
  
  public Memento() {
    liesMementoDatei();
  }
  
  public int gibStufenSicherung() {
    this.liesMementoDatei();
    return this.abschlussStufe;
  }

  /** Löscht Daten von Memento: überschreibt textdatei, resettet Memento.
   * 
   */
  public void loesche() {
    Writer fw = null;
    try {
      fw = new FileWriter("Resources/Sicherung/Sicherung.txt");
    } catch (IOException e) {
      System.err.println("Sicherung konnte nicht gelöscht werden.");
    } finally {
      if (fw != null) {
        try {
          fw.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    this.memento = new ArrayList<String>();
  }

  /**
   * Quelle: http://openbook.rheinwerk-verlag.de/javainsel9/
   * javainsel_17_001.htm#mj87f7ea8c7b8051417049399df2c5782a
   * Erstellt eine Textdatei, welche den aktuellen Spielstand, sprich die höchste
   * geloeste Stufe und den Raetselnamen enthaelt.
   * 
   * @param stufe Die Stufe des geloesten Raetsels.
   * @param name  Der Name des geloesten Raetsels.
   * @return True, wenn die Datei erfolgreich erstellt wurde.
   */
  public boolean erstelleMementoDatei(Raetsel raetsel) {
    Writer fw = null;
    liesMementoDatei();
    if (istNeu(raetsel.gibName())) {
      memento.add(raetsel.gibName());
    }
    try {
      fw = new FileWriter("Resources/Sicherung/Sicherung.txt");
      if (raetsel.gibStufe() > this.abschlussStufe) {
        fw.write(raetsel.gibStufe() + "\n");  
      } else {
        fw.write(abschlussStufe + "\n");  
      }
      fw.write("##\n");             
      for (int i = 0; i < memento.size(); i++) {
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
    try {
      memento = extrahiere(Files.readAllLines(
          FileSystems.getDefault().getPath("Resources/Sicherung/Sicherung.txt"),
          StandardCharsets.UTF_8));
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (memento != null && !memento.isEmpty()) {
      this.abschlussStufe = Integer.parseInt(memento.get(0));
    }
    memento.remove(1);
    memento.remove(0);
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
  
  public List<String> gibGeloesteRaetsel() {
    liesMementoDatei();
    return memento;
  }
}
