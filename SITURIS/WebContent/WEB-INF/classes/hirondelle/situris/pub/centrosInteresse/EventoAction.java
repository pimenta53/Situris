package hirondelle.situris.pub.centrosInteresse;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.model.AppException;
import hirondelle.web4j.action.ActionImpl;
import hirondelle.web4j.request.RequestParameter;
import hirondelle.web4j.request.RequestParser;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.SqlId;

public class EventoAction extends ActionImpl {

	public static final SqlId EVENTO_FETCH = new SqlId("EVENTO_FETCH");
    public static final SqlId SET_PAT_EVENTO = new SqlId("SET_PAT_EVENTO");
	public static final SqlId LIST_COMMENTS_EVENTO = new SqlId("LIST_COMMENTS_EVENTO");

	public static final RequestParameter EVENTO_ID = RequestParameter.withLengthCheck("ParentId");

	public EventoAction(RequestParser aRequestParser) {
		super(FORWARD, aRequestParser);
	}

	public ResponsePage execute() throws AppException {

      EventoDAO dao = new EventoDAO();
		Evento e = EventoDAO.fetch(getIdParam(EVENTO_ID));

		if (e == null) {
			addError("Item no longer exists. Likely deleted by another user.");
		} else {
          dao.change(e.getIdEvento());
          addToRequest(ITEM_FOR_EDIT, e);
		}
		addToRequest(ITEMS_FOR_LISTING, dao.list(getIdParam(EVENTO_ID)));
		return getResponsePage();
	}

	private static final ResponsePage FORWARD = TemplatedPage.get("Event", "evento.jsp", EventoAction.class);
}
