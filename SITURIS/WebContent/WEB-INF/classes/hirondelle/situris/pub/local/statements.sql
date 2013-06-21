MAP_COUNTRY {
 SELECT * FROM Pais WHERE nomePais = ? 
}

MAP_LIST_PONTOS_REF {
  SELECT PontoReferencia.idPontoRef, PontoReferencia.nome, PontoReferencia.descricao, PontoReferencia.privadoPontoRef, 
            ROUND(AVG(Avaliacao.estrelas)) AS ava 
  FROM PontoReferencia LEFT JOIN Avaliacao ON PontoReferencia.idPontoRef = Avaliacao.idPontoRef
  WHERE PontoReferencia.idPais = ? AND PontoReferencia.privadoPontoRef = 0
  GROUP BY PontoReferencia.idPontoRef
  ORDER BY ava DESC
}

MAP_LIST_ROTEIROS {
  SELECT Roteiro.idRoteiro, Roteiro.nome, Roteiro.descricao,Roteiro.privadoRoteiro, TipoInteresse.descricao, ROUND(AVG(Avaliacao.estrelas)) AS ava
  FROM (((Roteiro LEFT JOIN Roteiro_PontoReferencia ON Roteiro.idRoteiro = Roteiro_PontoReferencia.idRoteiro) LEFT JOIN 
    PontoReferencia ON Roteiro_PontoReferencia.idPontoRef = PontoReferencia.idPontoRef) LEFT JOIN Avaliacao ON 
    Roteiro.idRoteiro = Avaliacao.idRoteiro) LEFT JOIN TipoInteresse ON Roteiro.idTipoInteresse = TipoInteresse.idTipoInteresse
  WHERE PontoReferencia.idPais = ? AND Roteiro.privadoRoteiro = 0
  GROUP BY Roteiro.idRoteiro
  ORDER BY ava DESC
}


MAP_LIST_VISITAS {
  SELECT Visita.idVisita, Visita.nome, Visita.descricao, Visita.privadoVisita, TipoInteresse.descricao, ROUND(AVG(Avaliacao.estrelas)) AS ava
  FROM (((Visita LEFT JOIN PontoReferencia_Visita ON Visita.idVisita = PontoReferencia_Visita.idVisita) LEFT JOIN 
    PontoReferencia ON PontoReferencia_Visita.idPontoRef = PontoReferencia.idPontoRef) LEFT JOIN Avaliacao ON 
    Visita.idVisita = Avaliacao.idVisita) LEFT JOIN TipoInteresse ON Visita.idTipoInteresse = TipoInteresse.idTipoInteresse
  WHERE PontoReferencia.idPais = ? AND Visita.privadoVisita = 0
  GROUP BY Visita.idVisita
  ORDER BY ava DESC
}

MAP_LIST_EVENTOS {
  SELECT Evento.idEvento, Evento.nome, Evento.descricao, Evento.privadoEvento, TipoInteresse.descricao, ROUND(AVG(Avaliacao.estrelas)) AS ava,
         Evento.dataInicio, Evento.dataFim
  FROM (((Evento LEFT JOIN PontoReferencia_Evento ON Evento.idEvento = PontoReferencia_Evento.idEvento) LEFT JOIN 
    PontoReferencia ON PontoReferencia_Evento.idPontoRef = PontoReferencia.idPontoRef) LEFT JOIN Avaliacao ON 
    Evento.idEvento = Avaliacao.idEvento) LEFT JOIN TipoInteresse ON Evento.idTipoInteresse = TipoInteresse.idTipoInteresse
  WHERE PontoReferencia.idPais = ? AND Evento.privadoEvento = 0
  GROUP BY Evento.idEvento
  ORDER BY ava DESC
}

MAP_LIST_PONTOS_REF_USER {
  SELECT PontoReferencia.idPontoRef, PontoReferencia.nome, PontoReferencia.descricao, PontoReferencia.privadoPontoRef, 
          ROUND(AVG(Avaliacao.estrelas)) AS ava 
  FROM PontoReferencia LEFT JOIN Avaliacao ON PontoReferencia.idPontoRef = Avaliacao.idPontoRef
  WHERE PontoReferencia.idPais = ? AND (PontoReferencia.privadoPontoRef = 0 OR PontoReferencia.idUser = ?)
  GROUP BY PontoReferencia.idPontoRef
  ORDER BY ava DESC
}

MAP_LIST_ROTEIROS_USER {
  SELECT Roteiro.idRoteiro, Roteiro.nome, Roteiro.descricao, Roteiro.privadoRoteiro, TipoInteresse.descricao, ROUND(AVG(Avaliacao.estrelas)) AS ava
  FROM (((Roteiro LEFT JOIN Roteiro_PontoReferencia ON Roteiro.idRoteiro = Roteiro_PontoReferencia.idRoteiro) LEFT JOIN 
    PontoReferencia ON Roteiro_PontoReferencia.idPontoRef = PontoReferencia.idPontoRef) LEFT JOIN Avaliacao ON 
    Roteiro.idRoteiro = Avaliacao.idRoteiro) LEFT JOIN TipoInteresse ON Roteiro.idTipoInteresse = TipoInteresse.idTipoInteresse
  WHERE PontoReferencia.idPais = ? AND (Roteiro.privadoRoteiro = 0 OR Roteiro.idUser = ?)
  GROUP BY Roteiro.idRoteiro
  ORDER BY ava DESC
}

MAP_LIST_VISITAS_USER {
  SELECT Visita.idVisita, Visita.nome, Visita.descricao, Visita.privadoVisita, TipoInteresse.descricao, ROUND(AVG(Avaliacao.estrelas)) AS ava
  FROM (((Visita LEFT JOIN PontoReferencia_Visita ON Visita.idVisita = PontoReferencia_Visita.idVisita) LEFT JOIN 
    PontoReferencia ON PontoReferencia_Visita.idPontoRef = PontoReferencia.idPontoRef) LEFT JOIN Avaliacao ON 
    Visita.idVisita = Avaliacao.idVisita) LEFT JOIN TipoInteresse ON Visita.idTipoInteresse = TipoInteresse.idTipoInteresse
  WHERE PontoReferencia.idPais = ? AND (Visita.privadoVisita = 0 OR Visita.idUser = ?)
  GROUP BY Visita.idVisita
  ORDER BY ava DESC
}

MAP_LIST_EVENTOS_USER {
  SELECT Evento.idEvento, Evento.nome, Evento.descricao, Evento.privadoEvento, TipoInteresse.descricao, ROUND(AVG(Avaliacao.estrelas)) AS ava,
         Evento.dataInicio, Evento.dataFim
  FROM (((Evento LEFT JOIN PontoReferencia_Evento ON Evento.idEvento = PontoReferencia_Evento.idEvento) LEFT JOIN 
    PontoReferencia ON PontoReferencia_Evento.idPontoRef = PontoReferencia.idPontoRef) LEFT JOIN Avaliacao ON 
    Evento.idEvento = Avaliacao.idEvento) LEFT JOIN TipoInteresse ON Evento.idTipoInteresse = TipoInteresse.idTipoInteresse
  WHERE PontoReferencia.idPais = ? AND (Evento.privadoEvento = 0 OR Evento.idUser = ?)
  GROUP BY Evento.idEvento
  ORDER BY ava DESC
}

MAP_LIST_PONTOS_REF_ADMIN {
  SELECT PontoReferencia.idPontoRef, PontoReferencia.nome, PontoReferencia.descricao, PontoReferencia.privadoPontoRef, ROUND(AVG(Avaliacao.estrelas)) AS ava 
  FROM PontoReferencia LEFT JOIN Avaliacao ON PontoReferencia.idPontoRef = Avaliacao.idPontoRef
  WHERE PontoReferencia.idPais = ?
  GROUP BY PontoReferencia.idPontoRef
  ORDER BY ava DESC
}

MAP_LIST_ROTEIROS_ADMIN {
  SELECT Roteiro.idRoteiro, Roteiro.nome, Roteiro.descricao, Roteiro.privadoRoteiro, TipoInteresse.descricao, ROUND(AVG(Avaliacao.estrelas)) AS ava
  FROM (((Roteiro LEFT JOIN Roteiro_PontoReferencia ON Roteiro.idRoteiro = Roteiro_PontoReferencia.idRoteiro) LEFT JOIN 
    PontoReferencia ON Roteiro_PontoReferencia.idPontoRef = PontoReferencia.idPontoRef) LEFT JOIN Avaliacao ON 
    Roteiro.idRoteiro = Avaliacao.idRoteiro) LEFT JOIN TipoInteresse ON Roteiro.idTipoInteresse = TipoInteresse.idTipoInteresse
  WHERE PontoReferencia.idPais = ?
  GROUP BY Roteiro.idRoteiro
  ORDER BY ava DESC
}

MAP_LIST_VISITAS_ADMIN {
  SELECT Visita.idVisita, Visita.nome, Visita.descricao, Visita.privadoVisita, TipoInteresse.descricao, ROUND(AVG(Avaliacao.estrelas)) AS ava
  FROM (((Visita LEFT JOIN PontoReferencia_Visita ON Visita.idVisita = PontoReferencia_Visita.idVisita) LEFT JOIN 
    PontoReferencia ON PontoReferencia_Visita.idPontoRef = PontoReferencia.idPontoRef) LEFT JOIN Avaliacao ON 
    Visita.idVisita = Avaliacao.idVisita) LEFT JOIN TipoInteresse ON Visita.idTipoInteresse = TipoInteresse.idTipoInteresse
  WHERE PontoReferencia.idPais = ?
  GROUP BY Visita.idVisita
  ORDER BY ava DESC
}

MAP_LIST_EVENTOS_ADMIN {
  SELECT Evento.idEvento, Evento.nome, Evento.descricao, Evento.privadoEvento, TipoInteresse.descricao, ROUND(AVG(Avaliacao.estrelas)) AS ava,
         Evento.dataInicio, Evento.dataFim
  FROM (((Evento LEFT JOIN PontoReferencia_Evento ON Evento.idEvento = PontoReferencia_Evento.idEvento) LEFT JOIN 
    PontoReferencia ON PontoReferencia_Evento.idPontoRef = PontoReferencia.idPontoRef) LEFT JOIN Avaliacao ON 
    Evento.idEvento = Avaliacao.idEvento) LEFT JOIN TipoInteresse ON Evento.idTipoInteresse = TipoInteresse.idTipoInteresse
  WHERE PontoReferencia.idPais = ?
  GROUP BY Evento.idEvento
  ORDER BY ava DESC
}
