package modell.raetsel;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


/** Der Raetselinterpret liest die Ordnerstruktur aus 
 * und liefert darauf basierend Raetselnamen zurueck.
 * Selbige koennen angegeben werden, um nach einer Raetseltextdatei gleichen Namens zu suchen 
 * und diese als Reatselobjekt zurueckgeliefert zu bekommen. 
 * Außerdem ist der RInterpret fuer die Erstellung freier Raetsel(FR) zustaendig.

 * @author Flo
 *
 */
public class Raetselinterpret {

  public static final String ERROR_NO_FILE_FOUND = "Es wurde kein R�tsel gefunden";
  public static final String FREIES_RAETSEL_NAME = "Freies R�tsel";
  public static final int NUMBER_OF_ROWS = 8;
  
  private String[] extrahiere(String input) {
    String[] output = input.split("##");
    return output;
  }
  
  private List<String> exAtome(String input) {
    String[] sep = input.split(",");
    List<String> output = new ArrayList<String>();
    for (String temp : sep) {
      output.add(temp);
    }
    return output;
  }
  
  private List<File> gibRaetselausOrdner(int stufe) {
    List<File> output = new ArrayList<File>();
    File path = null;
    switch (stufe) {
      case 1: path = new File("Resources/Raetsel/Stufe 1");
      break;
      case 2: path = new File("Resources/Raetsel/Stufe 2");
      break;
      case 3: path = new File("Resources/Raetsel/Stufe 3");
      break;
      case 4: path = new File("Resources/Raetsel/Stufe 4");
      break;
      default: path = null;
    }
    File[] files = path.listFiles();
    if (files != null) { 
      for (int i = 0; i < files.length; i++)
        if (files[i].isDirectory()) {
        } else {
          output.add(files[i]);
          
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
      String[] name = temp.getName().split(".txt");
      output.add(name[0]);
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

   * @param input Angegebener Name des Reatsels, aufgrund dessen die Raetseltextdatei gesucht wird.
   * @return Ein neues Raetselobjekt.
   */
  public Raetsel liesRaetsel(String input) {
    String titel = input + ".txt";
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
    String[] antwortM = lines[3].split(",");
    List<String> formeln = new ArrayList<String>()
;    for (String temp : lines[6].split(",")) {
      formeln.add(temp);
    }
    
    String[] name = lines[0].split(".txt");
    atome = this.exAtome(lines[4]);
    return new Raetsel(
        name[0],                    //spaltenAnz
        Integer.parseInt(lines[7]), //stufe
        atome,                      //Atome
        lines[1],                   //Raetseltext
        antwortM,                   //Antwortm�glichkeiten
        Integer.parseInt(lines[5]), //Wievielte Antwort die L�sung ist
        lines[2],                   //Antworttext
        formeln);                   //Formelliste  
  }
  
  /** Hiermit wird ein Raetsel im freien Modus erstellt.

   * @param atomA Anzahl und Namen der verf�gbaren Atomaren Aussagen, die der Benutzer angeben kann.
   */
  public Raetsel erstelleFR(List<String> atomA) {
    return new Raetsel(
        FREIES_RAETSEL_NAME,
        4,
        atomA, 
        null, 
        null, 
        0, 
        null, 
        new ArrayList<String>());
  }
}
