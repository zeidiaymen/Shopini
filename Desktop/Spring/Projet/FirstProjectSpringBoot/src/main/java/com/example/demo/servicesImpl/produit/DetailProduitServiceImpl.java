package com.example.demo.servicesImpl.produit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.DetailProduit;
import com.example.demo.repository.produit.IDetailProduitRepository;
import com.example.demo.services.produit.IDetailProduitService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DetailProduitServiceImpl implements IDetailProduitService {

	@Autowired
	IDetailProduitRepository detailproduitRepository ;
	
	@Override
	public List<DetailProduit> retrieveAllDetailProduits() {
		 List<DetailProduit> produits= ( List<DetailProduit>)detailproduitRepository.findAll();
		 return produits;
	}

	@Override
	public DetailProduit addDetailProduit(DetailProduit dp) {
		return detailproduitRepository.save(dp);	
	}

	@Override
	public DetailProduit updateDetailProduit(DetailProduit dp) {
		return detailproduitRepository.save(dp);
	}

	@Override
	public DetailProduit retrieveDetailProduit(Long id) {
		return detailproduitRepository.findById(id).orElse(null);
	}
	
	
}
