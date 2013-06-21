 package hirondelle.situris.users.editaccount;

import static hirondelle.web4j.util.Consts.FAILS;
import hirondelle.web4j.model.Check;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelUtil;
import hirondelle.web4j.security.SafeText;

/** Model Object for a registering user, creating a new account. */
public final class Account {

  /**
  * Full constructor. All params are required. 
  *  
  * @param aLoginName Name used when logging in (required)
  * @param aScreenName User's name as typically displayed on the screen (required)
  * @param aPassword Login password, 6..50 chars (required)
  * @param aPasswordConfirm must be same as password (required)
  * @param aEmail Valid email address (required)
  */
  public Account(SafeText aName, SafeText aPassword, SafeText aPasswordConfirm, SafeText aEmail) throws ModelCtorException {
    fName = aName;
    fPassword = aPassword;
    fPasswordConfirm = aPasswordConfirm;
    fEmail = aEmail;
    validateState();
  }
  
  public SafeText getName() { return fName; }  
  public SafeText getPassword() { return fPassword; }  
  public SafeText getPasswordConfirm() { return fPasswordConfirm; }  
  public SafeText getEmail() { return fEmail; }  

  /** Intended for debugging only. Passwords are not emitted. */
  @Override public String toString(){
    StringBuilder builder = new StringBuilder();
    builder.append(Account.class);
    builder.append(" Name " + fName +  " Email: " + fEmail);
    return builder.toString();
  }
 
  @Override public boolean equals(Object aThat){
    Boolean result = ModelUtil.quickEquals(this, aThat);
    if ( result == null ) {
      Account that = (Account)aThat;
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

  // PRIVATE //
  private final SafeText fName;
  private final SafeText fPassword;
  private final SafeText fPasswordConfirm;
  private final SafeText fEmail;
  private int fHashCode;

  private void validateState() throws ModelCtorException {
    ModelCtorException ex = new ModelCtorException();
    
    if ( FAILS == Check.optional(fName, Check.range(6, 50)) ) {
      ex.add("Screen Name is required, minimum 6 characters.");
    }
    if ( FAILS == Check.optional(fPassword, Check.range(6, 50)) ) {
      ex.add("Password is required, minimum 6 characters.");
    }
    if ( FAILS == Check.optional(fPasswordConfirm, Check.range(6, 50)) ) {
      ex.add("Password confirmation is required, must match the password supplied above.");
    }
    if( passwordsDontMatch() ){
      ex.add("Password confirmation doesn't match the original password.");      
    }
    if ( FAILS == Check.optional(fEmail, Check.email()) ) {
      ex.add("Valid email address is required (in case you forget your password).");
    }

    if ( ! ex.isEmpty() ) throw ex;
  }
  
  private Object[] getSignificantFields(){
    return new Object[] {fName, fPassword, fPasswordConfirm, fEmail};
  }
  
  private boolean passwordsDontMatch(){
    if (fPassword == null || fPasswordConfirm == null) return false;
    return ! fPassword.equals(fPasswordConfirm);
  }
}