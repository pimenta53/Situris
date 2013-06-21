-- Fetch a user record
FETCH_PARTIAL_USER {
  SELECT email FROM Users WHERE email=?
}

-- Update the user's record, to set a temporary nonce for resetting the password.
-- When the user attempts to reset it, the given nonce must be present, and 'not too old'
SET_TEMP_PASSWORD_NONCE {
  UPDATE Users SET passwordNonce=?, passwordNonceCreatedOn=NOW() WHERE email=?
}



