package hirondelle.situris.users.admins;

import hirondelle.situris.pub.register.Register;
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

import java.util.List;
import java.util.logging.Logger;

public class NewPatrocinioAction extends ActionTemplateShowAndApply {
  /** Constructor. */
  public NewPatrocinioAction(RequestParser aRequestParser){
    super(FORWARD, REDIRECT, aRequestParser);
  }
  
  public static final SqlId CHANGE_PROPOSAL = new SqlId("CHANGE_PROPOSAL");
  public static final SqlId LIST_ALL_EVENTS = new SqlId("LIST_ALL_EVENTS");
  public static final SqlId LIST_ALL_VISITS = new SqlId("LIST_ALL_VISITS");
  public static final SqlId LISTA_ALL_CATEGORIAS = new SqlId("LISTA_ALL_CATEGORIAS");
  public static final SqlId LISTA_ALL_PERFIL = new SqlId("LISTA_ALL_PERFIL");
  public static final SqlId CREATE_PATROCINIO_EVENTO = new SqlId("CREATE_PATROCINIO_EVENTO");
  public static final SqlId CREATE_PATROCINIO_VISITA = new SqlId("CREATE_PATROCINIO_VISITA");
  public static final SqlId SET_USER_CLIENT = new SqlId("SET_USER_CLIENT");
  public static final SqlId FETCH_USER_USERNAME = new SqlId("FETCH_USER_USERNAME");
  
  public static final RequestParameter IDPROP = RequestParameter.withLengthCheck("Id");
  public static final RequestParameter IDUSER = RequestParameter.withLengthCheck("ParentId");
  
  public static final RequestParameter BEGINDATE = RequestParameter.withLengthCheck("beginDate");
  public static final RequestParameter ENDDATE = RequestParameter.withLengthCheck("endDate");
  public static final RequestParameter DESCRIPTION = RequestParameter.withLengthCheck("description");
  public static final RequestParameter IDCAT = RequestParameter.withLengthCheck("idCat");
  public static final RequestParameter IDPERFIL = RequestParameter.withLengthCheck("idPerfil");
  public static final RequestParameter IDEVENTO = RequestParameter.withLengthCheck("idEvento");
  public static final RequestParameter IDVISITA = RequestParameter.withLengthCheck("idVisita");
  public static final RequestParameter IDP = RequestParameter.withLengthCheck("idProp");
  public static final RequestParameter IDU = RequestParameter.withLengthCheck("idUser");
  
  /** Show the empty form, with no prepopulation. */
  protected void show() throws DAOException {
    fLogger.fine("Forwarding to JSP");
    System.out.println("TESTE: " + getIdParam(IDPROP) + " " + getIdParam(IDUSER));
    List<OptionHtml> events = dao.list(LIST_ALL_EVENTS);
    List<OptionHtml> visits = dao.list(LIST_ALL_VISITS);
    List<OptionHtml> categorias = dao.list(LISTA_ALL_CATEGORIAS);
    List<OptionHtml> perfis = dao.list(LISTA_ALL_PERFIL);
    addToRequest("events", events);
    addToRequest("visits", visits);
    addToRequest("categorias", categorias);
    addToRequest("perfis", perfis);
    addToRequest("idProp",getIdParam(IDPROP));
    addToRequest("idUser", getIdParam(IDUSER));
  }
  
  /** Check user input can build a {@link Register} object. */
  protected void validateUserInput() {
    //System.out.println("TESTE: " + getIdParam(IDPROP) + " " + getIdParam(IDUSER) + " " + getParam(BEGINDATE) + " " + getParam(ENDDATE) + " " + getParam(DESCRIPTION)
    //    + " " + getIdParam(IDCAT) + " " + getIdParam(IDPERFIL) + " " + getIdParam(IDVISITA) + " " + getIdParam(IDEVENTO)); 
    try {
      validateState();
      ModelFromRequest builder = new ModelFromRequest(getRequestParser());
      if (getIdParam(IDEVENTO) == null && getIdParam(IDVISITA) != null)
      fNewPat = builder.build(NewPatrocinio.class, BEGINDATE, ENDDATE, DESCRIPTION, getIdParam(IDUSER), getIdParam(IDPROP),
          IDCAT, IDPERFIL, IDVISITA);
      else if(getIdParam(IDEVENTO) != null && getIdParam(IDVISITA) == null) 
        fNewPat = builder.build(NewPatrocinio.class, BEGINDATE, ENDDATE, DESCRIPTION, getIdParam(IDUSER), getIdParam(IDPROP),
          IDCAT, IDPERFIL, IDEVENTO);
      validateState();
    }
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
   //System.out.println("TESTE: " + fNewPat.getfIdProp() + " " + getIdParam(IDUSER) + " " + getParam(BEGINDATE) + " " + getParam(ENDDATE) + " " + getParam(DESCRIPTION)
   //    + " " + getIdParam(IDCAT) + " " + getIdParam(IDPERFIL) + " " + getIdParam(IDVISITA) + " " + getIdParam(IDEVENTO));   
   try {
     OptionHtml st = dao.getUsername(getParam(IDUSER));
     boolean b = dao.changeRole(st.getText());
     if(!b) throw new DuplicateException("It wasn't possible to change user role!", null);
     //b = dao.change(getParam(IDPROP));
     //if(!b) throw new DuplicateException("Please try again", null);
     if(getIdParam(IDVISITA) != null)
     dao.add(fNewPat,CREATE_PATROCINIO_VISITA,getParam(IDPROP));
     else
       dao.add(fNewPat,CREATE_PATROCINIO_EVENTO,getParam(IDPROP));
     addMessage("The new sponsorship is active.");
   }
   catch (DuplicateException ex){
     addError("Please try again.");
   }
 }
  
  // PRIVATE //
  private NewPatrocinio fNewPat;
  private NewPatrocinioDAO dao = new NewPatrocinioDAO();
  private static final ResponsePage FORWARD = TemplatedPage.get("New Sponsorship", "newSponsorship.jsp", NewPatrocinioAction.class);
  private static final ResponsePage REDIRECT = new ResponsePage("checkProposalsAction.list");
  private static final Logger fLogger = Util.getLogger(NewPatrocinioAction.class);
  
  private void validateState() throws ModelCtorException {
    ModelCtorException ex = new ModelCtorException();
    
    if ( (getIdParam(IDVISITA) == null && getIdParam(IDEVENTO) == null) || 
        (getIdParam(IDVISITA) != null && getIdParam(IDEVENTO) != null) ) {
      ex.add("You must choose either an event or a visit");
    }

    if ( ! ex.isEmpty() ) throw ex;
  }
}
