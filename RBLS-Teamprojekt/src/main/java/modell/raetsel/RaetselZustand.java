package modell.raetsel;

/**
 * Diese Klasse gehoert zum Memento und beinhaltet den Zustand des konkreten Raetsels
 * sowie dessen Namen.
 * @author janne
 *
 */
public class RaetselZustand {

  private String raetselname;
  private Raetsel raetsel;
  private int stufe;
  private boolean geloest = false;
  
  /**
   * Ein Objekt, welches den aktuellen Zustand des Rätsels enthält.
   * @param r Ein Rätselobjekt.
   * @param stufe Die zum Raetsel gehoerige Stufe.
   * @param geloest True, wenn das Raetsel erfolgreich geloest wurde.
   */
  public RaetselZustand(Raetsel r, boolean geloest) {
    this.raetsel = r;
    this.stufe = r.gibStufe();
    this.raetselname = r.gibName();
    this.geloest = geloest;
  }
  
  public int gibStufe() {
    return this.stufe;
  }
  
  public String gibRaetselname() {
    return this.raetselname;
  }
  
  public boolean gibGeloest() {
    return this.geloest;
  }
  
}
