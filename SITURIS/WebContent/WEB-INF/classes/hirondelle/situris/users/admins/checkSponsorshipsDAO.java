package hirondelle.situris.users.admins;

import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.Db;
import java.util.List;

public final class checkSponsorshipsDAO {
  
  public List<Sponsorship> listSponsorships() throws DAOException {
    return Db.list(Sponsorship.class, checkSponsorshipsAction.LIST_SPONSORHIPS_ADMIN);
  }
  
  public List<Pagamento> listPayments() throws DAOException {
    return Db.list(Pagamento.class, CheckFinancesAction.LIST_PAYMENTS);
  }
}
