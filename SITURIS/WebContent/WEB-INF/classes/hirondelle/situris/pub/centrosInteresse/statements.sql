VISITA_FETCH {
 SELECT v.idVisita, v.nome, v.descricao, v.link, v.imagem, gps.latitude, gps.longitude, ti.descricao, v.idUser, ROUND(AVG(a.estrelas))
 FROM (((visita v LEFT JOIN avaliacao a ON
    v.idVisita = a.idVisita) LEFT JOIN coordenadasgps gps ON gps.idCoordenadasGPS = v.idCoordenadasGPS) LEFT JOIN tipointeresse ti ON
    ti.idTipoInteresse = v.idTipoInteresse)
 WHERE v.idVisita = ? and v.privadoVisita = 0
}

LIST_VISITAS {
 SELECT v.idVisita, v.nome, v.descricao, v.link, v.imagem, gps.latitude, gps.longitude, ti.descricao, v.idUser, ROUND(AVG(a.estrelas)) AS ava
 FROM ((Visita v LEFT JOIN TipoInteresse ti ON ti.idTipoInteresse = v.idTipoInteresse) LEFT JOIN CoordenadasGPS gps ON 
   gps.idCoordenadasGPS = v.idCoordenadasGPS) LEFT JOIN Avaliacao a ON a.idVisita = v.idVisita
 WHERE v.privadoVisita = 0
 GROUP BY v.idVisita
 ORDER BY AVG(a.estrelas) DESC, COUNT(a.idVisita) DESC
}

EVENTO_FETCH {
 SELECT e.idEvento, e.nome, e.descricao, e.link, e.imagem, gps.latitude, gps.longitude, ti.descricao, e.idUser, e.dataInicio, e.dataFim, ROUND(AVG(a.estrelas))
 FROM (((evento e LEFT JOIN avaliacao a ON
    e.idEvento = a.idEvento) LEFT JOIN coordenadasgps gps ON gps.idCoordenadasGPS = e.idCoordenadasGPS) LEFT JOIN tipointeresse ti ON
    ti.idTipoInteresse = e.idTipoInteresse)
 WHERE e.idEvento = ? and e.privadoEvento = 0
}

LIST_EVENTOS {
 SELECT e.idEvento, e.nome, e.descricao, e.link, e.imagem, gps.latitude, gps.longitude, ti.descricao, e.idUser, e.dataInicio, e.dataFim, ROUND(AVG(a.estrelas))
 FROM ((Evento e LEFT JOIN TipoInteresse ti ON ti.idTipoInteresse = e.idTipoInteresse) LEFT JOIN CoordenadasGPS gps ON 
   gps.idCoordenadasGPS = e.idCoordenadasGPS) LEFT JOIN Avaliacao a ON a.idEvento = e.idEvento
 WHERE e.privadoEvento = 0 and gps.idCoordenadasGPS = e.idCoordenadasGPS and ti.idTipoInteresse = e.idTipoInteresse
 GROUP BY e.idEvento
 ORDER BY AVG(a.estrelas) DESC, COUNT(a.idEvento) DESC
}

LIST_COMMENTS_EVENTO {
	SELECT a.idAvaliacao, a.estrelas, a.comentario, a.data, u.name, a.idEvento
	FROM (Avaliacao a LEFT JOIN Users u ON u.idUser = a.idUser) LEFT JOIN Evento e ON e.idEvento = a.idEvento
	WHERE e.idEvento = ?
	ORDER BY a.data DESC LIMIT 10
}

LIST_COMMENTS_VISITA {
	SELECT a.idAvaliacao, a.estrelas, a.comentario, a.data, u.name, a.idVisita
  FROM (Avaliacao a LEFT JOIN Users u ON u.idUser = a.idUser) LEFT JOIN Visita v ON v.idVisita = a.idVisita
  WHERE v.idVisita = ?
	ORDER BY a.data DESC LIMIT 10
}

SET_PAT_EVENTO{
  UPDATE Patrocinio p SET p.nCliques = p.nCliques + 1 WHERE p.idEvento = ? 
        AND p.dataInicio <= CURRENT_DATE AND p.dataFm >= CURRENT_DATE
}

SET_PAT_VISITA{
  UPDATE Patrocinio p SET p.nCliques = p.nCliques + 1 WHERE p.idVisita = ? 
        AND p.dataInicio <= CURRENT_DATE AND p.dataFm >= CURRENT_DATE
}
