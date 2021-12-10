package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;
import com.example.demo.entity.UserPasswordRequest;


public interface UserPasswordRequestRepository  extends JpaRepository <UserPasswordRequest,Integer>{
	public Optional<UserPasswordRequest> findByToken(String token);
	public Optional<UserPasswordRequest> findByUser(User user);


}
