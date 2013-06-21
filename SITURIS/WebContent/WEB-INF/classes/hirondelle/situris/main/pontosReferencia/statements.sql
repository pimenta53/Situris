FETCH_PONTO_ADMIN {
SELECT pr.idPontoRef, pr.nome, pr.descricao, pr.idCoordenadasGPS, c.latitude, c.longitude, c.altitude, p.idPais, p.NomePais
 FROM (PontoReferencia pr LEFT JOIN CoordenadasGPS c on c.idCoordenadasGPS = pr.idCoordenadasGPS) LEFT JOIN pais p ON 
      p.idPais = pr.idPais
 WHERE pr.idPontoRef = ?
}

LIST_PONTOS_ADMIN{
SELECT pr.idPontoRef, pr.nome, p.nomePais, COUNT(a.idAvaliacao) AS cnt, AVG(a.estrelas) AS ava, COUNT(DISTINCT pre.idEvento) AS cntE, 
      COUNT(DISTINCT prv.idVisita) AS cntV, COUNT(DISTINCT rpr.idRoteiro) AS cntR
 FROM ((((PontoReferencia pr LEFT JOIN pais p ON p.idPais = pr.idPais) LEFT JOIN Avaliacao a ON a.idPontoRef = pr.idPontoRef) 
      LEFT JOIN PontoReferencia_Evento pre on pr.idPontoRef = pre.idPontoRef) LEFT JOIN PontoReferencia_Visita prv ON 
      pr.idPontoRef = prv.idPontoRef) LEFT JOIN Roteiro_PontoReferencia rpr ON pr.idPontoRef = rpr.idPontoRef 
 GROUP BY pr.idPontoRef
 ORDER BY cnt DESC, ava DESC
}

ADD_PONTO_ADMIN {
  INSERT INTO PontoReferencia(nome, descricao, idUser, idCoordenadasGPS, idPais)  VALUES (?,?,?,?,?)
}

--Done
CHANGE_PONTO_ADMIN{
  UPDATE PontoReferencia SET nome=?, descricao=?, idUser=?, idPais = ?  WHERE idPontoRef=?
}

ADD_GPS_PTREF{
  INSERT INTO CoordenadasGPS (latitude,longitude,altitude)  VALUES (?,?,?)
}

SET_GPS_PTREF{
  UPDATE CoordenadasGPS SET latitude = ?, longitude = ?, altitude = ? WHERE idCoordenadasGPS = ?
}

DELETE_PONTO_ROTEIRO{
 DELETE FROM Roteiro_PontoReferencia WHERE idPontoRef = ?
}

COUNT_PONTO_EVENTO{
 SELECT COUNT(DISTINCT pre.idEvento) FROM PontoReferencia_Evento pre WHERE pre.idPontoRef = ?
}

COUNT_PONTO_VISITA{
 SELECT COUNT(DISTINCT prv.idVisita) FROM PontoReferencia_Visita prv WHERE prv.idPontoRef = ?
}

DELETE_PONTO_PTREF {
  DELETE FROM PontoReferencia WHERE idPontoRef = ?
}

DELETE_PONTO_COMMENT {
 DELETE FROM Avaliacao WHERE idPontoRef = ?
}

DELETE_PONTO_TRANSPORT {
 DELETE FROM HorarioTransporte WHERE idPontoRef = ?
}

LIST_PAISES {
 SELECT * FROM pais
}

--Get all routes that have associated a certain reference point
GET_ROUTES{
 SELECT idRoteiro, posicao FROM Roteiro_PontoReferencia WHERE idPontoRef = ?
}
 
SET_NEW_POSITIONS{
 UPDATE Roteiro_PontoReferencia rpr SET rpr.posicao = rpr.posicao - 1 WHERE rpr.idRoteiro = ? and rpr.posicao > ?
}

