LIST_PROPOSALS_CLIENT {
  SELECT pp.idProposta, pp.valor, pp.descricao, pp.estado, pp.data FROM PropostaPatrocinio pp
  WHERE pp.idUser = ?
  ORDER BY pp.estado DESC
}

LIST_SPONSORSHIPS_CLIENT{
  SELECT p.idPatrocinio, p.dataInicio, p.dataFm, p.nCliques, p.estadoPagamento, p.descricao, p.idProposta, 
    p.idCategoria, p.idPerfil, p.idEvento, p.idVisita
  FROM Patrocinio p
  WHERE p.idUser = ?
  ORDER BY p.nCliques DESC
}