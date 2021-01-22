package modell.formel;

public class Atom extends Formel{

  private String aussage;
    
  public Atom(String repraesentation) {
    this.aussage = repraesentation;
  }

  public String getAussage() {
    return aussage;
  }

}
