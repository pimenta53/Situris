package hirondelle.situris.pub.pontosReferencias;

import hirondelle.situris.pub.avaliacoes.Avaliacao;
import hirondelle.situris.pub.centrosInteresse.Evento;
import hirondelle.situris.pub.centrosInteresse.Visita;
import hirondelle.situris.pub.local.LocalAction;
import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.Db;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.security.SafeText;

import java.util.List;

final class ListaDAO {

	public List<PontoReferencia> list() throws DAOException {
		return Db.list(PontoReferencia.class, ListaPontoAction.LIST_PONTOS);
	}

	static PontoReferencia fetch(Id idPontoRef) throws DAOException {
		return Db.fetch(PontoReferencia.class, VerPontoAction.FETCH_PONTO, idPontoRef);
	}

	List<Evento> list_eventos_ponto(Id idPontoRef) throws DAOException {
		return Db.list(Evento.class, VerPontoAction.LIST_EVENTOS_PONTO, idPontoRef);
	}

	List<Visita> list_visitas_ponto(Id idPontoRef) throws DAOException {
		return Db.list(Visita.class, VerPontoAction.LIST_VISITAS_PONTO, idPontoRef);
	}

	List<Avaliacao> list_comentarios_Ponto(Id idPontoRef) throws DAOException {
		return Db.list(Avaliacao.class, VerPontoAction.LIST_COMMENTS_PONTO, idPontoRef);
	}
	  

}
