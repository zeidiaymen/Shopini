package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;


import com.example.demo.entity.Facture;

public interface RepFacture extends JpaRepository<Facture, Integer> {

	//@Query("SELECT c FROM Facture c WHERE c.codeQrAuthentif = ?1 ")

	public Facture getFactureByCodeQrAuthentif(String code);
	
	
	@Query("SELECT c from Facture c WHERE c.client.id = ?1")
	public Facture getFactureByIdClient(String id );
	
	@Query("SELECT count(*) FROM Facture c WHERE c.client.id =?1")
	public int clientFactureCounter(String id);
	@Query("SELECT sum(c.total) FROM Facture c ")
	public float getBillOfTheDay();
}
