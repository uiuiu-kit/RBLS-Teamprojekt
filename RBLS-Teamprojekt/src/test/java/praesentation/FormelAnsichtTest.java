package praesentation;

import org.junit.Test;
import steuerung.Hauptsteuerung;

public class FormelAnsichtTest {
  @Test
  public void guiTest() {
    praesentation.Fensterverwaltung fv = 
        new praesentation.Fensterverwaltung(new Hauptsteuerung(), null);
    fv.init();
    new FormelAnsicht(new String[] {"A", "Be", "Ce", "De"}, null);
    try {
      Thread.sleep(100000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
