ADD_NEW_EVAL_ROT{
  INSERT INTO Avaliacao (estrelas, comentario, idUser, idRoteiro) VALUES (?,?,?,?);
}

ADD_NEW_EVAL_VIS{
  INSERT INTO Avaliacao (estrelas, comentario, idUser, idVisita) VALUES (?,?,?,?);
}

ADD_NEW_EVAL_EVE{
  INSERT INTO Avaliacao (estrelas, comentario, idUser, idEvento) VALUES (?,?,?,?);
}

ADD_NEW_EVAL_PON{
  INSERT INTO Avaliacao (estrelas, comentario, idUser, idPontoRef) VALUES (?,?,?,?);
}


