package modelltests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    
  }
  
  @Test
  public void gibRaetselListeTest() {
    
  }
  
  @Test
  public void gibFragestellungTest() {
    
  }
  
  @Test
  public void gibAntwortTextTest() {
    
  }
  
  @Test
  public void gibLoesungTest() {
    
  }
  
  @Test
  public void gibAktuelleStufe() {
    int testStufe = 1;
    assertTrue(testStufe == testen.gibAktuelleStufe());
  }
  
  @Test
  public void gibZelleTest() {
    String t = "false";
    int [] a = {1, 3};
    System.out.print(testen.gibZelle(a));
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
    System.out.print(testen.gibFormelText(3));
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
