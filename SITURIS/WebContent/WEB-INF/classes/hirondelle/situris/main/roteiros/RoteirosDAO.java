package hirondelle.situris.main.roteiros;

import java.util.List;


import hirondelle.situris.pub.centrosInteresse.TipoInteresse;
import hirondelle.situris.pub.pontosReferencias.PontoReferencia;

import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.Db;
import hirondelle.web4j.database.DuplicateException;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.util.Util;

public final class RoteirosDAO {

	public List<Roteiro> listRoteirosAdmin() throws DAOException {
		return Db.list(Roteiro.class, RoteirosAction.LIST_ROTEIROS_ADMIN_VIEW);
	}

	public List<TipoInteresse> listTipos() throws DAOException {
		return Db.list(TipoInteresse.class,GestaoRoteirosAction.LIST_TIPOS);
	}
   
	public List<PontoReferencia> listPtRefs() throws DAOException {
      return Db.list(PontoReferencia.class,AddRemoveAction.LIST_PR_ROTEIRO_ADMIN);
    }
	
	public List<Refs> getPtRefsRot(Id aIdRot) throws DAOException {
	  return Db.list(Refs.class, AddRemoveAction.FETCH_PR_ROTEIRO_X, aIdRot);
	}
	
	public void addPtRef(Id aRot, Id aPtRef, Id aPos) throws DAOException, DuplicateException{
	  Db.add(AddRemoveAction.ADD_PONTO_ROTEIRO, aRot, aPtRef, aPos);
	}
	
	public void removePtRef(Id aRot, Id aPtRef) throws DAOException {
	  Db.delete(AddRemoveAction.REMOVE_ROT, aRot, aPtRef);
	}

    public void deleteRoteiro(Id aRot) throws DAOException {
      Db.delete(DeleteRouteAction.DELETE_ROTEIRO_ROTEIRO, aRot);
    }
    
    public void deleteRoteiroAva(Id aRot) throws DAOException {
      Db.delete(DeleteRouteAction.DELETE_ROTEIRO_COMMENT, aRot);
    }

    public void deleteRoteiroPtRef(Id aRot) throws DAOException {
      Db.delete(DeleteRouteAction.DELETE_ROTEIRO_PTREF, aRot);
    }   

    public Roteiro fetch(Id aRot) throws DAOException{
      return Db.fetch(Roteiro.class, GestaoRoteirosAction.FETCH_ROTEIRO_ADMIN, aRot);
    }
    
    public boolean change(Roteiro aRot) throws DAOException{
      Object params[] = {aRot.getNome(), aRot.getDescricao(),aRot.getIdTipo(),aRot.getIdRoteiro()};
      return Util.isSuccess(Db.edit(GestaoRoteirosAction.CHANGE_ROTEIRO_ADMIN, params));
    }
    
    public Id addNewRoute(Roteiro aRot, Id aIdUser) throws DuplicateException, DAOException{
      return Db.add(NewRoteiroAction.ADD_ROTEIRO, aRot.getNome(), aRot.getDescricao(), aRot.getTipoInteresse(), aIdUser);
    }
    
    
	public void addRoteiro(Id aIdPonto, Id aIdRoute) throws DuplicateException, DAOException {
		Db.add(AddRemoveAction.ADD_PONTO_ROTEIRO, aIdRoute, aIdPonto);
	}
	
	public boolean changePosicao(SqlId aSql, Id aIdRot, Id aPosicao, Id aPontoRef) throws DAOException{
	  return Util.isSuccess(Db.edit(aSql, aIdRot, aPosicao, aPontoRef));
	}
	
	public Integer getNumPos(Id aIdRot) throws DAOException {
	  return Db.fetchValue(Integer.class, AddRemoveAction.GET_NUM_POS, aIdRot);
	}



}

