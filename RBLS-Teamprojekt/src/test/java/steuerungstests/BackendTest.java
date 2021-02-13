package steuerungstests;

import org.junit.Before;
import org.junit.Test;

import modell.SteuerungFassade;
import modelltests.Testinterpret;
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
  public void raetselFormeln() {
    sf.gibNoetigeFormel();
  }
  
  @Test
  public void aufbauTabelle1Test() {
    wts.befehl("AufbauTabelle()");
    
    String fall;
    int[] koordinate = new int[2];
    for (int i = 0; i < sf.gibZeilenAnz(); i++) {
      fall = "";
      koordinate[0] = i;
      for (int j = 0; j < sf.gibSpaltenAnz(); j++) {
        koordinate[1] = j;
        fall = fall + sf.gibZelleWW(koordinate);
      }
      System.out.println(fall);
    }
  }
}
