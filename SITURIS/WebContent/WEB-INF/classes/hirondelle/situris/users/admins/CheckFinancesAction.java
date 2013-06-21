package hirondelle.situris.users.admins;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.action.ActionTemplateListAndEdit;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.request.RequestParser;

public class CheckFinancesAction extends ActionTemplateListAndEdit {

  public static final SqlId LIST_PAYMENTS =  new SqlId("LIST_PAYMENTS");
  
  public CheckFinancesAction(RequestParser aRequestParser){
    super(FORWARD, REDIRECT_TO_LISTING, aRequestParser);
  }

  @Override
  protected void attemptAdd() throws DAOException {
  }

  @Override
  protected void attemptChange() throws DAOException {
  }

  @Override
  protected void attemptDelete() throws DAOException {
  }

  @Override
  protected void attemptFetchForChange() throws DAOException {
  }


  @Override
  protected void doList() throws DAOException {
    checkSponsorshipsDAO fDAO = new checkSponsorshipsDAO();
    addToRequest("pays", fDAO.listPayments());
  }

  @Override
  protected void validateUserInput() {
  }

  private static final ResponsePage FORWARD = TemplatedPage.get("Finances", "finances.jsp", CheckFinancesAction.class);
  private static final ResponsePage REDIRECT_TO_LISTING = new ResponsePage("CheckFinancesAction.list");
}
