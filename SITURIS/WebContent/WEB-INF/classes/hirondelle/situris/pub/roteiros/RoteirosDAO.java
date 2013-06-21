package hirondelle.situris.pub.roteiros;

import java.util.List;

import hirondelle.situris.pub.avaliacoes.Avaliacao;
import hirondelle.situris.pub.local.LocalAction;
import hirondelle.situris.pub.pontosReferencias.PontoReferencia;
import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.Db;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.security.SafeText;

public final class RoteirosDAO{

	public List<Roteiro> listRoteiros() throws DAOException {
		return Db.list(Roteiro.class, RoteirosAction.LIST_ROTEIROS);
	}

	public Roteiro fetch(Id idRoteiro) throws DAOException {
		return Db.fetch(Roteiro.class, RoteiroAction.FETCH_ROTEIRO, idRoteiro);
	}

	List<PontoReferencia> list_PR_Roteiro(Id idRoteiro) throws DAOException {
		return Db.list(PontoReferencia.class, RoteiroAction.LIST_PR_ROTEIRO, idRoteiro);
	}

	List<Avaliacao> list_Ava_Roteiro(Id idRoteiro) throws DAOException {
		return Db.list(Avaliacao.class, RoteiroAction.LIST_COMMENTS_ROTEIRO, idRoteiro);
	}
	  public List<SafeText> listImagensVisitas(Id aIdRot) throws DAOException {
		  return Db.listValues(SafeText.class, RoteiroAction.LIST_IMAGENS_VISITAS, aIdRot);
	  }
}
