package hirondelle.situris.users.clients;

import java.util.List;
import java.util.logging.Logger;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.action.ActionTemplateListAndEdit;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.request.RequestParser;
import hirondelle.web4j.util.Util;

public final class checkSponsorshipAction extends ActionTemplateListAndEdit {
  
  /** Constructor.  */
  public checkSponsorshipAction(RequestParser aRequestParser){
    super(FORWARD, REDIRECT_TO_LISTING, aRequestParser);
  }
  
  public static final SqlId LIST_SPONSORSHIPS_CLIENT =  new SqlId("LIST_SPONSORSHIPS_CLIENT");

  /** Show all lists owned by the current user. */
  protected void doList() throws DAOException {
    List<Sponsorship> sponsorshipList = fDAO.listSponsorships(getUserId());
    addToRequest(ITEMS_FOR_LISTING, sponsorshipList);
    if( sponsorshipList.isEmpty() ){
      addMessage("You don't have any sponsorship!");
    }
  }
  
  /** Try to build a {@link PredictionList} from user input. */
  @Override protected void validateUserInput() {
  }
  
  /** Try to add a new {@link PredictionList}. */
  protected void attemptAdd() throws DAOException {
  }
  
  /** Fetch an existing {@link PredictionList}, in preparation for editing it. */
  protected void attemptFetchForChange() throws DAOException {
  /*  PredictionList predictionList = fDAO.fetch(getIdParam(ID), fUserId);
    if( predictionList == null ){
      addError("Item no longer exists. Likely deleted by another user.");
    }
    else {
      addToRequest(ITEM_FOR_EDIT, predictionList);
    }*/
  }

  /** Try to change an existing {@link PredictionList}. */
  protected void attemptChange() throws DAOException {
  /*  try {
      boolean success = fDAO.change(fPredictionList);
      if (success){
        addMessage("Your list has been changed successfully.");
      }
      else {
        addError("No update occurred. Item likely deleted by another user.");
      }
    }
    catch(DuplicateException ex){
      addError("Duplicate Prediction List. Please use another identifier.");  
    }*/
  }
  
  /** Try to delete an existing {@link PredictionList}. */
  protected void attemptDelete() throws DAOException {
  /*  try {
      fDAO.delete(getIdParam(ID), fUserId);
      addMessage("Your list has been deleted.");
    }
    catch (ForeignKeyException ex){
      //Some might prefer to delete all related items at once, either using a cascading delete, 
      //or in a transaction
      addError("Cannot delete this list. You must first remove its predictions, one by one.");      
    }*/
  }
  
  // PRIVATE
  private Sponsorship fSponsorship;
  private checkSponsorshipDAO fDAO = new checkSponsorshipDAO();
  private static final ResponsePage FORWARD = TemplatedPage.get("My Sponsorships", "sponsorship.jsp", checkSponsorshipAction.class);
  private static final ResponsePage REDIRECT_TO_LISTING = new ResponsePage("checkSponsorshipAction.do?Operation=List");
  private static final Logger fLogger = Util.getLogger(checkSponsorshipAction.class);
}
