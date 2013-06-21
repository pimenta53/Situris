package hirondelle.situris.pub.centrosInteresse;

import hirondelle.situris.pub.avaliacoes.Avaliacao;
import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.Db;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.util.Util;

import java.util.List;

public class EventoDAO {

	public static Evento fetch(Id idEvento) throws DAOException {
	    return Db.fetch(Evento.class, EventoAction.EVENTO_FETCH, idEvento);
	  }

	List<Evento> list() throws DAOException{
		return Db.list(Evento.class, EventosAction.LIST_EVENTOS);
	}

	List<Avaliacao> list(Id idEvento) throws DAOException{
		return Db.list(Avaliacao.class, EventoAction.LIST_COMMENTS_EVENTO, idEvento);
	}
	
	boolean change(Id aPropId) throws DAOException {
	  Object[] params = {aPropId};
	  return Util.isSuccess(Db.edit(EventoAction.SET_PAT_EVENTO, params));
	}
}

