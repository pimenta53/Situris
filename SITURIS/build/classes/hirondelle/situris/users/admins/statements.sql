LIST_PROPOSALS_ADMIN {
  SELECT pp.idProposta, pp.valor, pp.descricao, pp.estado, pp.data, pp.idUser FROM PropostaPatrocinio pp
  ORDER BY pp.data DESC
}

LIST_SPONSORSHIPS_ADMIN{
  SELECT p.idPatrocinio, p.dataInicio, p.dataFm, p.nCliques, p.estadoPagamento, p.descricao, p.idProposta, 
    p.idCategoria, p.idPerfil, p.idEvento, p.idVisita, p.idUser
  FROM Patrocinio p
  ORDER BY p.dataFm ASC
}

CHANGE_PROPOSAL{
  UPDATE PropostaPatrocinio pp SET pp.estado = 1 WHERE pp.idProposta = ?
}

LIST_ALL_EVENTS{
  SELECT e.idEvento, e.nome FROM Evento e WHERE e.privadoEvento = 0
}

LIST_ALL_VISITS{
  SELECT v.idVisita, v.nome FROM Visita v WHERE v.privadoVisita = 0
}

LISTA_ALL_CATEGORIAS{
  SELECT c.idCategoria, c.descricao FROM Categoria c
}

LISTA_ALL_PERFIL{
 SELECT p.idPerfil, p.descricao FROM Perfil p
}

CREATE_PATROCINIO_EVENTO{
 INSERT INTO Patrocinio (dataInicio,dataFm,descricao,idUser,idProposta,idCategoria,idPerfil,idEvento) values (?,?,?,?,?,?,?,?)
}

CREATE_PATROCINIO_VISITA{
 INSERT INTO Patrocinio (dataInicio,dataFm,descricao,idUser,idProposta,idCategoria,idPerfil,idVisita) values (?,?,?,?,?,?,?,?)
}

FETCH_USER_USERNAME{
  SELECT username FROM Users WHERE idUser = ?
}

SET_USER_CLIENT {
  UPDATE UserRole SET role = 'client' WHERE username = ?
}

GET_DATA_PAG{
 SELECT tb.idTabelaPrecos, tb.valor, p.descricao, c.descricao, pat.nCliques, pat.descricao, pat.dataInicio, pat.dataFm, u.name, 
        e.nome, v.nome
 FROM (((((Patrocinio pat LEFT JOIN Categoria c ON pat.idCategoria = c.idCategoria) LEFT JOIN Perfil p ON pat.idPerfil = p.idPerfil) 
      LEFT JOIN TabelaPrecos tb ON pat.idPerfil = tb.idPerfil AND pat.idCategoria = tb.idCategoria AND tb.tipoTabela = ?) LEFT JOIN Users u ON 
      u.idUser = pat.idUser) LEFT JOIN Evento e ON e.idEvento = pat.idEvento) LEFT JOIN Visita v ON pat.idVisita = v.idVisita 
 WHERE pat.idPatrocinio = ?
}

GET_VALOR_PAT{
 SELECT pp.valor FROM Patrocinio p LEFT JOIN PropostaPatrocinio pp on p.idProposta = pp.idProposta
 WHERE p.idPatrocinio = ?
}

ADD_PAG{
 INSERT INTO Pagamento (valorPagamento, idPatrocinio, idTabelaPrecos) VALUES (?,?,?) 
}

SET_ESTADO_PAT{
 UPDATE Patrocinio SET estadoPagamento = 1 WHERE idPatrocinio = ?
}

LIST_PAYMENTS{
  SELECT pag.dataPagamento, pag.valorPagamento, per.descricao, c.descricao, u.name, e.nome, v.nome
  FROM (((((Pagamento pag LEFT JOIN Patrocinio pat ON pag.idPatrocinio = pat.idPatrocinio) LEFT JOIN Categoria c ON 
      c.idCategoria = pat.idCategoria) LEFT JOIN Perfil per ON per.idPerfil = pat.idPerfil) LEFT JOIN Users u ON 
      u.idUser = pat.idUser) LEFT JOIN Evento e ON e.idEvento = pat.idEvento) LEFT JOIN Visita v ON v.idVisita = pat.idVisita
  ORDER BY pag.valorPagamento DESC, pag.dataPagamento DESC
}

