package hirondelle.situris.main.evaluation;

import hirondelle.web4j.database.DAOException;
import hirondelle.web4j.database.Db;
import hirondelle.web4j.database.DuplicateException;
import hirondelle.web4j.model.Id;

final class EvaluationDAO {

	void addComentarioRoteiro(Evaluation aEva, Id aUserId)
			throws DuplicateException, DAOException {
		Db.add(EvaluationRoteiroAction.ADD_NEW_EVAL_ROT, aEva.getStars(),
				aEva.getComment(), aUserId, aEva.getId());
	}

	void addComentarioVisita(Evaluation aEva, Id aUserId)
			throws DuplicateException, DAOException {
		Db.add(EvaluationVisitaAction.ADD_NEW_EVAL_VIS, aEva.getStars(),
				aEva.getComment(), aUserId, aEva.getId());
	}

	void addComentarioEvento(Evaluation aEva, Id aUserId)
			throws DuplicateException, DAOException {
		Db.add(EvaluationEventoAction.ADD_NEW_EVAL_EVE, aEva.getStars(),
				aEva.getComment(), aUserId, aEva.getId());
	}

	void addComentarioPonto(Evaluation aEva, Id aUserId)
			throws DuplicateException, DAOException {
		Db.add(EvaluationPontoAction.ADD_NEW_EVAL_PON, aEva.getStars(),
				aEva.getComment(), aUserId, aEva.getId());
	}
}
