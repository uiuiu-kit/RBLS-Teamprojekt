import java.util.ArrayList;
import java.util.List;
import modell.formel.Formel;

public class Raetseldummy {
  
  private String raetselText = "Text";
  private int stufe = 1;
  private List<String> atom = {"A", "B", "C"};
  private String antworttext = "Antwort";
  private List<String> antworten = {"A", "B", "C"};
  private List<Formel> formeln = new List<Formel>();
  private int spaltenAnz = 4;
  private int zeilenAnz = 8;
  private int loesung = 2;
  private String name = "Test";
  
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
