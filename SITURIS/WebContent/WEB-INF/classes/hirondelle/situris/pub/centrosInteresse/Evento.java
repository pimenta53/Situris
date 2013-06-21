package hirondelle.situris.pub.centrosInteresse;

import hirondelle.web4j.model.DateTime;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.security.SafeText;

public class Evento {

	private Id idEvento;
	private SafeText nome;
	private SafeText descricao;
	private SafeText link;
	private SafeText imagem;
	private SafeText latitude;
	private SafeText longitude;
	private SafeText descricaoTI;
	private Id idUser;
	private DateTime dataInicio;
	private DateTime dataFim;
	private Integer estrelas;
	private Id idTipo;
	private Id idCoord;

	public Evento(Id id, SafeText n, SafeText d, SafeText l, SafeText i,
			SafeText lat, SafeText lon, SafeText dTI, Id idU, DateTime dataI, DateTime dataF) {
		idEvento = id;
		nome = n;
		descricao = d;
		imagem = i;
		link = l;
		imagem = i;
		latitude = lat;
		longitude = lon;
		descricaoTI = dTI;
		idUser = idU;
		dataInicio = dataI;
		dataFim = dataF;
	}

	public Evento(Id id, SafeText n, SafeText d, SafeText l, SafeText i,
			SafeText lat, SafeText lon, SafeText dTI, Id idU, DateTime dataI, DateTime dataF, Integer estrelas) {
      idEvento = id;
      nome = n;
      descricao = d;
      imagem = i;
      link = l;
      imagem = i;
      latitude = lat;
      longitude = lon;
      descricaoTI = dTI;
      idUser = idU;
      dataInicio = dataI;
      dataFim = dataF;
      this.estrelas = estrelas;
	}

	public Evento(Id id, SafeText n, SafeText d, SafeText l,DateTime inicio, DateTime fim, Id tipo, Id cor ,Id user){
		idEvento = id;
		nome = n;
		descricao = d;
		link = l;
		idCoord=cor;
		idUser = user;
		dataInicio=inicio;
		dataFim=fim;
		idTipo=tipo;
	}

	public Evento(SafeText n, SafeText d, SafeText l, Id tipo, DateTime inicio, DateTime fim){
      nome = n;
      descricao = d;
      link = l;
      idTipo=tipo;
      dataInicio=inicio;
      dataFim=fim;

  }

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

	public DateTime getDataInicio() {
		return dataInicio;
	}


	public DateTime getDataFim() {
		return dataFim;
	}


	public Id getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Id idTipo) {
		this.idTipo = idTipo;
	}

	public Id getIdCoord() {
		return idCoord;
	}

	public void setIdCoord(Id idCoord) {
		this.idCoord = idCoord;
	}

	public Integer getEstrelas() {
		return estrelas;
	}

	public void setEstrelas(Integer estrelas) {
		this.estrelas = estrelas;
	}
}