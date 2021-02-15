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

  @Ignore
  public void testEinAtome() {
    Formel formel = FormelParser.pars("0", sfMock);
    assertEquals(true, formel.auswerten(fall));
    assertEquals("0", formel.gibStringRep());
  }

  @Ignore
  public void testZweiAtome() {
    Formel formel = FormelParser.pars("1u2", sfMock);
    assertEquals(false, formel.auswerten(fall));
    assertEquals("(1u2)", formel.gibStringRep());
  }

  @Ignore
  public void testXor() {
    Formel formel = FormelParser.pars("1x2", sfMock);
    assertEquals(true, formel.auswerten(fall));
    assertEquals("(1x2)", formel.gibStringRep());
  }

  @Ignore
  public void testDreiAtom() {
    Formel formel = FormelParser.pars("1u2o0", sfMock);
    assertEquals(true, formel.auswerten(fall));
    assertEquals("((1u2)o0)", formel.gibStringRep());
  }

  @Ignore
  public void testNurKlammer() {
    Formel formel = FormelParser.pars("(1)", sfMock);
    assertEquals(false, formel.auswerten(fall));
    assertEquals("1", formel.gibStringRep());
  }

  @Ignore
  public void testNicht() {
    Formel formel = FormelParser.pars("n1", sfMock);
    assertEquals(true, formel.auswerten(fall));
    assertEquals("(n1)", formel.gibStringRep());
  }

  @Ignore
  public void testKlammerMitKonnektor() {
    Formel formel = FormelParser.pars("(1u2)", sfMock);
    assertEquals(false, formel.auswerten(fall));
    assertEquals("(1u2)", formel.gibStringRep());
  }

  @Ignore
  public void testKlammerOderZusatz() {
    Formel formel = FormelParser.pars("0o(1u2)", sfMock);
    assertEquals(true, formel.auswerten(fall));
    assertEquals("(0o(1u2))", formel.gibStringRep());
  }

  @Ignore
  public void testKlammerUndZusatz() {
    Formel formel = FormelParser.pars("0u(1x2)", sfMock);
    assertEquals(true, formel.auswerten(fall));
    assertEquals("(0u(1x2))", formel.gibStringRep());
  }

  @Ignore
  public void testdoppelKlammer() {
    Formel formel = FormelParser.pars("0u(2x(1f1))", sfMock);
    assertEquals(false, formel.auswerten(fall));
    assertEquals("(0u(2x(1f1)))", formel.gibStringRep());
  }
  
  @Ignore
  public void testzweiunabhänigeKlammern() {
    Formel formel = FormelParser.pars("(0u2)x(1f1)", sfMock);
    assertEquals(false, formel.auswerten(fall));
    assertEquals("((0u2)x(1f1))", formel.gibStringRep());
  }
}
