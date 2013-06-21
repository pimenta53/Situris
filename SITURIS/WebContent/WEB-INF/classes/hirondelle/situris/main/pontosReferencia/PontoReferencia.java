package hirondelle.situris.main.pontosReferencia;

import java.math.BigDecimal;

import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.security.SafeText;

public class PontoReferencia {
	private Id idPontoRef;
	private SafeText nome;
	private SafeText descricao;
	private Integer fPrivado;
	private Id fIdCoord;
	private SafeText latitude;
	private SafeText longitude;
	private SafeText altitude;
	private Id fIdPais;
	private SafeText fNomePais;
	private Id user;
	private Integer privadoPontoRef;
	private Integer numAva;
	private BigDecimal estrelasMedia;
	private Integer numRoteiros;
	private Integer numEventos;
	private Integer numVisitas;

	public PontoReferencia(Id aIdPt, SafeText aNome, SafeText aNomePais, Integer aNumAva, BigDecimal aEstrelasMedia,
	      Integer aNumEventos, Integer aNumVisitas, Integer aNumRoteiros) throws ModelCtorException{
	  idPontoRef = aIdPt;
	  nome = aNome;
	  fNomePais = aNomePais;
	  numAva = aNumAva;
	  estrelasMedia = aEstrelasMedia;
	  numRoteiros = aNumRoteiros;
	  numEventos = aNumEventos;
	  numVisitas = aNumVisitas;
	}
	
	public PontoReferencia(Id b, SafeText nome, SafeText descricao, Id aIdCoord,
        SafeText la, SafeText lg, SafeText al, Id aIdPais, SafeText pais)
        throws ModelCtorException {
    idPontoRef = b;
    this.nome = nome;
    this.descricao = descricao;
    fIdCoord = aIdCoord;
    this.latitude = la;
    this.longitude = lg;
    this.altitude = al;
    fIdPais = aIdPais;
    this.fNomePais = pais;
}
	
	public PontoReferencia(Id b, SafeText nome, SafeText descricao,
			SafeText la, SafeText lg, SafeText al, SafeText p)
			throws ModelCtorException {
		idPontoRef = b;
		this.nome = nome;
		this.descricao = descricao;
		this.latitude = la;
		this.longitude = lg;
		this.altitude = al;
		this.fNomePais = p;
	}

	public PontoReferencia(Id b, SafeText nome, SafeText descricao,
			SafeText lat, SafeText log, SafeText alt) throws ModelCtorException {
		idPontoRef = b;
		this.nome = nome;
		this.descricao = descricao;
		latitude = lat;
		longitude = log;
		altitude = alt;
	}

	public PontoReferencia(Id b, SafeText nome, SafeText descricao, Id pais)
			throws ModelCtorException {
		idPontoRef = b;
		this.nome = nome;
		this.descricao = descricao;
		fIdPais = pais;
	}

	public PontoReferencia(SafeText aNome, SafeText aDescricao, Id aIdPais)
			throws ModelCtorException {
		nome = aNome;
		descricao = aDescricao;
		fIdPais = aIdPais;
	}
	
	public PontoReferencia(Id aIdPais, SafeText aNomePais) throws ModelCtorException{
	  fIdPais = aIdPais;
	  fNomePais = aNomePais;
	}

    public Integer getNumRoteiros(){return numRoteiros;}
    public Integer getNumEventos(){return numEventos;}
    public Integer getNumVisitas(){return numVisitas;}
	public Integer getNumAva(){return numAva;}
	public BigDecimal getEstrelasMedia(){
      if(estrelasMedia != null)
      return estrelasMedia.setScale(2, BigDecimal.ROUND_HALF_UP);
      return null;}
	public Id getIdPais(){return fIdPais;}
	public Integer getPrivado(){return fPrivado;}
	public Id getPais(){return fIdPais;}
    public Id getIdPtRef(){return idPontoRef;}
    public Id getIdCoord(){return fIdCoord;}
	
	public Id getIdPontoRef() {
		return idPontoRef;
	}

	public SafeText getNome() {
		return nome;
	}

	public SafeText getDescricao() {
		return descricao;
	}

	public Id getUser() {
		return user;
	}

	public SafeText getLongitude() {
		return longitude;
	}

	public SafeText getLatitude() {
		return latitude;
	}

	public SafeText getAltitude() {
		return altitude;
	}

	public SafeText getNomePais() {
		return fNomePais;
	}

	public void setPais(SafeText pais) {
		this.fNomePais = pais;
	}

	public Integer getPrivadoPontoRef() {
		return privadoPontoRef;
	}

	public void setPrivadoPontoRef(Integer privadoPontoRef) {
		this.privadoPontoRef = privadoPontoRef;
	}

}
