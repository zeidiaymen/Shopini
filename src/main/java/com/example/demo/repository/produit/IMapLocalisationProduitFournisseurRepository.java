package com.example.demo.repository.produit;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MapLocalisationProduitFournisseur;

public interface IMapLocalisationProduitFournisseurRepository
extends JpaRepository<MapLocalisationProduitFournisseur,Long> {

}
