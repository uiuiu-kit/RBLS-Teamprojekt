package modell;

import java.util.List;
import modell.raetsel.Raetsel;
import modell.raetsel.RaetselZustand;
import modell.raetsel.Raetselinterpret;
import modell.tabelle.Tabelle;

public class PraesentationFassade {
  
  private Raetselinterpret interpret;
  private RaetselZustand raetselZustand = new RaetselZustand();
  private Raetsel raetsel;
  private Tabelle tabelle;

  public void setzeRaetsel(Raetsel r, Tabelle t, Raetselinterpret i) {
    this.interpret = i;
    this.raetsel = r;
    this.tabelle = t;
  }
  
  //laut Sequenzdiagramm so, aber im uml anders
  public List<String> gibRaetselListe(int i) {
    return interpret.liesOrdner(i);
  }

  public List<String> gibRaetselnamen() {
    //TODO
    return null;
  }
  
  public String gibAktivenRaetselnamen() {
    return raetselZustand.raetselname;
  }
  
  public String gibFragestellung() {
    return raetsel.gibRaetselText();
  }
  
  public String gibAntwortText() {
    return raetsel.gibAntworttext();
  }

  public List<String> gibAntwortmoeglichkeiten() {
    return raetsel.gibAntwort();
  }
  
  public String gibLoesung() {
    return raetsel.gibLoesung();
  }
  
  public int gibAktuelleStufe() {
    return raetsel.gibStufe();
  }
  
  public String gibZelle(int[] zelle) {
    return tabelle.gibZelle(zelle);
  }
  
  public boolean gibZellenWert(int[] zelle) {
    return tabelle.gibZellenWert(zelle);
  }
  
  public void erstelleRaetsel(List<String> atome) {
    //TODO
  }
  
  public int gibZeilenAnz() {
    return tabelle.gibZeilenAnz();
  }
  
  public int gibSpaltenAnz() {
    return tabelle.gibSpaltenAnz();
  }
  
  public String gibFormelText(int spalte) {
    return tabelle.gibFormelText(spalte);
  }
}
