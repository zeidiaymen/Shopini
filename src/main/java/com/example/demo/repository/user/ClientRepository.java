package com.example.demo.repository.user;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.user.Client;
@Repository
public interface ClientRepository extends JpaRepository <Client,String> {	
	public Optional<Client> findByActivationToken(String activationToken);
	public Optional<Client> findByEmail(String email);
	
	
	@Query(value = "SELECT COUNT(*) FROM USER u where u.role='CLIENT'",
			  nativeQuery = true)
			int sizeClients();

}
