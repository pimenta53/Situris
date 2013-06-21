--VISITS
VISITA_FETCH_ADMIN {
 SELECT v.idVisita, v.nome, v.descricao, v.link, v.imagem, v.idCoordenadasGPS, gps.latitude, gps.longitude, ti.idTipoInteresse, 
          ti.descricao, prv.idPontoRef
 FROM ((Visita v LEFT JOIN TipoInteresse ti ON ti.idTipoInteresse = v.idTipoInteresse) LEFT JOIN 
      CoordenadasGPS gps ON gps.idCoordenadasGPS = v.idCoordenadasGPS) LEFT JOIN PontoReferencia_Visita prv ON 
      prv.idVisita = v.idVisita
 WHERE v.idVisita = ?
}

LIST_VISITAS_ADMIN {
 SELECT v.idVisita, v.nome, v.link, ti.descricao, COUNT(a.idAvaliacao) AS cnt, AVG(a.estrelas) AS ava, p.estadoPagamento, p.dataFm
 FROM ((Visita v LEFT JOIN TipoInteresse ti ON ti.idTipoInteresse = v.idTipoInteresse) LEFT JOIN Avaliacao a ON 
      a.idVisita = v.idVisita) LEFT JOIN Patrocinio p ON p.idVisita = v.idVisita
 GROUP BY v.idVisita
 ORDER BY cnt DESC, ava DESC
}
--Edit a visit
EDIT_VISIT{
  UPDATE Visita SET nome = ?, descricao = ?, link = ?, imagem = ?, idTipoInteresse = ?, idUser = ? WHERE idVisita = ?
}
EDIT_VISIT_PTREF{
 UPDATE PontoReferencia_Visita SET idPontoRef = ? WHERE idVisita = ?
}
--Delete a visit
DELETE_VISITA_COMMENT{
 DELETE FROM Avaliacao WHERE idVisita = ?
}
DELETE_VISITA_PATROCINIO{
 UPDATE Patrocinio SET idVisita = NULL WHERE idVisita = ?
}
DELETE_VISITA_HORARIOS{
 DELETE FROM Horario WHERE idVisita = ?
}
DELETE_VISITA_PTREF{
 DELETE FROM PontoReferencia_Visita WHERE idVisita = ?
}
DELETE_VISITA_VISITA{
 DELETE FROM Visita WHERE idVisita = ?
}
--Add a visit
ADD_VISITA{
 INSERT INTO Visita(nome, descricao, link, imagem, idCoordenadasGPS, idTipoInteresse, idUser) VALUES (?,?,?,?,?,?,?)
}
ADD_VISITA_PTREF{
INSERT INTO PontoReferencia_Visita (idPontoRef, idVisita) VALUES (?,?)
}

--EVENTS
EVENTO_FETCH_ADMIN{
 SELECT e.idEvento, e.nome, e.descricao, e.link, e.imagem, e.dataInicio, e.dataFim, e.idCoordenadasGPS, gps.latitude, gps.longitude, 
          ti.idTipoInteresse, ti.descricao, pre.idPontoRef
 FROM ((Evento e LEFT JOIN tipointeresse ti ON ti.idTipoInteresse = e.idTipoInteresse) LEFT JOIN 
      CoordenadasGPS gps ON gps.idCoordenadasGPS = e.idCoordenadasGPS) LEFT JOIN PontoReferencia_Evento pre ON 
      pre.idEvento = e.idEvento
 WHERE e.idEvento = ?
}

LIST_EVENTOS_ADMIN {
 SELECT e.idEvento, e.nome, e.link, e.dataInicio, e.dataFim, ti.descricao, COUNT(a.idAvaliacao) AS cnt, AVG(a.estrelas) AS ava, 
 p.estadoPagamento, p.dataFm
 FROM ((Evento e LEFT JOIN TipoInteresse ti ON ti.idTipoInteresse = e.idTipoInteresse) LEFT JOIN Avaliacao a ON 
      a.idEvento = e.idEvento) LEFT JOIN Patrocinio p ON p.idEvento = e.idEvento
 GROUP BY e.idEvento
 ORDER BY cnt DESC, ava DESC
}

--Edit an event
EDIT_EVENT{
  UPDATE Evento SET nome = ?, descricao = ?, link = ?, imagem = ?, dataInicio = ?, dataFim = ?, idTipoInteresse = ?, 
                    idUser = ? WHERE idEvento = ?
}
EDIT_EVENT_PTREF{
 UPDATE PontoReferencia_Evento SET idPontoRef = ? WHERE idEvento = ?
}

--Delete an event
DELETE_EVENTO_COMMENT{
 DELETE FROM Avaliacao WHERE idEvento = ?
}
DELETE_EVENTO_PATROCINIO{
 UPDATE Patrocinio SET idEvento = NULL WHERE idEvento = ?
}
DELETE_EVENTO_HORARIOS{
 DELETE FROM Horario WHERE idEvento = ?
}
DELETE_EVENTO_PTREF{
 DELETE FROM PontoReferencia_Evento WHERE idEvento = ?
}
DELETE_EVENTO_EVENTO{
 DELETE FROM Evento WHERE idEvento = ?
}

--Add an event
ADD_EVENTO{
 INSERT INTO Evento(nome, descricao, link, imagem, dataInicio, dataFim, idCoordenadasGPS, idTipoInteresse, idUser) 
 VALUES (?,?,?,?,?,?,?,?,?)
}
ADD_EVENTO_PTREF{
 INSERT INTO PontoReferencia_Evento (idPontoRef, idEvento) VALUES (?,?)
}

--All Interest Types
LIST_TIPOS_CENTROINTERESSE {
 SELECT idTipoInteresse, descricao FROM TipoInteresse
}
--GPS POINT
ADD_GPS_CENTROINTERESSE{
  INSERT INTO CoordenadasGPS (latitude,longitude,altitude)  VALUES (?,?,?)
}
SET_GPS_CENTROINTERESSE{
  UPDATE CoordenadasGPS SET latitude = ?, longitude = ?, altitude = ? WHERE idCoordenadasGPS = ?
}
--All Reference Points
LIST_PTREFS_CENTROINTERESSE{
  SELECT idPontoRef, nome, privadoPontoRef FROM PontoReferencia
}
