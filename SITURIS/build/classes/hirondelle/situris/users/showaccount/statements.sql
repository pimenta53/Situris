
FETCH_USER {
 SELECT Users.idUser, Users.username, Users.name, Users.email, UserRole.role FROM Users LEFT JOIN UserRole ON
        Users.username = UserRole.username
 WHERE Users.idUser = ?
}

COUNT_ROT {
  SELECT COUNT(idRoteiro) FROM Roteiro WHERE idUser = ?
}

COUNT_PTREF {
  SELECT COUNT(idPontoRef) FROM PontoReferencia WHERE idUser = ?
}

COUNT_VIS {
  SELECT COUNT(idVisita) FROM Visita WHERE idUser = ?
}

COUNT_EV {
  SELECT COUNT(idEvento) FROM Evento WHERE idUser = ?
}

COUNT_EVA {
 SELECT COUNT(idAvaliacao) FROM Avaliacao WHERE idUser = ?
}

COUNT_COMP {
 SELECT COUNT(idReclamacao) FROM Reclamacao WHERE idUser = ?
}

COUNT_PROP {
 SELECT COUNT(idProposta) FROM PropostaPatrocinio WHERE idUser = ?
}

COUNT_PAT {
 SELECT COUNT(idPatrocinio) FROM Patrocinio WHERE idUser = ?
}