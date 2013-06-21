package hirondelle.situris.pub.home;

import hirondelle.web4j.model.DateTime;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelUtil;
import hirondelle.web4j.security.SafeText;

public final class Evento {
  
  public Evento(Id aIdEvento, SafeText aNome, DateTime aDataInicio, DateTime aDataFim, SafeText aLink) 
      throws ModelCtorException{
    fIdEvento = aIdEvento;
    fNome = aNome;
    fLink = aLink;
    fDataInicio = aDataInicio;
    fDataFim = aDataFim;
  }
  
  public Id getIdEvento() {return fIdEvento;}  
  public SafeText getNome() {return fNome;}
  public SafeText getLink() {return fLink;}
  public DateTime getDataInicio() {return fDataInicio;}
  public DateTime getDataFim() {return fDataFim;}
  
  /** Intended for debugging only. */
  @Override public String toString(){
    return ModelUtil.toStringFor(this);
  }

  @Override public boolean equals(Object aThat){
    Boolean result = ModelUtil.quickEquals(this, aThat);
    if ( result == null ) {
      Evento that = (Evento)aThat;
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
  private Id fIdEvento;
  private SafeText fNome;
  private SafeText fLink;
  //for Events
  private DateTime fDataInicio;
  private DateTime fDataFim;
  
  private int fHashCode;
 
  private Object[] getSignificantFields(){
    return new Object[] {fNome, fLink, fDataInicio, fDataFim};
  }
}
