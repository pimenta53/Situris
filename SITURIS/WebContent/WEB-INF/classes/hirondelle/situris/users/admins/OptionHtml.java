package hirondelle.situris.users.admins;

import hirondelle.web4j.model.Id;
import hirondelle.web4j.security.SafeText;

public final class OptionHtml {

  public OptionHtml(Id aId, SafeText aText){
    fId = aId;
    fText = aText;
  }
  
  public OptionHtml(SafeText aText){
    fText = aText;
  }
  
  public Id getId(){return fId;}
  public SafeText getText(){return fText;}
  
  private Id fId;
  private SafeText fText;
}
