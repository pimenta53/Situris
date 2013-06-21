-- Reset the user's password.

-- Resetting the user's password can only be accomplished with the 
-- nonce value for the given account, and only for a short interval of 
-- time after the nonce was set

RESET_LOST_PASSWORD {
  UPDATE Users SET password = ? WHERE 
  email=? AND 
  passwordNonce=? AND 
  passwordNonceCreatedOn >= DATE_SUB(NOW(), INTERVAL 15 MINUTE);
}



