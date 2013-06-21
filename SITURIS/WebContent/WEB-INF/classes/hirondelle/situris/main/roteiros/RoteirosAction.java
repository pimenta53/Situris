package hirondelle.situris.main.roteiros;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.model.AppException;
import hirondelle.web4j.action.ActionImpl;
import hirondelle.web4j.request.RequestParser;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.SqlId;

public class RoteirosAction extends ActionImpl {

  public static final SqlId LIST_ROTEIROS_ADMIN_VIEW = new SqlId("LIST_ROTEIROS_ADMIN_VIEW");

  public RoteirosAction(RequestParser aRequestParser) {
    super(FORWARD, aRequestParser);
  }

	public ResponsePage execute() throws AppException {
	  RoteirosDAO dao = new RoteirosDAO();
      addToRequest(ITEMS_FOR_LISTING, dao.listRoteirosAdmin());

      return getResponsePage();
	}

	private static final ResponsePage FORWARD = TemplatedPage.get("All Routes", "roteiros.jsp", RoteirosAction.class);

}
