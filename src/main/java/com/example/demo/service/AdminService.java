package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Admin;
import com.example.demo.entity.PasswordHistory;
import com.example.demo.entity.User;
import com.example.demo.models.MailRequest;
import com.example.demo.repository.AdminRepository;
import com.example.demo.utils.Utils;

@Service
public class AdminService {

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	UserService userService;

	@Autowired
	AdminRepository adminRepository;
	@Autowired
	Utils utils;

	@Autowired
	PasswordHistoryService passwordHistoryService;

	public Admin addAdmin(Admin admin, MultipartFile file) {
		User user = new Admin();
		user = (User) admin;

		String password = this.utils.getRandomString(8);

		// MailRequest
		MailRequest mailRequest = new MailRequest(user.getLastName().toUpperCase() + " " + user.getFirstName(),
				user.getEmail(), "Vous avez ete ajoute comme Admin avec shopini",
				"Votre mot de passe est :" + password, "Consulter", "http://localhost:4200/login");

		user = userService.addUser(user, file, "ADMIN", "VERIFIED", mailRequest);

		if (user != null) {
			String encodedPassword = this.passwordEncoder.encode(password);
			user.setPassword(encodedPassword);
			admin = new Admin();
			admin = (Admin) user;
			this.adminRepository.save(admin);
			PasswordHistory passwordHistory = new PasswordHistory(admin, user.getPassword(),
					Utils.getCurrentDate());
			passwordHistoryService.addPasswordHistory(passwordHistory);
			return admin;
		}
		return null;

	}

}