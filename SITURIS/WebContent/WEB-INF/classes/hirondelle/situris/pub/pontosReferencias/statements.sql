FETCH_PONTO {
 SELECT pr.idPontoRef, pr.nome, pr.descricao, gps.latitude, gps.longitude , gps.altitude, p.nomePais, ROUND(AVG(a.estrelas)) AS ava
  FROM ((PontoReferencia pr LEFT JOIN CoordenadasGPS gps ON pr.idCoordenadasGPS = gps.idCoordenadasGPS) LEFT JOIN pais p ON
        p.idPais = pr.idPais) LEFT JOIN Avaliacao a ON a.idPontoRef = pr.idPontoRef
  WHERE pr.idPontoRef = ? and pr.privadoPontoRef = 0
}

LIST_PONTOS {
 SELECT pr.idPontoRef, pr.nome, pr.descricao, gps.latitude, gps.longitude , gps.altitude, p.nomePais, ROUND(AVG(a.estrelas)) AS ava
  FROM ((PontoReferencia pr LEFT JOIN CoordenadasGPS gps ON pr.idCoordenadasGPS = gps.idCoordenadasGPS) LEFT JOIN pais p ON
        p.idPais = pr.idPais) LEFT JOIN Avaliacao a ON a.idPontoRef = pr.idPontoRef
  WHERE pr.privadoPontoRef = 0
  GROUP BY pr.idPontoRef
  ORDER BY AVG(a.estrelas) DESC, COUNT(a.idPontoRef) DESC
}

LIST_EVENTOS_PONTO {
 SELECT e.idEvento, e.nome, e.descricao, e.link, e.imagem, gps.latitude, gps.longitude, ti.descricao, e.idUser,
      e.dataInicio, e.dataFim
 FROM (((PontoReferencia pr LEFT JOIN PontoReferencia_Evento pre ON pr.idPontoRef = pre.idPontoRef) LEFT JOIN Evento e ON
      e.idEvento = pre.idEvento) LEFT JOIN TipoInteresse ti ON ti.idTipoInteresse = e.idTipoInteresse) LEFT JOIN CoordenadasGPS gps
      ON gps.idCoordenadasGPS = e.idCoordenadasGPS
 WHERE pr.idPontoRef = ? and pr.privadoPontoRef = 0
}

LIST_VISITAS_PONTO {
 SELECT v.idVisita, v.nome, v.descricao, v.link, v.imagem, gps.latitude, gps.longitude, ti.descricao, v.idUser
 FROM (((PontoReferencia pr LEFT JOIN PontoReferencia_Visita prv ON pr.idPontoRef = prv.idPontoRef) LEFT JOIN Visita v ON
      v.idVisita = prv.idVisita) LEFT JOIN TipoInteresse ti ON ti.idTipoInteresse = v.idTipoInteresse) LEFT JOIN CoordenadasGPS gps
      ON gps.idCoordenadasGPS = v.idCoordenadasGPS
 WHERE pr.idPontoRef = ?  and pr.privadoPontoRef = 0
}

LIST_COMMENTS_PONTO {
	SELECT a.idAvaliacao, a.estrelas, a.comentario, a.data, u.name, a.idPontoRef
	FROM (Avaliacao a LEFT JOIN Users u ON u.idUser = a.idUser) LEFT JOIN pontoreferencia pr ON pr.idPontoRef = a.idPontoRef
	WHERE pr.idPontoRef = ?
	ORDER BY a.data DESC LIMIT 10
}



