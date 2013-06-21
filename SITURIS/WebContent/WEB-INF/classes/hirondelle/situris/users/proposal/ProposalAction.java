package hirondelle.situris.users.proposal;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.action.ActionTemplateShowAndApply;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.DuplicateException;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.model.AppException;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelFromRequest;
import hirondelle.web4j.request.RequestParameter;
import hirondelle.web4j.request.RequestParser;
import hirondelle.web4j.util.Util;

import java.util.logging.Logger;

public final class ProposalAction extends ActionTemplateShowAndApply {

  /** Constructor. */
  public ProposalAction(RequestParser aRequestParser){
    super(FORWARD, REDIRECT, aRequestParser);
  }
  
  public static final SqlId ADD_NEW_PROPOSAL = new SqlId("ADD_NEW_PROPOSAL");
  
  public static final RequestParameter DESCRICAO = RequestParameter.withLengthCheck("descricao");
  public static final RequestParameter VALOR = RequestParameter.withLengthCheck("offer");
  
  /** Show the empty form, with no prepopulation. */
  protected void show() throws DAOException {
    fLogger.fine("Forwarding to JSP");
  }
  
  /** Check user input can build a {@link Register} object. */
  protected void validateUserInput() {
    try {
      ModelFromRequest builder = new ModelFromRequest(getRequestParser());
      fProposal = builder.build(Proposal.class, VALOR, DESCRICAO);}
    catch (ModelCtorException ex){
      addError(ex);
    }    
  }
  
  /** 
  Add a new user to the database.
  If the user name or email address is already taken, then the operation fails, 
  and the user is asked to try a different user name. 
 */
 protected void apply() throws AppException {
   ProposalDAO dao = new ProposalDAO();
   try {
     dao.add(fProposal, getUserId());
     addMessage("Thank you! Your proposal will now be reviewed.");
   }
   catch (DuplicateException ex){
     addError("Please try again. Your proposal wasn't registered.");
   }
 }
  
  // PRIVATE //
  private Proposal fProposal;
  private static final ResponsePage FORWARD = TemplatedPage.get("Make a Proposal", "view.jsp", ProposalAction.class);
  private static final ResponsePage REDIRECT = new ResponsePage("../../users/clients/checkProposalsAction.list");
  private static final Logger fLogger = Util.getLogger(ProposalAction.class);
}