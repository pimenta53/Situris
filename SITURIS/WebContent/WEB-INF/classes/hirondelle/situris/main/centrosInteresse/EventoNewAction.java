package hirondelle.situris.main.centrosInteresse;

import java.io.File;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import hirondelle.situris.pub.pontosReferencias.CoordGPS;
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
import hirondelle.web4j.security.SafeText;
import hirondelle.web4j.util.Util;

public class EventoNewAction extends ActionTemplateShowAndApply {

  public static final SqlId ADD_EVENTO_PTREF = new SqlId("ADD_EVENTO_PTREF");
  public static final SqlId ADD_EVENTO = new SqlId("ADD_EVENTO");
  public static final SqlId ADD_GPS_CENTROINTERESSE = new SqlId("ADD_GPS_CENTROINTERESSE");

  public static final RequestParameter NOME = RequestParameter.withLengthCheck("Nome");
  public static final RequestParameter DESCRICAO = RequestParameter.withLengthCheck("Descricao");
  public static final RequestParameter LINK = RequestParameter.withLengthCheck("Link");
  public static final RequestParameter DATAINICIO = RequestParameter.withLengthCheck("DataInicio");
  public static final RequestParameter DATAFIM = RequestParameter.withLengthCheck("DataFim");
  public static final RequestParameter TIPO = RequestParameter.withLengthCheck("TipoInteresse");
  public static final RequestParameter LATITUDE = RequestParameter.withLengthCheck("Latitude");
  public static final RequestParameter LONGITUDE = RequestParameter.withLengthCheck("Longitude");
  public static final RequestParameter ALTITUDE = RequestParameter.withLengthCheck("Altitude");
  public static final RequestParameter ID_PONTOREF = RequestParameter.withLengthCheck("PontoRef");
  /** This file upload param will not have its <em>value</em> checked as would a regular param. */
  public static final RequestParameter IMAGE_FILE = RequestParameter.forFileUpload("ImageFile");
  
  public EventoNewAction(RequestParser aRequestParser) {
    super(FORWARD, REDIRECT, aRequestParser);
  }

  @Override
  protected void apply() throws AppException {
    try{
      fDAO.addEvento(fEvento, coord, getUserId(), getIdParam(ID_PONTOREF));
      if(!fImage.getFileName().toString().isEmpty()) overwriteImageFile(fImage);
      addMessage("Event created successfully!");
    }
    catch(DuplicateException ex){
      addError("Please try again!");
    }
    catch(DAOException dex){
      addError("Please try again!");
    }
  }

  @Override
  protected void show() throws AppException {
    addToRequest(ITEMS_FOR_LISTING, fDAO.listTipos());
    addToRequest("ptrefs", fDAO.listPtRefs());
  }

  @Override
  protected void validateUserInput() throws AppException {
    extractFileUploadFields();
    try {
      ModelFromRequest builder = new ModelFromRequest(getRequestParser());
      coord = builder.build(CoordGPS.class, LATITUDE, LONGITUDE, ALTITUDE);
      fEvento = builder.build(Evento.class, NOME, DESCRICAO, LINK, SafeText.from(fFileItem.getName()), DATAINICIO, DATAFIM, 
          TIPO, getUserId());
      fImage = builder.build(Image.class, SafeText.from(fFileItem.getName()),SafeText.from(fFileItem.getContentType()), 
          fFileItem.getSize());
    }
    catch (ModelCtorException ex){
      addError(ex);
    }
  }

  //Private
  private FileItem fFileItem;
  private Image fImage;
  private static final String IMAGE_FILE_SAVE_AS = "/Users/cedricpimenta/workspace/SITURIS/WebContent/images/events/";
  private CoordGPS coord;
  private Evento fEvento;
  private EventoDAO fDAO = new EventoDAO();
  private static final ResponsePage FORWARD = TemplatedPage.get("Create a new Event", "eventoNew.jsp", EventoNewAction.class);
  private static final ResponsePage REDIRECT = new ResponsePage("EventosAction.list");
  private static final Logger fLogger = Util.getLogger(EventoNewAction.class);

  private void extractFileUploadFields(){
    HttpServletRequest request = getRequestParser().getRequest();
    
    //note a cast is needed here to get the file-related data
    FileUploadWrapper wrappedRequest = (FileUploadWrapper)request;
    fFileItem = wrappedRequest.getFileItem(IMAGE_FILE.getName());
    fLogger.fine("Extracted File Name: " + fFileItem.getName());
    fLogger.fine("Extracted File Content Type: " + fFileItem.getContentType());
    fLogger.fine("Extracted File Size : " + fFileItem.getSize());
  }

  private void overwriteImageFile(Image aImage) throws AppException {
    String imageFilePath = IMAGE_FILE_SAVE_AS + aImage.getFileName().getRawString();
    System.out.println(imageFilePath);
    File file = new File(imageFilePath);
    try {
      fFileItem.write(file);
    }
    catch(Exception ex){
      throw new AppException("Cannot save file.", ex);
    }
  }
}
