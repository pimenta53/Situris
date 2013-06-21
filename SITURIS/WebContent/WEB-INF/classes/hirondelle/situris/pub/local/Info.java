package hirondelle.situris.pub.local;

import hirondelle.web4j.model.DateTime;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelUtil;
import hirondelle.web4j.security.SafeText;

public final class Info {

  //Used for itineraries and visits
  public Info(Id aIdInfo, SafeText aNome, SafeText aDescricao, Integer aPrivado, SafeText aTipoInteresse, Integer aEstrelas) 
                throws ModelCtorException{
    fIdInfo = aIdInfo;    
    fNome = aNome;
    fDescricao = aDescricao;
    fPrivado = aPrivado;
    fTipoInteresse = aTipoInteresse;
    fEstrelas = aEstrelas;
  }
  
  //Used for events
  public Info(Id aIdInfo, SafeText aNome, SafeText aDescricao, Integer aPrivado, SafeText aTipoInteresse, Integer aEstrelas, 
                 DateTime aDataInicio, DateTime aDataFim) throws ModelCtorException{
    fIdInfo = aIdInfo;    
    fNome = aNome;
    fDescricao = aDescricao;
    fPrivado = aPrivado;
    fTipoInteresse = aTipoInteresse;
    fEstrelas = aEstrelas;
    fDataInicio = aDataInicio;
    fDataFim = aDataFim;
  }
  
  //Used for reference points
  public Info(Id aIdInfo, SafeText aNome, SafeText aDescricao, Integer aPrivado, Integer aEstrelas) throws ModelCtorException{
    fIdInfo = aIdInfo;    
    fNome = aNome;
    fDescricao = aDescricao;
    fPrivado = aPrivado;
    fEstrelas = aEstrelas;
  }
  
  public Id getIdInfo() {return fIdInfo;}  
  public SafeText getNome() {return fNome;}
  public SafeText getDescricao() {return fDescricao;}  
  public Integer getPrivado() {return fPrivado;}  
  public SafeText getTipoInteresse() {return fTipoInteresse;}
  public Integer getEstrelas() {return fEstrelas;}  
  public DateTime getDataInicio() {return fDataInicio;}
  public DateTime getDataFim() {return fDataFim;}
  
  /** Intended for debugging only. */
  @Override public String toString(){
    return ModelUtil.toStringFor(this);
  }

  @Override public boolean equals(Object aThat){
    Boolean result = ModelUtil.quickEquals(this, aThat);
    if ( result == null ) {
      Info that = (Info)aThat;
      result = ModelUtil.equalsFor(this.getSignificantFields(), that.getSignificantFields());
    }
    return result;
  }
  
  @Override public int hashCode(){
    if (fHashCode == 0) {
      fHashCode = ModelUtil.hashCodeFor(getSignificantFields());
    }
    return fHashCode;
  }
  
  //PRIVATE 
  private Id fIdInfo;
  private SafeText fNome;
  private SafeText fDescricao;
  private Integer fPrivado;
  private SafeText fTipoInteresse;
  private Integer fEstrelas;
  //for Events
  private DateTime fDataInicio;
  private DateTime fDataFim;
  
  private int fHashCode;
 
  private Object[] getSignificantFields(){
    return new Object[] {fNome, fDescricao, fPrivado, fTipoInteresse, fEstrelas, fDataInicio, fDataFim};
  }
}