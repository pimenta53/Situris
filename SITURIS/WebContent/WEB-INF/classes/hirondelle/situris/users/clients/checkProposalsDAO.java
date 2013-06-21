package hirondelle.situris.users.clients;

import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.Db;
import hirondelle.web4j.model.Id;

import java.util.List;

public final class checkProposalsDAO {
  
  public List<Proposal> listProposals(Id aUserId) throws DAOException {
    return Db.list(Proposal.class, checkProposalsAction.LIST_PROPOSALS_CLIENT, aUserId);
  }
}
