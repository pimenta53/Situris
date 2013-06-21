package hirondelle.situris.users.proposal;

import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.Db;
import hirondelle.web4j.database.DuplicateException;
import hirondelle.web4j.model.Id;

final class ProposalDAO {

  void add(Proposal aProp, Id aUserId) throws DuplicateException, DAOException{
    Db.add(ProposalAction.ADD_NEW_PROPOSAL, aProp.getValor(), aProp.getDescricao(), aUserId);
  }
} 
