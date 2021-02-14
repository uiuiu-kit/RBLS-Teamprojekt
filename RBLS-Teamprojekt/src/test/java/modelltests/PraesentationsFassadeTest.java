package modelltests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import modell.PraesentationFassade;
import modell.SteuerungFassade;

public class PraesentationsFassadeTest {

  PraesentationFassade testen;
  SteuerungFassade test;
  
  @Before
  public void init() {
    test = new SteuerungFassade();
    testen = test.erstelleTestUmgebung(new Testinterpret());
  }
  
  @Test
  public void gibPraFaTest() {
    assertEquals(testen, PraesentationFassade.gibPraFa());
  }
  
  @Test
  public void setzeRaetselTest() {
    testen.setzeRaetsel("Raetseldummy");
    assertEquals("Raetseldummy", testen.gibRaetselString());
    
  }
  
  @Test
  public void gibRaetselListeTest() {
    List<String> test = new ArrayList<String>();
    test.add("Raetsel1");
    test.add("Raetsel2");
    test.add("Raetseldummy");
    assertEquals(test, testen.gibRaetselListe(0));
  }
  
  @Test
  public void gibFragestellungTest() {
    String test = "Test";
    assertEquals(test, testen.gibFragestellung());
  }
  
  @Test
  public void gibAntwortTextTest() {
    String test = "Text";
    assertEquals(test, testen.gibAntwortText());
  }
  
  @Test
  public void gibAntwortmoeglichkeitenTest() {
    List<String> test = new ArrayList<String>();
    test.add("A");
    test.add("B");
    test.add("C");
    assertEquals(test, testen.gibAntwortmoeglichkeiten());
  }
  
  @Test
  public void gibLoesungTest() {
    String test = "C";
    assertEquals(test, testen.gibLoesung());
  }
  
  @Test
  public void gibAktuelleStufe() {
    int testStufe = 1;
    assertTrue(testStufe == testen.gibAktuelleStufe());
  }
  
  @Test
  public void gibZelleTest() {
    String t = "false";
    int [] a = {1, 1};
    assertTrue(t.equals(testen.gibZelle(a)));
  }
  
  @Test
  public void gibZellenWertTest() {
    boolean zellenwert = false;
    int [] a = {1, 1};
    assertTrue(zellenwert == testen.gibZellenWert(a));
  }
  
  @Test
  public void erstelleRaetselTest() {
    
  }
  
  @Test
  public void gibZeilenAnzTest() {
    int zeilen = 9;
    assertTrue(zeilen == testen.gibZeilenAnz());
  }
  
  @Test
  public void gibSpaltenAnzTest() {
    int spalten = 3;
    assertTrue(spalten == testen.gibSpaltenAnz());
  }
  
  @Test
  public void gibFormelTextTest() {
//    System.out.print(testen.gibFormelText(3));
  }
  
  @Test
  public void setzeAbgeschlossenStufeTest() {
    testen.setzeAbgeschlosseneStufe(3);
    assertTrue(3 == testen.gibAbgeschlosseneStufe());
  }
  
  @Test
  public void gibAbgeschlosseneStufeTest() {
    int abgeschlossen = 1;
    testen.setzeAbgeschlosseneStufe(abgeschlossen);
    assertTrue(abgeschlossen == testen.gibAbgeschlosseneStufe());
  }
}
