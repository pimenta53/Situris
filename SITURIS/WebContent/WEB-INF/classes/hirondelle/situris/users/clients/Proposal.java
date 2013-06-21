package hirondelle.situris.users.clients;

import java.math.BigDecimal;

import hirondelle.web4j.model.DateTime;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelUtil;
import hirondelle.web4j.security.SafeText;

public final class Proposal {
  
 public Proposal(Id aId, BigDecimal aValor, SafeText aDescricao, Integer aEstado, DateTime aDate) throws ModelCtorException {
   fId = aId;
   fValor = aValor;
   fDescricao = aDescricao;
   fEstado = aEstado;
   fDate = aDate;
 }
 
 public Id getId() { return fId; }  
 public BigDecimal getValor() {return fValor; }
 public SafeText getDescricao() { return fDescricao; }
 /** Returns null if no creation date passed to constructor. */
 public DateTime getDate() {  return fDate; }  
 public Integer getEstado() {return fEstado;}
 
 @Override public String toString(){
   return ModelUtil.toStringFor(this);
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

 // PRIVATE 
 private final Id fId;
 private final SafeText fDescricao;
 private DateTime fDate;
 private Integer fEstado;
 private BigDecimal fValor;
 private int fHashCode;

 private Object[] getSignificantFields(){
   return new Object[] {fDescricao, fDate, fEstado, fValor};
 }
}
