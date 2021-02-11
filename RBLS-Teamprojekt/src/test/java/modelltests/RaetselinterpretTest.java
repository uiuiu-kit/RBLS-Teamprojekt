package modelltests;

import static org.junit.Assert.*;

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
  
  /** Initialisiert den Raetselinterpreten sowie das Standartraetsel "Butterbierdiebe".
   * 
   */
  @Before
  public void init() {
    testen = new Raetselinterpret();
    
    String[] typen = {"Charles", "Donald", "Edgar"};
    List<String> atome = new ArrayList<String>();
    for (String temp: typen) {
      atome.add(temp);
    }
    vergleich = new Raetsel(
        8,                     //zeilenAnz
        5,                    //spaltenAnz
        1,                    //stufe
        atome,                      //Atome
        "Der â€žHonigtopfâ€� wurde ausgeraubt, "
        + "sÃ¤mtliche ButterbierfÃ¤sser sind verschwunden. "
        + "Die schwarzen Magier Charles, Donald und Edgar "
        + "wurden in der NÃ¤he gesichtet. \r\n" 
        + "Beim VerhÃ¶r geben sie folgende Aussagen zu Protokoll:\r\n" 
        +  "(1) Charles: â€žIch war es nicht, aber Donald und Edgar haben eingebrochen.â€œ\r\n" 
        +  "(2) Donald: â€žIch bin unschuldig, Edgar hat auch nichts damit zu tun, "
        + "aber Charles war es.â€œ\r\n" 
        + "(3) Edgar: â€žCharles und Donald haben das durchgezogen, "
        + "ich habe nichts damit zu tun.â€œ\r\n" 
        + "Dumbledore geht davon aus, dass jeder Schuldige lÃ¼gt "
        + "und jeder Unschuldige die Wahrheit sagt "
        + "und stellt nach sorgfÃ¤ltiger Untersuchung fest: \r\n" 
        + "â€žEiner ist auf jeden Fall schuldig!â€œ. "
        + "Zeige mit Hilfe einer Wahrheitstabelle, "
        + "wen Dumbledore meint. \r\n" 
        + "Wahr steht dabei dafÃ¼r, dass die Personen die Wahrheit sagt, "
        + "also nicht der Dieb ist, und Falsch dafÃ¼r, dass die Person lÃ¼gt "
        + "und einer der Diebe ist.",                   //Raetseltext
        typen,        //Antwortmöglichkeiten
        2,            //Wievielte Antwort die Lösung ist
        "ist auf jeden Fall schuldig",       //Antworttext
        new ArrayList<Formel>());            //Formelliste  )
  }
  
  @Test
  public void liesOrdnerTest() {
    List<String> ordner = testen.liesOrdner(1);
    assertEquals(false, ordner.isEmpty());
  }
  
  @Test
  public void liesRaetselTest() {
    Raetsel test = testen.liesRaetsel("Butterbierdiebe");
    
    assert (test != null);
  }
  
  @Test
  public void liesExplizitesRaetselTest() {
    Raetsel test = testen.liesRaetsel("Butterbierdiebe");
    
    assertEquals(vergleich, test);
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
    Raetsel test = testen.liesRaetsel("Butterbierdiebe");
    
    assert (test != null);
  }
}
