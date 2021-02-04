package praesentation;

import org.junit.Test;

import steuerung.FormelEditor;
import steuerung.Hauptsteuerung;

public class FormelAnsichtTest {
  @Test
  public void guiTest() {
    praesentation.Fensterverwaltung fv = 
        new praesentation.Fensterverwaltung(new Hauptsteuerung(), null);
    fv.init();
    new FormelAnsicht(new String[] {"Alfred", "Berta", "Collin", "Diesel"}, new FormelEditor(null));
    try {
      Thread.sleep(100000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
