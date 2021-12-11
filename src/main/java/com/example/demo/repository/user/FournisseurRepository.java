package com.example.demo.repository.user;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Fournisseur;
@Repository
public interface FournisseurRepository extends JpaRepository <Fournisseur,String> {	
	public Optional<Fournisseur> findByActivationToken(String activationToken);
	public Optional<Fournisseur> findByEmail(String email);
	
	@Query(value = "SELECT COUNT(*) FROM USER u where u.role='FOURNISSEUR'",
			  nativeQuery = true)
			int sizeFournisseur();

}
