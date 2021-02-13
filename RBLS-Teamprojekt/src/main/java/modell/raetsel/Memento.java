package modell.raetsel;

/**
 * Diese Klasse symbolisiert ein Memento. Sie verwaltet die Speicherung und das Abrufen der Raetsel
 * sowie deren Zustaende.
 * @author janne
 *
 */
public class Memento {
  
  private RaetselZustand zustand;
  
  /**
   * Konstruktor fuer ein Memento. Dieses speichert den aktuellen Spielstand,
   * also welches Rätsel zuletzt geloest wurde.
   * @param r Das Raetsel, welches gespeichert werden soll.
   */
  public Memento (Raetsel r) {
    this.zustand = new RaetselZustand(r, true);
  }
  
  public RaetselZustand gibSicherung() {
    return this.zustand;
  }
  
  public void loesche() {
    this.zustand = null;
  }
  
}
