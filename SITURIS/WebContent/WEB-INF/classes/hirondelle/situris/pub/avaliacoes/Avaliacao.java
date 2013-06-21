package hirondelle.situris.pub.avaliacoes;

import hirondelle.web4j.model.Id;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.security.SafeText;

public class Avaliacao {

	private Id idAvaliacao;
	private Integer estrelas;
	private SafeText comentario;
	private SafeText data;
	private SafeText nomeUtilizador;
	private Id idRoteiro;

	public Avaliacao(Id i, Integer e, SafeText c, SafeText d, SafeText n, Id id)
			throws ModelCtorException {
		idAvaliacao = i;
		estrelas = e;
		comentario = c;
		data = d;
		nomeUtilizador = n;
		idRoteiro = id;
	}

	public Id getIdAvaliacao() {
		return idAvaliacao;
	}

	public Integer getEstrelas() {
		return estrelas;
	}

	public SafeText getComentario() {
		return comentario;
	}

	public SafeText getData() {
		return data;
	}

	public SafeText getNomeUtilizador() {
		return nomeUtilizador;
	}

	public Id getIdRoteiro() {
		return idRoteiro;
	}
}