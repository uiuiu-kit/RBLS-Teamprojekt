package steuerungstests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import modell.SteuerungFassade;
import modell.formel.Atom;
import modell.formel.Formel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import steuerung.FormelParser;

public class FormelParserTest {

  @Mock
  private SteuerungFassade sfMock;

  /**
   * initalisiert die SteuerungFassade und die atomaen Aussagen.
   */
  @Before
  public void setup() {
    List<String> atomareAussagenMock = new ArrayList<String>();
    atomareAussagenMock.add("Charlie");
    atomareAussagenMock.add("Donald");
    atomareAussagenMock.add("Edgar");
    sfMock = Mockito.mock(SteuerungFassade.class);
    Mockito.when(sfMock.gibAtomareAussage()).thenReturn(atomareAussagenMock);
  }

  @Test
  public void testEinAtome() {
    Formel formel = FormelParser.pars("0", sfMock);
    assertEquals("C", formel.gibStringRep());
  }

  @Test
  public void testZweiAtome() {
    Formel formel = FormelParser.pars("1u2", sfMock);
    assertEquals("DuE", formel.gibStringRep());
  }

  @Test
  public void testDreiAtom() {
    Formel formel = FormelParser.pars("1u2o0", sfMock);
    assertEquals("DuEoC", formel.gibStringRep());
  }

  @Test
  public void testNurKlammer() {
    Formel formel = FormelParser.pars("(1)", sfMock);
    assertEquals("D", formel.gibStringRep());
  }
  
  @Test
  public void testNicht() {
    Formel formel = FormelParser.pars("n1", sfMock);
    assertEquals("nD", formel.gibStringRep());
  }

  @Test
  public void testKlammerMitKonnektor() {
    Formel formel = FormelParser.pars("(1u2)", sfMock);
    assertEquals("DuE", formel.gibStringRep());
  }

  @Test
  public void testKlammerOderZusatz() {
    Formel formel = FormelParser.pars("0o(1u2)", sfMock);
    assertEquals("CoDuE", formel.gibStringRep());
  }
  
  @Test
  public void testKlammerUndZusatz() {
    Formel formel = FormelParser.pars("0u(1o2)", sfMock);
    assertEquals("CuDoE", formel.gibStringRep());
  }
}
