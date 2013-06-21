package hirondelle.situris.main.roteiros;

import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;

public class Refs {

  public Refs(Id aIdPr, Integer pos) throws ModelCtorException{
    fIdPr = aIdPr;
    fPos = pos;
  }
  
  public Id getIdPr(){return fIdPr;}
  public Integer getPos(){return fPos;}
  
  private Id fIdPr;
  private Integer fPos;
}
