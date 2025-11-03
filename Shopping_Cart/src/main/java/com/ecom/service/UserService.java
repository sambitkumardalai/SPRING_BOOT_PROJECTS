package com.ecom.service;

import java.util.List;

import com.ecom.model.UserDtls;
import com.ecom.repository.UserRepository;

public interface UserService {
	public UserDtls saveUser(UserDtls user);

	public void increaseFailedAttempt(UserDtls userDtls);

	public boolean unlockAccountTimeExpired(UserDtls userDtls);

	public void userAccountLock(UserDtls userDtls);

	public UserDtls getUserByEmail(String email);
	
	public List<UserDtls> getUsers(String role);
	
	public boolean updateAccountStatus(Integer id,Boolean status) ;
	public void resetAttempt(int userId);

	public void updateUserResetToken(String email, String resetToken);

	public UserDtls getUserByToken(String token);

	public UserDtls updateUser(UserDtls userByToken);
}
