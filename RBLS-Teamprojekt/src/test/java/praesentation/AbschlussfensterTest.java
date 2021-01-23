package praesentation;

import modell.PraesentationFassade;
import org.junit.Test;
import steuerung.Hauptsteuerung;

public class AbschlussfensterTest {
  @Test
  public void guiTest() {
    praesentation.Fensterverwaltung fv = 
        new praesentation.Fensterverwaltung(new Hauptsteuerung(), null);
    fv.init();
    fv.oeffneAbschlussFenster();
    try {
      Thread.sleep(100000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
