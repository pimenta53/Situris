-- Here, the login name is used as criterion, not the user id, since it's already convenient to do so
FETCH_PREFERENCES {
  SELECT Users.idUser, Users.username, Users.name, UserRole.role FROM Users LEFT JOIN UserRole ON
            Users.username = UserRole.username
  WHERE Users.username=?
}

CHANGE_PREFERENCES {
  UPDATE Users SET  name=? WHERE username=?
}
