package hirondelle.situris.pub.local;

import java.util.List;

import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.Db;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.security.SafeText;

final class LocalDAO {
  
  Local retrieveCountryId(SafeText aLocal) throws DAOException{
    return Db.fetch(Local.class, LocalAction.MAP_COUNTRY, aLocal);
  }
  
  //Itineraries or Visits or Events or Reference Points; returned class is used for all
  List<Info> listInfo(Id aIdPais, SqlId aSql) throws DAOException{
    return Db.list(Info.class, aSql, aIdPais);
  }
  
  //Same as above but also receives the user id in case someone is logged in
  List<Info> listInfoLogged(Id aIdPais, Id aIdUser, SqlId aSql) throws DAOException{
    return Db.list(Info.class, aSql, aIdPais, aIdUser);
  }

}
