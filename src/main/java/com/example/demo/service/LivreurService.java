package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Livreur;
import com.example.demo.entity.Livreur;
import com.example.demo.entity.PasswordHistory;
import com.example.demo.entity.User;
import com.example.demo.models.MailRequest;
import com.example.demo.repository.LivreurRepository;
import com.example.demo.utils.Utils;

@Service
public class LivreurService {

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	UserService userService;

	@Autowired
	LivreurRepository livreurRepository;
	@Autowired
	Utils utils;

	@Autowired
	PasswordHistoryService passwordHistoryService;

	public Livreur addLivreur(Livreur livreur) {
		int pourcentage =livreur.getPourcentage();
		int solde=livreur.getSolde();
		User user = new Livreur();
		user = (User) livreur;

		String password = this.utils.getRandomString(8);

		// MailRequest
		MailRequest mailRequest = new MailRequest(user.getLastName().toUpperCase() + " " + user.getFirstName(),
				user.getEmail(), "Vous avez ete ajoute comme Livreur avec shopini",
				"Votre mot de passe est :" + password, "Consulter", "http://localhost:4200/login");

		user = userService.addUser(user, null, "LIVREUR", "VERIFIED", mailRequest);

		if (user != null) {
			String encodedPassword = this.passwordEncoder.encode(password);
			user.setPassword(encodedPassword);
			livreur = new Livreur();
			livreur = (Livreur) user;
			livreur.setPourcentage(pourcentage);
			livreur.setSolde(solde);
			this.livreurRepository.save(livreur);
			PasswordHistory passwordHistory = new PasswordHistory(livreur, user.getPassword(),
					Utils.getCurrentDate());
			passwordHistoryService.addPasswordHistory(passwordHistory);
			return livreur;
		}
		return null;

	}
	
	
	public int sizeLivreur(){
		return this.livreurRepository.sizeLivreur();
	}
	
	
	public List<Livreur> getLivreurs() {

		return livreurRepository.findAll();

	}
	
	public Livreur changeLivreur(Livreur livreur) {
		return this.livreurRepository.save(livreur);

	}

	public Livreur getLivreurByEmail(String email) {

		return this.livreurRepository.findByEmail(email).orElse(null);

	}

}
