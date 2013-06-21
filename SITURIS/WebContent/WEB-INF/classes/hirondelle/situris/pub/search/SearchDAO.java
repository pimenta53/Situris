package hirondelle.situris.pub.search;

import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.Db;
import hirondelle.web4j.database.DynamicCriteria;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.util.Util;
import static hirondelle.web4j.util.Consts.NEW_LINE;
import static hirondelle.web4j.database.DynamicCriteria.WHERE;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/** 
 Data Access Object (DAO) for returning {@link SearchResult}s for the given 
 {@link SearchCriteria}.
 
 <P>The input parameters from the user are date-only. For the purposes of this SELECT, those 
 dates are coerced into date-times, to match with the underlying data type of the column.
 
 <P>For example, the from-date is coerced to the start-of-day, as in: 
 <PRE>2010-01-15 00:00:00.000000000</PRE>

 while the to-date is coerced to the end-of-day, as in:
 <PRE>2010-01-15 23:59:59.999999999</PRE>
*/
final class SearchDAO {
 
  /**
   Return search results.
   <P>This method uses {@link DynamicCriteria} to construct the SQL SELECT statement. 
   The number of items returned is limited to 200. The search is not sensitive to case.
   Items are sorted by creation date, with the newest appearing first. 
  */
  List<SearchResult> listSearchResults(SearchCriteria aSearchCriteria, SqlId aSqlId) throws DAOException {
    DynamicCriteria dc = getCriteriaExactPhrase(aSearchCriteria, 1, aSqlId.getStatementName());
    dc.turnOffCheckingForSqlInjection();
    return Db.search(SearchResult.class, aSqlId, dc, getParamsExactPhrase(aSearchCriteria, null));
  }
  
  List<SearchResult> listSearchResultsLogged(SearchCriteria aSearchCriteria, SqlId aSqlId, Id aIdUser) throws DAOException {
    DynamicCriteria dc = getCriteriaExactPhrase(aSearchCriteria, 0, aSqlId.getStatementName());
    dc.turnOffCheckingForSqlInjection();
    return Db.search(SearchResult.class, aSqlId, dc, getParamsExactPhrase(aSearchCriteria, aIdUser));
  }
  
  List<SearchResult> listSearchResultsAdmin(SearchCriteria aSearchCriteria, SqlId aSqlId) throws DAOException {
    DynamicCriteria dc = getCriteriaExactPhrase(aSearchCriteria, 2, aSqlId.getStatementName());
    dc.turnOffCheckingForSqlInjection();
    return Db.search(SearchResult.class, aSqlId, dc, getParamsExactPhrase(aSearchCriteria, null));
  }
  
  
  
  // PRIVATE 

  private static final String AND_USER = "AND Users.idUser = ?))" + NEW_LINE;
  private static final String ROTEIRO_NOME_LIKE = "AND (Roteiro.nome LIKE ?" + NEW_LINE;
  private static final String ROTEIRO_DESCRICAO_LIKE = "OR Roteiro.descricao LIKE ?)" + NEW_LINE;
  private static final String PTREF_NOME_LIKE = "AND (PontoReferencia.nome LIKE ?" + NEW_LINE;
  private static final String PTREF_DESCRICAO_LIKE = "OR PontoReferencia.descricao LIKE ?)" + NEW_LINE;
  private static final String VISITA_NOME_LIKE = "AND (Visita.nome LIKE ?" + NEW_LINE;
  private static final String VISITA_DESCRICAO_LIKE = "OR Visita.descricao LIKE ?)" + NEW_LINE;
  private static final String EVENTO_NOME_LIKE = "AND (Evento.nome LIKE ?" + NEW_LINE;
  private static final String EVENTO_DESCRICAO_LIKE = "OR Evento.descricao LIKE ?)" + NEW_LINE;
  private static final String ROTEIRO_NOME_LIKE_ADMIN = "Roteiro.nome LIKE ?" + NEW_LINE;
  private static final String ROTEIRO_DESCRICAO_LIKE_ADMIN = "OR Roteiro.descricao LIKE ?" + NEW_LINE;
  private static final String PTREF_NOME_LIKE_ADMIN = "PontoReferencia.nome LIKE ?" + NEW_LINE;
  private static final String PTREF_DESCRICAO_LIKE_ADMIN = "OR PontoReferencia.descricao LIKE ?" + NEW_LINE;
  private static final String VISITA_NOME_LIKE_ADMIN = "Visita.nome LIKE ?" + NEW_LINE;
  private static final String VISITA_DESCRICAO_LIKE_ADMIN = "OR Visita.descricao LIKE ?" + NEW_LINE;
  private static final String EVENTO_NOME_LIKE_ADMIN = "Evento.nome LIKE ?" + NEW_LINE;
  private static final String EVENTO_DESCRICAO_LIKE_ADMIN = "OR Evento.descricao LIKE ?" + NEW_LINE;
  
  private static final String ORDER_BY_DATE_DESC = "ORDER BY Prediction.CreationDate DESC LIMIT 200" + NEW_LINE;
  
  private static final Logger fLogger = Util.getLogger(SearchDAO.class);
  
  /** Return the criteria for the case of searching for an exact phrase. */
  private DynamicCriteria getCriteriaExactPhrase(SearchCriteria aSearchCriteria, int pub, String sql) {
    StringBuilder result = null;
    if (pub == 1){
      if (sql.contains("ROTEIROS")) result = new StringBuilder(ROTEIRO_NOME_LIKE + ROTEIRO_DESCRICAO_LIKE);
      else if (sql.contains("PONTOS_REF")) result = new StringBuilder(PTREF_NOME_LIKE + PTREF_DESCRICAO_LIKE);
      else if (sql.contains("VISITAS")) result = new StringBuilder(VISITA_NOME_LIKE + VISITA_DESCRICAO_LIKE);
      else if (sql.contains("EVENTOS")) result = new StringBuilder(EVENTO_NOME_LIKE + EVENTO_DESCRICAO_LIKE);
    }
    else if (pub == 0){
      if (sql.contains("ROTEIROS")) result = new StringBuilder(AND_USER + ROTEIRO_NOME_LIKE + ROTEIRO_DESCRICAO_LIKE);
      else if (sql.contains("PONTOS_REF")) result = new StringBuilder(AND_USER + PTREF_NOME_LIKE + PTREF_DESCRICAO_LIKE);
      else if (sql.contains("VISITAS")) result = new StringBuilder(AND_USER + VISITA_NOME_LIKE + VISITA_DESCRICAO_LIKE);
      else if (sql.contains("EVENTOS")) result = new StringBuilder(AND_USER + EVENTO_NOME_LIKE + EVENTO_DESCRICAO_LIKE);
    }
    //pub == 2, se for admin
    else {
      if (sql.contains("ROTEIROS")) result = new StringBuilder(WHERE + ROTEIRO_NOME_LIKE_ADMIN + ROTEIRO_DESCRICAO_LIKE_ADMIN);
      else if (sql.contains("PONTOS_REF")) result = new StringBuilder(WHERE + PTREF_NOME_LIKE_ADMIN + PTREF_DESCRICAO_LIKE_ADMIN);
      else if (sql.contains("VISITAS")) result = new StringBuilder(WHERE + VISITA_NOME_LIKE_ADMIN + VISITA_DESCRICAO_LIKE_ADMIN);
      else if (sql.contains("EVENTOS")) result = new StringBuilder(WHERE + EVENTO_NOME_LIKE_ADMIN + EVENTO_DESCRICAO_LIKE_ADMIN);
    }
    fLogger.fine("Dynamic SQL criteria: " + result);
    return new DynamicCriteria(result);
  }
  
  private Object[] getParamsExactPhrase(SearchCriteria aSearchCriteria, Id aIdUser){
    List<Object> result = new ArrayList<Object>();
    if(aIdUser != null) result.add(aIdUser);
    result.add(like(aSearchCriteria.getSearchText().getRawString()));
    result.add(like(aSearchCriteria.getSearchText().getRawString()));
    return result.toArray();
  }
  
  /** Return the criteria for the case of searching for all words. */
  /*
  private DynamicCriteria getCriteriaAllWords(SearchCriteria aSearchCriteria, String[] aSearchTerms) {
    StringBuilder result = new StringBuilder(WHERE);
    int idx = 0;
    for (String searchTerm: aSearchTerms) {
      if(idx > 0){
        result.append(AND);
      }
      result.append(TEXT_LIKE);
      ++idx;
    }
    if(aSearchCriteria.getFromDate() != null){
      result.append(AND_FROM_DATE);
    }
    if(aSearchCriteria.getToDate() != null){
      result.append(AND_TO_DATE);
    }
    result.append(ORDER_BY_DATE_DESC);
    fLogger.fine("Dynamic SQL criteria: " + result);
    return new DynamicCriteria(result);
  }
  
  /** Search terms are separated by white space. */
  /*
  private String[] getSearchTerms(SearchCriteria aSearchCriteria){
    String WHITESPACE = "\\s";
    String searchTerms = aSearchCriteria.getSearchText().getRawString();
    return searchTerms.split(WHITESPACE);
  }*/
  /*
  private Object[] getParamsAllWords(SearchCriteria aSearchCriteria, String[] aSearchTerms){
    List<Object> result = new ArrayList<Object>();
    //common words are left in here; they are not removed
    for(String searchTerm: aSearchTerms){
      result.add(like(searchTerm));
    }
    if(aSearchCriteria.getFromDate() != null){
      result.add(aSearchCriteria.getFromDate());
    }
    if(aSearchCriteria.getToDate() != null){
      result.add(aSearchCriteria.getToDate());
    }
    return result.toArray();
  }*/
  
  /** Surround by '%', for LIKE operator. */
  private Id like(String aSearchTerm){
    return Id.from("%" + aSearchTerm + "%"); 
  }
}
