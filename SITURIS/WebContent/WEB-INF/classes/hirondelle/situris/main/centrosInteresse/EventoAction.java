package hirondelle.situris.main.centrosInteresse;

import java.io.File;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import hirondelle.situris.pub.pontosReferencias.CoordGPS;
import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.model.AppException;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelFromRequest;
import hirondelle.web4j.action.ActionTemplateListAndEdit;
import hirondelle.web4j.request.RequestParameter;
import hirondelle.web4j.request.RequestParser;
import hirondelle.web4j.security.SafeText;
import hirondelle.web4j.util.Util;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.SqlId;

public class EventoAction extends ActionTemplateListAndEdit {

	public static final SqlId EVENTO_FETCH_ADMIN = new SqlId("EVENTO_FETCH_ADMIN");
    public static final SqlId LIST_PTREFS_CENTROINTERESSE = new SqlId("LIST_PTREFS_CENTROINTERESSE");
    public static final SqlId SET_GPS_CENTROINTERESSE = new SqlId("SET_GPS_CENTROINTERESSE");
    public static final SqlId LIST_TIPOS_CENTROINTERESSE = new SqlId("LIST_TIPOS_CENTROINTERESSE");
    public static final SqlId EDIT_EVENT = new SqlId("EDIT_EVENT");
    public static final SqlId EDIT_EVENT_PTREF = new SqlId("EDIT_EVENT_PTREF");
    public static final SqlId DELETE_EVENTO_EVENTO = new SqlId("DELETE_EVENTO_EVENTO");
    public static final SqlId DELETE_EVENTO_PTREF = new SqlId("DELETE_EVENTO_PTREF");
    public static final SqlId DELETE_EVENTO_HORARIOS = new SqlId("DELETE_EVENTO_HORARIOS");
    public static final SqlId DELETE_EVENTO_PATROCINIO = new SqlId("DELETE_EVENTO_PATROCINIO");
    public static final SqlId DELETE_EVENTO_COMMENT = new SqlId("DELETE_EVENTO_COMMENT");

	
	public static final RequestParameter ID_EVENTO = RequestParameter.withLengthCheck("IdEvento");
    public static final RequestParameter NOME = RequestParameter.withLengthCheck("Nome");
    public static final RequestParameter DESCRICAO = RequestParameter.withLengthCheck("Descricao");
    public static final RequestParameter LINK = RequestParameter.withLengthCheck("Link");
    public static final RequestParameter DATAINICIO = RequestParameter.withLengthCheck("DataInicioTB");
    public static final RequestParameter DATAFIM = RequestParameter.withLengthCheck("DataFimTB");
    public static final RequestParameter TIPO = RequestParameter.withLengthCheck("TipoInteresse");
    public static final RequestParameter LATITUDE = RequestParameter.withLengthCheck("Latitude");
    public static final RequestParameter LONGITUDE = RequestParameter.withLengthCheck("Longitude");
    public static final RequestParameter ALTITUDE = RequestParameter.withLengthCheck("Altitude");
    public static final RequestParameter ID_PONTOREF = RequestParameter.withLengthCheck("PontoRef");
    public static final RequestParameter ID_COORD = RequestParameter.withLengthCheck("idCoord");
    /** This file upload param will not have its <em>value</em> checked as would a regular param. */
    public static final RequestParameter IMAGE_FILE = RequestParameter.forFileUpload("ImageFile");

	public EventoAction(RequestParser aRequestParser) {
		super(FORWARD, REDIRECT_TO_LISTING, aRequestParser);
	}

    @Override
    protected void attemptAdd() throws DAOException {
    }
    
    @Override
    protected void attemptChange() throws DAOException {
      boolean success = fDAO.changeEvento(fEvento, coord, getUserId(), getIdParam(ID_PONTOREF));
      try{
        if(!fImage.getFileName().toString().isEmpty()) overwriteImageFile(fImage);
        if (success)
          addMessage("Event changed successfully.");
        else
          addError("No update occurred. Event likely deleted by another user.");
      } catch (AppException e) {
        addError("No update occurred!");
      }
    }
    
    @Override
    protected void attemptDelete() throws DAOException {
      try {
        fDAO.delete(DELETE_EVENTO_COMMENT,getIdParam(ID_EVENTO));
        fDAO.delete(DELETE_EVENTO_HORARIOS,getIdParam(ID_EVENTO));
        fDAO.delete(DELETE_EVENTO_PATROCINIO,getIdParam(ID_EVENTO));
        fDAO.delete(DELETE_EVENTO_PTREF,getIdParam(ID_EVENTO));
        fDAO.delete(DELETE_EVENTO_EVENTO,getIdParam(ID_EVENTO));
        addMessage("Event deleted successfully.");
      }
      catch(DAOException ex){
        addError("Cannot delete. Event used elsewhere.");
      }
    }
    
    @Override
    protected void attemptFetchForChange() throws DAOException {
      Evento e = fDAO.fetchEvento(getIdParam(ID_EVENTO));
      if(e == null)
        addError("This Event no longer exists. Likely deleted by another user.");
      else
        addToRequest(ITEM_FOR_EDIT, e); 
    }
    
    @Override
    protected void doList() throws DAOException {
      addToRequest(ITEMS_FOR_LISTING, fDAO.listTipos());
      addToRequest("ptrefs", fDAO.listPtRefs());
    }
    
    @Override
    protected void validateUserInput() {
      extractFileUploadFields();
      try {
        ModelFromRequest builder = new ModelFromRequest(getRequestParser());
        coord = builder.build(CoordGPS.class, ID_COORD, LATITUDE, LONGITUDE, ALTITUDE);
        fEvento = builder.build(Evento.class, ID_EVENTO, NOME, DESCRICAO, LINK, SafeText.from(fFileItem.getName()), DATAINICIO, 
                      DATAFIM, TIPO, getUserId());
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
    private static final ResponsePage FORWARD = TemplatedPage.get("Event", "evento.jsp", EventoAction.class);
    private static final ResponsePage REDIRECT_TO_LISTING = new ResponsePage("EventosAction.list");  
    private static final Logger fLogger = Util.getLogger(EventoAction.class);
    
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
