package hirondelle.situris.pub.search;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.action.ActionTemplateSearch;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.model.AppException;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelFromRequest;
import hirondelle.web4j.request.RequestParameter;
import hirondelle.web4j.request.RequestParser;
import hirondelle.web4j.util.Util;
import java.util.List;
import java.util.logging.Logger;

/**
 Search for predictions using a text match.
 
  @view view.jsp
  @sql statements.sql
*/
public final class SearchAction extends ActionTemplateSearch {
  
  public static final RequestParameter SEARCH_TEXT = RequestParameter.withLengthCheck("Search Text");

  public static final SqlId SEARCH_ROTEIROS_PUB = new SqlId("SEARCH_ROTEIROS_PUB");
  public static final SqlId SEARCH_PONTOS_REF_PUB = new SqlId("SEARCH_PONTOS_REF_PUB");
  public static final SqlId SEARCH_VISITAS_PUB = new SqlId("SEARCH_VISITAS_PUB");
  public static final SqlId SEARCH_EVENTOS_PUB = new SqlId("SEARCH_EVENTOS_PUB");
  //in case user is logged in, he will be able to search for his itineraries and etc too
  public static final SqlId SEARCH_ROTEIROS_USER = new SqlId("SEARCH_ROTEIROS_USER");
  public static final SqlId SEARCH_PONTOS_REF_USER = new SqlId("SEARCH_PONTOS_REF_USER");
  public static final SqlId SEARCH_VISITAS_USER = new SqlId("SEARCH_VISITAS_USER");
  public static final SqlId SEARCH_EVENTOS_USER = new SqlId("SEARCH_EVENTOS_USER");
  //in case user logged in is an admin, he can see everything
  public static final SqlId SEARCH_ROTEIROS_ADMIN = new SqlId("SEARCH_ROTEIROS_ADMIN");
  public static final SqlId SEARCH_PONTOS_REF_ADMIN = new SqlId("SEARCH_PONTOS_REF_ADMIN");
  public static final SqlId SEARCH_VISITAS_ADMIN = new SqlId("SEARCH_VISITAS_ADMIN");
  public static final SqlId SEARCH_EVENTOS_ADMIN = new SqlId("SEARCH_EVENTOS_ADMIN");
  
  /** Constructor. */
  public SearchAction(RequestParser aRequestParser){
    super(FORWARD, aRequestParser);
  }
  
  /** Ensure user input can construct a {@link SearchCriteria} object. */
  @Override protected void validateUserInput() throws AppException {
    try {
      ModelFromRequest builder = new ModelFromRequest(getRequestParser());
      fSearchCriteria = builder.build(SearchCriteria.class, SEARCH_TEXT);
      fLogger.fine("Search Criteria: " + fSearchCriteria);
    }
    catch (ModelCtorException ex){
      addError(ex);
    }    
  }

  /** List the {@link SearchResult}s that correspond to the given criteria. */
  @Override protected void listSearchResults() throws AppException {
    SearchDAO dao = new SearchDAO();
    List<SearchResult> matchingRots = null, matchingRefs = null, matchingVis = null, matchingEvs = null;

    System.out.println("asda - " + getFromSession("Role"));
    //in case user is not logged in
    if(getUserId() == null){
      matchingRots = dao.listSearchResults(fSearchCriteria, SEARCH_ROTEIROS_PUB);
      matchingRefs = dao.listSearchResults(fSearchCriteria, SEARCH_PONTOS_REF_PUB);
      matchingVis = dao.listSearchResults(fSearchCriteria, SEARCH_VISITAS_PUB);
      matchingEvs =  dao.listSearchResults(fSearchCriteria, SEARCH_EVENTOS_PUB);
    }
    else{
      if (getFromSession("Role").toString().equals("admin")){
        matchingRots = dao.listSearchResultsAdmin(fSearchCriteria, SEARCH_ROTEIROS_ADMIN);
        matchingRefs = dao.listSearchResultsAdmin(fSearchCriteria, SEARCH_PONTOS_REF_ADMIN);
        matchingVis = dao.listSearchResultsAdmin(fSearchCriteria, SEARCH_VISITAS_ADMIN);
        matchingEvs =  dao.listSearchResultsAdmin(fSearchCriteria, SEARCH_EVENTOS_ADMIN);
      } else{
        matchingRots = dao.listSearchResultsLogged(fSearchCriteria, SEARCH_ROTEIROS_USER, getUserId());
        matchingRefs = dao.listSearchResultsLogged(fSearchCriteria, SEARCH_PONTOS_REF_USER, getUserId());
        matchingVis = dao.listSearchResultsLogged(fSearchCriteria, SEARCH_VISITAS_USER, getUserId());
        matchingEvs =  dao.listSearchResultsLogged(fSearchCriteria, SEARCH_EVENTOS_USER, getUserId());
      }
    }
    for(SearchResult st : matchingRots)
       System.out.println("ROTEIROS: " + st.getIdPoI() + ", " + st.getNome() + ", " + st.getIdUser());

    for(SearchResult st : matchingRefs)
       System.out.println("PTREFS: " + st.getIdPoI() + ", " + st.getNome() + ", " + st.getIdUser());

    for(SearchResult st : matchingVis)
       System.out.println("VISITAS: " + st.getIdPoI() + ", " + st.getNome() + ", " + st.getIdUser());

    for(SearchResult st : matchingEvs)
       System.out.println("EVENTOS: " + st.getIdPoI() + ", " + st.getNome() + ", " + st.getIdUser());
    
    addToRequest("mRots", matchingRots);
    addToRequest("mRefs", matchingRefs);
    addToRequest("mVis", matchingVis);
    addToRequest("mEvs", matchingEvs);
    if(matchingRots.isEmpty() && matchingRefs.isEmpty() && matchingVis.isEmpty() && matchingEvs.isEmpty()){
      addMessage("No Results. Please try again");
    }
  }
  
  // PRIVATE 
  private SearchCriteria fSearchCriteria;
  private static final ResponsePage FORWARD = TemplatedPage.get("Search Results", "view.jsp", SearchAction.class);
  private static final Logger fLogger = Util.getLogger(SearchAction.class);
}
