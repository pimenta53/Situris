package hirondelle.situris.main.roteiros;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.action.ActionTemplateShowAndApply;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.DuplicateException;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.model.AppException;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelFromRequest;
import hirondelle.web4j.request.RequestParameter;
import hirondelle.web4j.request.RequestParser;

public class NewRoteiroAction extends ActionTemplateShowAndApply {

  public static final SqlId LIST_TIPOS = new SqlId("LIST_TIPOS");
  public static final SqlId ADD_ROTEIRO = new SqlId("ADD_ROTEIRO");
  
  public static final RequestParameter NOME = RequestParameter.withLengthCheck("Nome");
  public static final RequestParameter DESCRICAO = RequestParameter.withLengthCheck("Descricao");
  public static final RequestParameter TIPO = RequestParameter.withLengthCheck("TipoInteresse");
  public static final RequestParameter ID = RequestParameter.withLengthCheck("Id");


  public NewRoteiroAction(RequestParser aRequestParser) {
    super(FORWARD, REDIRECT, aRequestParser);
  }

  @Override
  protected void apply() throws AppException {
    try{
      rotId = fRoteiroDAO.addNewRoute(fRoteiro, getUserId());
      addToSession("IdRot", rotId);
      addMessage("Route saved. Now add at least 2 reference points to this route.");
    }
    catch (DuplicateException ex){
      addError("Please try again. That name is already taken.");
    }
  }

  @Override
  protected void show() throws AppException {
    addToRequest(ITEMS_FOR_LISTING, fRoteiroDAO.listTipos());
    //addToRequest("ptRefs", fRoteiroDAO.listPtRefs());
  }

  @Override
  protected void validateUserInput() throws AppException {
    try {
      ModelFromRequest builder = new ModelFromRequest(getRequestParser());
      fRoteiro = builder.build(Roteiro.class, NOME, DESCRICAO, TIPO);
    }
    catch (ModelCtorException ex){
      addError(ex);
    } 
  }

  //PRIVATE
  private Roteiro fRoteiro;
  private static Id rotId;
  private RoteirosDAO fRoteiroDAO = new RoteirosDAO();
  private static final ResponsePage FORWARD = TemplatedPage.get("Create a new Route", "newRoteiro.jsp", NewRoteiroAction.class);
  private static final ResponsePage REDIRECT = new ResponsePage("AddRemoveAction.list");
  /*
  private static ResponsePage dynamicRedirect(RequestParser aRequestParser){
    //String value = aRequestParser.getRawParamValue(PREDICTION_LIST_ID);
    return REDIRECT.appendQueryParam("Id", rotId.getRawString());
  }*/
}
