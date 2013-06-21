package hirondelle.situris.pub.pontosReferencias;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.action.ActionImpl;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.model.AppException;
import hirondelle.web4j.request.RequestParameter;
import hirondelle.web4j.request.RequestParser;

public class VerPontoAction extends ActionImpl {

	public static final SqlId FETCH_PONTO = new SqlId("FETCH_PONTO");
	public static final SqlId LIST_EVENTOS_PONTO = new SqlId("LIST_EVENTOS_PONTO");
	public static final SqlId LIST_VISITAS_PONTO = new SqlId("LIST_VISITAS_PONTO");
	public static final SqlId LIST_COMMENTS_PONTO = new SqlId("LIST_COMMENTS_PONTO");

	public static final RequestParameter PONTO_ID = RequestParameter.withLengthCheck("ParentId");

	private static final String itemsForListing1 = "listaEventos";
	private static final String itemsForListing2 = "listaVisitas";
	private static final String itemsForListing3 = "listaComentarios";

	public VerPontoAction(RequestParser aRequestParser) {
		super(FORWARD, aRequestParser);
	}

	public ResponsePage execute() throws AppException {
		PontoReferencia pr = ListaDAO.fetch(getIdParam(PONTO_ID));
		if (pr == null) {
			addError("Item no longer exists!");
		} else {
			addToRequest(ITEM_FOR_EDIT, pr);
		}
		ListaDAO dao = new ListaDAO();
		addToRequest(itemsForListing1, dao.list_eventos_ponto(getIdParam(PONTO_ID)));
		addToRequest(itemsForListing2, dao.list_visitas_ponto(getIdParam(PONTO_ID)));
		addToRequest(itemsForListing3, dao.list_comentarios_Ponto(getIdParam(PONTO_ID)));
		return getResponsePage();
	}

	private static final ResponsePage FORWARD = TemplatedPage.get("Reference Point", "pontoRef.jsp", VerPontoAction.class);
}
