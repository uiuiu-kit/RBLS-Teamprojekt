package modell.formel;

/**Abstrakte Unterklasse der Formel, von ihr erbt der NICHT-Konnektor und der BiKonnektor direkt.

 * @author Flo
 *
 */
public abstract class Konnektor extends Formel {
  
  protected Formel rechts;
  protected String zeichen;
}

