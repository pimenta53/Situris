package hirondelle.situris.users.admins;

import java.math.BigDecimal;

import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.Db;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.util.Util;

public class NewPagamentoDAO {
  
  public Pagamento fetchDados(Id aIdPat, Id aTipoTabela) throws DAOException{
    return Db.fetch(Pagamento.class, NewPagamentoAction.GET_DATA_PAG, aTipoTabela, aIdPat); 
  }

  public BigDecimal getValor(Id aIdPat) throws DAOException{
    return Db.fetchValue(BigDecimal.class, NewPagamentoAction.GET_VALOR_PAT, aIdPat);
  }
  
  public boolean setEstado(Id aIdPat) throws DAOException{
    return Util.isSuccess(Db.edit(NewPagamentoAction.SET_ESTADO_PAT, aIdPat));
  }
  
  public Id addPag(BigDecimal aValor, Id aIdPat, Id aIdTab) throws DAOException{
    return Db.add(NewPagamentoAction.ADD_PAG, aValor, aIdPat, aIdTab);
  }
}
