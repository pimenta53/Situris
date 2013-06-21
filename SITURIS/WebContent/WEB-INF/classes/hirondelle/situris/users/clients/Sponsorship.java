package hirondelle.situris.users.clients;

import hirondelle.web4j.model.DateTime;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelUtil;
import hirondelle.web4j.security.SafeText;

public final class Sponsorship {
  
  public Sponsorship(Id aId, DateTime aDateInicio, DateTime aDateFim, Integer aNCliques, 
      Integer aEstadoPagamento, SafeText aDescricao, Id aIdProposta, Id aIdCategoria, Id aIdPerfil, Id aIdEvento, 
      Id aIdVisita) throws ModelCtorException {
    fId = aId;
    fDateInicio = aDateInicio;
    fDateFim = aDateFim;
    fNCliques = aNCliques;
    fEstadoPagamento = aEstadoPagamento;
    fDescricao = aDescricao;
    fIdProposta = aIdProposta;
    fIdCategoria = aIdCategoria;
    fIdPerfil = aIdPerfil;
    fIdEvento = aIdEvento;
    fIdVisita = aIdVisita;
  }
  
  public Id getId() { return fId; }  
  public DateTime getDateInicio() {  return fDateInicio; } 
  public DateTime getDateFim() {  return fDateFim; } 
  public Integer getCliques() {return fNCliques;}
  public Integer getEstadoPagamento() {return fEstadoPagamento;}
  public SafeText getDescricao() { return fDescricao; }
  public Id getIdProposta() { return fIdProposta; }  
  public Id getIdCategoria() { return fIdCategoria; }  
  public Id getIdPerfil() { return fIdPerfil; }  
  public Id getIdEvento() { return fIdEvento; }  
  public Id getIdVisita() { return fIdVisita; }  
  
  @Override public String toString(){
    return ModelUtil.toStringFor(this);
  }
  
  @Override public boolean equals(Object aThat){
    Boolean result = ModelUtil.quickEquals(this, aThat);
    if ( result == null ) {
      Sponsorship that = (Sponsorship)aThat;
      result = ModelUtil.equalsFor(this.getSignificantFields(), that.getSignificantFields());
    }
    return result;
  }
  
  @Override public int hashCode(){
    if ( fHashCode == 0 ) {
      fHashCode = ModelUtil.hashCodeFor(getSignificantFields());
    }
    return fHashCode;
  }

  // PRIVATE 
  private final Id fId;
  private final SafeText fDescricao;
  private DateTime fDateInicio;
  private DateTime fDateFim;
  private Integer fEstadoPagamento;
  private Integer fNCliques;
  private final Id fIdProposta;
  private final Id fIdCategoria;
  private final Id fIdPerfil;
  private final Id fIdEvento;
  private final Id fIdVisita;
  private int fHashCode;

  private Object[] getSignificantFields(){
    return new Object[] {fDescricao, fDateInicio, fDateFim, fEstadoPagamento, fNCliques};
  }
 }

