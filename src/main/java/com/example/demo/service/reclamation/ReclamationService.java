package com.example.demo.service.reclamation;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Produit;
import com.example.demo.entity.Reclamation;
import com.example.demo.entity.User;
import com.example.demo.repository.produit.IProduitRepository;
import com.example.demo.repository.reclamation.ReclamationRepository;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.service.facture.SmsFactService;
import com.example.demo.service.user.UserService;

@Service
public class ReclamationService {
	@Autowired
	private ReclamationRepository reclamationRepository;
	@Autowired
	UserRepository userRepository;
	
	
	@Autowired
	IProduitRepository produitRepository;

	 @Autowired
	 JavaMailSender emailSender;
	 @Autowired
	 SmsFactService userService;
	
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
			//System.out.println(user.toString());
	  	this.sendSimpleEmail(user.getEmail(), "reclamation bien recu avec id "+reclamation.getContent() +" Merci ");
			this.userService.sendSMS("reclamation recu"); 
			return reclamationRepository.save(reclamation);	

		}
		return null;
		
		
			
	 }
	
	public void sendSimpleEmail(String to,String listproduits) {

        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo(to);
        message.setSubject("stock produits epuisé ");
        message.setText("Bonjour Mr "+to +" Produit stock epuisé : "+listproduits);

        // Send Message!
        this.emailSender.send(message);

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