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

public class EvaluationVisitaAction extends ActionTemplateShowAndApply {

	/** Constructor. */
	public EvaluationVisitaAction(RequestParser aRequestParser) {
		super(FORWARD, REDIRECT, aRequestParser);
	}

	public static final SqlId ADD_NEW_EVAL_VIS = new SqlId("ADD_NEW_EVAL_VIS");

	public static final RequestParameter STARS = RequestParameter
			.withLengthCheck("stars");
	public static final RequestParameter COMMENT = RequestParameter
			.withLengthCheck("comment");
	public static final RequestParameter ID_VISITA = RequestParameter
			.withLengthCheck("idVisita");

	@Override
	protected void apply() throws AppException {
		EvaluationDAO dao = new EvaluationDAO();
		try {
			dao.addComentarioVisita(fEvaluation, getUserId());
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
					ID_VISITA);
		} catch (ModelCtorException ex) {
			addError(ex);
		}
	}

	// PRIVATE //
	private Evaluation fEvaluation;
	private static final ResponsePage FORWARD = TemplatedPage.get("Visit",
			"view.jsp", EvaluationVisitaAction.class);
	private static final ResponsePage REDIRECT = new ResponsePage(
			"../../pub/centrosInteresse/VisitasAction.list");
}