package hirondelle.situris.main.pontosReferencia;

import hirondelle.situris.pub.pontosReferencias.CoordGPS;
import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.action.ActionTemplateShowAndApply;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.DuplicateException;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.model.AppException;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelFromRequest;
import hirondelle.web4j.request.RequestParameter;
import hirondelle.web4j.request.RequestParser;

public class NewPtRefAction extends ActionTemplateShowAndApply {

  public static final SqlId ADD_PONTO_ADMIN = new SqlId("ADD_PONTO_ADMIN");
  public static final SqlId ADD_GPS_PTREF = new SqlId("ADD_GPS_PTREF");
  public static final SqlId LIST_PAISES = new SqlId("LIST_PAISES");

  public static final RequestParameter NOME = RequestParameter.withLengthCheck("Nome");
  public static final RequestParameter DESCRICAO = RequestParameter.withLengthCheck("Descricao");
  public static final RequestParameter PAIS = RequestParameter.withLengthCheck("Pais");
  public static final RequestParameter LATITUDE = RequestParameter.withLengthCheck("Latitude");
  public static final RequestParameter LONGITUDE = RequestParameter.withLengthCheck("Longitude");
  public static final RequestParameter ALTITUDE = RequestParameter.withLengthCheck("Altitude");


  public NewPtRefAction(RequestParser aRequestParser) {
    super(FORWARD, REDIRECT, aRequestParser);
  }

  @Override
  protected void apply() throws AppException {
    try{
      fPtRefDAO.add(fPtRef, coord, getUserId());
      addMessage("Reference Points saved!");
    }
    catch (DuplicateException ex){
      addError("Please try again. That name is already taken.");
    }
  }

  @Override
  protected void show() throws AppException {
    addToRequest(ITEMS_FOR_LISTING, fPtRefDAO.listPaises());
    //addToRequest("ptRefs", fRoteiroDAO.listPtRefs());
  }

  @Override
  protected void validateUserInput() throws AppException {
    try {
      ModelFromRequest builder = new ModelFromRequest(getRequestParser());
      coord = builder.build(CoordGPS.class, LATITUDE, LONGITUDE, ALTITUDE);
      fPtRef = builder.build(PontoReferencia.class, NOME, DESCRICAO, PAIS);
    }
    catch (ModelCtorException ex){
      addError(ex);
    } 
  }

  //PRIVATE
  private CoordGPS coord;
  private PontoReferencia fPtRef;
  private PontosRefDAO fPtRefDAO = new PontosRefDAO();
  private static final ResponsePage FORWARD = TemplatedPage.get("Create a new Reference Point", "NewPtRef.jsp", NewPtRefAction.class);
  private static final ResponsePage REDIRECT = new ResponsePage("PontosAction.list");
}
