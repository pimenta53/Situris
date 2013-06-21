package hirondelle.situris.pub.centrosInteresse;

import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.model.Id;
import hirondelle.web4j.security.SafeText;

public class Visita {

	private Id idVisita;
	private SafeText nome;
	private SafeText descricao;
	private SafeText link;
	private SafeText imagem;
	private SafeText latitude;
	private SafeText longitude;
	private SafeText descricaoTI;
	private Id idUser;
	private Integer estrelas;

	public Visita(Id id, SafeText n, SafeText d, SafeText l, SafeText i,
			SafeText lat, SafeText lon, SafeText dTI, Id idU) throws DAOException {
		idVisita = id;
		nome = n;
		descricao = d;
		imagem = i;
		link = l;
		imagem = i;
		latitude = lat;
		longitude = lon;
		descricaoTI = dTI;
		idUser = idU;
	}

	public Visita(Id id, SafeText n, SafeText d, SafeText l, SafeText i,
			SafeText lat, SafeText lon, SafeText dTI, Id idU, Integer estrelas) throws DAOException {
		idVisita = id;
		nome = n;
		descricao = d;
		imagem = i;
		link = l;
		imagem = i;
		latitude = lat;
		longitude = lon;
		descricaoTI = dTI;
		idUser = idU;
		this.estrelas = estrelas;
	}

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

	public Integer getEstrelas() {
		return estrelas;
	}

	public void setEstrelas(Integer estrelas) {
		this.estrelas = estrelas;
	}
}
