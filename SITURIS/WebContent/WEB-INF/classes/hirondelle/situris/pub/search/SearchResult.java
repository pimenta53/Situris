package hirondelle.situris.pub.search;

import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelUtil;
import hirondelle.web4j.security.SafeText;

/** 
 Simple data carrier for search results. Does no validation.
*/
public final class SearchResult {

  /**
   Constructor.
  */
  public SearchResult(Id aIdPoI, SafeText aNome, Integer aPrivado) throws ModelCtorException {
    fIdPoI = aIdPoI;
    fNome = aNome;
    fPrivado = aPrivado;
    //validation seems redundant here - no user input - would only check db integrity - skip it
  }
  
  public SearchResult(Id aIdPoI, SafeText aNome, Integer aPrivado, Id aIdUser) throws ModelCtorException {
    fIdPoI = aIdPoI;
    fNome = aNome;
    fPrivado = aPrivado;
    fIdUser = aIdUser;
  }

  public Id getIdPoI() {return fIdPoI;}
  public SafeText getNome() {return fNome;}
  public Integer getPrivado() {return fPrivado;}
  public Id getIdUser() {return fIdUser;}

  /** Intended for debugging only. */
  @Override public String toString(){
    return ModelUtil.toStringFor(this);
  }
 
  @Override public boolean equals(Object aThat){
    Boolean result = ModelUtil.quickEquals(this, aThat);
    if ( result == null ) {
      SearchResult that = (SearchResult)aThat;
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
  
  // PRIVATE 
  private Id fIdPoI;
  private SafeText fNome;
  private Integer fPrivado;
  private Id fIdUser;
  private int fHashCode;
  
  private Object[] getSignificantFields(){
    return new Object[] {fNome, fPrivado, fIdUser};
  }
}
