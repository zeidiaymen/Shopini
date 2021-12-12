package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.DetailProduit;


public interface IDetailProduitService {
	List<DetailProduit> retrieveAllDetailProduits();
	DetailProduit addDetailProduit(DetailProduit dp);
	DetailProduit updateDetailProduit(DetailProduit dp);
	DetailProduit retrieveDetailProduit(Long id);

}
