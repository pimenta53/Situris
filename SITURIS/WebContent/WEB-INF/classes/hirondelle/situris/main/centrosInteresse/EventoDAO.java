package hirondelle.situris.main.centrosInteresse;

import hirondelle.situris.pub.pontosReferencias.CoordGPS;
import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.Db;
import hirondelle.web4j.database.DuplicateException;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.util.Util;

import java.util.List;

public class EventoDAO {

  public List<Evento> listTipos() throws DAOException{
    return Db.list(Evento.class, EventoAction.LIST_TIPOS_CENTROINTERESSE);
  }
  
  public List<Evento> listPtRefs() throws DAOException{
    return Db.list(Evento.class, EventoAction.LIST_PTREFS_CENTROINTERESSE);
  }
  
  public Evento fetchEvento(Id aIdEvento) throws DAOException{
    return Db.fetch(Evento.class, EventoAction.EVENTO_FETCH_ADMIN, aIdEvento);
  }

	public List<Evento> listEventos() throws DAOException{
		return Db.list(Evento.class, EventosAction.LIST_EVENTOS_ADMIN);
	}
	

    public boolean changeEvento(Evento aEvento, CoordGPS aCoord, Id aIdUser, Id aIdPontoRef) throws DuplicateException, DAOException{
      Db.edit(EventoAction.SET_GPS_CENTROINTERESSE, aCoord.getLatitude(), aCoord.getLongitude(), aCoord.getAltitude(), 
                    aCoord.getIdCoord());
      boolean e = false;
      if(!aEvento.getImagem().toString().isEmpty())
          e = Util.isSuccess(Db.edit(EventoAction.EDIT_EVENT, aEvento.getNome(), aEvento.getDescricao(),
                 aEvento.getLink(), aEvento.getImagem(), aEvento.getDataInicio(), aEvento.getDataFim(), aEvento.getIdTipoInteresse(), 
                 aIdUser, aEvento.getIdEvento()));
      else
          e = Util.isSuccess(Db.edit(EventoAction.EDIT_EVENT, aEvento.getNome(), aEvento.getDescricao(),
             aEvento.getLink(), null, aEvento.getDataInicio(), aEvento.getDataFim(), aEvento.getIdTipoInteresse(), 
             aIdUser, aEvento.getIdEvento()));
      boolean pre = Util.isSuccess(Db.edit(EventoAction.EDIT_EVENT_PTREF, aIdPontoRef, aEvento.getIdEvento()));
      if (e && pre) return true;
      else return false;
    }

    public void delete(SqlId aSqlId,Id aIdEvento) throws DAOException {
      Db.delete(aSqlId, aIdEvento);
    }
    
    public void addEvento(Evento aEvento, CoordGPS aCoord, Id aIdUser, Id aIdPontoRef) throws DAOException, DuplicateException{
      Id coord = Db.add(EventoNewAction.ADD_GPS_CENTROINTERESSE, aCoord.getLatitude(), aCoord.getLongitude(), aCoord.getAltitude());
      Id event = null;
      if(!aEvento.getImagem().toString().isEmpty())
        event = Db.add(EventoNewAction.ADD_EVENTO, aEvento.getNome(), aEvento.getDescricao(), aEvento.getLink(), aEvento.getImagem(), 
                        aEvento.getDataInicio(), aEvento.getDataFim(), coord, aEvento.getIdTipoInteresse(), aIdUser);
      else 
        event = Db.add(EventoNewAction.ADD_EVENTO, aEvento.getNome(), aEvento.getDescricao(), aEvento.getLink(), null, 
            aEvento.getDataInicio(), aEvento.getDataFim(), coord, aEvento.getIdTipoInteresse(), aIdUser);
      
      if (aIdPontoRef != null)
        Db.add(EventoNewAction.ADD_EVENTO_PTREF, aIdPontoRef, event);
    }    

}

