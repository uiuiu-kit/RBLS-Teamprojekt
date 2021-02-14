package steuerungstests;

import modell.Fassade;
import modelltests.Testinterpret;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import steuerung.WahrheitstabellenSteuerungen;

public class BackendTest {
  Fassade sf;
  WahrheitstabellenSteuerungen wts;

  /**
   * Baut die SteuerungFassade mit allen Komponenten auf und übergiebt sie der
   * WahrheitstabellenSteuerungen. Dabei sind Antwortmöglichkeit, Lösung und
   * Rätseltext nur Dummys da sie nicht benötigt werden in diesen Tests. Die
   * Stufe, die Formeln und die Atome können noch geändert werden.
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
    System.out.println("AufbauTabelle");
    System.out.println(sf.gibAtomareAussage().size());
    gibTabelle();
  }

  @Test
  public void formelEingebenTest() {
    wts.befehl("SpalteHinzufuegen");
    wts.befehl("FormelEingeben(3)");
    System.out.println("FormelEingeben");
    System.out.println(sf.gibFormelText(3));
  }

  @Test
  public void fuelleTabelleTest() {
    wts.befehl("FuelleTabelle");
    System.out.println("FuelleTabelle");
    gibTabelle();
  }

  @Test
  public void spalteEntfernenTest() {
    wts.befehl("SpalteHinzufuegen");
    wts.befehl("SpalteHinzufuegen");
    wts.befehl("SpalteEntfernen(4)");
    System.out.println("SpalteEntfernen");
    gibTabelle();
  }

  @Test
  public void spalteHinzufügenTest() {
    wts.befehl("SpalteHinzufuegen");
    System.out.println("SpalteHinzufuegen");
    gibTabelle();
  }

  @Test
  public void ZelleInBlauOrangeAendernTest() {
    gibTabelle();
    wts.befehl("ZelleAendern(0,2)");
    System.out.println("ZelleAendern(0,2)");
    gibTabelle();
  }

  private void gibTabelle() {
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
        output = output + " " + sf.gibZelleWW(koordinate);
      }
    }
    System.out.println(output);
  }
}
