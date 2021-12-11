package com.example.demo.repository;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;
@Repository
public interface UserRepository extends JpaRepository <User,String> {	
	public Optional<User> findByActivationToken(String activationToken);
	public Optional<User> findByEmail(String email);
	
	@Query(value = "SELECT COUNT(*) FROM USER",
			  nativeQuery = true)
			int sizeUsers();
	
	
	@Query(value = "SELECT COUNT(*) FROM USER u where u.accountStatus='VERIFIED'",
			  nativeQuery = true)
			int sizeUsersVerified();
	
	
	@Query(value = "SELECT COUNT(*) FROM USER u where u.accountStatus='NOT_VERIFIED'",
			  nativeQuery = true)
			int sizeUsersNotVerified();
	
	@Query(value = "SELECT COUNT(*) FROM USER u where u.address= :address",
			  nativeQuery = true)
			int sizeUsersByCountry(@Param("address") String address);
	
	
	
	
	
	
	
	

	
	

}
