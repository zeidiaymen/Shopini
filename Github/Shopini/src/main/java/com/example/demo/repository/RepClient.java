package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Client;



public interface RepClient extends JpaRepository<Client, Integer> {

	@Query("SELECT c FROM Client c where c.id = ?1")
	public Client findByIds(String id);
	
}
