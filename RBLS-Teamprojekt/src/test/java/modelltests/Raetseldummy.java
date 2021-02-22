package modelltests;

import java.util.ArrayList;
import java.util.List;

import modell.formel.Atom;
import modell.formel.Formel;
import modell.formel.Konnektor;
import modell.formel.Und;
import modell.raetsel.Raetsel;

public class Raetseldummy extends Raetsel {

  public Raetseldummy(String name, int stufe, List<String> atom, String raetselText, String[] antwortMoeglichkeiten,
      int loesung, String antworttext, List<Formel> formeln) {
    super("Raetseldummy", 2, new ArrayList<String>(), "Test", new String[2], 2, "Text",
        new ArrayList<String>());

    this.atom.add(new Atom("A", 0));
    this.atom.add(new Atom("B", 1));
    this.atom.add(new Atom("C", 2));
    antworten = new String[] {"A", "B", "C"};
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

  /**
   * Wandelt die Liste der Atome in eine Liste der entsprechenden Namen der Atome
   * um und gibt diese zurueck.
   * 
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

  public String[] gibAntwort() {
    return antworten;
  }

  public String gibLoesung() {
    return this.antworten[loesung];
  }

  /**
   * Wandelt die Liste der Formeln in eine Liste der entsprechenden Namen der
   * Formeln um und gibt diese zurueck.
   * 
   * @return Liste der benb�tigten Formelnamen, die zur Loesung des Raetsels
   *         ben�tigt werden.
   */
  public List<String> gibFormeln() {
    List<String> temp = new ArrayList<String>();
    for (int i = 0; i < this.formeln.size(); i++) {
      temp.add(formeln.get(i));
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
