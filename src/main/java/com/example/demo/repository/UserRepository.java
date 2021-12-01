package com.example.demo.repository;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;
@Repository
public interface UserRepository extends JpaRepository <User,String> {	
	public User findByActivationToken(String activationToken);
	public Optional<User> findByEmail(String email);

	

	

}
