package praesentation.tabelle;

import javax.swing.table.DefaultTableModel;


public class FarbModell extends DefaultTableModel {
  ZellenStatus[][] status;
  
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
