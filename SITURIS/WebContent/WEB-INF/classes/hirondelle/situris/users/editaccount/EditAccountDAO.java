package hirondelle.situris.users.editaccount;

import hirondelle.situris.util.PasswordHasher;
import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.Db;
import hirondelle.web4j.database.DuplicateException;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.security.SafeText;

final class EditAccountDAO {  
  
  void editAccount(Id aIdUser, Account aNewInfo) throws DAOException, DuplicateException{
    
    if (aNewInfo.getEmail() != null)
      Db.edit(EditAccountAction.UPDATE_USER_EMAIL, aNewInfo.getEmail(), aIdUser);
    
    if (aNewInfo.getName() != null)
      Db.edit(EditAccountAction.UPDATE_USER_NAME, aNewInfo.getName(), aIdUser);
    
    if (aNewInfo.getPassword() != null){
      String hashedPassword = PasswordHasher.hash(aNewInfo.getPassword().getRawString()); 
      SafeText st = new SafeText(hashedPassword);
      Db.edit(EditAccountAction.UPDATE_USER_PASS, st, aIdUser);
    }
  }
}