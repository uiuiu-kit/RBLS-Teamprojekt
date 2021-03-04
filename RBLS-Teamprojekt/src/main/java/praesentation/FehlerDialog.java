package praesentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Oeffnet ein modales Dialogfenster mit Text und einer Schaltflaeche, um es wieder zu schliessen.
 * @author Nick
 */
public class FehlerDialog {
  private int hoehe = 200;
  private int breite = 600;
  
  public FehlerDialog(String text) {
    JDialog dialog = new JDialog();
    dialog.getContentPane().setLayout(new BorderLayout());
    JLabel dialogtext = new JLabel(text, SwingConstants.CENTER);
    dialogtext.setFont(new javax.swing.plaf.FontUIResource("Arial Unicode MS", Font.BOLD, 20));
    dialogtext.setForeground(Color.WHITE);
    Schaltflaeche button = new Schaltflaeche("OK", Schaltflaeche.WEISS);
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(button);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dialog.dispose();
      }
    });
    dialog.getContentPane().add(dialogtext, BorderLayout.CENTER);
    dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    dialog.setSize(breite, hoehe);
    dialog.setLocationRelativeTo(null);
    dialog.setAlwaysOnTop(true);
    dialog.getContentPane().setBackground(new Color(255, 102, 0));
    buttonPanel.setBackground(new Color(255, 102, 0));
    dialog.setModal(true);
    dialog.setResizable(false);
    dialog.setVisible(true);
  }
  
}
