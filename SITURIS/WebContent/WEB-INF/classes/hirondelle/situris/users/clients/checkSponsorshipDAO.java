package hirondelle.situris.users.clients;

import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.Db;
import hirondelle.web4j.model.Id;

import java.util.List;

public final class checkSponsorshipDAO {
  
  public List<Sponsorship> listSponsorships(Id aUserId) throws DAOException {
    return Db.list(Sponsorship.class, checkSponsorshipAction.LIST_SPONSORSHIPS_CLIENT, aUserId);
  }
}
