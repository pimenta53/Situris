package hirondelle.situris.main.pontosReferencia;

import hirondelle.web4j.model.Id;

public class PtRefPosicao {

  public PtRefPosicao(Id aIdRoteiro, Integer aPosicao){
    fIdRoteiro = aIdRoteiro;
    fPosicao = aPosicao;
  }
  
  public Id getIdRoteiro(){return fIdRoteiro;}
  public Integer getPosicao(){return fPosicao;}
  
  private Id fIdRoteiro;
  private Integer fPosicao;
}
