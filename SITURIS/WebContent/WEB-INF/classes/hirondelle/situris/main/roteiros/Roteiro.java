package hirondelle.situris.main.roteiros;

import java.math.BigDecimal;

import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.security.SafeText;

public class Roteiro {

	private Id idRoteiro;
	private SafeText nome;
	private SafeText descricao;
	private Id idUser;
	private SafeText descricaoTI;
	private Id idTipo;
	private Id idPo;
	private Integer estrelas;
	private Integer numAva;
	private BigDecimal estrelasMedia;
	
	 public Roteiro(SafeText aNome, SafeText aDescricao, Id aIdTipo) throws ModelCtorException{
	      nome = aNome;
	      descricao = aDescricao;
	      idTipo = aIdTipo;
	    }
	
    public Roteiro(Id aIdRoteiro, SafeText aNome, SafeText aDescricao, Id aIdTipo) throws ModelCtorException{
      idRoteiro = aIdRoteiro;
      nome = aNome;
      descricao = aDescricao;
      idTipo = aIdTipo;
    }
	
	public Roteiro(Id aIdRoteiro, SafeText aNome, SafeText aDescricaoTI, Integer aNumAva, BigDecimal aEstrelas) 
	    throws ModelCtorException{
      idRoteiro = aIdRoteiro;
      nome = aNome;
      descricaoTI = aDescricaoTI;
      numAva = aNumAva;
      estrelasMedia = aEstrelas;
	}

	public Roteiro(Id idRoteiro, SafeText nome, SafeText descricao, Id user, Id idTI,
			SafeText descricaoTI) throws ModelCtorException {
		this.idRoteiro = idRoteiro;
		this.nome = nome;
		this.idTipo = idTI;
		this.idUser = user;
		this.descricao = descricao;
		this.descricaoTI = descricaoTI;
	}

	public Id getTipoInteresse(){return idTipo;}
	
	   public Roteiro(Id aIdRot, Id aIdPonto){
	      idRoteiro = aIdRot;
	      idPo = aIdPonto;
	    }
	
	public Roteiro(SafeText nome, SafeText descricao) throws ModelCtorException {
		this.nome = nome;
		this.descricao = descricao;
	}
	
    public Roteiro(Id aIdRot, SafeText aNome){
      idRoteiro = aIdRot;
	  nome = aNome;
    }

	public Id getIdRoteiro() {
		return idRoteiro;
	}

	public Integer getEstrelas(){ return estrelas;}
	public Integer getNumAva(){ return numAva;}
	public BigDecimal getEstrelasMedia(){ 
	  if(estrelasMedia != null)
	  return estrelasMedia.setScale(2, BigDecimal.ROUND_HALF_UP);
	  return null;}
    
	
	
	public void setIdRoteiro(Id idRoteiro) {
		this.idRoteiro = idRoteiro;
	}
	
	public SafeText getNome() {
		return nome;
	}

	public void setNome(SafeText nome) {
		this.nome = nome;
	}

	public SafeText getDescricao() {
		return descricao;
	}

	public void setDescricao(SafeText descricao) {
		this.descricao = descricao;
	}

	public SafeText getDescricaoTI() {
		return descricaoTI;
	}

	public void setDescricaoTI(SafeText descricaoTI) {
		this.descricaoTI = descricaoTI;
	}
	
	public Id getIdUser(){ return idUser;}

	public Id getIdTipo() {
		return idTipo;
	}

	public Id getIdPo() {
		return idPo;
	}

	public void setIdPo(Id idPo) {
		this.idPo = idPo;
	}

}
