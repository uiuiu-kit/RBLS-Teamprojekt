package modell.raetsel;

import java.util.List;
import modell.formel.Atom;


/** Der Raetselinterpret liest die Ordnerstruktur aus 
 * und liefert darauf basierend Raetselnamen zurück. 
 * Selbige können angegeben werden, um nach einer Raetseltextdatei gleichen Namens zu suchen 
 * und diese als Reatselobjekt zurückgeliefert zu bekommen. 
 * Außerdem ist der RInterpret für die Erstellung freier Raetsel(FR) zuständig
 * @author Flo
 *
 */
public class Raetselinterpret {

  /**Lisstet alle verfügbaren Raetselnamen auf, die im Ordner 
   * src,main,resources,Raetsel unter der jeweiligen Stufe hinterlegt sind.
   * @param i Raetselstufe, nach deren Raetsel gesucht wird.
   * @return Liste aller Raetselnamen, die die genannte Stufe erfüllen.
   */
  public List<String> liesOrdner(int i) {
    //TODO
    return null;
  }
  
  /**Sucht die angegebene Textdatei des Reatsels und liest deren Daten aus, 
   * speichert sie zwischen und erstellt dann ein Raetselobjekt.
   * @param name Angegebener Name des Reatsels, aufgrund dessen die Raetseltextdatei gesucht wird.
   * @return neues Raetselobjekt.
   */
  public Raetsel liesRaetsel(String name) {
    String loesung = null;;
    int zeilen = 0;
    int spalten = 0;
    List<Atom> atome = null;
    //TODO
    //lesen der Textdatei, zwischenspeichern in lokaler Variable 
    //und dann mit Konstruktor Raetsel erstellen.
    
    return new Raetsel(zeilen, spalten, atome, loesung
        /*Parameter bei Ausimplementieren aktuallisieren*/);
  }
  
  /** Hiermit wird ein Raetsel im freien Modus erstellt.
   * @param atomA Anzahl und Namen der verfügbaren Atomaren Aussagen, die der Benutzer angeben kann.
   */
  public void erstelleFR(List<String> atomA) {
    //TODO
  }
  
  
  
}
