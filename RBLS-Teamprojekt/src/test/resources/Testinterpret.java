import java.util.ArrayList;
import java.util.List;

import modell.formel.Formel;
import modell.raetsel.Raetsel;

public class Testinterpret {
  
  public static final String PATH = "/src/main/resources/Raetsel";
  public static final String ERROR_NO_FILE_FOUND = "Es wurde kein Rätsel gefunden";
  public static final String FREIES_RAETSEL_NAME = "Freies Rätsel";
  public static final int NUMBER_OF_ROWS = 8;
  public static final int INIT_NUMBER_OF_COL = 2;
  
  
  private int calcRow(List<String> atome) {
    return (int) Math.pow(2, atome.size());
  }
  
  public List<String> liesOrdner(int stufe) {
    return {"Raetsel1", "Raetsel2", "Raetseldummy"};
  }
  
  public Raetsel liesRaetsel(String titel) {
    return new Raetseldummy();
  }
  
  /** Hiermit wird ein Raetsel im freien Modus erstellt.
   * @param atomA Anzahl und Namen der verfügbaren Atomaren Aussagen, die der Benutzer angeben kann.
   */
  public Raetsel erstelleFR(List<String> atomA) {
    return new Raetsel(
        FREIES_RAETSEL_NAME,
        this.calcRow(atomA), 
        atomA.size() + INIT_NUMBER_OF_COL,
        0, 
        atomA, 
        null, 
        null, 
        0, 
        null, 
        new ArrayList<Formel>());
  }
}
