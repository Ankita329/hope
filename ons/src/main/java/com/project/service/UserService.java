package com.project.service;

import java.util.Base64;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
			User updatedUser = (User) userRepository.save(user);
			return updatedUser.getId();
		}
	}
	
	public User get(int id) {
		return userRepository.fetch(User.class, id);
	}
	
	public User login(String email, String password) {
		try {
			password = Base64.getEncoder().encodeToString(password.getBytes());
			int id = userRepository.fetchIdByEmailAndPassword(email, password);
			User user= userRepository.fetch(User.class, id);
			return user;
		}
		catch(EmptyResultDataAccessException e) {
			throw new UserServiceException("Invalid username/password");
		}
	}
	
	
}
