package praesentation;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import modell.Fassade;
import org.junit.Test;
import steuerung.Hauptsteuerung;

public class RaetselwahlTest {
  @Test
  public void guiTest() {
    praesentation.Fensterverwaltung fv = 
        new praesentation.Fensterverwaltung(new Hauptsteuerung(), 
            Fassade.gibSteuFa());
    fv.init();
    List<String> raetsel = new ArrayList<String>();
    
    /*RaetselZustand r1 = new RaetselZustand();
    r1.geloest = false;
    r1.raetselname = "Rätsel 1";
    raetsel.add(r1.raetselname);
    RaetselZustand r2 = new RaetselZustand();
    r2.geloest = true;
    r2.raetselname = "Rätsel 2";
    raetsel.add(r2.raetselname);
    RaetselZustand r3 = new RaetselZustand();
    r3.geloest = true;
    r3.raetselname = "Rätsel 3";
    raetsel.add(r3.raetselname);
    RaetselZustand r4 = new RaetselZustand();
    r4.geloest = false;
    r4.raetselname = "Rätsel 4";
    raetsel.add(r4.raetselname);
    RaetselZustand r5 = new RaetselZustand();
    r5.geloest = false;
    r5.raetselname = "Rätsel 5";
    raetsel.add(r5.raetselname);*/
    raetsel.add("Platzhalterrätsel 1");
    raetsel.add("Platzhalterrätsel 2");
    
    JFrame aktivesFenster = new Raetselwahl(fv, raetsel);
    aktivesFenster.setTitle("Rätselwahl");
    aktivesFenster.setSize(1000, 620);
    aktivesFenster.setResizable(true);
    aktivesFenster.setLocation(50, 50);
    aktivesFenster.setVisible(true);
    try {
      Thread.sleep(100000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
