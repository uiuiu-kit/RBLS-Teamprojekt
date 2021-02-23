package modelltests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import modell.Fassade;
import modell.formel.Atom;
import modell.formel.Formel;
import modell.formel.Und;
import modell.raetsel.Memento;



public class FassadeTest {

  private Fassade testen;
  private Memento memento;
  
  @Before
  public void init() {
    testen = Fassade.gibFa();
    testen.erstelleTestUmgebung(new Testinterpret());
    testen.spalteHinzufuegen();
    testen.setzeFormel(new Und(new Atom("A", 0), new Atom("B", 1)), 3);
  }
  
  
  @Test
  public void gibSteuerungsfassadetest() {
    assertEquals(testen, Fassade.gibFa());
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
  public void gibRaetselStringTest() {
    assertEquals(testen.gibRaetselString(), "Raetseldummy");
  }
  
  @Test
  public void gibRaetselListeTest() {
    List<String> raetsel = new ArrayList<String>();
    raetsel.add("Raetsel1");
    raetsel.add("Raetsel2");
    raetsel.add("Raetseldummy");
    assertEquals(testen.gibRaetselListe(0), raetsel);
  }
  
  @Test
  public void gibAktivenRaetselnamenTest() {
    assertEquals(testen.gibAktivenRaetselnamen(), "Raetseldummy");
  }
  
  @Test
  public void gibFragestellungTest() {
    assertEquals(testen.gibFragestellung(), "Test");
  }
  
  @Test
  public void gibAntwortTextTest() {
    assertEquals(testen.gibAntwortText(), "Text");
  }
  
  @Test
  public void gibAntwortmoeglichkeitenTest() {
    assertTrue(testen.gibAntwortmoeglichkeiten().length == 3);
    assertEquals(testen.gibAntwortmoeglichkeiten()[0], "A");
    assertEquals(testen.gibAntwortmoeglichkeiten()[1], "B");
    assertEquals(testen.gibAntwortmoeglichkeiten()[2], "C");
  }
  
  @Test
  public void gibLoesungTest() {
    assertEquals(testen.gibLoesung(), "C");
  }
  
  //@Test
  public void erstelleRaetselTest() {
    
  }
  
  @Test
  public void gibNoetigeFormelTest() {
    Formel test = new Und(new Atom("A", 0), new Atom("B", 1)); 
    
    assertEquals(test.gibStringRep(), testen.gibNoetigeFormel().get(0));
  }
  
  @Test
  public void gibZelleTest() {
    int[] pos = {2, 2};
    testen.setzeZelleWaWe(pos, true);
    assertEquals(testen.gibZelle(pos), "true");
  }
  
  @Test
  public void zellenTest() {
    int[] pos = {2, 2};
    
    testen.setzeZelleWaWe(pos, true);
    
    assert (testen.gibZelleWaWe(pos));
  }
  
  @Test
  public void gibZeilenAnzTest() {
    assertEquals(testen.gibZeilenAnz(), 9);
  }
  
  @Test
  public void gibSpaltenAnzTest() {
    assertEquals(testen.gibSpaltenAnz(), 4);
  }
  
  @Test
  public void tabellenGroesseTest() {
    assertEquals(9, testen.gibZeilenAnz());
    
    assertEquals(4, testen.gibSpaltenAnz());
  }
  
  @Test
  public void spaltenaendernTest() {
    
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
    assertEquals(2, testen.gibStufe());
  }
  
  //@Test
  public void sicherungTest() {
    testen.fuehreSicherungAus();
  }
  
  @Test
  public void zeileFallTest() {
    int[] a = {1, 0};
    Boolean[] test = {true, false, false};
    testen.setzeZelleWaWe(a, true);
    
    assertEquals(test[0], testen.gibZeileFall(1)[0]);
    assertEquals(test[1], testen.gibZeileFall(1)[1]);
    assertEquals(test[2], testen.gibZeileFall(1)[2]);
  }
  
  @Test
  public void tabelleTest() {
    for (int z = 1; z < 9; z++) {
      String temp = "";
      for (int s = 0; s <= 2; s++) {
        int[] a = {z, s};
        testen.setzeZelleWaWe(a, true);
        temp = temp + " / " + testen.gibZelleWaWe(a);
        testen.setzeZelleWaWe(a, false);
      }
      //System.out.println(temp);
    } 
    
    for (int z = 1; z < 9; z++) {
      String temp = "";
      for (int s = 0; s <= 2; s++) {
        int[] a = {z, s};
        temp = temp + " / " + testen.gibZelleWaWe(a);
      }
      //System.out.println(temp);
    } 
  }
  
  //@Test
  public void gibGeloesteRaetselTest() {
    this.memento = new Memento();
    testen.fuehreSicherungAus();
    System.out.print(testen.gibGeloesteRaetsel(1));
  }
  
  //@Test
  public void gibAbgeschlosseneStufeTest() {
    memento.loesche();
    testen.fuehreSicherungAus();
    assertTrue(2 == testen.gibAbgeschlosseneStufe());
  }
}
