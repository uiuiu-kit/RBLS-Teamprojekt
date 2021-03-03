package praesentation.tabelle;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Unterklasse von DefaultTableCellRenderer zum Aendern der Farben einzelner Tabellenzellen.

 * @author Nick
 */
public class FarbRenderer extends DefaultTableCellRenderer {
  /**
   * Standard-ID der Unterklasse.
   */
  private static final long serialVersionUID = 1L;

  @Override
  public Component getTableCellRendererComponent(
      JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
    JLabel l = (JLabel) super.getTableCellRendererComponent(
        table, value, isSelected, hasFocus, row, col);
    FarbModell fm = (FarbModell) table.getModel();
    switch (fm.gibStatus(row, col)) {
      case standard:l.setBackground(Color.WHITE);
      break;
      case atomar:l.setBackground(new Color(186, 185, 219));
      break;
      case wahr:l.setBackground(new Color(133, 242, 184));
      break;
      case falsch:l.setBackground(new Color(242, 133, 133));
      break;
      case markiert_wahr:l.setBackground(Color.GREEN.darker());
      break;
      case markiert_falsch:l.setBackground(Color.RED);
      break;
      case tipp:l.setBackground(Color.DARK_GRAY);
      break;
      default: l.setBackground(Color.WHITE);
    }
    setHorizontalAlignment(javax.swing.JLabel.CENTER);
    return l;
  }
}