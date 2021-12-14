package com.example.demo.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.user.Fournisseur;
import com.example.demo.entity.user.PasswordHistory;
import com.example.demo.entity.user.User;
import com.example.demo.models.user.MailRequest;
import com.example.demo.repository.user.FournisseurRepository;
import com.example.demo.utils.user.Utils;

@Service
public class FournisseurService {

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	UserService userService;

	@Autowired
	FournisseurRepository fournisseurRepository;
	@Autowired
	Utils utils;

	@Autowired
	PasswordHistoryService passwordHistoryService;

	public Fournisseur addFournisseur(Fournisseur fournisseur) {
		User user = new Fournisseur();
		user = (User) fournisseur;

		String password = this.utils.getRandomString(8);

		// MailRequest
		MailRequest mailRequest = new MailRequest(user.getLastName().toUpperCase() + " " + user.getFirstName(),
				user.getEmail(), "Vous avez ete ajoute comme fournisseur avec shopini",
				"Votre mot de passe est :" + password, "Consulter", "http://localhost:4200/login");

		user = userService.addUser(user, null, "FOURNISSEUR", "VERIFIED", mailRequest);

		if (user != null) {
			String encodedPassword = this.passwordEncoder.encode(password);
			user.setPassword(encodedPassword);
			fournisseur = new Fournisseur();
			fournisseur = (Fournisseur) user;
			this.fournisseurRepository.save(fournisseur);
			PasswordHistory passwordHistory = new PasswordHistory(fournisseur, user.getPassword(),
					Utils.getCurrentDate());
			passwordHistoryService.addPasswordHistory(passwordHistory);
			return fournisseur;
		}
		return null;

	}

	public Fournisseur changeFournisseur(Fournisseur fournisseur) {
		return this.fournisseurRepository.save(fournisseur);

	}
	
	public int sizeFournisseurs(){
		return this.fournisseurRepository.sizeFournisseur();	
		}
	

	public Fournisseur getFournisseurByEmail(String email) {

		return this.fournisseurRepository.findByEmail(email).orElse(null);

	}
	
	
	public List<Fournisseur> getFournisseurs() {

		return fournisseurRepository.findAll();

	}
}
