package hirondelle.situris.main.preferences;

import static hirondelle.situris.main.preferences.PreferencesAction.CHANGE_PREFERENCES;
import static hirondelle.situris.main.preferences.PreferencesAction.FETCH_PREFERENCES;
import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.Db;
import hirondelle.web4j.security.SafeText;

/**
 Data Access Object for user {@link Preferences}. 
*/
public final class PreferencesDAO {
  
  /** Fetch the {@link Preferences} for a given user. */ 
  public Preferences fetch(SafeText aUserName) throws DAOException {
    //public : needed for the login feature
    return Db.fetch(Preferences.class, FETCH_PREFERENCES, aUserName);
  }
  
  /** Change the {@link Preferences} for a given user. */
  void change(Preferences aPreferences) throws DAOException {
    Db.edit(
      CHANGE_PREFERENCES, 
      aPreferences.getUserName(), 
      aPreferences.getName()
    );
  }
}
