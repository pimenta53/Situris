package hirondelle.situris.pub.pontosReferencias;

import java.math.BigDecimal;

import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelUtil;
import hirondelle.web4j.security.SafeText;

public class PontoReferencia {

	private Id idPontoRef;
	private SafeText nome;
	private SafeText descricao;
	private Integer privadoPR;
	private BigDecimal fArea;
	private SafeText la;
	private SafeText lg;
	private SafeText al;
	private SafeText p;
	private SafeText fNomePais;
	private Integer fEstrelas;
	
	public PontoReferencia(Id aIdPtRef, SafeText aNome, SafeText aNomePais) throws ModelCtorException{
	  idPontoRef= aIdPtRef;
	  nome = aNome;
	  fNomePais = aNomePais;
	}

	public PontoReferencia(Id b, SafeText nome, SafeText descricao,
			SafeText la, SafeText lg, SafeText al, SafeText p, Integer aEstrelas) throws ModelCtorException {
		idPontoRef = b;
		this.nome = nome;
		this.descricao = descricao;
		this.la = la;
		this.lg = lg;
		this.al = al;
		this.p = p;
		fEstrelas = aEstrelas;
		validateState();
	}
	
    public PontoReferencia(Id b, SafeText nome, SafeText descricao, Integer fPrivadoPR, BigDecimal aArea,
        SafeText la, SafeText lg, SafeText al, SafeText p) throws ModelCtorException {
    idPontoRef = b;
    this.nome = nome;
    this.descricao = descricao;
    fArea = aArea;
    this.la = la;
    this.lg = lg;
    this.al = al;
    this.p = p;
    privadoPR = fPrivadoPR;
    validateState();
}
    
    public PontoReferencia(Id idPtRef, SafeText nome){
      this.idPontoRef = idPtRef;
      this.nome = nome;
    }
    
	private int fHashCode;

	public Integer getEstrelas(){return fEstrelas;}
	
	public SafeText getNomePais(){return fNomePais;}
	
	public Id getIdPontoRef() {
		return idPontoRef;
	}

	public SafeText getNome() {
		return nome;
	}

	public SafeText getDescricao() {
		return descricao;
	}

	public BigDecimal getArea(){ return fArea;}
	
	public SafeText getLa() {
		return la;
	}

	public SafeText getLg() {
		return lg;
	}

	public SafeText getAl() {
		return al;
	}

	/** Intended for debugging only. */
	@Override
	public String toString() {
		return ModelUtil.toStringFor(this);
	}

	@Override
	public boolean equals(Object aThat) {
		Boolean result = ModelUtil.quickEquals(this, aThat);
		if (result == null) {
			PontoReferencia that = (PontoReferencia) aThat;
			result = ModelUtil.equalsFor(this.getSignificantFields(),
					that.getSignificantFields());
		}
		return result;
	}

	@Override
	public int hashCode() {
		if (fHashCode == 0) {
			fHashCode = ModelUtil.hashCodeFor(getSignificantFields());
		}
		return fHashCode;
	}

	private void validateState() throws ModelCtorException {
		ModelCtorException ex = new ModelCtorException();

		if (!ex.isEmpty())
			throw ex;
	}

	private Object[] getSignificantFields() {
		return new Object[] { idPontoRef, nome, descricao, la, lg, al };
	}

	public SafeText getP() {
		return p;
	}

	public void setP(SafeText p) {
		this.p = p;
	}

}