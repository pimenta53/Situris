package hirondelle.situris.users.admins;


import static hirondelle.situris.pub.register.RegisterAction.ADD_NEW_USER;
import static hirondelle.situris.pub.register.RegisterAction.ADD_NEW_USER_ROLE;

import java.util.List;

import hirondelle.situris.pub.register.Register;
import hirondelle.situris.util.PasswordHasher;
import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.Db;
import hirondelle.web4j.database.DuplicateException;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.database.TxSimple;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.security.SafeText;
import hirondelle.web4j.util.Util;

final class NewPatrocinioDAO {

  Id add(NewPatrocinio aNP, SqlId aSql) throws DAOException {
    Object[] params = {aNP.getBeginDate(), aNP.getEndDate(), aNP.getDescription(), aNP.getIdUser(), 
        aNP.getfIdProp(), aNP.getfIdCat(), aNP.getfIdPerfil(), aNP.getfIdPoI()};
    return Db.add(aSql, params);
  }
  
  boolean change(SafeText aPropId) throws DAOException {
    Object[] params = {aPropId};
    return Util.isSuccess(Db.edit(NewPatrocinioAction.CHANGE_PROPOSAL, params));
  }
  
  public List<OptionHtml> list(SqlId aSql) throws DAOException {
    return Db.list(OptionHtml.class, aSql);
  }
  
  boolean changeRole(SafeText aUserName) throws DAOException, DuplicateException{
    return Util.isSuccess(Db.edit(NewPatrocinioAction.SET_USER_CLIENT, aUserName));
  }
  
  OptionHtml getUsername(SafeText aUsername) throws DAOException{
    return Db.fetch(OptionHtml.class, NewPatrocinioAction.FETCH_USER_USERNAME, aUsername);
  }
  
  void add(NewPatrocinio np, SqlId aSql, SafeText st) throws DAOException, DuplicateException {
    SqlId[] sqls = new SqlId[]{NewPatrocinioAction.CHANGE_PROPOSAL, aSql};
    Object[] params = new Object[]{
      /*params for change proposal: */
      st,
      /*params for create new sponsorship: */
      np.getBeginDate(), np.getEndDate(), np.getDescription(), np.getIdUser(), np.getfIdProp(), np.getfIdCat(), np.getfIdPerfil(),
      np.getfIdPoI()
    };
    TxSimple tx = new TxSimple(sqls, params);
    int numRecords = tx.executeTx();
    if ( numRecords != 2 ){
      String msg = "Should have changed 2 records (changed proposal and added sponsorship), but added this many: " + numRecords;
      throw new AssertionError(msg);
    }
  }
}
