package com.example.demo.repository;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Fournisseur;
@Repository
public interface FournisseurRepository extends JpaRepository <Fournisseur,String> {	
	public Optional<Fournisseur> findByActivationToken(String activationToken);
	public Optional<Fournisseur> findByEmail(String email);

}
