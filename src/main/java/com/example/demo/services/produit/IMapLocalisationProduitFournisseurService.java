package com.example.demo.services.produit;

import java.util.List;

import com.example.demo.entity.MapLocalisationProduitFournisseur;

public interface IMapLocalisationProduitFournisseurService {
	public List<? extends MapLocalisationProduitFournisseur> retrieveProduitsGeoLoc(List<? extends MapLocalisationProduitFournisseur> pgeolocalisations);

}
