package modell.formel;

/**Abstrakte Oberklasse der Formeln. Von ihr erbt das Atom und der Konnektor direkt.

 * @author Flo
 *
 */
public abstract class Formel {
    
  protected String rep;
  protected int bindungsstaerke;
  
  /**Beim Auswerten wird ein Wahrheitswert ermittelt und zurueckgegeben. 
   * Im Falle des Atoms ist dies der eigene Wahrheitswert. Ansonsten der Wahrheitswert des rechten
   * und, falls vorhanden, des linken Formelnachbarn logisch verknuepft. (Nachbar.auswerten())
   * Die Belegung der Atome wird mitgegeben.

   * @param werte Die Belegung der atomaren Aussagen in Form eines booleschen Arrays.
   * @return Das Ergebnis der Wahrheitswertberechnung.
   */
  public abstract boolean auswerten(boolean[] werte);
    
  public String gibStringRep() {
    return null;
  }
}
