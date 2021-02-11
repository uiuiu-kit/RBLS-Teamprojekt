package modell.raetsel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import modell.formel.Formel;


/** Der Raetselinterpret liest die Ordnerstruktur aus 
 * und liefert darauf basierend Raetselnamen zurück.
 * Selbige können angegeben werden, um nach einer Raetseltextdatei gleichen Namens zu suchen 
 * und diese als Reatselobjekt zurückgeliefert zu bekommen. 
 * Außerdem ist der RInterpret für die Erstellung freier Raetsel(FR) zuständig
 * @author Flo
 *
 */
public class Raetselinterpret {

  public static final String PATH = "/src/main/resources/Raetsel";
  public static final String ERROR_NO_FILE_FOUND = "Es wurde kein Raetsel gefunden";
  public static final int NUMBER_OF_ROWS = 8;
  public static final int INIT_NUMBER_OF_COL = 2;
  
  
  
  /**Wird von LiesOrdner und liesRaetsel benötigt, 
   * um den Inhalt der Textdateien in einen String zu wandeln.
   * Lädt die angegebene Textdatei und wandelt sie in einen String um.
   * @param datName Name der gesuchten Datei.
   * @return String mit dem Inhalt der Textdatei.
   */
  private String ladeDatei(String datName) {
    File file = new File(datName);
    String output = "";
    if (!file.canRead() || !file.isFile()) {
      return null;
    }
    BufferedReader in = null;
    try {
      in = new BufferedReader(new FileReader(datName));
      String zeile = null;
      while ((zeile = in.readLine()) != null) {
        output = output + zeile;
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          return null;
        }
      }   
    }
    return output;
  }
  
  private List<String> liesDateinamen() {
    try {
      return Files.readAllLines(FileSystems.getDefault().getPath(PATH), 
          StandardCharsets.UTF_8);
    } catch (IOException e) {
      List<String> output = new ArrayList<String>();
      output.add(ERROR_NO_FILE_FOUND);
      return output;
    } 
  }
  
  private String[] extrahiere(String input) {
    String[] output = input.split("##");
    if (output.length == NUMBER_OF_ROWS) {
      return (output[7].matches("[+-]?\\d*(\\.\\d+)?") ?  output : null);
    }
    return null;
  }
  
  private int calcRow(List<String> atome) {
    return (int) Math.pow(2, atome.size());
  }
  
  private List<String> exAtome(String input) {
    String[] sep = input.split(",");
    List<String> output = new ArrayList<String>();
    for (String temp: sep) {
      output.add(temp);
    }
    return output;
  }
  
  /**Listet alle verfügbaren Raetselnamen auf, die im Ordner 
   * src,main,resources,Raetsel unter der jeweiligen Stufe hinterlegt sind.
   * @param stufe Raetselstufe, nach deren Raetsel gesucht wird.
   * @return Liste aller Raetselnamen, die die genannte Stufe erfüllen.
   */
  public List<String> liesOrdner(int stufe) {
    List<String> output = new ArrayList<String>();   
    for (String name : liesDateinamen()) {
      String[] temp = extrahiere(ladeDatei(name));
      if (Integer.parseInt(temp[7]) == stufe) {
        output.add(name);
      }
    }
    return output;
  }
  
  /**Sucht die angegebene Textdatei des Reatsels und liest deren Daten aus, 
   * speichert sie zwischen und erstellt dann ein Raetselobjekt.
   * @param titel Angegebener Name des Reatsels, aufgrund dessen die Raetseltextdatei gesucht wird.
   * @return neues Raetselobjekt.
   */
  public Raetsel liesRaetsel(String titel) {
    int spalten = 0;
    List<String> atome = null;
    String[] lines = null;
    for (String name : liesDateinamen()) {
      if (name.equals(titel)) {
        lines = extrahiere(ladeDatei(name));
        break;
      }
    }
    atome = this.exAtome(lines[4]);
    spalten = atome.size() + INIT_NUMBER_OF_COL;
    return new Raetsel(
        this.calcRow(atome),        //zeilenAnz
        spalten,                    //spaltenAnz
        Integer.parseInt(lines[7]), //stufe
        atome,                      //Atome
        lines[1],                   //Raetseltext
        lines[3].split(","),        //Antwortmöglichkeiten
        lines[5],                   //Lösung
        lines[2],                   //Antworttext
        new ArrayList<Formel>());   //Formelliste  
  }
  
  /** Hiermit wird ein Raetsel im freien Modus erstellt.
   * @param atomA Anzahl und Namen der verfügbaren Atomaren Aussagen, die der Benutzer angeben kann.
   */
  public Raetsel erstelleFR(List<String> atomA) {
    return new Raetsel(
        this.calcRow(atomA), 
        atomA.size() + INIT_NUMBER_OF_COL,
        0, 
        atomA, 
        null, 
        null, 
        null, 
        null, 
        new ArrayList<Formel>());
  }

}
