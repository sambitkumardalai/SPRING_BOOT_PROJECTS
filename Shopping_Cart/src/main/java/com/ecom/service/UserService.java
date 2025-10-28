package com.ecom.service;

import com.ecom.model.UserDtls;

public interface UserService {
	public UserDtls saveUser(UserDtls user);

	public void increaseFailedAttempt(UserDtls userDtls);

	public boolean unlockAccountTimeExpired(UserDtls userDtls);

	public void userAccountLock(UserDtls userDtls);

	public UserDtls getUserByEmail(String email);
}
