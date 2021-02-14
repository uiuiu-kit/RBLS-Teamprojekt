package modelltests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import modell.formel.Formel;
import modell.raetsel.Raetsel;
import modell.raetsel.Raetselinterpret;
import org.junit.Before;
import org.junit.Test;



/** Test zum Raetselinterpreten.
 * @author Flo
 *
 */
public class RaetselinterpretTest {

  Raetselinterpret testen;
  Raetsel vergleich;
  String raetselName = "Raetseldummy.txt";
  
  /** Initialisiert den Raetselinterpreten sowie das Standartraetsel "Butterbierdiebe".
   * 
   */
  @Before
  public void init() {
    testen = new Raetselinterpret();
    
    String[] typen = {"A", "B", "C"};
    List<String> atome = new ArrayList<String>();
    for (String temp: typen) {
      atome.add(temp);
    }
    vergleich = new Raetsel(
        "Test",  
        1,                    //stufe
        atome,                      //Atome
        "Text",                   //Raetseltext
        atome,        //Antwortmöglichkeiten
        2,            //Wievielte Antwort die Lösung ist
        "Antwort",       //Antworttext
        new ArrayList<Formel>());            //Formelliste  )
  }
  
  @Test
  public void liesOrdnerTest() {
    System.out.println("liesOrdnerTest");
    List<String> ordner = testen.liesOrdner(1);
    
    for (String temp: ordner) {
      System.out.println(temp);
    }
    
    assertEquals(false, ordner.isEmpty());
  }
  
  @Test
  public void liesRaetselTest() {
    System.out.println("liesRaetselTest");
    Raetsel test = testen.liesRaetsel(raetselName);
    
    assert (test != null);
  }
  
  @Test
  public void liesExplizitesRaetselTest() {
    System.out.println("liesExplizietesRaetselTest");
    Raetsel test = testen.liesRaetsel(raetselName);
    
    //assertEquals(vergleich, test);
    assertEquals(vergleich.gibName(), test.gibName());
    assertEquals(vergleich.gibAntwort(), test.gibAntwort());
    assertEquals(vergleich.gibAntworttext(), test.gibAntworttext());
    assertEquals(vergleich.gibAtomareAussage(), test.gibAtomareAussage());
    assertEquals(vergleich.gibLoesung(), test.gibLoesung());
    assertEquals(vergleich.gibRaetselText(), test.gibRaetselText());
    assertEquals(vergleich.gibStufe(), test.gibStufe());
    assertEquals(vergleich.gibZeilenAnz(), test.gibZeilenAnz());
    assertEquals(vergleich.gibSpaltenAnz(), test.gibSpaltenAnz());
    assertEquals(vergleich.gibAtomAnz(), test.gibAtomAnz());
    assertEquals(vergleich.gibAtomNamen(), test.gibAtomNamen());
  }
  
  @Test
  public void erstelleFreiesRaetselTest() {
    System.out.println("erstelleFreiesRaetselTest");
    String[] typen = {"A", "B", "C"};
    List<String> atome = new ArrayList<String>();
    for (String temp: typen) {
      atome.add(temp);
    }
    Raetsel test = testen.erstelleFR(atome);
    
    assert (test != null);
  }
}
