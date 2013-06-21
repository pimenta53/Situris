package hirondelle.situris.pub.centrosInteresse;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.model.AppException;
import hirondelle.web4j.action.ActionImpl;
import hirondelle.web4j.request.RequestParameter;
import hirondelle.web4j.request.RequestParser;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.SqlId;

public class VisitaAction extends ActionImpl {

	public static final SqlId VISITA_FETCH = new SqlId("VISITA_FETCH");
    public static final SqlId SET_PAT_VISITA = new SqlId("SET_PAT_VISITA");
	public static final SqlId LIST_COMMENTS_VISITA = new SqlId("LIST_COMMENTS_VISITA");

	public static final RequestParameter VISITA_ID = RequestParameter.withLengthCheck("ParentId");

	public VisitaAction(RequestParser aRequestParser) {
		super(FORWARD, aRequestParser);
	}

	public ResponsePage execute() throws AppException {

        VisitaDAO dao = new VisitaDAO();
		Visita v = VisitaDAO.fetch(getIdParam(VISITA_ID));

		if (v == null) {
			addError("Item no longer exists. Likely deleted by another user.");
		} else {
	        dao.change(v.getIdVisita());
			addToRequest(ITEM_FOR_EDIT, v);
		}
		addToRequest(ITEMS_FOR_LISTING, dao.list(getIdParam(VISITA_ID)));
		return getResponsePage();
	}

	private static final ResponsePage FORWARD = TemplatedPage.get("Visit", "visita.jsp", VisitaAction.class);
}