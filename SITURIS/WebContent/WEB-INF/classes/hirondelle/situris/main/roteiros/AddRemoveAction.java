package hirondelle.situris.main.roteiros;

import java.util.ArrayList;
import java.util.List;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.action.ActionTemplateListAndEdit;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.DuplicateException;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.request.RequestParameter;
import hirondelle.web4j.request.RequestParser;


public final class AddRemoveAction extends ActionTemplateListAndEdit {


  public static final SqlId ADD_PONTO_ROTEIRO = new SqlId("ADD_PONTO_ROTEIRO");
  public static final SqlId LIST_PR_ROTEIRO_ADMIN = new SqlId("LIST_PR_ROTEIRO_ADMIN");
  public static final SqlId REMOVE_ROT = new SqlId("REMOVE_ROT");
  public static final SqlId FETCH_PR_ROTEIRO_X = new SqlId("FETCH_PR_ROTEIRO_X");
  public static final SqlId SET_DEC_POSITIONS = new SqlId("SET_DEC_POSITIONS");
  public static final SqlId SET_INC_POSITIONS = new SqlId("SET_INC_POSITIONS");
  public static final SqlId GET_NUM_POS = new SqlId("GET_NUM_POS");
  
  
  //public static final RequestParameter ID_ROUTE = RequestParameter.withLengthCheck("IdRoteiro");
  public static final RequestParameter ID_PONTOREF = RequestParameter.withLengthCheck("IdPtRef");
  public static final RequestParameter POSICAO = RequestParameter.withLengthCheck("posicao");


/** Constructor. */
  public AddRemoveAction(RequestParser aRequestParser){
    super(FORWARD, REDIRECT_TO_LISTING, aRequestParser);
    System.out.println(getFromSession("IdRot"));
  }

  /** List all {@link Visit}s. */
  protected void doList() throws DAOException {
	  addToRequest("PtRefs", fRoteiroDAO.listPtRefs());
	  addToRequest("ids", fRoteiroDAO.getPtRefsRot((Id)getFromSession("IdRot")));
	  List<Integer> posicao = new ArrayList<Integer>();
	  for(int i = 1; i <= fRoteiroDAO.getNumPos((Id)getFromSession("IdRot")) + 1; i++) posicao.add(i);
	  addToRequest("pos", posicao);
	  //addToRequest(roteiros, dao.list_roteiros_user(getUserId()));
  }


  /** Ensure user input can build a {@link Visit}.  */
  protected void validateUserInput() {
   /* try {
      ModelFromRequest builder = new ModelFromRequest(getRequestParser());

      fRoteiro = builder.build(Roteiro.class,ID_ROUTE,ID_PONTO);
      System.out.println("TESTE1: " + fRoteiro.getIdRoteiro() + "   " + fRoteiro.getIdPo());

      System.out.println("TESTE2: " + getIdParam(ID_PONTO) + "   " + getIdParam(ID_ROUTE));
      System.out.println("TESTE3: " + getParam(ID_PONTO) + "   " + getParam(ID_ROUTE));
    }

    catch (ModelCtorException ex){
      addError(ex);
    }*/
  }

  /** Add a new {@link Visit}. */
  protected void attemptAdd() throws DAOException {

    if(getFromSession("IdRot") != null && getIdParam(ID_PONTOREF) != null && getIdParam(POSICAO) != null){
      try{
		fRoteiroDAO.addPtRef((Id)getFromSession("IdRot"),getIdParam(ID_PONTOREF),getIdParam(POSICAO));
		fRoteiroDAO.changePosicao(SET_INC_POSITIONS, (Id)getFromSession("IdRot"), getIdParam(POSICAO), getIdParam(ID_PONTOREF));
      }
      catch(DuplicateException ex){
        addError("That point is already associated with this route!");
      }
    }
    else addError("Please select a position!");
    System.out.println("TESTE6: " + getParam(ID_PONTOREF) + "   " + getFromSession("IdRot") + "    " + getParam(POSICAO));
   
  }

  /** Fetch an existing {@link Ponto} in preparation for editing it. */
  protected void attemptFetchForChange() throws DAOException {
	

    
  }

  /** Update an existing {@link Ponto}. */
  protected void attemptChange() throws DAOException {
 
  }

  public static final RequestParameter IDREF = RequestParameter.withLengthCheck("IdRef");
  public static final RequestParameter IDROT = RequestParameter.withLengthCheck("IdRot");

  /** Delete an existing {@link Visit}. */
  protected void attemptDelete() throws DAOException {
    try {
      System.out.println(getFromSession("IdRot") + "    " + getIdParam(ID_PONTOREF));
      fRoteiroDAO.removePtRef((Id)getFromSession("IdRot"),getIdParam(ID_PONTOREF));
      fRoteiroDAO.changePosicao(SET_DEC_POSITIONS, (Id)getFromSession("IdRot"), getIdParam(POSICAO), getIdParam(ID_PONTOREF));
      addMessage("The reference point was removed from this route!");
    }
    catch (Exception ex){
      addError("The operation wasn't possible!");
    }
  }

  // PRIVATE //
  private RoteirosDAO fRoteiroDAO = new RoteirosDAO();
 // private static final Logger fLogger = Util.getLogger(GestaoPontoAction.class);
  private static final ResponsePage FORWARD = TemplatedPage.get("Add Reference Points to the new Route", "referencePoints.jsp", AddRemoveAction.class);
  private static final ResponsePage REDIRECT_TO_LISTING = new ResponsePage("AddRemoveAction.list");










}