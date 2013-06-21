UPDATE_USER_NAME {
  UPDATE Users SET name = ? WHERE idUser = ?;
}

UPDATE_USER_PASS {
  UPDATE Users SET password = ? WHERE idUser = ?;
}

UPDATE_USER_EMAIL {
  UPDATE Users SET email = ? WHERE idUser = ?;
}


