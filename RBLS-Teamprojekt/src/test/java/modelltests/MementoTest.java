package modelltests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


import modell.Fassade;
import modell.raetsel.Memento;
import modell.raetsel.Raetsel;

public class MementoTest {
  
  Fassade steuFaTest;
  Memento testen;
  Raetsel raetselTest;
  
  @Before
  public void init() {
    steuFaTest = new Fassade();
    steuFaTest.erstelleTestUmgebung(new Testinterpret());
    raetselTest = steuFaTest.gibRaetsel();
    testen = new Memento(raetselTest);
  }
  
  @Test
  public void erstelleMementoDateiTest() {
    assertTrue(true == testen.erstelleMementoDatei(1, "Raetseldummy"));
  }
  
  @Test
  public void mementoListeTest() {
    
  }

}
