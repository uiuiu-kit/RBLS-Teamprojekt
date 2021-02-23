package modelltests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


import modell.Fassade;
import modell.raetsel.Memento;
import modell.raetsel.Raetsel;

public class MementoTest {
  
  Fassade FaTest;
  Memento memTest;
  Raetsel raetselTest;
  
  @Before
  public void init() {
    FaTest = new Fassade();
    FaTest.erstelleTestUmgebung(new Testinterpret());
    //raetselTest = FaTest.gibRaetsel();
    memTest = new Memento();
  }
  
  //@Test
  public void gibStufensicherungTest() {
    
  }
  
  //@Test
  public void loescheTest() {
    
  }
  
  //@Test
  public void erstelleMementoDateiTest() {
    
  }
  
  //@Test
  public void istNeuTest() {
    
  }
  
  //@Test
  public void liesMementoDateiTest() {
    
  }
  
  //@Test
  public void extrahiereTest() {
    
  }

  //@Test
  public void gibGeloesteRaetselTest() {
    
  }
}
