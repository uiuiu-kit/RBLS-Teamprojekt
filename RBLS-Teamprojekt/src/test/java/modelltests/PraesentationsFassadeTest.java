package modelltests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import modell.PraesentationFassade;
import modell.SteuerungFassade;

public class PraesentationsFassadeTest {

  PraesentationFassade testen;
  SteuerungFassade test;
  
  @BeforeClass
  public void init() {
    test = new SteuerungFassade();
    testen = test.erstelleTestUmgebung(new Testinterpret());
  }
  
  @Test
  public void gibPraFaTest() {
    assertEquals(testen, PraesentationFassade.gibPraFa());
  }
  
}
