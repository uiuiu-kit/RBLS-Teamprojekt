package modell.raetsel;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import modell.formel.Formel;


/** Der Raetselinterpret liest die Ordnerstruktur aus 
 * und liefert darauf basierend Raetselnamen zurueck.
 * Selbige koennen angegeben werden, um nach einer Raetseltextdatei gleichen Namens zu suchen 
 * und diese als Reatselobjekt zurueckgeliefert zu bekommen. 
 * Außerdem ist der RInterpret fuer die Erstellung freier Raetsel(FR) zustaendig.
 * @author Flo
 *
 */
public class Raetselinterpret {

  public static final String ERROR_NO_FILE_FOUND = "Es wurde kein Rätsel gefunden";
  public static final String FREIES_RAETSEL_NAME = "Freies Rätsel";
  public static final int NUMBER_OF_ROWS = 8;
  
  private String[] extrahiere(String input) {
    String[] output = input.split("##");
    if (output.length == NUMBER_OF_ROWS) {
      return (output[7].matches("[+-]?\\d*(\\.\\d+)?") ?  output : null);
    }
    return null;
  }
  
  private List<String> exAtome(String input) {
    String[] sep = input.split(",");
    List<String> output = new ArrayList<String>();
    for (String temp: sep) {
      output.add(temp);
    }
    return output;
  }
  
  private List<File> gibRaetselausOrdner(int stufe) {
    List<File> output = new ArrayList<File>();
    File path = null;
    switch (stufe) {
      case 1: path = new File("src/main/resources/Raetsel/Stufe 1");
      break;
      case 2: path = new File("src/main/resources/Raetsel/Stufe 2");
      break;
      case 3: path = new File("src/main/resources/Raetsel/Stufe 3");
      break;
      case 4: path = new File("src/main/resources/Raetsel/Stufe 4");
      break;
      default: path = null;
    }
    File[] files = path.listFiles();
    if (files != null) { 
      for (int i = 0; i < files.length; i++) {
        //System.out.print(files[i]);
        if (files[i].isDirectory()) {
          //System.out.print(" (Ordner)\n");
        } else {
          output.add(files[i]);
          //System.out.println(" (Datei)\n");
        }
      }
    }
    return output;
  }
  
  /**Listet alle verfuegbaren Raetselnamen auf, die im Ordner 
   * src,main,resources,Raetsel unter der jeweiligen Stufe hinterlegt sind.
   * @param stufe Raetselstufe, nach deren Raetsel gesucht wird.
   * @return Liste aller Raetselnamen, die die genannte Stufe erfuellen.
   */
  public List<String> liesOrdner(int stufe) {
    List<String> output = new ArrayList<String>();
    for (File temp : gibRaetselausOrdner(stufe)) {
      output.add(temp.getName());
    }
    return output;
  }
  
  private File findeRaetsel(String titel) {
    for (int i = 1; i < 5; i++) {
      List<File> ordner = gibRaetselausOrdner(i);
      for (File raetsel : ordner) {
        if (raetsel.getName().equals(titel)) {
          return raetsel;
        }
      }
    }
    return null;
  }
  
  /**Sucht die angegebene Textdatei des Reatsels und liest deren Daten aus, 
   * speichert sie zwischen und erstellt dann ein Raetselobjekt.
   * @param titel Angegebener Name des Reatsels, aufgrund dessen die Raetseltextdatei gesucht wird.
   * @return Ein neues Raetselobjekt.
   */
  public Raetsel liesRaetsel(String titel) {
    List<String> rows = null;
    try {
      rows = Files.readAllLines(
          FileSystems.getDefault().getPath(findeRaetsel(titel).getAbsolutePath()), 
          StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }
    String text = "";
    for (String temp : rows) {
      text += temp;
    }
    String[] lines = extrahiere(text);
    List<String> atome = null;
    List<String> antwortM = new ArrayList<String>();
    for (String temp : lines[3].split(",")) {
      antwortM.add(temp);
    }
    atome = this.exAtome(lines[4]);
    return new Raetsel(
        lines[0],//spaltenAnz
        Integer.parseInt(lines[7]), //stufe
        atome,                      //Atome
        lines[1],                   //Raetseltext
        antwortM,                   //Antwortmöglichkeiten
        Integer.parseInt(lines[5]), //Wievielte Antwort die Lösung ist
        lines[2],                   //Antworttext
        new ArrayList<Formel>());   //Formelliste  
  }
  
  /** Hiermit wird ein Raetsel im freien Modus erstellt.
   * @param atomA Anzahl und Namen der verfügbaren Atomaren Aussagen, die der Benutzer angeben kann.
   */
  public Raetsel erstelleFR(List<String> atomA) {
    return new Raetsel(
        FREIES_RAETSEL_NAME,
        0, 
        atomA, 
        null, 
        null, 
        0, 
        null, 
        new ArrayList<Formel>());
  }
}
