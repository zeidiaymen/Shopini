package com.example.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.ByteArrayInputStream;
import java.sql.Date;

import com.example.demo.entity.Produit;


public interface IProduitService {
	
	List<Produit> retrieveAllProduits();
	Produit addProduit(Produit p,String idFournisseur);
	Produit modProduit(Produit p);
	void deleteProduit(Long id);	
	Produit retrieveProduit(Long id);
	ByteArrayInputStream  loadGeoMapCSV();
	ByteArrayInputStream  loadGeoMapPDF();
	Produit assignFournisseurToProduit(String fournisseurId, Long produitId) ;
	List<Produit> getProductsCreatedBetween(Date dateDebut , Date dateFin) ;
	float getProductsRevenueBetween(Date dateDebut , Date dateFin) ;
	List<Produit> getProductsLimiteConsommation(Date dateDebut , Date dateFin) ;
	Map<String, Integer> statistiqueProduit();
}
