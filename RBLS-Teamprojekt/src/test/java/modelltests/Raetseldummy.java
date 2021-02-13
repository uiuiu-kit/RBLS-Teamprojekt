package modelltests;

import java.util.ArrayList;
import java.util.List;

import modell.formel.Atom;
import modell.formel.Formel;
import modell.formel.Konnektor;
import modell.formel.Und;
import modell.raetsel.Raetsel;

public class Raetseldummy extends Raetsel {

  public Raetseldummy(String name, int zeilenAnz, int spaltenAnz, int stufe, List<String> atom, String raetselText,
      String[] antwortMöglichkeiten, int loesung, String antworttext, List<Formel> formeln) {
    super("Text", 9, 3, 1, new ArrayList<String>(), "Test", new ArrayList<String>(), 2, "Text", new ArrayList<Formel>());
    this.atom.add("A");
    this.atom.add("B");
    this.atom.add("C");
    antworten = this.atom;
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
