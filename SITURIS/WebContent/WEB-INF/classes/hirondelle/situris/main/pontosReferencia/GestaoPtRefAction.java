package hirondelle.situris.main.pontosReferencia;

import java.util.List;

import hirondelle.situris.pub.pontosReferencias.CoordGPS;
import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.action.ActionTemplateListAndEdit;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelFromRequest;
import hirondelle.web4j.request.RequestParameter;
import hirondelle.web4j.request.RequestParser;


public final class GestaoPtRefAction extends ActionTemplateListAndEdit {

/** Constructor. */
  public GestaoPtRefAction(RequestParser aRequestParser){
    super(FORWARD, REDIRECT_TO_LISTING, aRequestParser);
  }

  public static final SqlId DELETE_PONTO_ROTEIRO = new SqlId("DELETE_PONTO_ROTEIRO");
  public static final SqlId COUNT_PONTO_EVENTO = new SqlId("COUNT_PONTO_EVENTO");
  public static final SqlId COUNT_PONTO_VISITA = new SqlId("COUNT_PONTO_VISITA");
  public static final SqlId DELETE_PONTO_PTREF = new SqlId("DELETE_PONTO_PTREF");
  public static final SqlId DELETE_PONTO_COMMENT = new SqlId("DELETE_PONTO_COMMENT");
  public static final SqlId DELETE_PONTO_TRANSPORT = new SqlId("DELETE_PONTO_TRANSPORT");
  public static final SqlId FETCH_PONTO_ADMIN = new SqlId("FETCH_PONTO_ADMIN");
  public static final SqlId LIST_PAISES = new SqlId("LIST_PAISES");
  public static final SqlId CHANGE_PONTO_ADMIN = new SqlId("CHANGE_PONTO_ADMIN");
  public static final SqlId SET_GPS_PTREF = new SqlId("SET_GPS_PTREF");
  public static final SqlId GET_ROUTES = new SqlId("GET_ROUTES");
  public static final SqlId SET_NEW_POSITIONS = new SqlId("SET_NEW_POSITIONS");
  
  public static final RequestParameter ID_PTREF = RequestParameter.withLengthCheck("IdPtRef");
  public static final RequestParameter NOME = RequestParameter.withLengthCheck("Nome");
  public static final RequestParameter DESCRICAO = RequestParameter.withLengthCheck("Descricao");
  public static final RequestParameter PAIS = RequestParameter.withLengthCheck("Pais");
  public static final RequestParameter LATITUDE = RequestParameter.withLengthCheck("Latitude");
  public static final RequestParameter LONGITUDE = RequestParameter.withLengthCheck("Longitude");
  public static final RequestParameter ALTITUDE = RequestParameter.withLengthCheck("Altitude");
  public static final RequestParameter ID_COORD = RequestParameter.withLengthCheck("idCoord");
  

  /** List all {@link Visit}s. */
  protected void doList() throws DAOException {
    addToRequest(ITEMS_FOR_LISTING, fPtRefDAO.listPaises());
  }

  /** Ensure user input can build a {@link Visit}.  */
  protected void validateUserInput() {
    try {
      ModelFromRequest builder = new ModelFromRequest(getRequestParser());
      coord = builder.build(CoordGPS.class, ID_COORD, LATITUDE, LONGITUDE, ALTITUDE);
      fPtRef = builder.build(PontoReferencia.class, ID_PTREF, NOME, DESCRICAO, PAIS);
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
	PontoReferencia ptRef = fPtRefDAO.fetch(getIdParam(ID_PTREF));
    if( ptRef == null ){
     addError("Reference Points no longer exists. Likely deleted by another user.");
    }
    else {
      addToRequest(ITEM_FOR_EDIT, ptRef);
    }
  }

  /** Update an existing {@link Ponto}. */
  protected void attemptChange() throws DAOException {
    boolean success = fPtRefDAO.change(fPtRef, coord, getUserId());
    if (success){
      addMessage("Reference Point changed successfully.");
    }
    else {
      addError("No update occurred. Reference Point likely deleted by another user.");
    }
  }

  /** Delete an existing {@link Route}. */
  protected void attemptDelete() throws DAOException {
    try {
      if(fPtRefDAO.getNumEvVis(COUNT_PONTO_EVENTO, getIdParam(ID_PTREF)) != 0 || 
          fPtRefDAO.getNumEvVis(COUNT_PONTO_VISITA, getIdParam(ID_PTREF)) != 0 )
            throw new DAOException("Reference Point has a visit or event associated!", null);
      fPtRefDAO.delete(DELETE_PONTO_COMMENT,getIdParam(ID_PTREF));
      fPtRefDAO.delete(DELETE_PONTO_TRANSPORT,getIdParam(ID_PTREF));
      //Here the positions of the reference points in the routes will be updated
      List<PtRefPosicao> routes = fPtRefDAO.getRoutes(getIdParam(ID_PTREF));
      boolean b = false;
      for(PtRefPosicao prp : routes){
        b = fPtRefDAO.updatePositions(prp.getIdRoteiro(), prp.getPosicao());
        if(!b) throw new Exception();
      }
      fPtRefDAO.delete(DELETE_PONTO_ROTEIRO,getIdParam(ID_PTREF));
      fPtRefDAO.delete(DELETE_PONTO_PTREF,getIdParam(ID_PTREF));
      addMessage("Reference Point deleted successfully.");
    }
    catch(DAOException ex){
      addError("Cannot delete. Reference Point used elsewhere.");
    }
    catch(Exception ex){
      addError("It wasn't possible to update the positions of all routes!");
    }
  }

  // PRIVATE //
  private CoordGPS coord;
  private PontoReferencia fPtRef;
  private PontosRefDAO fPtRefDAO = new PontosRefDAO();
 // private static final Logger fLogger = Util.getLogger(GestaoPontoAction.class);
  private static final ResponsePage FORWARD = TemplatedPage.get("Reference Point", "PontoReferencia.jsp", GestaoPtRefAction.class);
  private static final ResponsePage REDIRECT_TO_LISTING = new ResponsePage("PontosAction.list");
}