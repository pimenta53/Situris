package hirondelle.situris.users.admins;

import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.Db;

import java.util.List;

public final class checkProposalsDAO {
  
  public List<Proposal> listProposals() throws DAOException {
    return Db.list(Proposal.class, checkProposalsAction.LIST_PROPOSALS_ADMIN);
  }
}
