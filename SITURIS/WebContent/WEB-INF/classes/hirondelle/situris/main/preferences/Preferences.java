package hirondelle.situris.main.preferences;

import static hirondelle.web4j.util.Consts.FAILS;
import hirondelle.web4j.model.Check;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelUtil;
import hirondelle.web4j.security.SafeText;

import java.util.regex.Pattern;

/**
 User preferences. 
 
 <P>User preferences are placed into session scope by this app's implementation of {@link hirondelle.web4j.security.LoginTasks}.
*/
public final class Preferences {
  
  /** Key under which user's screen name is saved in session scope. */
  public static final String NAME = "name";

  /** 
   Constructor. 
  
   @param aUserId required.
   @param aUserName required, <tt>6..50</tt> characters, no spaces.
   @param aName optional, <tt>6..50</tt> characters.
  */
  public Preferences(Id aIdUser, SafeText aUserName, SafeText aName, SafeText aRole) throws ModelCtorException {
    fIdUser = aIdUser;
    fUserName = aUserName;
    fName = aName;
    fRole = aRole;
    validateState();
  }
  
  /** Return the user id passed to the constructor. */
  public Id getIdUser() { return fIdUser; }
  
  /** Return the login name passed to the constructor. */
  public SafeText getUserName() {  return fUserName; }
  
  /** Return the screen name passed to the constructor. */
  public SafeText getName() {  return fName; }  
  
  /** Return the role passed to the constructor. */
  public SafeText getRole() {  return fRole; }
  
  /** Intended for debugging only.  */
  public @Override String toString() {
    return ModelUtil.toStringFor(this);
  }

  public @Override boolean equals( Object aThat ) {
    Boolean result = ModelUtil.quickEquals(this, aThat);
    if ( result == null ){
      Preferences that = (Preferences) aThat;
      result = ModelUtil.equalsFor(this.getSignificantFields(), that.getSignificantFields());
    }
    return result;    
  }

  public @Override int hashCode() {
    if ( fHashCode == 0 ) {
      fHashCode = ModelUtil.hashCodeFor(getSignificantFields());
    }
    return fHashCode;
  }
   
  //PRIVATE//
  private final Id fIdUser;
  private final SafeText fUserName;
  private final SafeText fName;
  private final SafeText fRole;
  private int fHashCode;
  
  private static final Pattern USER_NAME = Pattern.compile("(?:\\S){6,50}");
  private static final Pattern NAME_REGEX = Pattern.compile("(?:.){6,50}");

  private void validateState() throws ModelCtorException {
    ModelCtorException ex = new ModelCtorException();
    if ( FAILS == Check.required(fIdUser) ) {
      ex.add("User id is required (programmer error).");
    }
    if ( FAILS == Check.required(fUserName,  Check.pattern(USER_NAME)) ) {
      ex.add("Login Name is required, 6..50 chars, no spaces.");
    }
    if ( FAILS == Check.optional(fName,  Check.pattern(NAME_REGEX)) ) {
      ex.add("Screen Name is optional, 6..50 chars.");
    }
    if ( ! ex.isEmpty() ) throw ex;
  }
   
  private Object[] getSignificantFields(){
    return new Object[] {fUserName, fName, fRole};
  }
}
