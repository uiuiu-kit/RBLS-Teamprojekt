package steuerungstests;

import static org.junit.Assert.*;

import org.junit.Test;
import steuerung.Berechner;

public class BerechnerTest {

  @Test
  public void berechnerTest2() {
    int anzAtom = 2;
    boolean[][] expFaelle = { { true, true }, { false, true }, { true, false }, { false, false } };
    boolean[][] faelle = new boolean[4][2];
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
