package hirondelle.situris.pub.home;

import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelUtil;
import hirondelle.web4j.security.SafeText;

public final class Roteiro {
  
  public Roteiro(Id aIdEvento, SafeText aNome, SafeText aDescricaoTI, Integer aEstrelas) throws ModelCtorException{
    fIdRoteiro = aIdEvento;
    fNome = aNome;
    fDescricaoTI = aDescricaoTI;
    fEstrelas = aEstrelas;
  }
  
  public Id getIdRoteiro() {return fIdRoteiro;}  
  public SafeText getNome() {return fNome;}
  public SafeText getDescricaoTI() {return fDescricaoTI;}
  public Integer getEstrelas() {return fEstrelas;}
  
  /** Intended for debugging only. */
  @Override public String toString(){
    return ModelUtil.toStringFor(this);
  }

  @Override public boolean equals(Object aThat){
    Boolean result = ModelUtil.quickEquals(this, aThat);
    if ( result == null ) {
      Roteiro that = (Roteiro)aThat;
      result = ModelUtil.equalsFor(this.getSignificantFields(), that.getSignificantFields());
    }
    return result;
  }
  
  @Override public int hashCode(){
    if (fHashCode == 0) {
      fHashCode = ModelUtil.hashCodeFor(getSignificantFields());
    }
    return fHashCode;
  }
  
  //PRIVATE 
  private Id fIdRoteiro;
  private SafeText fNome;
  private SafeText fDescricaoTI;
  private Integer fEstrelas;
  private int fHashCode;
 
  private Object[] getSignificantFields(){
    return new Object[] {fNome, fEstrelas, fDescricaoTI};
  }
}
