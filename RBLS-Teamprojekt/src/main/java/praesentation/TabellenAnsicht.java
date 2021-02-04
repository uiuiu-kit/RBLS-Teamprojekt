package praesentation;

import javax.swing.JTable;

/**
 * Vorlage für die Ansicht einer Wahrheitstabelle.
 * @author Nick
 */
public abstract class TabellenAnsicht {

  protected JTable tabelle;
  
  public abstract void zeigeTippAn();

  public abstract void aktualisiere(int[] zelle);
  
}