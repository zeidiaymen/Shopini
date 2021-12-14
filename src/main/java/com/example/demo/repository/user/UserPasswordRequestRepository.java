package com.example.demo.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.user.User;
import com.example.demo.entity.user.UserPasswordRequest;


public interface UserPasswordRequestRepository  extends JpaRepository <UserPasswordRequest,Integer>{
	public Optional<UserPasswordRequest> findByToken(String token);
	public Optional<UserPasswordRequest> findByUser(User user);


}
