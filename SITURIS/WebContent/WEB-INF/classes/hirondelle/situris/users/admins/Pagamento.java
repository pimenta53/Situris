package hirondelle.situris.users.admins;

import java.math.BigDecimal;

import hirondelle.web4j.model.DateTime;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.security.SafeText;

public class Pagamento {

  public Pagamento(Id aIdTabelaPrecos, BigDecimal aValor, SafeText aDescricaoPer, SafeText aDescricaoCat, Integer aNCliques, 
      SafeText aDescricaoPat, DateTime aDataInicio, DateTime aDataFim, SafeText aNomeUser, 
      SafeText aNomeEvento, SafeText aNomeVisita) throws ModelCtorException{
    fIdTabelaPrecos = aIdTabelaPrecos;
    fValor = aValor;
    fDescricaoPer = aDescricaoPer;
    fDescricaoCat = aDescricaoCat;
    fDescricaoPat = aDescricaoPat;
    fNCliques = aNCliques;
    fDataInicio = aDataInicio;
    fDataFim = aDataFim;
    fNomeUser = aNomeUser;
    fNomeEvento = aNomeEvento;
    fNomeVisita = aNomeVisita;
  }
  
  public Pagamento(DateTime aDataPag, BigDecimal aValor, SafeText aDescricaoPer, SafeText aDescricaoCat, SafeText aNomeUser, 
      SafeText aNomeEvento, SafeText aNomeVisita) throws ModelCtorException{
    fDataPag = aDataPag;
    fValor = aValor;
    fDescricaoPer = aDescricaoPer;
    fDescricaoCat = aDescricaoCat;
    fNomeUser = aNomeUser;
    fNomeEvento = aNomeEvento;
    fNomeVisita = aNomeVisita;
  }
  
  public Id getIdTabelaPrecos(){return fIdTabelaPrecos;}
  public BigDecimal getValor(){return fValor;}
  public SafeText getDescricaoPer(){return fDescricaoPer;}
  public SafeText getDescricaoCat(){return fDescricaoCat;}
  public SafeText getDescricaoPat(){return fDescricaoPat;}
  public Integer getNCliques(){return fNCliques;}
  public DateTime getDataInicio(){return fDataInicio;}
  public DateTime getDataFim(){return fDataFim;}
  public DateTime getDataPag(){return fDataPag;}
  public SafeText getNomeUser(){return fNomeUser;} 
  public SafeText getNomeEvento(){return fNomeEvento;}  
  public SafeText getNomeVisita(){return fNomeVisita;}      
  
  private Id fIdTabelaPrecos;
  private BigDecimal fValor;
  private SafeText fDescricaoPer;
  private SafeText fDescricaoCat;
  private SafeText fDescricaoPat;
  private Integer fNCliques;
  private DateTime fDataInicio;
  private DateTime fDataFim;
  private DateTime fDataPag;
  private SafeText fNomeUser;
  private SafeText fNomeEvento;
  private SafeText fNomeVisita;
}
