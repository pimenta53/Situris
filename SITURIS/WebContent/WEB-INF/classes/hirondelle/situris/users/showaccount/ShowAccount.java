package hirondelle.situris.users.showaccount;

import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelUtil;
import hirondelle.web4j.security.SafeText;

public class ShowAccount {

  public ShowAccount(Id aIdUser, SafeText aUsername, SafeText aName, SafeText aEmail, SafeText aRole){
    fIdUser = aIdUser;
    fUsername = aUsername;
    fName = aName;
    fEmail = aEmail;
    fRole = aRole;
  }
  
  public Id getIdUser() {return fIdUser;}
  public SafeText getUsername() {return fUsername;}  
  public SafeText getName() {return fName;}
  public SafeText getEmail() {return fEmail;}  
  public SafeText getRole() {return fRole;}
  
  /** Intended for debugging only. */
  @Override public String toString(){
    return ModelUtil.toStringFor(this);
  }

  @Override public boolean equals(Object aThat){
    Boolean result = ModelUtil.quickEquals(this, aThat);
    if ( result == null ) {
      ShowAccount that = (ShowAccount)aThat;
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
    
  private Id fIdUser;
  private SafeText fUsername;
  private SafeText fName;
  private SafeText fEmail;
  private SafeText fRole;
  private int fHashCode;
  

  private Object[] getSignificantFields(){
    return new Object[] {fUsername, fName, fEmail, fRole};
  }
}
