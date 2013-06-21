FETCH_EVENTS_HOME{
  SELECT e.idEvento, e.nome, e.dataInicio, e.dataFim, e.link FROM Evento e 
  WHERE e.privadoEvento = 0 AND e.dataFim >= CURRENT_DATE
  ORDER BY e.dataFim ASC 
  LIMIT 10
}

FETCH_ROUTES_HOME{
  SELECT r.idRoteiro, r.nome, ti.descricao, ROUND(AVG(a.estrelas)) AS ava FROM (Roteiro r LEFT JOIN 
  Avaliacao a ON r.idRoteiro = a.idRoteiro) LEFT JOIN TipoInteresse ti ON r.idTipoInteresse = ti.idTipoInteresse
  WHERE r.privadoRoteiro = 0
  GROUP BY r.idTipoInteresse
  ORDER BY AVG(a.estrelas) DESC, COUNT(a.idRoteiro) DESC
  LIMIT 10
}

FETCH_CLIENTS_HOME{
  SELECT p.idPatrocinio, e.idEvento, e.nome, v.idVisita, v.nome  FROM ((Patrocinio p LEFT JOIN PropostaPatrocinio pp ON 
    p.idProposta = pp.idProposta) LEFT JOIN Visita v ON v.idVisita = p.idVisita) LEFT JOIN Evento e ON 
    e.idEvento = p.idEvento
  WHERE p.dataFm >= CURRENT_DATE AND (v.privadoVisita = 0 OR e.privadoEvento = 0)
  GROUP BY p.idPatrocinio
  ORDER BY pp.valor DESC
  LIMIT 10
}