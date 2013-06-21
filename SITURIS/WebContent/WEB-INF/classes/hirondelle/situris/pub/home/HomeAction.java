package hirondelle.situris.pub.home;

import java.util.List;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.action.ActionTemplateListAndEdit;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.request.RequestParser;

public final class HomeAction extends ActionTemplateListAndEdit {

  /** Constructor. */
  public HomeAction(RequestParser aRequestParser){
    super(FORWARD, REDIRECT_TO_LISTING, aRequestParser);
  }
  
  public static final SqlId FETCH_EVENTS_HOME = new SqlId("FETCH_EVENTS_HOME");
  public static final SqlId FETCH_ROUTES_HOME = new SqlId("FETCH_ROUTES_HOME");
  public static final SqlId FETCH_CLIENTS_HOME = new SqlId("FETCH_CLIENTS_HOME");
  
  /** List all {@link Visit}s. */
  protected void doList() throws DAOException {
    List<Roteiro> rots = fDAO.listRoteiros();
    List<Evento> evs = fDAO.listEventos();
    List<Patrocinio> pats = fDAO.listPatrocinios();
    
    for(Roteiro r : rots)
    System.out.println("ROTEIROS: " + r.getIdRoteiro() + " " + r.getNome() + " " + r.getDescricaoTI() + " " + r.getEstrelas());
    for(Evento e : evs)
      System.out.println("EVENTOS: " + e.getIdEvento() + " " + e.getNome() + " " + e.getLink() + " " + e.getDataInicio() + 
          " " + e.getDataFim());
    for(Patrocinio p : pats)
      System.out.println("PATROCINIOS: " + " " + p.getIdPatrocinio() + " " + p.getIdEvento() + " " + p.getNomeEvento() + 
          " " + p.getIdVisita() + " " + p.getNomeVisita());
      
    addToRequest("Roteiros", rots);
    addToRequest("Eventos", evs);
    addToRequest("Patrocinios", pats);
  }
  
  @Override
  protected void attemptAdd() throws DAOException {
    // User will add nothing
    
  }
  @Override
  protected void attemptChange() throws DAOException {
    // TODO User will change nothing
    
  }
  
  @Override
  protected void attemptDelete() throws DAOException {
    // User will delete nothing
  }
  
  @Override
  protected void attemptFetchForChange() throws DAOException {
    //  
  }
  
  @Override
  protected void validateUserInput() {
    //User will input nothing  
  }
  
  // PRIVATE
  private HomeDAO fDAO = new HomeDAO();
  private static final ResponsePage FORWARD = TemplatedPage.get("Home", "Home.jsp", HomeAction.class);
  private static final ResponsePage REDIRECT_TO_LISTING = new ResponsePage("HomeAction.do?Operation=List");
  //private static final ResponsePage FORWARD = TemplatedPage.get("Your Lists", "../../Home.jsp", HomeAction.class);
  //private static final ResponsePage REDIRECT_TO_LISTING = new ResponsePage("../../Home.jsp");
  //private static final Logger fLogger = Util.getLogger(HomeAction.class);
}
