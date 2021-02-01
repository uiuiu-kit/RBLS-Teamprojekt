package modell.formel;

/**Abstrakte Oberklasse der Formeln. von ihr erbt das Atom und der Konnektor direkt.
 * @author Flo
 *
 */
public abstract class Formel {
    
  protected String rep;
  
  /**Beim Auswerten wird ein Wahrheitswert ermittelt und zurückgegeben. 
   * Im Falle des Atoms ist dies der eigene Wahrheitswert, ansonsen der Wahrheitswert des rechten, 
   * und falls vorhanden des linken, Formelnachbarn logisch verknüpft. (Nachbar.auswerten())
   * Die Belegung der Atome wird mitgegeben.
   * @param werte Belegung der Atomaren Aussagen in Form eines boolschen Arrays.
   * @return Ergebnis der Wahrheitswertberechnung.
   */
  public abstract boolean auswerten(boolean[] werte);
    
  public String gibStringRep() {
    return null;
  }
}
