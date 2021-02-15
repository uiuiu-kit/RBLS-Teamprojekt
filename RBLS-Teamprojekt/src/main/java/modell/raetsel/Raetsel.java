package modell.raetsel;

import java.util.ArrayList;
import java.util.List;

import modell.formel.Atom;

/**
 * Das Raetsel ist ein Objekt um die Daten der Raetseltextdatei dem Rest des
 * Programms zur Verfuegung zu stellen. Es wird daher vom Raetselinterpreten
 * erstellt und der Fassade uebergeben.
 * 
 * @author Flo
 *
 */
public class Raetsel {

  protected String raetselText;
  protected int stufe;
  protected List<Atom> atom;
  protected String antworttext;
  protected String[] antworten;
  protected List<String> formeln;
  protected int spaltenAnz;
  protected int zeilenAnz;
  protected int loesung;
  protected String name;

  /**
   * @param name
   * @param stufe
   * @param atom
   * @param raetselText
   * @param antwortM�glichkeiten
   * @param loesung
   * @param antworttext
   * @param formeln
   */
  public Raetsel(String name, int stufe, List<String> atom, String raetselText, String[] antwortMoeglichkeiten,
      int loesung, String antworttext, List<String> formeln) {
    this.spaltenAnz = atom.size();
    this.zeilenAnz = (int) Math.pow(2, atom.size());
    this.stufe = stufe;
    stringToAtomList(atom);
    this.loesung = loesung;
    this.raetselText = raetselText;
    this.formeln = formeln;
    this.antworttext = antworttext;
    this.name = name;
    this.antworten = antwortMoeglichkeiten;
  }

  private void stringToAtomList(List<String> atomS) {
    atom = new ArrayList<Atom>();
    for (int i = 0; i < atomS.size(); i++) {
      atom.add(new Atom(atomS.get(i), i));
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
    return this.antworten[loesung - 1];
  }

  /**
   * Wandelt die Liste der Formeln in eine Liste der entsprechenden Namen der
   * Formeln um und gibt diese zurueck.
   * 
   * @return Liste der benotigten Formelnamen, die zur Loesung des Raetsels
   *         benoetigt werden.
   */
  public List<String> gibFormeln() {
    return formeln;
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

  public List<Atom> gibAtome() {
    return atom;
  }
}
