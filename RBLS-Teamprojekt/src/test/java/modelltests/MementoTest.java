package modelltests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import modell.PraesentationFassade;
import modell.SteuerungFassade;
import modell.raetsel.Memento;
import modell.raetsel.Raetsel;

public class MementoTest {
  
  PraesentationFassade praFaTest;
  SteuerungFassade steuFaTest;
  Memento testen;
  Raetsel raetselTest;
  
  @Before
  public void init() {
    steuFaTest = new SteuerungFassade();
    praFaTest = steuFaTest.erstelleTestUmgebung(new Testinterpret());
    raetselTest = praFaTest.gibRaetsel();
    testen = new Memento(raetselTest);
  }
  
  @Test
  public void erstelleMementoDateiTest() {
    assertTrue(true == testen.erstelleMementoDatei(1, "Raetseldummy"));
  }

}
