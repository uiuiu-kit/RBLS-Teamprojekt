import java.util.ArrayList;
import java.util.List;
import modell.formel.Formel;
import modell.raetsel.Raetsel;

public class Raetseldummy extends Raetsel {
  
  
  public Raetseldummy(String name, int zeilenAnz, int spaltenAnz, int stufe, List<String> atom, String raetselText,
      String[] antwortMöglichkeiten, int loesung, String antworttext, List<Formel> formeln) {
    super(name, zeilenAnz, spaltenAnz, stufe, atom, raetselText, antwortMöglichkeiten, loesung, antworttext, formeln);
    raetselText = "Text";
    stufe = 1;
    atom = new ArrayList<String>();
    antworttext = "Antwort";
    formeln = new ArrayList<Formel>();
    spaltenAnz = 4;
    zeilenAnz = 8;
    loesung = 2;
    name = "Test";
    atom.add("A");
    atom.add("B");
    atom.add("C");
    antworten = atom;
    
  }

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
