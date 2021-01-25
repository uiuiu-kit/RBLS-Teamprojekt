package modell.raetsel;

import java.util.ArrayList;
import java.util.List;
import modell.formel.Atom;
import modell.formel.Formel;

/**Das Raetsel ist ein Objekt um die Daten der Raetseltextdatei 
 * dem Rest des Programms zur Verfügung zu stellen.
 * Es wird daher vom Raetselinterpreten erstellt und der Fassade übergeben.
 * @author Flo
 *
 */
public class Raetsel {
  
  private String raetselText;
  private int stufe;
  private List<Atom> atom;
  private String antworttext;
  private List<String> antworten;
  private List<Formel> formeln;
  private int spaltenAnz;
  private int zeilenAnz;
  private String loesung;

  /**Konstruktor des Raetsels. Soll durch RInterpret erstellt werden
   * 
   */
  public Raetsel(int zeilenAnz, int spaltenAnz, List<Atom> atom, String loesung) {
    this.spaltenAnz = spaltenAnz;
    this.zeilenAnz = zeilenAnz;
    this.atom = atom;
    this.loesung = loesung;
  }
  
  public String gibRaetselText() {
    return raetselText;
  }
  
  public int gibStufe() {
    return stufe;
  }
  
  /**Wandelt die Liste der Atome in eine Liste der entsprechenden Namen der Atome um 
   * und gibt diese zurück.
   * @return Liste der Atomnamen.
   */
  public List<String> gibAtomNamen() {
    List<String> temp = new ArrayList<String>();
    for (int i = 0; i < this.atom.size(); i++) {
      temp.add(atom.get(i).gibStringRep());
    }
    return temp;
  }
  
  public List<Atom> gibAtomareAussage() {
    return atom;
  }
  
  public String gibAntworttext() {
    return antworttext;
  }
  
  public List<String> gibAntwort() {
    return antworten;
  }
  
  public String gibLoesung() {
    return this.loesung;
  }
  
  /**Wandelt die Liste der Formeln in eine Liste der entsprechenden Namen der Formeln um 
   * und gibt diese zurück.
   * @return Liste der benbötigten Formelnamen, die zur Lösung des Raetsels benötigt werden.
   */
  public List<String> gibFormeln() {
    List<String> temp = new ArrayList<String>();
    for (int i = 0; i < this.formeln.size(); i++) {
      temp.add(formeln.get(i).gibStringRep());
    }
    return temp;
  }
  
  public int gibSpaltenAnz() {
    return this.spaltenAnz;
  }
  
  public int gibZeilenAnz() {
    return this.zeilenAnz;
  }
  
  public int gibAtomAnz() {
    return atom.size();
  }
}
