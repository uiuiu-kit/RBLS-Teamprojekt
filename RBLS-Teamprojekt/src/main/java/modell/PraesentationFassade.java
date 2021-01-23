package modell;

import java.util.List;

import modell.raetsel.Raetsel;
import modell.raetsel.RaetselZustand;
import modell.tabelle.Tabelle;

public class PraesentationFassade {
  
  private RaetselZustand rZustand = new RaetselZustand();
  private Raetsel raetsel = new Raetsel();
  private Tabelle tabelle = new Tabelle(raetsel.zeilenAnz, raetsel.spaltenAnz, raetsel.atomAnz);

  public void setzeRaetsel(String s) {
    //TODO
  }
  
  public List<RaetselZustand> gibRaetselListe(int i) {
    //TODO;
    return null;
  }

  public List<String> gibRaetselnamen() {
    //TODO;
    return null;
  }
  
  public String gibAktivenRaetselnamen() {
    return rZustand.raetselname;
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
    //TODO
    return null;
  }
  
  public int gibAktuelleStufe() {
    return raetsel.gibStufe();
  }
  
  public String gibZelle(int[] zelle) {
    //TODO
    //return tabelle.gibAtomareAussage(zelle);
    return null;
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
    //TODO
    return null;
  }
}
