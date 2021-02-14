package modelltests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import modell.SteuerungFassade;
import modell.formel.Atom;
import modell.formel.Formel;
import modell.formel.Und;
import modell.raetsel.Memento;



public class SteuerungsFassadeTest {

  SteuerungFassade testen;
  
  @Before
  public void init() {
    testen = SteuerungFassade.gibSteuFa();
    testen.erstelleTestUmgebung(new Testinterpret());
    testen.spalteHinzufuegen();
    testen.setzeFormel(new Und(new Atom("A", 0), new Atom("B", 1)), 3);
  }
  
  
  @Test
  public void gibSteuerungsfassadetest() {
    assertEquals(testen, SteuerungFassade.gibSteuFa());
  }
 
  @Test
  public void gibAtomareAussageTest() {
    String[] vergleich = {"0", "1", "2"};
    
    assert (testen.gibAtomareAussage() != null);
    
    assertEquals(vergleich[0], testen.gibAtomareAussage().get(0));
    assertEquals(vergleich[1], testen.gibAtomareAussage().get(1));
    assertEquals(vergleich[2], testen.gibAtomareAussage().get(2));
  }
  
  @Test
  public void gibNoetigeFormelTest() {
    Formel test = new Und(new Atom("A", 0), new Atom("B", 1)); 
    
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
    
    assertEquals(4, testen.gibSpaltenAnz());
  }
  
  @Test
  public void spaltenÄndernTest() {
    
    assertEquals(4, testen.gibSpaltenAnz());
    
    testen.spalteHinzufuegen();
    
    assertEquals(5, testen.gibSpaltenAnz());
    
    testen.spalteEntfernen(4);
    
    assertEquals(4, testen.gibSpaltenAnz());
  }
  
  @Test
  public void formelTextTest() {
    String test = "(0u1)";
    
    assert (testen.gibFormelText(0) != null);
    
    assertEquals(test, testen.gibFormelText(3));
    
  }
  
  @Test
  public void formelTest() {
    Formel test = new Und(new Atom("A", 0), new Atom("B", 1));
    
    assertEquals(test.gibStringRep(), testen.gibFormel(3).gibStringRep());
  }
  
  @Test
  public void gibStufeTest() {
    assertEquals(1, testen.gibStufe());
  }
  
  @Test
  public void sicherungTest() {
    Memento m = testen.fuehreSicherungAus();
    assertEquals("Raetseldummy", m.gibSicherung().gibRaetselname());
    assertTrue(1 == m.gibSicherung().gibStufe());
  }
  
  @Test
  public void zeileFallTest() {
    int[] a = {1, 0};
    Boolean[] test = {true, false, false};
    testen.setzeZelleWW(a, true);
    
    assertEquals(test[0],testen.gibZeileFall(1)[0]);
    assertEquals(test[1],testen.gibZeileFall(1)[1]);
    assertEquals(test[2],testen.gibZeileFall(1)[2]);
  }
  
  @Test
  public void tabelleTest() {
    for (int z = 1; z < 9; z++) {
      String temp = "";
      for (int s = 0; s <= 2; s++) {
        int[] a = {z, s};
        testen.setzeZelleWW(a, true);
        temp = temp + " / " + testen.gibZelleWW(a);
        testen.setzeZelleWW(a, false);
      }
      System.out.println(temp);
    } 
    
    for (int z = 1; z < 9; z++) {
      String temp = "";
      for (int s = 0; s <= 2; s++) {
        int[] a = {z, s};
        temp = temp + " / " + testen.gibZelleWW(a);
      }
      System.out.println(temp);
    } 
  }
}
