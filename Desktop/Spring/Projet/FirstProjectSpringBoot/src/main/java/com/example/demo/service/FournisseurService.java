package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Fournisseur;
import com.example.demo.entity.PasswordHistory;
import com.example.demo.entity.User;
import com.example.demo.models.MailRequest;
import com.example.demo.repository.FournisseurRepository;
import com.example.demo.utils.Utils;

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

	public Fournisseur addFournisseur(Fournisseur fournisseur, MultipartFile file) {
		User user = new Fournisseur();
		user = (User) fournisseur;

		String password = this.utils.getRandomString(8);

		// MailRequest
		MailRequest mailRequest = new MailRequest(user.getLastName().toUpperCase() + " " + user.getFirstName(),
				user.getEmail(), "Vous avez ete ajoute comme fournisseur avec shopini",
				"Votre mot de passe est :" + password, "Consulter", "http://localhost:4200/login");

		user = userService.addUser(user, file, "FOURNISSEUR", "VERIFIED", mailRequest);

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

}
