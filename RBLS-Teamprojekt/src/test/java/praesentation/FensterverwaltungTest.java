package praesentation;

import org.junit.Test;
import steuerung.Hauptsteuerung;

public class FensterverwaltungTest {
  @Test
  public void guiTest() {
    praesentation.Fensterverwaltung fv =
        new praesentation.Fensterverwaltung(new Hauptsteuerung(), null);
    fv.init();
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
