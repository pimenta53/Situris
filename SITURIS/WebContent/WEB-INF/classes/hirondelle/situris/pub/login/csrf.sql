-- These statements are used by WEB4J to defend your application against
-- Cross-Site Request Forgery (CSRF) attacks.
-- See hirondelle.web4j.security for more information.

-- Returns the form-source id used in a previous session.
-- Must take user name as its single parameter.
FETCH_FORM_SOURCE_ID {
 SELECT formSourceId  FROM Users WHERE username=?
}

-- When a session ends, this statement will save the form-source id for possible 
-- use in the next session.
-- Must take two params: form-source id, and user name (in that order).
SAVE_FORM_SOURCE_ID {
  UPDATE Users SET formSourceId=? WHERE username=?
}