package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.Login;
import com.project.dto.LoginStatus;
import com.project.dto.RegisterStatus;
import com.project.entity.User;
import com.project.exception.UserServiceException;

import com.project.service.UserService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/UserRegister")
	public RegisterStatus register(@RequestBody User user) {
		try {
			int id = userService.register(user);
			RegisterStatus status = new RegisterStatus();
			status.setStatus(true);
			status.setMessage("Registration successful.");
			status.setRegisteredUserId(id);
			return status;
			
		}
		catch(UserServiceException e) {
			RegisterStatus status = new RegisterStatus();
			status.setStatus(false);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/UserLogin")
	public LoginStatus login(@RequestBody Login login) {
		try {
			User user= userService.login(login.getEmail(), login.getPassword());
            LoginStatus loginStatus = new LoginStatus();
			loginStatus.setStatus(true);
			loginStatus.setMessage("Login successful");
			loginStatus.setUserId(user.getId());
			loginStatus.setName(user.getName());
			return loginStatus;
		}
		catch(UserServiceException e) {
			LoginStatus loginStatus = new LoginStatus();
			loginStatus.setStatus(false);
			loginStatus.setMessage(e.getMessage());
			return loginStatus;
		}
	}
}
