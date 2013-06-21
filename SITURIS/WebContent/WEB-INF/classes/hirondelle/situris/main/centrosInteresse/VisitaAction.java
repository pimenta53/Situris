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

public class VisitaAction extends ActionTemplateListAndEdit {

	public static final SqlId VISITA_FETCH_ADMIN = new SqlId("VISITA_FETCH_ADMIN");
    public static final SqlId EDIT_VISIT = new SqlId("EDIT_VISIT");
    public static final SqlId EDIT_VISIT_PTREF = new SqlId("EDIT_VISIT_PTREF");
    public static final SqlId LIST_TIPOS_CENTROINTERESSE = new SqlId("LIST_TIPOS_CENTROINTERESSE");
    public static final SqlId LIST_PTREFS_CENTROINTERESSE = new SqlId("LIST_PTREFS_CENTROINTERESSE");
    public static final SqlId SET_GPS_CENTROINTERESSE = new SqlId("SET_GPS_CENTROINTERESSE");
    public static final SqlId DELETE_VISITA_VISITA = new SqlId("DELETE_VISITA_VISITA");
    public static final SqlId DELETE_VISITA_PTREF = new SqlId("DELETE_VISITA_PTREF");
    public static final SqlId DELETE_VISITA_HORARIOS = new SqlId("DELETE_VISITA_HORARIOS");
    public static final SqlId DELETE_VISITA_PATROCINIO = new SqlId("DELETE_VISITA_PATROCINIO");
    public static final SqlId DELETE_VISITA_COMMENT = new SqlId("DELETE_VISITA_COMMENT");
    
	public static final RequestParameter ID_VISITA = RequestParameter.withLengthCheck("IdVisita");
	public static final RequestParameter NOME = RequestParameter.withLengthCheck("Nome");
	public static final RequestParameter DESCRICAO = RequestParameter.withLengthCheck("Descricao");
    public static final RequestParameter LINK = RequestParameter.withLengthCheck("Link");
	public static final RequestParameter TIPO = RequestParameter.withLengthCheck("TipoInteresse");
	public static final RequestParameter LATITUDE = RequestParameter.withLengthCheck("Latitude");
	public static final RequestParameter LONGITUDE = RequestParameter.withLengthCheck("Longitude");
	public static final RequestParameter ALTITUDE = RequestParameter.withLengthCheck("Altitude");
	public static final RequestParameter ID_PONTOREF = RequestParameter.withLengthCheck("PontoRef");
	  public static final RequestParameter ID_COORD = RequestParameter.withLengthCheck("idCoord");
	  /** This file upload param will not have its <em>value</em> checked as would a regular param. */
	  public static final RequestParameter IMAGE_FILE = RequestParameter.forFileUpload("ImageFile");

	public VisitaAction(RequestParser aRequestParser) {
		super(FORWARD, REDIRECT_TO_LISTING, aRequestParser);
	}

    @Override
    protected void attemptAdd() throws DAOException {
    }

    @Override
    protected void attemptChange() throws DAOException {
      boolean success = fDAO.changeVisita(fVisita, coord, getUserId(), getIdParam(ID_PONTOREF));
      try {
        if(!fImage.getFileName().toString().isEmpty()) overwriteImageFile(fImage);
        if (success)
          addMessage("Visit changed successfully.");
        else
          addError("No update occurred. Visit likely deleted by another user.");
      } catch (AppException e) {
        addError("The image wasn't added with the visit!");
      }
    }

    @Override
    protected void attemptDelete() throws DAOException {
      try {
        fDAO.delete(DELETE_VISITA_COMMENT,getIdParam(ID_VISITA));
        fDAO.delete(DELETE_VISITA_HORARIOS,getIdParam(ID_VISITA));
        fDAO.delete(DELETE_VISITA_PATROCINIO,getIdParam(ID_VISITA));
        fDAO.delete(DELETE_VISITA_PTREF,getIdParam(ID_VISITA));
        fDAO.delete(DELETE_VISITA_VISITA,getIdParam(ID_VISITA));
        addMessage("Visit deleted successfully.");
      }
      catch(DAOException ex){
        addError("Cannot delete. Visit used elsewhere.");
      }
    }

    @Override
    protected void attemptFetchForChange() throws DAOException {
      Visita v = fDAO.fetchVisita(getIdParam(ID_VISITA));
      if(v == null)
        addError("This Visit no longer exists. Likely deleted by another user.");
       else
         addToRequest(ITEM_FOR_EDIT, v);
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
        fVisita = builder.build(Visita.class, ID_VISITA, NOME, DESCRICAO, LINK, SafeText.from(fFileItem.getName()), TIPO, getUserId());
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
    private static final String IMAGE_FILE_SAVE_AS = "/Users/cedricpimenta/workspace/SITURIS/WebContent/images/visits/";
    private CoordGPS coord;
    private Visita fVisita;
    private VisitaDAO fDAO = new VisitaDAO();
    private static final ResponsePage FORWARD = TemplatedPage.get("Visit", "visita.jsp", VisitaAction.class);
    private static final ResponsePage REDIRECT_TO_LISTING = new ResponsePage("VisitasAction.list");  
    private static final Logger fLogger = Util.getLogger(VisitaAction.class);
    
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
