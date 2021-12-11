package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Livreur;

@Repository
public interface LivreurRepository extends JpaRepository<Livreur, String> {
	public Optional<Livreur> findByActivationToken(String activationToken);

	public Optional<Livreur> findByEmail(String email);
	
	@Query(value = "SELECT COUNT(*) FROM USER u where u.role='LIVREUR'",
			  nativeQuery = true)
			int sizeLivreur();

}
