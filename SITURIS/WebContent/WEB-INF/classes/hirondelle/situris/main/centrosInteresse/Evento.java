package hirondelle.situris.main.centrosInteresse;

import java.math.BigDecimal;

import hirondelle.web4j.model.DateTime;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.security.SafeText;

public class Evento {

	private Id idEvento;
	private SafeText nome;
	private SafeText descricao;
	private SafeText link;
	private SafeText imagem;
	private Id fIdCoord;
	private SafeText latitude;
	private SafeText longitude;
	private SafeText altitude;
    private DateTime fDataInicio;
    private DateTime fDataFim;
	private Id idTipoInteresse;
	private SafeText descricaoTI;
	private Integer fNumAva;
	private BigDecimal fEstrelasMedia;
	private Integer fPagamentoPat;
	private DateTime fDataFimPat;
	private Id idUser;
	private Integer privadoEvento;
	//Listing Reference Points
    private Id fIdPontoRef;
    private SafeText fNomePontoRef;

	public Evento(Id aIdEvento, SafeText aNome, SafeText aLink, DateTime aDataInicio, DateTime aDataFim, SafeText aDescricaoTI, 
	    Integer aNumAva, BigDecimal aEstrelasMedia, Integer aPagamentoPat, DateTime aDataFimPat){
	  idEvento = aIdEvento;
	  nome = aNome;
	  link = aLink;
	  fDataInicio = aDataInicio;
	  fDataFim = aDataFim;
	  descricaoTI = aDescricaoTI;
	  fNumAva = aNumAva;
	  fEstrelasMedia = aEstrelasMedia;
	  fPagamentoPat = aPagamentoPat;
	  fDataFimPat = aDataFimPat;
	}
	
	public Evento(Id aIdEvento, SafeText aNome, SafeText aDescricao, SafeText aLink, SafeText aImagem, DateTime aDataInicio,
			DateTime aDataFim, Id aIdCoord, SafeText lat, SafeText lon, Id aIdTipoInteresse, SafeText aDescricaoTI, Id aIdPontoRef) {
		idEvento = aIdEvento;
		nome = aNome;
		descricao = aDescricao;
        link = aLink;
		imagem = aImagem;
		fDataInicio = aDataInicio;
		fDataFim = aDataFim;
		fIdCoord = aIdCoord;
		latitude = lat;
		longitude = lon;
		idTipoInteresse = aIdTipoInteresse;
		descricaoTI = aDescricaoTI;
		fIdPontoRef = aIdPontoRef;
	}
	
	public Evento(Id aIdEvento, SafeText aNome, SafeText aDescricao, SafeText aLink, SafeText aImagem, DateTime aDataInicio,
           DateTime aDataFim, Id aIdTipoInteresse, Id aIdUser) {
       idEvento = aIdEvento;
       nome = aNome;
       descricao = aDescricao;
       link = aLink;
       imagem = aImagem;
       fDataInicio = aDataInicio;
       fDataFim = aDataFim;
       idTipoInteresse = aIdTipoInteresse;
       idUser = aIdUser;
   }
	
    public Evento(SafeText aNome, SafeText aDescricao, SafeText aLink, SafeText aImagem, DateTime aDataInicio,
        DateTime aDataFim, Id aIdTipoInteresse, Id aIdUser) {
    nome = aNome;
    descricao = aDescricao;
    link = aLink;
    imagem = aImagem;
    fDataInicio = aDataInicio;
    fDataFim = aDataFim;
    idTipoInteresse = aIdTipoInteresse;
    idUser = aIdUser;
    }	
	
	public Evento(SafeText aNome, SafeText aDescricao, SafeText aLink, DateTime aDataInicio,
        DateTime aDataFim, Id aIdTipoInteresse) {
      nome = aNome;
      descricao = aDescricao;
      link = aLink;
      fDataInicio = aDataInicio;
      fDataFim = aDataFim;
      idTipoInteresse = aIdTipoInteresse;
	}
	
	//Used to list interest types
    public Evento(Id aIdTipo, SafeText aDescricaoIT) throws ModelCtorException{
      idTipoInteresse = aIdTipo;
      descricaoTI = aDescricaoIT;
    }
    
    //Used to list reference points
    public Evento(Id aIdPontoRef, SafeText aNomePontoRef, Integer privadoPontoRef) throws ModelCtorException{
      fIdPontoRef = aIdPontoRef;
      fNomePontoRef = aNomePontoRef;
    }
	
    public DateTime getDataInicio(){return fDataInicio;}
    public DateTime getDataFim(){return fDataFim;}
    public SafeText getDataInicioTB(){return new SafeText(fDataInicio.format("YYYYMMDD"));}
    public SafeText getDataFimTB(){return new SafeText(fDataFim.format("YYYYMMDD"));}
    public Integer getNumAva(){return fNumAva;}
    public BigDecimal getEstrelasMedia(){
      if(fEstrelasMedia != null)
        return fEstrelasMedia.setScale(2, BigDecimal.ROUND_HALF_UP);
      return null;
    }
    public Integer getPagamentoPat(){return fPagamentoPat;}
    public DateTime getDataFimPat(){return fDataFimPat;}
    public Id getTipoInteresse(){return idTipoInteresse;}
    public Id getIdTipoInteresse(){return idTipoInteresse;}
    public Id getIdPontoRef(){return fIdPontoRef;}
    public Id getPontoRef(){return fIdPontoRef;}
    public SafeText getNomePontoRef(){return fNomePontoRef;}
    public SafeText getAltitude() {return altitude;}	
    public Id getIdCoord(){return fIdCoord;}

	public Id getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Id idEvento) {
		this.idEvento = idEvento;
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

	public Integer getPrivadoEvento() {
		return privadoEvento;
	}

	public void setPrivadoEvento(Integer privadoEvento) {
		this.privadoEvento = privadoEvento;
	}
}