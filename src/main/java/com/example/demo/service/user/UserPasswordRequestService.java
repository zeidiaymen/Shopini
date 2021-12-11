package com.example.demo.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.user.User;
import com.example.demo.entity.user.UserPasswordRequest;
import com.example.demo.repository.user.UserPasswordRequestRepository;
import com.example.demo.utils.user.JwtUtil;
import com.example.demo.utils.user.Utils;

@Service
public class UserPasswordRequestService {
	@Autowired
	UserPasswordRequestRepository userPasswordRequestRepository;

	@Autowired
	Utils utils;
	@Autowired
	private JwtUtil jwtUtil;

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public UserPasswordRequestService() {
		// TODO Auto-generated constructor stub
	}

	public String addUserPasswordRequest(User user) {
		UserPasswordRequest userPasswordRequest = new UserPasswordRequest();
		userPasswordRequest.setUser(user);
		userPasswordRequest.setCreatedAt(Utils.getCurrentDate());

		String token = this.jwtUtil.generateToken(user.getEmail());
		userPasswordRequest.setToken(token);
		userPasswordRequestRepository.save(userPasswordRequest);
		return token;

	}

	public UserPasswordRequest findUserPasswordRequestByToken(String token) {

		return this.userPasswordRequestRepository.findByToken(token).orElse(null);

	}

	public User getUserByToken(String token) {

		UserPasswordRequest userPasswordRequest = this.findUserPasswordRequestByToken(token);
		if (userPasswordRequest != null) {
			User user = userPasswordRequest.getUser();

			if (user != null)
				return userPasswordRequest.getUser();
		}

		return null;

	}

	public UserPasswordRequest findByToken(String token) {
		return this.userPasswordRequestRepository.findByToken(token).orElse(null);

	}

	public void retrieveUserPasswordRequest(UserPasswordRequest userPasswordRequest) {
		this.userPasswordRequestRepository.delete(userPasswordRequest);
	}

	public void retrieveUserPasswordRequest(User user) {
		UserPasswordRequest userPasswordRequest;
		userPasswordRequest = this.userPasswordRequestRepository.findByUser(user).orElse(null);
		if (userPasswordRequest != null)
			this.userPasswordRequestRepository.delete(userPasswordRequest);

	}

}
