package hirondelle.situris.users.proposal;

import static hirondelle.web4j.util.Consts.FAILS;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import hirondelle.situris.pub.register.Register;
import hirondelle.web4j.model.Check;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelUtil;
import hirondelle.web4j.security.SafeText;

public final class Proposal {

  public Proposal(SafeText aValor, SafeText aDescricao) throws ModelCtorException {
    fValor = aValor;
    fDescricao = aDescricao;
    validateState();
    fValorBD = new BigDecimal(aValor.getRawString());
  }
  
  public BigDecimal getValor() { return fValorBD; }  
  public SafeText getDescricao() { return fDescricao; }

  /** Intended for debugging only. Passwords are not emitted. */
  @Override public String toString(){
    StringBuilder builder = new StringBuilder();
    builder.append(Register.class);
    builder.append(" Valor: " + fValor +  " Descricao: " + fDescricao);
    return builder.toString();
  }
 
  @Override public boolean equals(Object aThat){
    Boolean result = ModelUtil.quickEquals(this, aThat);
    if ( result == null ) {
      Proposal that = (Proposal)aThat;
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

  // PRIVATE //
  private final SafeText fValor;
  private final BigDecimal fValorBD;
  private final SafeText fDescricao;
  private int fHashCode;


  private static final Pattern VALOR = Pattern.compile("\\d+(\\.\\d+)?");
  
  private void validateState() throws ModelCtorException {
    ModelCtorException ex = new ModelCtorException();
    
    if ( FAILS == Check.required(fDescricao, Check.range(6, 50)) ) {
      ex.add("Description is required, must be at least 6 characters.");
    }
    if ( FAILS == Check.required(fValor, Check.pattern(VALOR)) ) {
      ex.add("The offer must be a number!");
    }

    if ( ! ex.isEmpty() ) throw ex;
  }
  
  private Object[] getSignificantFields(){
    return new Object[] {fValor, fDescricao};
  }
}