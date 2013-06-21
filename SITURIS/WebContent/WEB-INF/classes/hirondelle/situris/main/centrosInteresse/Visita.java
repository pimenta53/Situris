package hirondelle.situris.main.centrosInteresse;

import java.math.BigDecimal;

import hirondelle.web4j.model.DateTime;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.security.SafeText;

public class Visita {

	private Id idVisita;
	private SafeText nome;
	private SafeText descricao;
	private SafeText link;
	private SafeText imagem;
	private Id fIdCoord;
	private SafeText latitude;
	private SafeText longitude;
    private SafeText altitude;
    private Id idTipoInteresse;
	private SafeText descricaoTI;
	private Id idUser;
	private Integer privadoVisita;
	private Integer numAva;
	private BigDecimal estrelasMedia;
	private Integer pagamentoPat;
	private DateTime dataFimPat;
	private Id fIdPontoRef;
	private SafeText fNomePontoRef;
	  

	public Visita(Id aIdVisita, SafeText aNome, SafeText aLink, SafeText aDescricaoTI, Integer aNumAva, 
	      BigDecimal aEstrelasMedia, Integer aPagamentoPat, DateTime aDataFimPat) throws ModelCtorException{
	  idVisita = aIdVisita;
	  nome = aNome;
	  link = aLink;
	  descricaoTI = aDescricaoTI;
	  numAva = aNumAva;
	  estrelasMedia = aEstrelasMedia;
	  pagamentoPat = aPagamentoPat;
	  dataFimPat = aDataFimPat;
	}
	
	public Visita(Id aIdVisita, SafeText aNome, SafeText aDescricao, SafeText aLink, SafeText aImagem, Id aIdCoord, SafeText aLat, 
	    SafeText aLon, Id aIdTipo, SafeText aDescricaoTI, Id aIdPontoRef) throws ModelCtorException{
    idVisita = aIdVisita;
    nome = aNome;
    descricao = aDescricao;
    link = aLink;
    imagem = aImagem;
    fIdCoord = aIdCoord;
    latitude = aLat;
    longitude = aLon;
    idTipoInteresse = aIdTipo;
    descricaoTI = aDescricaoTI;
    fIdPontoRef = aIdPontoRef;
  }
	
	public Visita(Id aIdVisita, SafeText aNome, SafeText aDescricao, SafeText aLink, SafeText aImagem, Id aIdTipo, Id aIdUser) 
	    throws ModelCtorException{
	  idVisita = aIdVisita;
	  nome = aNome;
	  descricao = aDescricao;
	  link = aLink;
	  imagem = aImagem;
	  idTipoInteresse = aIdTipo;
	  idUser = aIdUser;
	}
	
    public Visita(SafeText aNome, SafeText aDescricao, SafeText aLink, SafeText aImagem, Id aIdTipo, Id aIdUser) throws ModelCtorException{
      nome = aNome;
      descricao = aDescricao;
      link = aLink;
      imagem = aImagem;
      idTipoInteresse = aIdTipo;
      idUser = aIdUser;
    }	
	
	//Used to list interest types
	public Visita(Id aIdTipo, SafeText aDescricaoIT) throws ModelCtorException{
	  idTipoInteresse = aIdTipo;
	  descricaoTI = aDescricaoIT;
	}
	
	//Used to list reference points
	public Visita(Id aIdPontoRef, SafeText aNomePontoRef, Integer privadoPontoRef) throws ModelCtorException{
      fIdPontoRef = aIdPontoRef;
      fNomePontoRef = aNomePontoRef;
    }
	
	public Integer getNumAva(){return numAva;}
	public BigDecimal getEstrelasMedia(){
	  if(estrelasMedia != null)
	    return estrelasMedia.setScale(2, BigDecimal.ROUND_HALF_UP);
	  return null;
	}
	public Integer getPagamentoPat(){return pagamentoPat;}
	public DateTime getDataFimPat(){return dataFimPat;}
	public Id getTipoInteresse(){return idTipoInteresse;}
    public Id getIdTipoInteresse(){return idTipoInteresse;}
    public SafeText getAltitude() {return altitude;}
    public Id getIdPontoRef(){return fIdPontoRef;}
    public Id getPontoRef(){return fIdPontoRef;}
    public SafeText getNomePontoRef(){return fNomePontoRef;}
    public Id getIdCoord(){return fIdCoord;}
    
	public Id getIdVisita() {
		return idVisita;
	}

	public void setIdVisita(Id idVisita) {
		this.idVisita = idVisita;
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

	public SafeText getImagem() {
		return imagem;
	}

	public void setImagem(SafeText imagem) {
		this.imagem = imagem;
	}

	public SafeText getLink() {
		return link;
	}

	public void setLink(SafeText link) {
		this.link = link;
	}

	public SafeText getLatitude() {
		return latitude;
	}

	public void setLatitude(SafeText latitude) {
		this.latitude = latitude;
	}

	public SafeText getLongitude() {
		return longitude;
	}

	public void setLongitude(SafeText longitude) {
		this.longitude = longitude;
	}

	public SafeText getDescricaoTI() {
		return descricaoTI;
	}

	public void setDescricaoTI(SafeText descricaoTI) {
		this.descricaoTI = descricaoTI;
	}

	public Id getIdUser() {
		return idUser;
	}

	public void setIdUser(Id idUser) {
		this.idUser = idUser;
	}

	public Integer getPrivadoVisita() {
		return privadoVisita;
	}

	public void setPrivadoVisita(Integer privadoVisita) {
		this.privadoVisita = privadoVisita;
	}
}
