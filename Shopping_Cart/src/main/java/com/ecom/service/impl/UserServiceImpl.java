package com.ecom.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.controller.AdminController;
import com.ecom.model.UserDtls;
import com.ecom.repository.UserRepository;
import com.ecom.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDtls saveUser(UserDtls user) {
		user.setRole("ROLE_USER");
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		UserDtls saveUser = userRepository.save(user);
		return saveUser;
	}

	@Override
	public void increaseFailedAttempt(UserDtls userDtls) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean unlockAccountTimeExpired(UserDtls userDtls) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void userAccountLock(UserDtls userDtls) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserDtls getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<UserDtls> getUsers(String role) {
		return userRepository.findByRole(role);
	}

	@Override
	public boolean updateAccountStatus(Integer id, Boolean status) {
		Optional<UserDtls> findByUser = userRepository.findById(id);
		if (findByUser.isPresent()) {
			UserDtls userDtls = findByUser.get();
			userDtls.setIsEnable(status);
			userRepository.save(userDtls);
			log.info("data===========" + status);

			return true;
		}

		return false;
	}

}
