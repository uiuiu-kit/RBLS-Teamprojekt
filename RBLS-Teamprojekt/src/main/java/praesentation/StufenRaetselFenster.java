package praesentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import modell.Fassade;
import steuerung.WahrheitstabellenSteuerungen;

/**
 * Grafische Ansicht eines Raetsels. Zeigt eine Wahrheitstabelle
 * an und stoesst beim Klicken des Menue-Buttons den Wechsel der Ansicht zum
 * Raetselauswahlmenue an.

 * @author Nick
 */
public class StufenRaetselFenster extends RaetselFenster {

  private String name = "[Platzhalterrätsel]";
  private String frage = "[Platzhalterfragetext]";
  
  private JTextArea frageFeld;
  private JPanel antwortAnsicht;
  private Schaltflaeche tipp;
  private Schaltflaeche weiter;
  
  private Fensterverwaltung fv;
  private Fassade modell;

  /**
   * Erstellt die grafische Ansicht eines Stufenraetselfenster und initialisiert die Schaltflaechen.

   * @param fensterverwaltung Fensterverwaltung zum Wechseln des aktiven Fensters
   * @param modell Praesentationsfassade zum Erhalten von Informationen des aktiven Raetsels
   */
  public StufenRaetselFenster(Fensterverwaltung fensterverwaltung,
      Fassade modell, WahrheitstabellenSteuerungen wstrg) {
    this.fv = fensterverwaltung;
    this.modell = modell;
    this.tabelle = new KonkreteTabellenAnsicht(modell, wstrg);
    this.name = modell.gibAktivenRaetselnamen();
    this.frage = modell.gibFragestellung().replaceAll("#", "\n");
    
    ansicht = new JPanel();
    ansicht.setLayout(new BoxLayout(ansicht, BoxLayout.Y_AXIS));
    ansicht.setBackground(Color.WHITE);
    
    //FragePanel//
    JPanel fragePanel = new JPanel();
    fragePanel.setLayout(new BorderLayout());
    fragePanel.setBackground(Color.WHITE);
    
    menueKnopf = new Schaltflaeche("Menü", 2);
    menueKnopf.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          geheZuRaetselwahlMenue();
        }
      });
    JPanel menuePanel = new JPanel();
    menuePanel.setLayout(new FlowLayout());
    menuePanel.add(menueKnopf);
    menuePanel.setBackground(Color.WHITE);
    
    tipp = new Schaltflaeche("Tipp", 2);
    tipp.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          zeigeTippAn();
        }
      });
    JPanel tippPanel = new JPanel();
    tippPanel.setLayout(new FlowLayout());
    tippPanel.add(tipp);
    tippPanel.setBackground(Color.WHITE);
    
    frageFeld = new JTextArea(frage);
    frageFeld.setEditable(false);
    frageFeld.setHighlighter(null);
    frageFeld.setLineWrap(true);
    frageFeld.setWrapStyleWord(true);
    
    JPanel frageFeldPanel = new JPanel();
    frageFeldPanel.setLayout(new BorderLayout());
    JScrollPane schiebeRegler = new JScrollPane(frageFeld);
    schiebeRegler.setBorder(BorderFactory.createEmptyBorder());
    schiebeRegler.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
      protected void configureScrollBarColors() {
          this.thumbColor = new Color(255, 102, 0);
          this.trackColor = new Color(186, 185, 219);
      }
    });
    frageFeldPanel.add(schiebeRegler, BorderLayout.CENTER);
    JPanel frageRahmen = erzeugeRahmenPanel(frageFeldPanel, this.name);
    frageRahmen.setPreferredSize(new Dimension(1, (int) (frage.length() * 0.8)));
    
    fragePanel.add(menuePanel, BorderLayout.WEST);
    fragePanel.add(frageRahmen, BorderLayout.CENTER);
    fragePanel.add(tippPanel, BorderLayout.EAST);
    
    //WahrheitstabellenPanel//
    JPanel tabellenPanel = new JPanel();
    tabellenPanel = tabelle.gibAnsicht();
    tabellenPanel.setBackground(Color.WHITE);
    tabellenPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 1000));
    
    //AntwortfeldPanel//
    JPanel antwortPanel = new JPanel();
    antwortAnsicht = new AntwortFeld(modell.gibAntwortmoeglichkeiten(),
        modell.gibAntwortText(), modell.gibLoesung(), this).gibAnsicht();
    
    JPanel antwortRahmen = erzeugeRahmenPanel(antwortAnsicht, "Lösung");
    antwortPanel.setLayout(new BorderLayout());
    Border antwortBorder = BorderFactory.createEmptyBorder(10, 50, 10, 50);
    antwortPanel.setBorder(antwortBorder);
    antwortPanel.setBackground(Color.WHITE);
    antwortPanel.add(antwortRahmen, BorderLayout.CENTER);
    
    weiter = new Schaltflaeche("WEITER");
    weiter.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          fv.erledigeRaetsel();
        }
      });
    antwortPanel.add(weiter, BorderLayout.EAST);
    weiter.setVisible(false);
    
    //Ansicht zusammenfuegen//
    ansicht.add(fragePanel, 0);
    ansicht.add(tabellenPanel, 1);
    ansicht.add(antwortPanel, 2);
  }
  
  private JPanel erzeugeRahmenPanel(JPanel innen, String titel) {
    JPanel panel1 = new JPanel();
    panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
    Border border1 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    panel1.setBorder(border1);
    panel1.add(innen);
    panel1.setBackground(Color.WHITE);
    
    JPanel panel2 = new JPanel();
    panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
    Border border2 = BorderFactory.createEmptyBorder(1, 1, 1, 1);
    border2 = BorderFactory.createTitledBorder(border2, titel);
    panel2.setBorder(border2);
    panel2.add(panel1);
    panel2.setBackground(new Color(255, 102, 0));
    JPanel panel3 = new JPanel();
    panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
    Border border3 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    panel3.setBorder(border3);
    panel3.add(panel2, BorderLayout.CENTER);
    panel3.setBackground(Color.WHITE);
    
    return panel3;
  }

  private void zeigeTippAn() {
    tabelle.zeigeTippAn();
  }

  private void geheZuRaetselwahlMenue() {
    fv.oeffneRaetselwahl(modell.gibStufe());
  }
  
  /**
   * Gibt an, ob die Wahrheitstabelle ausgefuellt wurde und oeffnet evtl ein Hinweisfenster.
   * @return true falls Tabelle ausgefuellt, ansonsten false
   */
  public boolean pruefeTabelle() {
    if (!((KonkreteTabellenAnsicht) tabelle).istAusgefuellt()) {
      new FehlerDialog("Die Tabelle ist noch nicht (richtig) ausgefüllt");
      return false;
    } else {
      return true;
    }
  }
  
  public void schliesseRaetselAb() {
    weiter.setVisible(true);
  }

}