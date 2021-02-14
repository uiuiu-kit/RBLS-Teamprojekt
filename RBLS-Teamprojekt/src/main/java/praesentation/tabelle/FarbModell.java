package praesentation.tabelle;

import javax.swing.table.DefaultTableModel;

/** Unterklasse von DefaultTableModel, um einen Status von Zellen zu setzten, 
 * aus dem der Farbwert hervorgeht.
 * @author Nick
 */
public class FarbModell extends DefaultTableModel {
  /**
   * Standard-ID der Unterklasse.
   */
  private static final long serialVersionUID = 1L;
  ZellenStatus[][] status;
  
  /**
   * Erzeugt eine Tabelle mit dem angegebenem Inhalt.
   * @param inhalt Zweidimensionaler Array mit dem Tabelleninhalt als Strings
   * @param inhalt2 Array mit Inhalten der obersten Zeile
   */
  public FarbModell(String[][] inhalt, String[] inhalt2) {
    status = new ZellenStatus[inhalt.length][inhalt[0].length];
    this.setDataVector(inhalt, inhalt2);
    for (int i = 0; i < inhalt.length; i++) {
      for (int j = 0; j < inhalt[0].length; j++) {
        status[i][j] = ZellenStatus.standard;
      }
    }    
  }
  
  public boolean isCellEditable(int row, int column) {
    return false;
  }

  public void setzeStatus(int i, int j, ZellenStatus s) {
    status[i][j] = s;
  }
    
  public ZellenStatus gibStatus(int i, int j) {
    return status[i][j];
  }
}
