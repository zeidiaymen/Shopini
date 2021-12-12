package com.example.demo.produitBatch;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.MapLocalisationProduitFournisseur;
import com.example.demo.services.produit.IMapLocalisationProduitFournisseurService;



@Slf4j
public class produitGeoWriter implements ItemWriter<MapLocalisationProduitFournisseur> {

	
    @Autowired
    private IMapLocalisationProduitFournisseurService produitGeoService;

    /* écrire nos données dans la base de données*/
    public void write(List<? extends MapLocalisationProduitFournisseur> produitsgeo) {
            log.info("Enregistrement des lignes produits geo localisations dans la base de données", produitsgeo);
            /*toDo 10*/
            produitGeoService.retrieveProduitsGeoLoc(produitsgeo); 
            
    }
}
