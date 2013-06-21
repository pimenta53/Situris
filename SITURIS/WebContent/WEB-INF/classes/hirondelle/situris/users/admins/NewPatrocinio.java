package hirondelle.situris.users.admins;

import static hirondelle.web4j.util.Consts.FAILS;
import hirondelle.web4j.model.Check;
import hirondelle.web4j.model.DateTime;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelUtil;
import hirondelle.web4j.security.SafeText;

public class NewPatrocinio {

  public NewPatrocinio(DateTime aBeginDate, DateTime aEndDate, SafeText aDescription, Id aIdUser, Id aIdProp,
      Id aIdCat, Id aIdPerfil, Id aIdPoI) throws ModelCtorException{
    fBeginDate = aBeginDate;
    fEndDate = aEndDate;
    fDescription = aDescription;
    fIdUser = aIdUser;
    fIdProp = aIdProp;
    fIdCat = aIdCat;
    fIdPerfil = aIdPerfil;
    fIdPoI = aIdPoI;
    validateState();
  }
  
  public DateTime getBeginDate() {return fBeginDate;}
  public DateTime getEndDate() {return fEndDate;}
  public SafeText getDescription() {return fDescription;}
  public Id getIdUser() {return fIdUser;}
  public Id getfIdProp() {return fIdProp;}
  public Id getfIdCat() {return fIdCat;}
  public Id getfIdPerfil() {return fIdPerfil;}
  public Id getfIdPoI() {return fIdPoI;}
  
  @Override public String toString(){
    StringBuilder builder = new StringBuilder();
    builder.append(NewPatrocinio.class);
    builder.append(" Begin Date: " + fBeginDate +  " End Date: " + fEndDate);
    return builder.toString();
  }
 
  @Override public boolean equals(Object aThat){
    Boolean result = ModelUtil.quickEquals(this, aThat);
    if ( result == null ) {
      NewPatrocinio that = (NewPatrocinio)aThat;
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
  private final DateTime fBeginDate;
  private final DateTime fEndDate;
  private final SafeText fDescription;
  private final Id fIdUser;
  private final Id fIdProp;
  private final Id fIdCat;
  private final Id fIdPerfil;
  private final Id fIdPoI;
  private int fHashCode;

  private void validateState() throws ModelCtorException {
    ModelCtorException ex = new ModelCtorException();
    
    if ( FAILS == Check.optional(fDescription, Check.range(6, 50)) ) {
      ex.add("Description is optional but must have at least 6 characters.");
    }

    if ( ! ex.isEmpty() ) throw ex;
  }
  
  private Object[] getSignificantFields(){
    return new Object[] {fBeginDate, fEndDate, fDescription};
  }
}
