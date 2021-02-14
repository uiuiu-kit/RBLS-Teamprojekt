package steuerung;

import modell.Fassade;

public class WahrheitstabellenSteuerungen {

  private Fassade model;
  private TabellenPruefer tabellenPruefer;
  private int stufe;

  /**
   * Konstruktor für die WahrheitstabellenSteuerungen.
   * 
   * @param model die Fassade die von Befehlen angesprochen werden soll
   */
  public WahrheitstabellenSteuerungen(Fassade model) {
    this.model = model;
    this.stufe = model.gibStufe();
    tabellenPruefer = new TabellenPruefer(model, stufe);
  }

  /**
   * löst Befehl von der Präsentation auf und konstruiert das passenden
   * Befehls-Objekt und stößt damit dessen Ausführung an.
   * 
   * @param befehl der auszuführende Befehl
   */
  public void befehl(String befehl) {
    String[] split = befehl.split("\\(");
    String befehlsname = split[0];
    String[] parameter = null;
    if (split.length > 1) {
      split[1] = split[1].substring(0, split[1].length() - 1);
      parameter = split[1].split("\\,");
    }
    switch (befehlsname) {
      case "AufbauTabelle":
        switch (stufe) {
          case 1:
            new AufbauTabelle1(model);
            break;
          case 2:
          case 4:
            new AufbauTabelle24(model);
            break;
          case 3:
            new AufbauTabelle3(model);
            break;
          default:
            break;
        }
        break;
      case "FormelEingeben":
        new FormelEingeben(model, Integer.parseInt(parameter[0]));
        break;
      case "FuelleTabelle":
        if ((stufe == 1 || stufe == 2 || stufe == 4)) {
          new FuelleTabelle(model);
        }
        break;
      case "SpalteEntfernen":
        new SpalteEntfernen(model, Integer.parseInt(parameter[0]));
        break;
      case "SpalteHinzufuegen":
        new SpalteHinzufuegen(model);
        break;
      case "ZelleAendern":
        new ZelleInBlauOrangeAendern(model, Integer.parseInt(parameter[0]),
            Integer.parseInt(parameter[1]));
        ueberpruefeTabelle(Integer.parseInt(parameter[0]), Integer.parseInt(parameter[1]));
        break;
      default:
        break;
    }
  }

  public int[] gibTip() {
    return tabellenPruefer.gibFehlerhafteZelle();
  }

  /**
   * überprüft ob der Inhalt der Tabelle korrekt ist.
   * 
   * @return Korrektheit
   */
  private void ueberpruefeTabelle(int spalte, int zeile) {
    int[] koordinate = { spalte, zeile };
    if (stufe == 1) {
      if (koordinate[0] < model.gibAtomareAussage().size()) {
        tabellenPruefer.ueberpuefeFaelle(koordinate);
      }
    }
    if (stufe == 3) {
      if (koordinate[1] > 0 && koordinate[0] > model.gibAtomareAussage().size()) {
        tabellenPruefer.ueberpuefeWW(koordinate);
      }
    }
  }
}