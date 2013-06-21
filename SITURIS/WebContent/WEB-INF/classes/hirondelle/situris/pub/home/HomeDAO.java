package hirondelle.situris.pub.home;

import java.util.List;

import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.Db;

public final class HomeDAO {

  public List<Roteiro> listRoteiros() throws DAOException{
    return Db.list(Roteiro.class,HomeAction.FETCH_ROUTES_HOME);
  }
  
  public List<Evento> listEventos() throws DAOException{
    return Db.list(Evento.class,HomeAction.FETCH_EVENTS_HOME);
  }

  public List<Patrocinio> listPatrocinios() throws DAOException{
    return Db.list(Patrocinio.class,HomeAction.FETCH_CLIENTS_HOME);
  }
}
