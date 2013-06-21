package hirondelle.situris.pub.search;

import static hirondelle.web4j.util.Consts.FAILS;
import hirondelle.web4j.model.Check;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelUtil;
import hirondelle.web4j.model.DateTime;
import hirondelle.web4j.security.SafeText;

/** 
 Model object for user input of search criteria.
 
 <P>In this case, the <i>getXXX</i> methods are package-private.
 
 <P>The user enters dates only, without times. The underlying data, the creation date-time of the 
 {@link Prediction}, carries both date and time. To account for this, the {@link #getFromDate()} 
 and {@link #getToDate()} methods return {@link DateTime} objects that are coerced to specific times 
 of day, using {@link DateTime#getStartOfDay()} and {@link DateTime#getEndOfDay()}, respectively.
*/
public final class SearchCriteria {

  /**
   Full constructor.
   
   <P>The dates can occur in any combination. 
   If both the From-Date and To-Date are present, however, 
   then the From-Date can never come after the To-Date.
   
  <P>No checks are made for 'future dates'.
    
   @param aSearchText number of characters in range 2..255 (required)
   @param aSearchStyle required
   @param aFromDate minimum creation date, must never be after <tt>aToDate</tt> (optional)
   @param aToDate maximum creation date (optional)
   */
  public SearchCriteria(SafeText aSearchText) throws ModelCtorException {
    fSearchText = aSearchText;
    validateState();
  }
  
  SafeText getSearchText() {
    return fSearchText; 
  }
 
  @Override public boolean equals(Object aThat){
    Boolean result = ModelUtil.quickEquals(this, aThat);
    if ( result == null ) {
      SearchCriteria that = (SearchCriteria)aThat;
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
  private SafeText fSearchText;
  private int fHashCode;

  private Object[] getSignificantFields(){
    return new Object[] {fSearchText};
  }
  
  private void validateState() throws ModelCtorException {
    ModelCtorException ex = new ModelCtorException();

    if ( FAILS == Check.required(fSearchText, Check.range(3, 255)) ) {
      ex.add("Please input between 3 and 255 characters.");
    }

    if ( ! ex.isEmpty() ) throw ex;
  }
}
