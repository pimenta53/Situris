package hirondelle.situris.pub.home;

import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelUtil;
import hirondelle.web4j.security.SafeText;

public final class Patrocinio {
  
  public Patrocinio(Id aIdPatrocinio, Id aIdEvento, SafeText aNomeEvento, Id aIdVisita, SafeText aNomeVisita)
      throws ModelCtorException{
    fIdPatrocinio = aIdPatrocinio;
    fIdEvento = aIdEvento;
    fIdVisita = aIdVisita;
    fNomeEvento = aNomeEvento;
    fNomeVisita = aNomeVisita;
  }
  
  public Id getIdPatrocinio() {return fIdPatrocinio;}  
  public Id getIdEvento() {return fIdEvento;}  
  public Id getIdVisita() {return fIdVisita;}  
  public SafeText getNomeEvento() {return fNomeEvento;}
  public SafeText getNomeVisita() {return fNomeVisita;}
  
  /** Intended for debugging only. */
  @Override public String toString(){
    return ModelUtil.toStringFor(this);
  }

  @Override public boolean equals(Object aThat){
    Boolean result = ModelUtil.quickEquals(this, aThat);
    if ( result == null ) {
      Patrocinio that = (Patrocinio)aThat;
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
  private Id fIdPatrocinio;
  private Id fIdEvento;
  private Id fIdVisita;
  private SafeText fNomeEvento;
  private SafeText fNomeVisita;
  
  private int fHashCode;
 
  private Object[] getSignificantFields(){
    return new Object[] {fNomeEvento, fNomeVisita};
  }
}
