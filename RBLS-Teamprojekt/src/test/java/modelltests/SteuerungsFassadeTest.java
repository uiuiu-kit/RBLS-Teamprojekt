package modelltests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import modell.SteuerungFassade;
import modell.formel.Atom;
import modell.formel.Formel;
import modell.formel.Und;



public class SteuerungsFassadeTest {

  SteuerungFassade testen;
  
  @Before
  public void init() {
    testen = SteuerungFassade.gibSteuFa();
    testen.erstelleTestUmgebung(new Testinterpret());
  }
  
  
  @Test
  public void gibSteuerungsfassadetest() {
    assertEquals(testen, SteuerungFassade.gibSteuFa());
  }
 
  @Test
  public void gibAtomareAussageTest() {
    String[] vergleich = {"A", "B", "C"};
    
    assert (testen.gibAtomareAussage() != null);
    
    assertEquals(vergleich[0], testen.gibAtomareAussage().get(0));
    assertEquals(vergleich[1], testen.gibAtomareAussage().get(1));
    assertEquals(vergleich[2], testen.gibAtomareAussage().get(2));
  }
  
  @Test
  public void gibNoetigeFormelTest() {
    Formel test = new Und(new Atom("A", "A", 1), new Atom("B", "B", 2)); 
    
    assertEquals(test.gibStringRep(), testen.gibNoetigeFormel().get(0));
  }
  
  @Test
  public void zellenTest() {
    int[] pos = {2, 2};
    
    testen.setzeZelleWW(pos, true);
    
    assert (testen.gibZelleWW(pos));
  }
  
  @Test
  public void tabellenGrößeTest() {
    assertEquals(9, testen.gibZeilenAnz());
    
    assertEquals(3, testen.gibSpaltenAnz());
  }
}
