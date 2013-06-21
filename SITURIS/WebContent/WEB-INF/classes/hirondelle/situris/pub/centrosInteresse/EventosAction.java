package hirondelle.situris.pub.centrosInteresse;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.model.AppException;
import hirondelle.web4j.action.ActionImpl;
import hirondelle.web4j.request.RequestParser;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.SqlId;

public class EventosAction extends ActionImpl {

	public static final SqlId LIST_EVENTOS = new SqlId("LIST_EVENTOS");

	public EventosAction(RequestParser aRequestParser) {
		super(FORWARD, aRequestParser);
	}

	public ResponsePage execute() throws AppException {
	  EventoDAO dao = new EventoDAO();
	  addToRequest(ITEMS_FOR_LISTING, dao.list());
	  return getResponsePage();
	}

	private static final ResponsePage FORWARD = TemplatedPage.get("Events", "eventos.jsp", EventosAction.class);

}
