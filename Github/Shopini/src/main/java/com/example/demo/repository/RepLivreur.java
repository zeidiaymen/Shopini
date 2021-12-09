package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Livreur;

public interface RepLivreur extends JpaRepository<Livreur, Integer>{
	@Query("SELECT c From Livreur c where c.id = ?1")
	public Livreur findByIds(String id);
}
