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

  }
  
  public List<RaetselZustand> gibRaetselListe(int i) {
    List<RaetselZustand> zustand;
    return zustand;
  }

  public List<String> gibRaetselnamen() {
    List<String> namen;
    return namen;
  }
  
  public String gibAktivenRaetselnamen() {
    
    String aktiverRaetselname = rZustand.raetselname;
    return aktiverRaetselname;
  }
  
  public String gibFragestellung() {
    
    return raetsel.raetselText();
  }
  
  public String gibAntwortText() {
    
    return raetsel.gibAntworttext();
  }

  public List<String> gibAntwortmoeglichkeiten() {
    
    return raetsel.gibAntwort();
  }
  
  public String gibLoesung() {
    
    return raetsel.gibAntwort();
  }
  
  public void gibAktuelleStufe() {
    
  }
  
  public String gibZelle(int[] zelle) {
    
    return tabelle.gibAtomareAussage(zelle);
  }
  
  public boolean gibZellenWert(int[] zelle) {
    
    return tabelle.gibZellenWert(zelle);
  }
  
  public void erstelleRaetsel(List<String> atome) {
    
  }
  
  public int gibZeilenAnz() {
    
    return tabelle.gibZeilenAnz();
  }
  
  public int gibSpaltenAnz() {
    
    return tabelle.gibSpaltenAnz();
  }
  
  public String gibFormelText(int spalte) {
    
    return String s;
  }
}
