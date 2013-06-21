package hirondelle.situris.pub.roteiros;

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


	public Roteiro(Id idRoteiro, SafeText nome, SafeText descricao,
			SafeText descricaoTI) throws ModelCtorException {
		this.idRoteiro = idRoteiro;
		this.nome = nome;
		this.descricao = descricao;
		this.descricaoTI = descricaoTI;
	}
	
	public Roteiro(Id idRoteiro, SafeText nome, SafeText descricao, SafeText descricaoTI, Integer estrelas) throws ModelCtorException {
    this.idRoteiro = idRoteiro;
    this.nome = nome;
    this.descricao = descricao;
    this.descricaoTI = descricaoTI;
    this.estrelas = estrelas;
	}

	public Roteiro(SafeText nome, SafeText descricao) throws ModelCtorException {
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public Roteiro(Id idRoteiro, SafeText nome, SafeText descricao, Id aIdUser, SafeText descricaoTI) 
	    throws ModelCtorException {
      this.idRoteiro = idRoteiro;
      this.nome = nome;
      this.descricao = descricao;
      this.idUser = aIdUser;
      this.descricaoTI = descricaoTI;
  
	}
	
	public Roteiro(Id idRoteiro, SafeText nome, SafeText descricao, Id aIdUser,Id idTipo) 
		    throws ModelCtorException {
	      this.idRoteiro = idRoteiro;
	      this.nome = nome;
	      this.descricao = descricao;
	      this.idUser = aIdUser;
	    
	      this.idTipo=idTipo;
	      
		}
	public Roteiro(Id aIdRot, Id aIdPonto){
	  idRoteiro = aIdRot;
	  idPo = aIdPonto;
	}
	
    public Roteiro(Id aIdRot, SafeText aNome){
      idRoteiro = aIdRot;
	  nome = aNome;
    }

	public Id getIdRoteiro() {
		return idRoteiro;
	}

	public Integer getEstrelas(){ return estrelas;}
	
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
