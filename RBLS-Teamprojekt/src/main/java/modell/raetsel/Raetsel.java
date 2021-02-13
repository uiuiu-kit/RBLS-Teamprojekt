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
  
  protected String raetselText;
  protected int stufe;
  protected List<Atom> atom;
  protected String antworttext;
  protected List<String> antworten;
  protected List<Formel> formeln;
  protected int spaltenAnz;
  protected int zeilenAnz;
  protected int loesung;
  protected String name;
  
  /**Konstruktor, dem es dem Raetselinterpret ermöglicht, das Raetselobjekt zu erschaffen.
   * @param zeilenAnz
   * @param spaltenAnz
   * @param stufe
   * @param atom
   * @param raetselText
   * @param loesung
   */
  public Raetsel(String name, int stufe, List<String> atom, String raetselText, List<String> antwortMöglichkeiten, int loesung, String antworttext, List<Formel> formeln) {
    this.spaltenAnz = atom.size();
    this.zeilenAnz = (int) Math.pow(2, atom.size());
    this.stufe = stufe;
    this.atom = new ArrayList<Atom>();
    this.loesung = loesung;
    this.raetselText = raetselText;
    this.formeln = formeln;
    this.antworttext = antworttext;
    this.name = name;
    for (int i = 0; i < atom.size(); i++) {
      this.atom.add(new Atom(atom.get(i), i));
      formeln.add(this.atom.get(i));
    }
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
      temp.add(atom.get(i).gibStringRep());
    }
    return temp;
  }
  
  public List<String> gibAtomareAussage() {
    List<String> output = new ArrayList<String>();   
    for (Atom temp : atom) {
      output.add(temp.gibStringRep());
    }
    return output;
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
    this.spaltenAnz++;
  }
  
  public List<Atom> gibAtome() {
    return atom;
  }
}
