package hirondelle.situris.users.showaccount;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.action.ActionTemplateShowAndApply;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.model.AppException;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.request.RequestParameter;
import hirondelle.web4j.request.RequestParser;

public final class ShowAccountAction extends ActionTemplateShowAndApply {
  
  public ShowAccountAction(RequestParser aRequestParser){
    super(FORWARD, REDIRECT, aRequestParser);
  }

  public static final SqlId FETCH_USER =  new SqlId("FETCH_USER");
  //Statistics
  public static final SqlId COUNT_ROT =  new SqlId("COUNT_ROT");
  public static final SqlId COUNT_PTREF =  new SqlId("COUNT_PTREF");
  public static final SqlId COUNT_VIS =  new SqlId("COUNT_VIS");
  public static final SqlId COUNT_EV =  new SqlId("COUNT_EV");
  public static final SqlId COUNT_EVA =  new SqlId("COUNT_EVA");
  public static final SqlId COUNT_COMP =  new SqlId("COUNT_COMP");
  public static final SqlId COUNT_PROP =  new SqlId("COUNT_PROP");
  public static final SqlId COUNT_PAT =  new SqlId("COUNT_PAT");
  
  public static final RequestParameter USER_ID = RequestParameter.withLengthCheck("ParentId");
  
  @Override
  protected void validateUserInput() throws AppException {
  }

  @Override
  protected void apply() throws AppException {
  }
  
  protected void show() throws DAOException {

    ShowAccount acc = null; 
    AccountStat accStat = new AccountStat(); 
    Id idUser = null;
    if(getIdParam(USER_ID) != null && getFromSession("Role").toString().equals("admin"))
      idUser = getIdParam(USER_ID);
    else
      idUser = getUserId();
    
    //User Account
    acc = fDAO.fetchUser(idUser);
    
    //User statistics
    accStat.setfNumComps(fDAO.countUser(idUser, COUNT_COMP));
    accStat.setfNumEvas(fDAO.countUser(idUser, COUNT_EVA));
    accStat.setfNumEvs(fDAO.countUser(idUser, COUNT_EV));
    accStat.setfNumPats(fDAO.countUser(idUser, COUNT_PAT));
    accStat.setfNumProps(fDAO.countUser(idUser, COUNT_PROP));
    accStat.setfNumPtRef(fDAO.countUser(idUser, COUNT_PTREF));
    accStat.setfNumRoteiros(fDAO.countUser(idUser, COUNT_ROT));
    accStat.setfNumVis(fDAO.countUser(idUser, COUNT_VIS));
    
    
    System.out.println("Dados do utilizador: " + acc.getUsername() + ", " + acc.getName() + ", " + acc.getEmail() + 
                       ", " + acc.getRole());
    addToRequest("AccStat", accStat);
    addToRequest("Acc", acc);
  }
  
  // PRIVATE 
  private ShowAccountDAO fDAO = new ShowAccountDAO();
  private static final ResponsePage FORWARD = TemplatedPage.get("Account Preferences", "view.jsp", ShowAccountAction.class);
  private static final ResponsePage REDIRECT = new ResponsePage("AccountPreferencesAction.do?Operation=show");
}
