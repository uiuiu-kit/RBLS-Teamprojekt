package modell;

import java.util.List;

public class PraesentationFassade {

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
    
    String aktiverRaetselname = RaetselZustand.raetselname;
    return aktiverRaetselname;
  }
  
  public String gibFragestellung() {
    
    return Raetsel.raetselText();
  }
  
  public String gibAntwortText() {
    
    return Raetsel.gibAntworttext();
  }

  public List<String> gibAntwortmoeglichkeiten() {
    
    return Raetsel.gibAntwort();
  }
  
  public String gibLoesung() {
    
    return Raetsel.gibAntwort();
  }
  
  public void gibAktuelleStufe() {
    
  }
  
  public String gibZelle(int[] zelle) {
    
    return Tabelle.gibAtomareAussage(zelle);
  }
  
  public boolean gibZellenWert(int[] zelle) {
    
    return Tabelle.gibZellenWert(zelle);
  }
  
  public void erstelleRaetsel(List<String> atome) {
    
  }
  
  public int gibZeilenAnz() {
    
    return Tabelle.gibZeilenAnz();
  }
  
  public int gibSpaltenAnz() {
    
    return Tabelle.gibSpaltenAnz();
  }
  
  public String gibFormelText(int spalte) {
    
    return String s;
  }
}
