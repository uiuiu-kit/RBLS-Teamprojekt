package modell.raetsel;

import java.util.List;
import modell.formel.Atom;
import modell.formel.Formel;

public class Raetsel {
  
  private String raetselText;
  private int stufe;
  private List<Atom> atom;
  private String antworttext;
  private List<String> antworten;
  private List<Formel> formeln;

  public Raetsel() {
    
  }
  
  public String gibRaetselText() {
    return raetselText;
  }
  
  public int gibStufe() {
    return stufe;
  }
  
  public List<String> gibAtomareAussage() {
    //TODO
    return null;
  }
  
  public String gibAntworttext() {
    return antworttext;
  }
  
  public List<String> gibAntwort() {
    return antworten;
  }
  
  public String gibLoesung() {
    //TODO
    return null;
  }
  
  //Formel.gibStringRep gibt nur einen String und keine Liste zurueck
  public List<String> gibFormel() {
    //TODO
    //return Formel.gibStringRep();
    return null;
  }
  
  public int gibSpaltenAnz() {
    //TODO
    return 0;
  }
  
  public int gibZeilenAnz() {
    //TODO
    return 0;
  }
  
  public int gibAtomAnz() {
    return atom.size();
  }
}
