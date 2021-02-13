package modelltests;

import static org.junit.Assert.*;

import modell.SteuerungFassade;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


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
}
