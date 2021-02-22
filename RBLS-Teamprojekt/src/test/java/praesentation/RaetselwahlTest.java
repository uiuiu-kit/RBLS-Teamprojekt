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
            Fassade.gibFa());
    fv.init();
    List<String> raetsel = new ArrayList<String>();
    List<String> geloest = new ArrayList<String>();
    
    raetsel.add("Platzhalterraetsel 1");
    raetsel.add("Platzhalterraetsel 2");
    geloest.add("Platzhalterraetsel 1");
    
    JFrame aktivesFenster = new JFrame();
    aktivesFenster.setContentPane(new Raetselwahl(fv, raetsel, geloest, 1));
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
