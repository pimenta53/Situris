package hirondelle.situris.pub.local;

import hirondelle.situris.util.Countries;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelUtil;
import hirondelle.web4j.security.SafeText;

public final class Local {

  public Local(SafeText aCountry) throws ModelCtorException{
    fNomePais = aCountry;
    validateState();
  }
  
  public Local(Id aId, SafeText aCountry) throws ModelCtorException{
    fNomePais = aCountry;
    fIdPais = aId;
  }

  public Id getIdPais() {return fIdPais;}  
  public SafeText getNomePais() {return fNomePais;}
  
  /** Intended for debugging only. */
  @Override public String toString(){
    return ModelUtil.toStringFor(this);
  }

  @Override public boolean equals(Object aThat){
    Boolean result = ModelUtil.quickEquals(this, aThat);
    if ( result == null ) {
      Local that = (Local)aThat;
      result = ModelUtil.equalsFor(this.getSignificantFields(), that.getSignificantFields());
    }
    return result;
  }
  
  @Override public int hashCode(){
    if ( fHashCode == 0 ) {
      fHashCode = ModelUtil.hashCodeFor(getSignificantFields());
    }
    return fHashCode;
  }
  
  //PRIVATE 
  private Id fIdPais;
  private SafeText fNomePais;
  private int fHashCode;
 
  private Object[] getSignificantFields(){
    return new Object[] {fIdPais, fNomePais};
  }
  
  private void validateState() throws ModelCtorException {
    ModelCtorException ex = new ModelCtorException();
    
    if(!contains(fNomePais.getRawString())) ex.add("The Country doesn't exist in our system!");
    if(!ex.isEmpty()) throw ex;
  }

  public static boolean contains(String test) {

    for (Countries c : Countries.values()) {
      if (c.name().equals(test)) {
        return true;
      }
    }
    return false;
  }
}
