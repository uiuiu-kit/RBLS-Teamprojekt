package steuerungstests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import modell.Fassade;
import modell.formel.Formel;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import steuerung.FormelParser;

public class FormelParserTest {

  @Mock
  private Fassade sfMock;
  private boolean[] fall = new boolean[3];

  /**
   * initalisiert die SteuerungFassade und die atomaen Aussagen.
   */
  @Before
  public void setup() {
    List<String> atomareAussagenMock = new ArrayList<String>();
    atomareAussagenMock.add("Charlie");
    atomareAussagenMock.add("Donald");
    atomareAussagenMock.add("Edgar");
    fall[0] = true;
    fall[1] = false;
    fall[2] = true;
    sfMock = Mockito.mock(Fassade.class);
    Mockito.when(sfMock.gibAtomareAussage()).thenReturn(atomareAussagenMock);
  }

  @Test
  public void testEinAtome() {
    Formel formel = FormelParser.pars("0", sfMock);
    assertEquals(true, formel.auswerten(fall));
    assertEquals("A", formel.gibStringRep());
  }

  @Test
  public void testZweiAtome() {
    Formel formel = FormelParser.pars("1u2", sfMock);
    assertEquals(false, formel.auswerten(fall));
    assertEquals("B\u2227C", formel.gibStringRep());
  }

  @Test
  public void testXor() {
    Formel formel = FormelParser.pars("1x2", sfMock);
    assertEquals(true, formel.auswerten(fall));
    assertEquals("B\u2295C", formel.gibStringRep());
  }

  @Test
  public void testDreiAtom() {
    Formel formel = FormelParser.pars("1u2o0", sfMock);
    assertEquals(true, formel.auswerten(fall));
    assertEquals("B\u2227C\u2228A", formel.gibStringRep());
  }

  @Test
  public void testNurKlammer() {
    Formel formel = FormelParser.pars("(1)", sfMock);
    assertEquals(false, formel.auswerten(fall));
    assertEquals("B", formel.gibStringRep());
  }

  @Test
  public void testNicht() {
    Formel formel = FormelParser.pars("n1", sfMock);
    assertEquals(true, formel.auswerten(fall));
    assertEquals("\u00ACB", formel.gibStringRep());
  }

  @Test
  public void testAequivalenz() {
    Formel formel = FormelParser.pars("1a1", sfMock);
    assertEquals(true, formel.auswerten(fall));
    assertEquals("B\u2194B", formel.gibStringRep());
  }

  @Test
  public void testKlammerMitKonnektor() {
    Formel formel = FormelParser.pars("(1u2)", sfMock);
    assertEquals(false, formel.auswerten(fall));
    assertEquals("B\u2227C", formel.gibStringRep());
  }

  @Test
  public void testKlammerOderZusatz() {
    Formel formel = FormelParser.pars("0o(1u2)", sfMock);
    assertEquals(true, formel.auswerten(fall));
    assertEquals("A\u2228B\u2227C", formel.gibStringRep());
  }

  @Test
  public void testKlammerUndZusatz() {
    Formel formel = FormelParser.pars("0u(1x2)", sfMock);
    assertEquals(true, formel.auswerten(fall));
    assertEquals("A\u2227(B\u2295C)", formel.gibStringRep());
  }

  @Test
  public void testdoppelKlammer() {
    Formel formel = FormelParser.pars("0u(2x(1f1))", sfMock);
    assertEquals(false, formel.auswerten(fall));
    assertEquals("A\u2227(C\u2295(B\u2192B))", formel.gibStringRep());
  }

  @Test
  public void testzweiunabhaenigeKlammern() {
    Formel formel = FormelParser.pars("(0u2)x(1f1)", sfMock);
    assertEquals(false, formel.auswerten(fall));
    assertEquals("A\u2227C\u2295(B\u2192B)", formel.gibStringRep());
  }
}
