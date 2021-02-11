package modelltests;

import static org.junit.Assert.*;

import java.util.List;
import modell.raetsel.Raetselinterpret;
import org.junit.Before;
import org.junit.Test;


public class RaetselinterpretTest {

  Raetselinterpret testen;
  
  @Before
  public void init() {
    testen = new Raetselinterpret();
  }
  
  @Test
  public void liesOrdnerTest() {
    List<String> ordner = testen.liesOrdner(1);
    assertEquals(false, ordner.isEmpty());
  }
}
