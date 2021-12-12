package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Produit;


public interface IProduitRepository extends JpaRepository<Produit,Long> {

//	@Query("SELECT count(s) FROM Stock s")
//	int retrieveAllStocksCount();
	
	@Query("select p from Produit p join DetailProduit dp on dp=p.detailProduit where dp.quantite=0")
	List<Produit> findproduitStatusStock();
	
	@Query("select p from Produit p join DetailProduit dp on dp=p.detailProduit where dp.dateCreation >= ?1 and dp.dateCreation <= ?2 ")
	List<Produit> getProductsCreatedBetween(Date dateDebut , Date dateFin);
	
	@Query("select  sum(p.prixUnitaire*dp.quantite) from Produit p join DetailProduit dp on dp=p.detailProduit where dp.dateCreation >= ?1 and dp.dateCreation <= ?2 ")
	float getProductsRevenueBetween(Date dateDebut , Date dateFin);
	
	@Query("select p from Produit p join DetailProduit dp on dp=p.detailProduit where dp.dateLimiteConsommation >= ?1 and (dp.dateCreation >= ?2 and dp.dateCreation <= ?3) ")
	//@Query("select p from Produit p join DetailProduit dp on dp=p.detailProduit where dp.quantite=0")
	List<Produit> getProductsLimiteConsommation(Date todayDate,Date dateDebut , Date dateFin);
	
	@Query("select  dp.categorieProduit, COUNT(p) from Produit p join DetailProduit dp on dp=p.detailProduit group by dp.categorieProduit  ")
	List<String> stats();
}
