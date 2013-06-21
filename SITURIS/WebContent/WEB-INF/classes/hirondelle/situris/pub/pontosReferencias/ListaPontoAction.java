package hirondelle.situris.pub.pontosReferencias;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.model.AppException;
import hirondelle.web4j.action.ActionImpl;
import hirondelle.web4j.request.RequestParser;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.SqlId;

public class ListaPontoAction extends ActionImpl {

	public static final SqlId LIST_PONTOS = new SqlId("LIST_PONTOS");

	public ListaPontoAction(RequestParser aRequestParser) {
		super(FORWARD, aRequestParser);
	}

	@Override
	public ResponsePage execute() throws AppException {
		ListaDAO dao = new ListaDAO();
		addToRequest(ITEMS_FOR_LISTING, dao.list());

		return getResponsePage();
	}

	// PRIVATE
	private static final ResponsePage FORWARD = TemplatedPage.get("Reference Points", "pontosReferencia.jsp", ListaPontoAction.class);

}