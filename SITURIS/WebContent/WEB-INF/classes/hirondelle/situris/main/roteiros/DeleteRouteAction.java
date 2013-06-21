package hirondelle.situris.main.roteiros;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.action.ActionTemplateShowAndApply;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.model.AppException;
import hirondelle.web4j.request.RequestParameter;
import hirondelle.web4j.request.RequestParser;

public class DeleteRouteAction extends ActionTemplateShowAndApply  {

  public static final SqlId DELETE_ROTEIRO_PTREF = new SqlId("DELETE_ROTEIRO_PTREF");  
  public static final SqlId DELETE_ROTEIRO_ROTEIRO = new SqlId("DELETE_ROTEIRO_ROTEIRO");
  public static final SqlId DELETE_ROTEIRO_COMMENT = new SqlId("DELETE_ROTEIRO_COMMENT"); 

  public static final RequestParameter ID_ROTEIRO = RequestParameter.withLengthCheck("IdRoteiro"); 

  public DeleteRouteAction(RequestParser aRequestParser) {
    super(FORWARD, REDIRECT, aRequestParser);
  }
 
  @Override
  protected void apply() throws AppException {
    RoteirosDAO dao = new RoteirosDAO();
    try {
      dao.deleteRoteiroAva(getIdParam(ID_ROTEIRO));
      dao.deleteRoteiroPtRef(getIdParam(ID_ROTEIRO));
      dao.deleteRoteiro(getIdParam(ID_ROTEIRO));
      addMessage("Route deleted successfully.");
    }
    catch(DAOException ex){
      addError("Cannot delete. Route used elsewhere.");
    }
  }

  @Override
  protected void show() throws AppException {
  }

  @Override
  protected void validateUserInput() throws AppException {
  }

  private static final ResponsePage FORWARD = TemplatedPage.get("Create a new Route", "newRoteiro.jsp", DeleteRouteAction.class);
  private static final ResponsePage REDIRECT = new ResponsePage("RoteirosAction.list");
 
}
