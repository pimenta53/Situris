package hirondelle.situris.users.editaccount;

import java.util.logging.Logger;

import hirondelle.web4j.action.ActionTemplateShowAndApply;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.model.AppException;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelFromRequest;
import hirondelle.web4j.request.RequestParameter;
import hirondelle.web4j.request.RequestParser;
import hirondelle.web4j.util.Util;
import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.database.DuplicateException;

/**
 Allow the user to create an account on the site.
 A user can only log in after successfully creating an account.
 
  @view view.jsp
  @sql statements.sql
*/
public final class EditAccountAction extends ActionTemplateShowAndApply {

  /** Constructor. */
  public EditAccountAction(RequestParser aRequestParser){
    super(FORWARD, REDIRECT, aRequestParser);
  }
  
  public static final SqlId UPDATE_USER_NAME = new SqlId("UPDATE_USER_NAME");
  public static final SqlId UPDATE_USER_PASS = new SqlId("UPDATE_USER_PASS");
  public static final SqlId UPDATE_USER_EMAIL = new SqlId("UPDATE_USER_EMAIL");
  
  public static final RequestParameter NAME = RequestParameter.withLengthCheck("Name");
  public static final RequestParameter PASSWORD = RequestParameter.withLengthCheck("Password");
  public static final RequestParameter PASSWORDCONFIRM = RequestParameter.withLengthCheck("PasswordConfirm");
  public static final RequestParameter EMAIL = RequestParameter.withLengthCheck("Email");
  
  /** Show the empty form, with no prepopulation. */
  protected void show() throws DAOException {
    fLogger.fine("Forwarding to JSP");
  }
  
  /** Check user input can build a {@link Account} object. */
  protected void validateUserInput() {
    try {
      ModelFromRequest builder = new ModelFromRequest(getRequestParser());
      fAccount = builder.build(
        Account.class, 
        NAME, PASSWORD, PASSWORDCONFIRM, EMAIL);
    }
    catch (ModelCtorException ex){
      addError(ex);
    }    
  }

 protected void apply() throws AppException {
   EditAccountDAO dao = new EditAccountDAO();
   try {
     dao.editAccount(getUserId(), fAccount);
     addMessage("Your information was updated correctly.");
   }
   catch (DuplicateException ex){
     addError("Please try again. That email address is already taken.");
   }
 }
  
  // PRIVATE //
  private Account fAccount;
  private static final ResponsePage FORWARD = TemplatedPage.get("Edit Account", "view.jsp", EditAccountAction.class);
  private static final ResponsePage REDIRECT = new ResponsePage("../../users/showaccount/ShowAccountAction.show");
  private static final Logger fLogger = Util.getLogger(EditAccountAction.class);
}