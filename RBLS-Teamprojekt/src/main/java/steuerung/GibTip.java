package steuerung;

import modell.SteuerungFassade;

public class GibTip extends WahrheitstabellenBefehl {

  private TabellenPruefer tabellenPruefer;
  private int stufe;
  
  public GibTip(SteuerungFassade model, TabellenPruefer tabellenPruefer) {
    super(model);
    this.tabellenPruefer = tabellenPruefer;
  }

  @Override
  public void hohleDaten() {
    stufe = model.gibStufe();
    
  }

  @Override
  public void setzeDaten() {
    // TODO Auto-generated method stub

  }

}
