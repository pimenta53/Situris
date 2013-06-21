package hirondelle.situris.pub.centrosInteresse;

import hirondelle.situris.pub.avaliacoes.Avaliacao;
import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.Db;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.util.Util;

import java.util.List;

public class VisitaDAO {

	static Visita fetch(Id idVisita) throws DAOException {
	    return Db.fetch(Visita.class, VisitaAction.VISITA_FETCH, idVisita);
	  }

	List<Visita> list() throws DAOException{
		return Db.list(Visita.class, VisitasAction.LIST_VISITAS);
	}

	List<Avaliacao> list(Id idVisita) throws DAOException{
		return Db.list(Avaliacao.class, VisitaAction.LIST_COMMENTS_VISITA, idVisita);
	}
	
    boolean change(Id aPropId) throws DAOException {
      Object[] params = {aPropId};
      return Util.isSuccess(Db.edit(VisitaAction.SET_PAT_VISITA, params));
    }	
}
