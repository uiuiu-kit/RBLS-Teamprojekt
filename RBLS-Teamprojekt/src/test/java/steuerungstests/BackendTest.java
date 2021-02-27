package steuerungstests;

import modell.Fassade;
import modelltests.Testinterpret;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import steuerung.FormelParser;
import steuerung.WahrheitstabellenSteuerungen;

public class BackendTest {
  Fassade sf;
  WahrheitstabellenSteuerungen wts;

  /**
   * Baut die SteuerungFassade mit allen Komponenten auf und �bergiebt sie der
   * WahrheitstabellenSteuerungen. Dabei sind Antwortmoeglichkeit, L�sung und
   * R�tseltext nur Dummys da sie nicht ben�tigt werden in diesen Tests. Die
   * Stufe, die Formeln und die Atome k�nnen noch ge�ndert werden.
   */

  @Before
  public void setup() {
    sf = new Fassade();
    sf.erstelleTestUmgebung(new Testinterpret());

    wts = new WahrheitstabellenSteuerungen(sf);
  }

  @Test
  public void aufbauTabelle1Test() {
    wts.befehl("AufbauTabelle()");
    assertEquals("012\n false false false\n false false true\n false true false\n "
        + "false true true\n true false false\n true false true\n "
        + "true true false\n true true true", gibTabelle());
  }

  @Test
  public void fuelleTabelleTest() {
    wts.befehl("AufbauTabelle()");
    wts.befehl("SpalteHinzufuegen");
    sf.setzeFormel(FormelParser.pars("1", sf), sf.gibAtomareAussage().size());
    wts.befehl("FuelleTabelle");
    assertEquals(
        "0121\n false false false false\n false false true false\n false true false true\n "
            + "false true true true\n true false false false\n true false true false\n "
            + "true true false true\n true true true true",
        gibTabelle());
  }

  @Test
  public void spalteEntfernenTest() {
    wts.befehl("SpalteHinzufuegen");
    wts.befehl("SpalteHinzufuegen");
    wts.befehl("SpalteEntfernen(4)");
    assertEquals(
        "012Formel einfügen\n false false false false\n false false false false\n"
            + " false false false false\n false false false false\n false false false false\n"
            + " false false false false\n false false false false\n false false false false",
        gibTabelle());
  }

  @Test
  public void spalteHinzufuegenTest() {
    wts.befehl("SpalteHinzufuegen");
    assertEquals(
        "012Formel einfügen\n false false false false\n false false false false\n"
            + " false false false false\n false false false false\n false false false false\n"
            + " false false false false\n false false false false\n false false false false",
        gibTabelle());
  }

  /**
   * Da der Test auf Stufe 2 Ausgeführt wird ist es so korrekt das sich nichts
   * ändert da auf Stufe zwei es keine Möglichkeit gibt das sich ein Wahrheitswert
   * über ZelleAendern(x,y) ändert.
   */
  @Test
  public void zelleInBlauOrangeAendernTest() {
    gibTabelle();
    wts.befehl("ZelleAendern(2,1)");
    assertEquals("012\n false false false\n false false false\n"
        + " false false false\n false false false\n false false false\n"
        + " false false false\n false false false\n false false false", gibTabelle());
  }

  @Test
  public void gibTipTest() {
    gibTabelle();
    wts.gibTip();
    gibTabelle();
  }

  private String gibTabelle() {
    String output = "";
    int[] koordinate = new int[2];
    for (int i = 0; i < sf.gibSpaltenAnz(); i++) {
      output = output + sf.gibFormel(i).gibStringRep();
    }
    for (int i = 1; i < sf.gibZeilenAnz(); i++) {
      output = output + "\n";
      koordinate[0] = i;
      for (int j = 0; j < sf.gibSpaltenAnz(); j++) {
        koordinate[1] = j;
        output = output + " " + sf.gibZelleWaWe(koordinate);
      }
    }
    return output;
  }
}
