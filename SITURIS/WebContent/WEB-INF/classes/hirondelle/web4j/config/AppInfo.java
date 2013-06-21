package hirondelle.web4j.config;

import java.util.*;
import hirondelle.web4j.ApplicationInfo;
import hirondelle.web4j.util.Consts;

/**
 Implementation of {@link ApplicationInfo}, required by WEB4J.
 High-level information regarding the application.
*/
public final class AppInfo implements ApplicationInfo {
  
  public String getVersion(){    
    return "0.0.0.1";  
  }
  
  public Date getBuildDate(){
    Calendar calendar = new GregorianCalendar();
    calendar.set(2011, 8, 24, 0, 0, 0); //0-based month, 0s for h:m:s
    return calendar.getTime();
  }
  
  public String getName(){
    return "SITURIS";
  }
  
  public String getAuthor(){
    return "Grupo 5";
  }
  
  public String getLink(){
    return "www.situris.com";
  }
  
  public String getMessage(){
    return "Uses web4j.jar version 4.8.0";
  }

  /**
   Return {@link #getName()} + {@link #getVersion()}. 
  */
  @Override public String toString(){
    return getName() + Consts.SPACE + getVersion();
  }
}
