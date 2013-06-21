-- The search on the text field will not be case sensitive, since 
-- the Text field is not defined as 'BINARY'.

-- This is an example of dynamic criteria
-- All static criteria must appear here, and all dynamic criteria are appended in code
--SEARCH_FOR_EXACT_PHRASE {
--  SELECT 
--    Prediction.Text, Prediction.OutcomeFK, Prediction.CreationDate, 
--    Users.ScreenName, PredictionList.Id
--  FROM  Prediction 
--  LEFT JOIN PredictionList ON PredictionListFK = PredictionList.Id 
--  LEFT JOIN Users ON PredictionList.UserFK = Users.Id 
-- Dynamic Criteria that may follow :
-- WHERE 
-- TEXT Like ?  (required)
-- AND Prediction.CreationDate >= ? (optional)
-- AND Prediction.CreationDate <= ? (optional)
-- ORDER BY Prediction.CreationDate DESC LIMIT 200;
--}

SEARCH_ROTEIROS_PUB{
  SELECT Roteiro.idRoteiro, Roteiro.nome, Roteiro.privadoRoteiro FROM Roteiro 
  WHERE Roteiro.privadoRoteiro = 0
}

SEARCH_PONTOS_REF_PUB{
  SELECT PontoReferencia.idPontoRef, PontoReferencia.nome, PontoReferencia.privadoPontoRef FROM PontoReferencia 
  WHERE PontoReferencia.privadoPontoRef = 0
}

SEARCH_VISITAS_PUB{
  SELECT Visita.idVisita, Visita.nome, Visita.privadoVisita FROM Visita 
  WHERE Visita.privadoVisita = 0
}

SEARCH_EVENTOS_PUB{
  SELECT Evento.idEvento, Evento.nome, Evento.privadoEvento FROM Evento 
  WHERE Evento.privadoEvento = 0
}

SEARCH_ROTEIROS_USER{
  SELECT Roteiro.idRoteiro, Roteiro.nome, Roteiro.privadoRoteiro, Roteiro.idUser FROM Roteiro LEFT JOIN Users ON Roteiro.idUser = Users.idUser 
  WHERE (Roteiro.privadoRoteiro = 0 OR (Roteiro.privadoRoteiro = 1
}

SEARCH_PONTOS_REF_USER{
  SELECT PontoReferencia.idPontoRef, PontoReferencia.nome, PontoReferencia.privadoPontoRef, PontoReferencia.idUser FROM PontoReferencia LEFT JOIN Users ON 
      PontoReferencia.idUser = Users.idUser 
  WHERE (PontoReferencia.privadoPontoRef = 0 OR (PontoReferencia.privadoPontoRef = 1
}

SEARCH_VISITAS_USER{
  SELECT Visita.idVisita, Visita.nome, Visita.privadoVisita, Visita.idUser FROM Visita LEFT JOIN Users ON Visita.idUser = Users.idUser 
  WHERE (Visita.privadoVisita = 0 OR (Visita.privadoVisita = 1
}

SEARCH_EVENTOS_USER{
  SELECT Evento.idEvento, Evento.nome, Evento.privadoEvento, Evento.idUser FROM Evento LEFT JOIN Users ON Evento.idUser = Users.idUser 
  WHERE (Evento.privadoEvento = 0 OR (Evento.privadoEvento = 1
}

SEARCH_ROTEIROS_ADMIN{
  SELECT Roteiro.idRoteiro, Roteiro.nome, Roteiro.privadoRoteiro, Roteiro.idUser FROM Roteiro
}

SEARCH_PONTOS_REF_ADMIN{
  SELECT PontoReferencia.idPontoRef, PontoReferencia.nome, PontoReferencia.privadoPontoRef, PontoReferencia.idUser FROM PontoReferencia
}

SEARCH_VISITAS_ADMIN{
  SELECT Visita.idVisita, Visita.nome, Visita.privadoVisita, Visita.idUser FROM Visita 
}

SEARCH_EVENTOS_ADMIN{
  SELECT Evento.idEvento, Evento.nome, Evento.privadoEvento, Evento.idUser FROM Evento 
}