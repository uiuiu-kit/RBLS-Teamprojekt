package modell.raetsel;

import java.util.ArrayList;
import java.util.List;
import modell.formel.Atom;
import modell.formel.Formel;
import modell.tabelle.Tabelle;

/**Das Raetsel ist ein Objekt um die Daten der Raetseltextdatei 
 * dem Rest des Programms zur Verfügung zu stellen.
 * Es wird daher vom Raetselinterpreten erstellt und der Fassade übergeben.
 * @author Flo
 *
 */
public class Raetsel {
  
  private String raetselText;
  private int stufe;
  private List<String> atom;
  private String antworttext;
  private List<String> antworten;
  private List<Formel> formeln;
  private int spaltenAnz;
  private int zeilenAnz;
  private int loesung;
  private String name;
  

  
  /**Konstruktor, dem es dem Raetselinterpret ermöglicht, das Raetselobjekt zu erschaffen.
   * @param zeilenAnz
   * @param spaltenAnz
   * @param stufe
   * @param atom
   * @param raetselText
   * @param loesung
   */
  public Raetsel(String name, int zeilenAnz, int spaltenAnz, int stufe, List<String> atom, String raetselText, String[] antwortMöglichkeiten, int loesung, String antworttext, List<Formel> formeln) {
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
   * und gibt diese zurück.
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
  
  public void addFormel(Formel formel) {
    this.formeln.add(formel);
  }
}
