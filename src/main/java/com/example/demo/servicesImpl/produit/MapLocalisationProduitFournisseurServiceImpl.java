package com.example.demo.servicesImpl.produit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MapLocalisationProduitFournisseur;
import com.example.demo.repository.produit.IMapLocalisationProduitFournisseurRepository;
import com.example.demo.services.produit.IMapLocalisationProduitFournisseurService;

@Service
public class MapLocalisationProduitFournisseurServiceImpl implements 
IMapLocalisationProduitFournisseurService {

	@Autowired
	IMapLocalisationProduitFournisseurRepository georepository;
	
	@Override
	public List<? extends MapLocalisationProduitFournisseur> retrieveProduitsGeoLoc(
			List<? extends MapLocalisationProduitFournisseur> pgeolocalisations) {
		return georepository.saveAll(pgeolocalisations) ;
	}
	
}
