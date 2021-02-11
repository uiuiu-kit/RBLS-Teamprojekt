package steuerungstests;

import java.util.ArrayList;
import java.util.List;
import modell.SteuerungFassade;
import modell.formel.Atom;
import modell.formel.Formel;
import modell.raetsel.Raetsel;
import modell.raetsel.Raetselinterpret;
import modell.tabelle.Tabelle;
import org.junit.Before;
import org.junit.Test;

import steuerung.FormelParser;
import steuerung.WahrheitstabellenSteuerungen;

public class BackendTest {
  SteuerungFassade sf;
  WahrheitstabellenSteuerungen wts;

  /**
   * Baut die SteuerungFassade mit allen Komponenten auf und übergiebt sie der
   * WahrheitstabellenSteuerungen. Dabei sind Antwortmöglichkeit, Lösung und
   * Rätseltext nur Dummys da sie nicht benötigt werden in diesen Tests. Die
   * Stufe, die Formeln und die Atome können noch geändert werden.
   */

  @Before
  public void setup() {
    sf = new SteuerungFassade();
    
    List<Atom> atomareAussagen = new ArrayList<Atom>();
    atomareAussagen.add(new Atom("Charlie", "C", 0));
    atomareAussagen.add(new Atom("Donald", "D", 1));
    atomareAussagen.add(new Atom("Edgar", "E", 2));
    
    List<Formel> noetigeFormeln = new ArrayList<Formel>();
    noetigeFormeln.add(FormelParser.pars("1u2", sf));
    noetigeFormeln.add(FormelParser.pars("0o2", sf));
    noetigeFormeln.add(FormelParser.pars("n0", sf));
    
    int stufe = 1;
    int anzAtome = atomareAussagen.size();
    String[] awm = { "XX", "YY" };
    
    //Rätsel irgendwie mit den Daten erstellen
    //sf.setRaetsel(raetsel);
    
    Tabelle tabelle = new Tabelle((int) Math.pow(2, anzAtome), anzAtome, anzAtome);
    //sf.setTabelle(tabelle);
    
    wts = new WahrheitstabellenSteuerungen(sf);
  }

  @Test
  public void aufbauTabelle1Test() {
    wts.befehl("AufbauTabelle()");
    String fall;
    int[] koordinate = new int[2];
    for (int i = 0; i < sf.gibZeilenAnz(); i++) {
      fall = "";
      koordinate[0] = i; 
      for (int j = 0; j < sf.gibSpaltenAnz(); j++) {
        koordinate[1] = j;
        fall = fall + sf.gibZelleWW(koordinate);
      }
      System.out.println(fall);
    }
  }
}
