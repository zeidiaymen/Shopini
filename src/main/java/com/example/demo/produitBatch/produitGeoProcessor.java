package com.example.demo.produitBatch;
import org.springframework.batch.item.ItemProcessor;

import com.example.demo.entity.MapLocalisationProduitFournisseur;


public class produitGeoProcessor implements ItemProcessor<MapLocalisationProduitFournisseur, MapLocalisationProduitFournisseur> {
	/*11. logique métier de notre job*/
	@Override
	public MapLocalisationProduitFournisseur process(MapLocalisationProduitFournisseur produitgeo) {
		
	
		produitgeo.setIdGeoLocalisation(produitgeo.getIdGeoLocalisation());
		produitgeo.setLat(produitgeo.getLat());
		produitgeo.setLng(produitgeo.getLng());
		return produitgeo;
	}
	
}

