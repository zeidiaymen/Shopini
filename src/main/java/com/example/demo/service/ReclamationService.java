package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Produit;
import com.example.demo.entity.Reclamation;
import com.example.demo.entity.User;
import com.example.demo.repository.ProduitRepository;
import com.example.demo.repository.ReclamationRepository;
import com.example.demo.repository.UserRepository;

@Service
public class ReclamationService {
	@Autowired
	private ReclamationRepository reclamationRepository;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProduitRepository produitRepository;

	public Reclamation addReclamation (Reclamation reclamation,Long idProduit,String idUser){
		User user;
		user=userRepository.findById(idUser).orElse(null);
		Produit produit;
		produit=produitRepository.findById(idProduit).orElse(null);
		if(user!=null&&produit!=null){
			reclamation.setUser(user);
			reclamation.setProduit(produit);
			Date d=new Date();
			reclamation.setCreatedAt(String.valueOf(d));
			 return reclamationRepository.save(reclamation);	
			
		}
		return null;
		
		
			
	 }
	
	public List<Reclamation> getAllReclamations (){
		 return reclamationRepository.findAll();	
		 }
	
	public Reclamation updateReclamation (Reclamation reclamation,Long idProduit,String idUser){
		User user;
		user=userRepository.findById(idUser).orElse(null);
		Produit produit;
		produit=produitRepository.findById(idProduit).orElse(null);
		if(user!=null&&produit!=null){
			reclamation.setUser(user);
			reclamation.setProduit(produit);
			Date d=new Date();
			reclamation.setCreatedAt(String.valueOf(d));
			reclamation.setModifiedAt(String.valueOf(d));
			 return reclamationRepository.save(reclamation);	
			
		}
		return null;
		
		
			
	 }
	
	public void deleteReclamation(int id){
		reclamationRepository.deleteById(id);
	}
	
 
}