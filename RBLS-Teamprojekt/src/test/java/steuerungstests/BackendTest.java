package steuerungstests;

import modell.SteuerungFassade;
import modelltests.Testinterpret;
import org.junit.Before;
import org.junit.Test;
import steuerung.WahrheitstabellenSteuerungen;

public class BackendTest {
  SteuerungFassade sf;
  WahrheitstabellenSteuerungen wts;

  /**
   * Baut die SteuerungFassade mit allen Komponenten auf und übergiebt sie der
   * WahrheitstabellenSteuerungen. Dabei sind Antwortmöglichkeit, Lösung und
   * Rätseltext nur Dummys da sie nicht benötigt werden in diesen Tests. Die
   * Stufe, die Formeln und die Atome können noch geändert werden.
   */

  @Before
  public void setup() {
    sf = new SteuerungFassade();
    sf.erstelleTestUmgebung(new Testinterpret());

    wts = new WahrheitstabellenSteuerungen(sf);
  }

  @Test
  public void aufbauTabelle1Test() {
    wts.befehl("AufbauTabelle()");

    gibTabelle();
  }

  @Test
  public void formelEingebenTest() {
    wts.befehl("FormelEingeben(1)");

    System.out.println(sf.gibFormelText(1));
  }

  @Test
  public void fuelleTabelleTest() {
    wts.befehl("FuelleTabelle");

    gibTabelle();
  }

  @Test
  public void spalteEntfernenTest() {
    wts.befehl("SpalteEntfernen(1)");

    gibTabelle();
  }
  
  @Test
  public void spalteHinzufügenTest() {
    wts.befehl("SpalteHinzufügen(1)");

    gibTabelle();
  }
  
  @Test
  public void ZelleInBlauOrangeAendernTest() {
    gibTabelle();
    wts.befehl("ZelleInBlauOrangeAendern(1,3)");

    gibTabelle();
  }
  
  private void gibTabelle() {
    String output = "";
    int[] koordinate = new int[2];
//    for (int i = sf.gibAtomareAussage().size(); i < sf.gibSpaltenAnz(); i++) {
//      output = output + sf.gibFormel(i).gibStringRep();
//    }
    for (int i = 1; i < sf.gibZeilenAnz(); i++) {
      output = output + "\n";
      koordinate[0] = i;
      for (int j = 0; j < sf.gibSpaltenAnz(); j++) {
        koordinate[1] = j;
        output = output + " " + sf.gibZelleWW(koordinate);
      }
    }
    System.out.println(output);
  }
}
