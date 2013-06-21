package hirondelle.situris.main.centrosInteresse;

import hirondelle.situris.pub.pontosReferencias.CoordGPS;
import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.Db;
import hirondelle.web4j.database.DuplicateException;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.util.Util;

import java.util.List;

public class VisitaDAO {
    
    public List<Visita> listTipos() throws DAOException{
      return Db.list(Visita.class, VisitaAction.LIST_TIPOS_CENTROINTERESSE);
    }
    
    public List<Visita> listPtRefs() throws DAOException{
      return Db.list(Visita.class, VisitaAction.LIST_PTREFS_CENTROINTERESSE);
    }
  
	public List<Visita> list() throws DAOException{
		return Db.list(Visita.class, VisitasAction.LIST_VISITAS_ADMIN);
	}
	
	public Visita fetchVisita(Id aIdVisita) throws DAOException{
	  return Db.fetch(Visita.class, VisitaAction.VISITA_FETCH_ADMIN, aIdVisita);
	}
	
	public boolean changeVisita(Visita aVisita, CoordGPS aCoord, Id aIdUser, Id aIdPontoRef) throws DuplicateException, DAOException{
	   Db.edit(VisitaAction.SET_GPS_CENTROINTERESSE, aCoord.getLatitude(), aCoord.getLongitude(), aCoord.getAltitude(), 
	                 aCoord.getIdCoord());
	   boolean v = false;
	   if(!aVisita.getImagem().toString().isEmpty())
	       v = Util.isSuccess(Db.edit(VisitaAction.EDIT_VISIT, aVisita.getNome(), aVisita.getDescricao(),
	              aVisita.getLink(), aVisita.getImagem(), aVisita.getIdTipoInteresse(), aIdUser, aVisita.getIdVisita()));
	   else
	       v = Util.isSuccess(Db.edit(VisitaAction.EDIT_VISIT, aVisita.getNome(), aVisita.getDescricao(),
                 aVisita.getLink(), null, aVisita.getIdTipoInteresse(), aIdUser, aVisita.getIdVisita()));
	   boolean prv = Util.isSuccess(Db.edit(VisitaAction.EDIT_VISIT_PTREF, aIdPontoRef, aVisita.getIdVisita()));
	   if (v && prv) return true;
	   else return false;
	}
	
	public void delete(SqlId aSqlId,Id aIdVisita) throws DAOException {
	  Db.delete(aSqlId, aIdVisita);
	}
	
	public void addVisita(Visita aVisita, CoordGPS aCoord, Id aIdUser, Id aIdPontoRef) throws DAOException, DuplicateException{
      Id coord = Db.add(VisitaNewAction.ADD_GPS_CENTROINTERESSE, aCoord.getLatitude(), aCoord.getLongitude(), aCoord.getAltitude());
      Id visit = null;
      if(!aVisita.getImagem().toString().isEmpty())
        visit = Db.add(VisitaNewAction.ADD_VISITA, aVisita.getNome(), aVisita.getDescricao(), aVisita.getLink(), 
                          aVisita.getImagem(), coord, aVisita.getIdTipoInteresse(), aIdUser);
      else
        visit = Db.add(VisitaNewAction.ADD_VISITA, aVisita.getNome(), aVisita.getDescricao(), aVisita.getLink(), 
            null, coord, aVisita.getIdTipoInteresse(), aIdUser);
        
      if (aIdPontoRef != null)
        Db.add(VisitaNewAction.ADD_VISITA_PTREF, aIdPontoRef, visit);
	}
}
