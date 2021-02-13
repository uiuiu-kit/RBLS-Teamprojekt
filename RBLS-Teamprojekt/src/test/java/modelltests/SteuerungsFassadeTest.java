package modelltests;

import static org.junit.Assert.*;

import modell.SteuerungFassade;
import org.junit.BeforeClass;
import org.junit.Test;


public class SteuerungsFassadeTest {

  SteuerungFassade testen;
  
  @BeforeClass
  public void init() {
    testen = new SteuerungFassade();
    testen.erstelleTestUmgebung(new Testinterpret());
    
  }
  
  @Test
  public void gibSteuerungsfassadetest() {
    assertEquals(testen, SteuerungFassade.gibSteuFa());
  }
  
  

}
