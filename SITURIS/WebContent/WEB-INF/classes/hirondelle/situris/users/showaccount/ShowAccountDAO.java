package hirondelle.situris.users.showaccount;

import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.Db;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.model.Id;

public final class ShowAccountDAO {

  public ShowAccount fetchUser(Id aIdUser) throws DAOException{
    return Db.fetch(ShowAccount.class, ShowAccountAction.FETCH_USER, aIdUser);
  }
  
  public Integer countUser(Id aIdUser, SqlId aSqlId) throws DAOException{
    
    return Db.fetch(Integer.class, aSqlId, aIdUser);
    
  }
}
