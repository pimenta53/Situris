package hirondelle.situris.pub.centrosInteresse;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.model.AppException;
import hirondelle.web4j.action.ActionImpl;
import hirondelle.web4j.request.RequestParser;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.SqlId;

public class VisitasAction extends ActionImpl {

	public static final SqlId LIST_VISITAS = new SqlId("LIST_VISITAS");

	public VisitasAction(RequestParser aRequestParser) {
	  super(FORWARD, aRequestParser);
	}

	public ResponsePage execute() throws AppException {
	  VisitaDAO dao = new VisitaDAO();
	  addToRequest(ITEMS_FOR_LISTING, dao.list());
	  return getResponsePage();
	}

	private static final ResponsePage FORWARD = TemplatedPage.get("Visits","visitas.jsp", VisitasAction.class);
}