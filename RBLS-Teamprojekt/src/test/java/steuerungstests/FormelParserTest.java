package steuerungstests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import modell.SteuerungFassade;
import modell.formel.Formel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import steuerung.FormelParser;

public class FormelParserTest {

  @Mock
  private SteuerungFassade sfMock;
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
    sfMock = Mockito.mock(SteuerungFassade.class);
    Mockito.when(sfMock.gibAtomareAussage()).thenReturn(atomareAussagenMock);
  }

  @Test
  public void testEinAtome() {
    Formel formel = FormelParser.pars("0", sfMock);
    assertEquals(true, formel.auswerten(fall));
    assertEquals("C", formel.gibStringRep());
  }

  @Test
  public void testZweiAtome() {
    Formel formel = FormelParser.pars("1u2", sfMock);
    assertEquals(false, formel.auswerten(fall));
    assertEquals("DuE", formel.gibStringRep());
  }

  @Test
  public void testXor() {
    Formel formel = FormelParser.pars("1x2", sfMock);
    assertEquals(true, formel.auswerten(fall));
    assertEquals("DxE", formel.gibStringRep());
  }

  @Test
  public void testDreiAtom() {
    Formel formel = FormelParser.pars("1u2o0", sfMock);
    assertEquals(true, formel.auswerten(fall));
    assertEquals("DuEoC", formel.gibStringRep());
  }

  @Test
  public void testNurKlammer() {
    Formel formel = FormelParser.pars("(1)", sfMock);
    assertEquals(false, formel.auswerten(fall));
    assertEquals("D", formel.gibStringRep());
  }

  @Test
  public void testNicht() {
    Formel formel = FormelParser.pars("n1", sfMock);
    assertEquals(true, formel.auswerten(fall));
    assertEquals("nD", formel.gibStringRep());
  }

  @Test
  public void testKlammerMitKonnektor() {
    Formel formel = FormelParser.pars("(1u2)", sfMock);
    assertEquals(false, formel.auswerten(fall));
    assertEquals("DuE", formel.gibStringRep());
  }

  @Test
  public void testKlammerOderZusatz() {
    Formel formel = FormelParser.pars("0o(1u2)", sfMock);
    assertEquals(true, formel.auswerten(fall));
    assertEquals("CoDuE", formel.gibStringRep());
  }

  @Test
  public void testKlammerUndZusatz() {
    Formel formel = FormelParser.pars("0u(1x2)", sfMock);
    assertEquals(true, formel.auswerten(fall));
    assertEquals("CuDxE", formel.gibStringRep());
  }

  @Test
  public void testdoppelKlammer() {
    Formel formel = FormelParser.pars("0u(2x(1o1))", sfMock);
    assertEquals(true, formel.auswerten(fall));
    assertEquals("CuExDoD", formel.gibStringRep());
  }
}
