package hirondelle.situris.main.evaluation;

import hirondelle.situris.util.TemplatedPage;
import hirondelle.web4j.action.ActionTemplateShowAndApply;
import hirondelle.web4j.action.ResponsePage;
import hirondelle.web4j.database.SqlId;
import hirondelle.web4j.model.AppException;
import hirondelle.web4j.model.ModelCtorException;
import hirondelle.web4j.model.ModelFromRequest;
import hirondelle.web4j.request.RequestParameter;
import hirondelle.web4j.request.RequestParser;

public class EvaluationPontoAction extends ActionTemplateShowAndApply {

	/** Constructor. */
	public EvaluationPontoAction(RequestParser aRequestParser) {
		super(FORWARD, REDIRECT, aRequestParser);
	}

	public static final SqlId ADD_NEW_EVAL_PON = new SqlId("ADD_NEW_EVAL_PON");

	public static final RequestParameter STARS = RequestParameter
			.withLengthCheck("stars");
	public static final RequestParameter COMMENT = RequestParameter
			.withLengthCheck("comment");
	public static final RequestParameter ID_PONTO = RequestParameter
			.withLengthCheck("idPontoRef");

	@Override
	protected void apply() throws AppException {
		EvaluationDAO dao = new EvaluationDAO();
		try {
			dao.addComentarioPonto(fEvaluation, getUserId());
			addMessage("Thank you! Your comment was registed.");
		} catch (Exception ex) {
			addError("Please try again. That user name (or email address) is already taken.");
		}
	}

	@Override
	protected void show() throws AppException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void validateUserInput() throws AppException {
		try {
			ModelFromRequest builder = new ModelFromRequest(getRequestParser());
			fEvaluation = builder.build(Evaluation.class, STARS, COMMENT,
					ID_PONTO);
		} catch (ModelCtorException ex) {
			addError(ex);
		}
	}

	// PRIVATE //
	private Evaluation fEvaluation;
	private static final ResponsePage FORWARD = TemplatedPage.get("Point",
			"view.jsp", EvaluationPontoAction.class);
	private static final ResponsePage REDIRECT = new ResponsePage(
			"../../pub/pontosReferencias/ListaPontoAction.list");
}
