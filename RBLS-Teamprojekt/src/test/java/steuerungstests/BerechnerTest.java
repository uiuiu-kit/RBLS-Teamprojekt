package steuerungstests;

import static org.junit.Assert.*;

import org.junit.Test;
import steuerung.Berechner;

public class BerechnerTest {

  @Test
  public void berechnerTest2() {
    int anzAtom = 3;
    boolean[][] expFaelle = { { true, true, true }, { false, true, true }, { true, false, true },
        { false, false, true }, { true, true, false }, { false, true, false }, { true, false, false },
        { false, false, false } };
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

}
