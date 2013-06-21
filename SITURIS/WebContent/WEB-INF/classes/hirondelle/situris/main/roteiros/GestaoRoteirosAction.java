package hirondelle.situris.main.roteiros;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.action.ActionTemplateListAndEdit;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelFromRequest;
import hirondelle.web4j.request.RequestParameter;
import hirondelle.web4j.request.RequestParser;


public final class GestaoRoteirosAction extends ActionTemplateListAndEdit {
  
  public static final SqlId ADD_ROTEIRO = new SqlId("ADD_ROTEIRO"); 
  public static final SqlId CHANGE_POSITION = new SqlId("CHANGE_POSITION"); 
  public static final SqlId FETCH_ROTEIRO_ADMIN = new SqlId("FETCH_ROTEIRO_ADMIN");
  public static final SqlId LIST_PR_ROTEIRO_ADMIN = new SqlId("LIST_PR_ROTEIRO_ADMIN");
  public static final SqlId LIST_TIPOS = new SqlId("LIST_TIPOS"); 
  public static final SqlId CHANGE_ROTEIRO_ADMIN = new SqlId("CHANGE_ROTEIRO_ADMIN");
  
/** Constructor. */
  public GestaoRoteirosAction(RequestParser aRequestParser){
    super(FORWARD, REDIRECT_TO_LISTING, aRequestParser);
  }

  public static final RequestParameter ID_ROTEIRO = RequestParameter.withLengthCheck("IdRoteiro");
  public static final RequestParameter NOME = RequestParameter.withLengthCheck("Nome");
  public static final RequestParameter DESCRICAO = RequestParameter.withLengthCheck("Descricao");
  public static final RequestParameter TIPO = RequestParameter.withLengthCheck("TipoInteresse");
  

  /** List all {@link Visit}s. */
  protected void doList() throws DAOException {
    addToRequest(ITEMS_FOR_LISTING, fRoteiroDAO.listTipos());
  }

  /** Ensure user input can build a {@link Visit}.  */
  protected void validateUserInput() {
    try {
      ModelFromRequest builder = new ModelFromRequest(getRequestParser());

      fRoteiro = builder.build(Roteiro.class, ID_ROTEIRO, NOME, DESCRICAO, TIPO);
    }
    catch (ModelCtorException ex){
      addError(ex);
    }
  }

  /** Add a new {@link Visit}. */
  protected void attemptAdd() throws DAOException {
  }

  /** Fetch an existing {@link Ponto} in preparation for editing it. */
  protected void attemptFetchForChange() throws DAOException {
	Roteiro roteiro = fRoteiroDAO.fetch(getIdParam(ID_ROTEIRO));
    if( roteiro == null ){
     addError("Roteiro no longer exists. Likely deleted by another user.");
    }
    else {
      addToRequest(ITEM_FOR_EDIT, roteiro);
    }
  }

  /** Update an existing {@link Ponto}. */
  protected void attemptChange() throws DAOException {

    boolean success = fRoteiroDAO.change(fRoteiro);
    addToSession("IdRot", fRoteiro.getIdRoteiro());
    if (success){
      addMessage("Route changed successfully.");
    }
    else {
      addError("No update occurred. Route likely deleted by another user.");
    }
  }

  /** Delete an existing {@link Route}. */
  protected void attemptDelete() throws DAOException {
  }

  // PRIVATE //
  private Roteiro fRoteiro;
  private RoteirosDAO fRoteiroDAO = new RoteirosDAO();
 // private static final Logger fLogger = Util.getLogger(GestaoPontoAction.class);
  private static final ResponsePage FORWARD = TemplatedPage.get("Route", "roteiro.jsp", GestaoRoteirosAction.class);
  private static final ResponsePage REDIRECT_TO_LISTING = new ResponsePage("AddRemoveAction.list");
}