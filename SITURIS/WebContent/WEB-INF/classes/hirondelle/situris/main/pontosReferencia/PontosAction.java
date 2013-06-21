package hirondelle.situris.main.pontosReferencia;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.action.ActionImpl;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.model.AppException;
import hirondelle.web4j.request.RequestParser;

public class PontosAction extends ActionImpl {

	public static final SqlId LIST_PONTOS_ADMIN = new SqlId("LIST_PONTOS_ADMIN");
    public static final SqlId ADD_PONTO_ADMIN = new SqlId("ADD_PONTO_ADMIN");

	public PontosAction(RequestParser aRequestParser) {
		super(FORWARD, aRequestParser);
	}

	@Override
	public ResponsePage execute() throws AppException {

	  addToRequest(ITEMS_FOR_LISTING, dao.listPtRef());

      return getResponsePage();
	}

	// PRIVATE
    PontosRefDAO dao = new PontosRefDAO();
	private static final ResponsePage FORWARD = TemplatedPage.get("All Reference Points", "Pontos.jsp", PontosAction.class);
}
