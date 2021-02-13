package modell.raetsel;

import java.util.ArrayList;
import java.util.List;

import modell.formel.Formel;

/**Das Raetsel ist ein Objekt um die Daten der Raetseltextdatei 
 * dem Rest des Programms zur Verf�gung zu stellen.
 * Es wird daher vom Raetselinterpreten erstellt und der Fassade �bergeben.
 * @author Flo
 *
 */
public class Raetsel {
  
  protected String raetselText;
  protected int stufe;
  protected List<String> atom;
  protected String antworttext;
  protected List<String> antworten;
  protected List<Formel> formeln;
  protected int spaltenAnz;
  protected int zeilenAnz;
  protected int loesung;
  protected String name;
  
  /**Konstruktor, dem es dem Raetselinterpret erm�glicht, das Raetselobjekt zu erschaffen.
   * @param zeilenAnz
   * @param spaltenAnz
   * @param stufe
   * @param atom
   * @param raetselText
   * @param loesung
   */
  public Raetsel(String name, int zeilenAnz, int spaltenAnz, int stufe, List<String> atom, String raetselText, List<String> antwortM�glichkeiten, int loesung, String antworttext, List<Formel> formeln) {
    this.spaltenAnz = spaltenAnz;
    this.zeilenAnz = zeilenAnz;
    this.stufe = stufe;
    this.atom = atom;
    this.loesung = loesung;
    this.raetselText = raetselText;
    this.formeln = formeln;
    this.antworttext = antworttext;
    this.name = name;
  }
  
  public String gibRaetselText() {
    return raetselText;
  }
  
  public int gibStufe() {
    return stufe;
  }
  
  public String gibName() {
    return this.name;
  }
  
  /**Wandelt die Liste der Atome in eine Liste der entsprechenden Namen der Atome um 
   * und gibt diese zur�ck.
   * @return Liste der Atomnamen.
   */
  public List<String> gibAtomNamen() {
    List<String> temp = new ArrayList<String>();
    for (int i = 0; i < this.atom.size(); i++) {
      temp.add(atom.get(i));
    }
    return temp;
  }
  
  public List<String> gibAtomareAussage() {
    return atom;
  }
  
  public String gibAntworttext() {
    return antworttext;
  }
  
  public List<String> gibAntwort() {
    return antworten;
  }
  
  public String gibLoesung() {
    return this.antworten.get(loesung);
  }
  
  /**Wandelt die Liste der Formeln in eine Liste der entsprechenden Namen der Formeln um 
   * und gibt diese zur�ck.
   * @return Liste der benb�tigten Formelnamen, die zur L�sung des Raetsels ben�tigt werden.
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
  
  public void addFormel(Formel formel) {
    this.formeln.add(formel);
  }
}
