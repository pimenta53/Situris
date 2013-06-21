LIST_ROTEIROS_ADMIN_VIEW {
 SELECT r.idRoteiro, r.nome, ti.descricao, COUNT(a.idAvaliacao) AS cnt, AVG(a.estrelas) AS ava
 FROM (Roteiro r LEFT JOIN TipoInteresse ti ON r.idTipoInteresse = ti.idTipoInteresse) LEFT JOIN Avaliacao a ON
  a.idRoteiro = r.idRoteiro
 GROUP BY r.idRoteiro
 ORDER BY cnt DESC, ava DESC
}

FETCH_ROTEIRO_ADMIN {
 SELECT r.idRoteiro, r.nome, r.descricao, r.idUser, ti.idTipoInteresse, ti.descricao
 FROM Roteiro r LEFT JOIN TipoInteresse ti ON r.idTipoInteresse = ti.idTipoInteresse
 WHERE r.idRoteiro = ?
}

FETCH_PR_ROTEIRO_X{
 SELECT rpr.idPontoRef, rpr.posicao
 FROM Roteiro_PontoReferencia rpr
 WHERE rpr.idRoteiro = ?
}

LIST_PR_ROTEIRO_ADMIN {
 SELECT pr.idPontoRef, pr.nome, p.nomePais
 FROM (PontoReferencia pr LEFT JOIN pais p ON pr.idPais = p.idPais) LEFT JOIN CoordenadasGPS gps ON 
  pr.idCoordenadasGPS = gps.idCoordenadasGPS
}

CHANGE_ROTEIRO_ADMIN {
 UPDATE Roteiro r SET r.nome = ?, r.descricao = ?, r.idTipoInteresse = ? WHERE r.idRoteiro = ?
}

ADD_ROTEIRO {
 INSERT INTO Roteiro(nome, descricao, idTipoInteresse, idUser) VALUES (?,?,?,?)
}

ADD_PONTO_ROTEIRO{
 INSERT INTO Roteiro_PontoReferencia (idRoteiro,idPontoRef,posicao) VALUES (?,?,?)
}

REMOVE_ROT{
 DELETE FROM Roteiro_PontoReferencia WHERE idRoteiro = ? AND idPontoRef = ?
}

DELETE_ROTEIRO_PTREF{
 DELETE FROM Roteiro_PontoReferencia WHERE idRoteiro = ?
}
 
DELETE_ROTEIRO_ROTEIRO{
 DELETE FROM Roteiro WHERE idRoteiro = ?
}

DELETE_ROTEIRO_COMMENT{
 DELETE FROM Avaliacao WHERE idRoteiro = ?
}
-- FALTA DEFINIR ESTA QUERY
CHANGE_POSITION{
 UPDATE Roteiro_PontoReferencia SET posicao = ? WHERE idRoteiro = ? AND idPontoRef = ?
}

LIST_TIPOS{
 SELECT idTipoInteresse, descricao FROM TipoInteresse
}

SET_DEC_POSITIONS{
 UPDATE Roteiro_PontoReferencia rpr SET rpr.posicao = rpr.posicao - 1 WHERE rpr.idRoteiro = ? AND rpr.posicao > ? 
          AND rpr.idPontoRef != ?
}

SET_INC_POSITIONS{
 UPDATE Roteiro_PontoReferencia rpr SET rpr.posicao = rpr.posicao + 1 WHERE rpr.idRoteiro = ? AND rpr.posicao >= ? 
          AND rpr.idPontoRef != ?
}

GET_NUM_POS{
 SELECT count(distinct idPontoRef) from Roteiro_PontoReferencia where idRoteiro = ?
}