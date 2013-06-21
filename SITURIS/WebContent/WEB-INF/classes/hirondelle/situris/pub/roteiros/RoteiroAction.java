package hirondelle.situris.pub.roteiros;

import java.util.List;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.model.AppException;
import hirondelle.web4j.action.ActionImpl;
import hirondelle.web4j.request.RequestParameter;
import hirondelle.web4j.request.RequestParser;
import hirondelle.web4j.security.SafeText;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.SqlId;

public class RoteiroAction extends ActionImpl {

    public static final SqlId FETCH_ROTEIRO = new SqlId("FETCH_ROTEIRO");
    public static final SqlId LIST_COMMENTS_ROTEIRO = new SqlId("LIST_COMMENTS_ROTEIRO");
    public static final SqlId LIST_PR_ROTEIRO = new SqlId("LIST_PR_ROTEIRO");

	public static final RequestParameter ROTEIRO_ID = RequestParameter.withLengthCheck("ParentId");

	private static final String itemsForListing1 = "x";
	private static final String itemsForListing2 = "y";
	  public static final SqlId LIST_IMAGENS_VISITAS = new SqlId("LIST_IMAGENS_VISITAS");
	
	
	public RoteiroAction(RequestParser aRequestParser) {
		super(FORWARD, aRequestParser);
	}

	public ResponsePage execute() throws AppException {

		Roteiro r = fDAO.fetch(getIdParam(ROTEIRO_ID));

		if (r == null) {
			addError("Item no longer exists. Likely deleted by another user.");
		} else {
			addToRequest(ITEM_FOR_EDIT, r);
		}
		addToRequest(itemsForListing1, fDAO.list_PR_Roteiro(getIdParam(ROTEIRO_ID)));
		addToRequest(itemsForListing2, fDAO.list_Ava_Roteiro(getIdParam(ROTEIRO_ID)));
		return getResponsePage();
	}

    private RoteirosDAO fDAO = new RoteirosDAO();
	private static final ResponsePage FORWARD = TemplatedPage.get(
			"Route", "roteiro.jsp", RoteiroAction.class);
}
