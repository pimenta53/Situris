package hirondelle.situris.pub.centrosInteresse;

import hirondelle.web4j.model.Id;
import hirondelle.web4j.security.SafeText;

public class TipoInteresse {

	private Id idTipoInteresse;
	private SafeText descricao;

	public TipoInteresse (Id id, SafeText d) {
	  idTipoInteresse=id;
	  descricao=d;
	}

	public Id getIdTipoInteresse() {return idTipoInteresse;}

	public SafeText getDescricao() {return descricao;}
}
