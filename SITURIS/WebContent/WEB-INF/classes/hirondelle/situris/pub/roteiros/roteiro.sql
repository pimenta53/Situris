FETCH_ROTEIRO {
 SELECT r.idRoteiro, r.nome, r.descricao, ti.descricao, ROUND(AVG(a.estrelas)) AS ava
 FROM (Roteiro r LEFT JOIN Avaliacao a ON r.idRoteiro = a.idRoteiro) LEFT JOIN TipoInteresse ti 
        ON r.idTipoInteresse = ti.idTipoInteresse
 WHERE r.idRoteiro = ?
}

LIST_ROTEIROS {
  SELECT r.idRoteiro, r.nome, r.descricao, ti.descricao, ROUND(AVG(a.estrelas)) AS ava FROM (Roteiro r LEFT JOIN 
  Avaliacao a ON r.idRoteiro = a.idRoteiro) LEFT JOIN TipoInteresse ti ON r.idTipoInteresse = ti.idTipoInteresse
  WHERE r.privadoRoteiro = 0
  GROUP BY r.idRoteiro
  ORDER BY AVG(a.estrelas) DESC, COUNT(a.idRoteiro) DESC
}

LIST_PR_ROTEIRO {
 SELECT pr.idPontoRef, pr.nome, pr.descricao, pr.privadoPontoRef, pr.area, gps.latitude, gps.longitude, gps.altitude, p.nomePais
 FROM (((Roteiro r LEFT JOIN Roteiro_PontoReferencia rpr ON r.idRoteiro = rpr.idRoteiro) LEFT JOIN PontoReferencia pr ON
  rpr.idPontoRef = pr.idPontoRef) LEFT JOIN pais p ON pr.idPais = p.idPais) LEFT JOIN CoordenadasGPS gps ON 
  pr.idCoordenadasGPS = gps.idCoordenadasGPS
 WHERE rpr.idRoteiro = ? and r.privadoRoteiro = 0
 GROUP BY pr.idPontoRef
 ORDER BY rpr.posicao ASC
}

LIST_COMMENTS_ROTEIRO {
	SELECT a.idAvaliacao, a.estrelas, a.comentario, a.data, u.name, a.idRoteiro
	FROM Avaliacao a LEFT JOIN Users u ON a.idUser = u.idUser
	WHERE a.idRoteiro = ?
	GROUP BY a.idAvaliacao
	ORDER BY a.data DESC LIMIT 10
}

LIST_IMAGENS_VISITAS{
SELECT v.imagem FROM (((Roteiro r LEFT JOIN Roteiro_pontoreferencia rpr on r.idroteiro = rpr.idroteiro) left join pontoreferencia pr on rpr.idpontoref = pr.idpontoref) LEFT JOIN pontoreferencia_visita prv on pr.idpontoref = prv.idpontoref) left join visita v on prv.idvisita = v.idvisita
WHERE r.idRoteiro = ?
}