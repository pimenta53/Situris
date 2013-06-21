package hirondelle.situris.pub.local;

import java.util.List;
import java.util.logging.Logger;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.action.ActionTemplateSearch;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.model.AppException;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelFromRequest;
import hirondelle.web4j.request.RequestParameter;
import hirondelle.web4j.request.RequestParser;
import hirondelle.web4j.security.SafeText;
import hirondelle.web4j.util.Util;

public final class LocalAction extends ActionTemplateSearch {

  public static final SqlId MAP_COUNTRY = new SqlId("MAP_COUNTRY");
  public static final SqlId MAP_LIST_PONTOS_REF = new SqlId("MAP_LIST_PONTOS_REF");
  public static final SqlId MAP_LIST_ROTEIROS = new SqlId("MAP_LIST_ROTEIROS");
  public static final SqlId MAP_LIST_VISITAS = new SqlId("MAP_LIST_VISITAS");
  public static final SqlId MAP_LIST_EVENTOS = new SqlId("MAP_LIST_EVENTOS");
  //Queries in case a user is logged in
  public static final SqlId MAP_LIST_PONTOS_REF_USER = new SqlId("MAP_LIST_PONTOS_REF_USER");
  public static final SqlId MAP_LIST_ROTEIROS_USER = new SqlId("MAP_LIST_ROTEIROS_USER");
  public static final SqlId MAP_LIST_VISITAS_USER = new SqlId("MAP_LIST_VISITAS_USER");
  public static final SqlId MAP_LIST_EVENTOS_USER = new SqlId("MAP_LIST_EVENTOS_USER");
  //Queries in case the ADMIN logged is an admin
  public static final SqlId MAP_LIST_PONTOS_REF_ADMIN = new SqlId("MAP_LIST_PONTOS_REF_ADMIN");
  public static final SqlId MAP_LIST_ROTEIROS_ADMIN = new SqlId("MAP_LIST_ROTEIROS_ADMIN");
  public static final SqlId MAP_LIST_VISITAS_ADMIN = new SqlId("MAP_LIST_VISITAS_ADMIN");
  public static final SqlId MAP_LIST_EVENTOS_ADMIN = new SqlId("MAP_LIST_EVENTOS_ADMIN");

  
  
  public static final RequestParameter COUNTRY = RequestParameter.withLengthCheck("loc");
  
  /** Constructor. */
  public LocalAction(RequestParser aRequestParser){
    super(FORWARD, aRequestParser);
  }

  @Override
  protected void validateUserInput() throws AppException {
    try {
      ModelFromRequest builder = new ModelFromRequest(getRequestParser());
      fLocal = builder.build(Local.class, COUNTRY);
      fLogger.fine("Local: " + fLocal);
    }
    catch (ModelCtorException ex) {addError(ex);}
  }

  @Override
  protected void listSearchResults() throws AppException {
    //get the id from the chosen country
    LocalDAO dao = new LocalDAO();
    List<Info> ptList = null, rotList = null, visList = null, evList = null;

    SafeText st = getCorrectName(getParam(COUNTRY));
    Local loc = dao.retrieveCountryId(st);
    System.out.println("Id do Pais: " + loc.getIdPais() + " e nome do pais: " + loc.getNomePais().getRawString());
    //get all the reference points from a given country
    System.out.println("ID DO UTILIZADOR LOGGADO: " + getUserId());
    if (getUserId() == null){
      //get all the public reference points/itineraries/events/visits from a given country 
      ptList = dao.listInfo(loc.getIdPais(), MAP_LIST_PONTOS_REF);
      rotList = dao.listInfo(loc.getIdPais(), MAP_LIST_ROTEIROS);
      visList = dao.listInfo(loc.getIdPais(), MAP_LIST_VISITAS);
      evList = dao.listInfo(loc.getIdPais(), MAP_LIST_EVENTOS);
      }
      
  
    
    else{
      if(getFromSession("Role").toString().equals("admin")){
        ptList = dao.listInfo(loc.getIdPais(), MAP_LIST_PONTOS_REF_ADMIN);
        rotList = dao.listInfo(loc.getIdPais(), MAP_LIST_ROTEIROS_ADMIN);
        visList = dao.listInfo(loc.getIdPais(), MAP_LIST_VISITAS_ADMIN);
        evList = dao.listInfo(loc.getIdPais(), MAP_LIST_EVENTOS_ADMIN);
      } else{
        //get all the public reference points/itineraries/events/visits from a given country and the ones
        //that are private but belong to the user
        ptList = dao.listInfoLogged(loc.getIdPais(), getUserId(), MAP_LIST_PONTOS_REF_USER);
        rotList = dao.listInfoLogged(loc.getIdPais(), getUserId(), MAP_LIST_ROTEIROS_USER);
        visList = dao.listInfoLogged(loc.getIdPais(), getUserId(), MAP_LIST_VISITAS_USER);
        evList = dao.listInfoLogged(loc.getIdPais(), getUserId(), MAP_LIST_EVENTOS_USER);
      }
    }
    //This only exist to know the information that is collected
    for(Info pt : ptList) System.out.println(pt.getIdInfo() + "-" + pt.getNome() + "-" + pt.getDescricao() + "-" + pt.getPrivado()
        + "-" + pt.getEstrelas());
    for(Info rot : rotList) System.out.println(rot.getIdInfo() + "-" + rot.getNome() + "-" + rot.getDescricao() + "-" + rot.getPrivado()
        + "-" + rot.getTipoInteresse() + "-" + rot.getEstrelas());
    for(Info vis : visList) System.out.println(vis.getIdInfo() + "-" + vis.getNome() + "-" + vis.getDescricao() + "-" + vis.getPrivado()
        + "-" + vis.getTipoInteresse() + "-" + vis.getEstrelas());
    for(Info ev : evList) System.out.println(ev.getIdInfo() + "-" + ev.getNome() + "-" + ev.getDescricao() + "-" + ev.getPrivado()
        + "-" + ev.getTipoInteresse() + "-" + ev.getEstrelas() + "-" + ev.getDataInicio() + "-" + ev.getDataFim());
    //add all the information to the scope
    addToRequest(ITEM_FOR_EDIT, loc);
    addToRequest("PontosReferencia", ptList);
    addToRequest("Roteiros", rotList);
    addToRequest("Visitas", visList);
    addToRequest("Eventos", evList);
    if(loc == null || (ptList.isEmpty() && rotList.isEmpty() && visList.isEmpty() && evList.isEmpty())){
      addMessage("No Results. Please try again");
    }
  }

  private Local fLocal;
  private static final ResponsePage FORWARD = TemplatedPage.get("Search for Everything", "view.jsp", LocalAction.class);
  private static final Logger fLogger = Util.getLogger(LocalAction.class);

  
  private SafeText getCorrectName(SafeText input){
    String str = input.getRawString();
    String s = null;
    if(str.contains("_") && str.contains("Bissau")){
      s = str.replace("_", "-");
      return new SafeText(s);
    }
    else if(str.contains("_")){
      s = str.replace("_", " ");
      return new SafeText(s);
    }
    return input;
  }
}
