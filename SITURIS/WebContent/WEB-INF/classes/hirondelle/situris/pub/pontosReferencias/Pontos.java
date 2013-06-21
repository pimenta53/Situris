package hirondelle.situris.pub.pontosReferencias;



import hirondelle.web4j.model.DateTime;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelUtil;
import hirondelle.web4j.security.SafeText;

public final class Pontos{

  //Used visits
  public Pontos(Id aIdPontos, SafeText aNome, SafeText aDescricao, SafeText aTipoInteresse, Integer aEstrelas) 
                throws ModelCtorException{
    fIdPontos = aIdPontos;    
    fNome = aNome;
    fDescricao = aDescricao;
    fTipoInteresse = aTipoInteresse;
    fEstrelas = aEstrelas;
  }
  
  //Used for events
  public Pontos(Id aIdPontos, SafeText aNome, DateTime aDataInicio, DateTime aDataFim,SafeText aTipoInteresse,Integer aEstrelas ) throws ModelCtorException{
    fIdPontos = aIdPontos;    
    fNome = aNome;
    fTipoInteresse = aTipoInteresse;
    fDataInicio = aDataInicio;
    fDataFim = aDataFim;
    fEstrelas=aEstrelas;
  }
  
  //Used for comentarios
  public Pontos(Id aIdPontos, Integer aEstrelas, SafeText aComentarioPositivo, SafeText aComentarioNegativo, DateTime data) throws ModelCtorException{
    fIdPontos = aIdPontos;    
    fEstrelas = aEstrelas;
    fComentarioPositivo=aComentarioPositivo;
    fComentarioNegativo=aComentarioNegativo;
    fData=data;
  }
  
  public Id getIdPontos() {return fIdPontos;}  
  public SafeText getNome() {return fNome;}
  public SafeText getDescricao() {return fDescricao;}  
  public SafeText getTipoInteresse() {return fTipoInteresse;}
  public Integer getEstrelas() {return fEstrelas;}  
  public DateTime getDataInicio() {return fDataInicio;}
  public DateTime getDataFim() {return fDataFim;}
  
  public DateTime getData() {return fData;}
  public SafeText getComentarioNegativo() {return fComentarioNegativo;}
  public SafeText getComentarioPositivo() {return fComentarioPositivo;}  
  
  
  /** Intended for debugging only. */
  @Override public String toString(){
    return ModelUtil.toStringFor(this);
  }

  @Override public boolean equals(Object aThat){
    Boolean result = ModelUtil.quickEquals(this, aThat);
    if ( result == null ) {
      Pontos that = (Pontos)aThat;
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
  private Id fIdPontos;
  private SafeText fNome;
  private SafeText fDescricao;
  private SafeText fTipoInteresse;
  private Integer fEstrelas;
  //for Events
  private DateTime fDataInicio;
  private DateTime fDataFim;
  //for coments
  private DateTime fData;
  private SafeText fComentarioPositivo;
  private SafeText fComentarioNegativo;
  private int fHashCode;
 
  private Object[] getSignificantFields(){
    return new Object[] {fIdPontos, fNome, fDescricao, fTipoInteresse, fEstrelas, fDataInicio, fDataFim, fData,fComentarioNegativo,fComentarioPositivo};
  }
}