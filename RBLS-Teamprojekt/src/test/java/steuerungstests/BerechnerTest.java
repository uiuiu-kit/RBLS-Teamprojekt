package steuerungstests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import modell.Fassade;
import steuerung.Berechner;
import steuerung.FormelParser;

public class BerechnerTest {

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
  public void berechnerTest2() {
    int anzAtom = 3;
    boolean[][] expFaelle = { { true, true, true }, { false, true, true }, { true, false, true },
        { false, false, true }, { true, true, false }, { false, true, false },
        { true, false, false }, { false, false, false } };
    boolean[][] faelle = new boolean[8][3];
    faelle = Berechner.faelleBerechnen(anzAtom, faelle, 0);
    assertArrayEquals(expFaelle, faelle);
  }

  @Test
  public void berechnerTest1() {
    int anzAtom = 1;
    boolean[][] expFaelle = { { true }, { false } };
    boolean[][] faelle = new boolean[2][1];
    faelle = Berechner.faelleBerechnen(anzAtom, faelle, 0);
    assertArrayEquals(expFaelle, faelle);
  }

  @Test
  public void formelvergleicherTest1() {
    assertTrue(Berechner.vergleicheFormel(FormelParser.pars("0", sfMock),
        FormelParser.pars("0", sfMock), 1));
  }
  
  @Test
  public void formelvergleicherTest2() {
    assertTrue(Berechner.vergleicheFormel(FormelParser.pars("0u1", sfMock),
        FormelParser.pars("0u1", sfMock), 2));
  }
  
  @Test
  public void formelvergleicherTest3() {
    assertTrue(Berechner.vergleicheFormel(FormelParser.pars("0o1", sfMock),
        FormelParser.pars("1o0", sfMock), 2));
  }

  @Test
  public void formelvergleicherTest4() {
    assertFalse(Berechner.vergleicheFormel(FormelParser.pars("0o1", sfMock),
        FormelParser.pars("1u0", sfMock), 2));
  }
  
}
