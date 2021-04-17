package com.project.service;



import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.entity.User;
import com.project.exception.UserServiceException;
import com.project.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public int register(User user) {
		if(userRepository.isUserPresent(user.getEmail()))
			throw new UserServiceException("Alredy registered!");
		else {
			user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
			User updateUser = (User) userRepository.save(user.getPassword().getBytes());
			return updateUser.getId();
		}
	}
}
