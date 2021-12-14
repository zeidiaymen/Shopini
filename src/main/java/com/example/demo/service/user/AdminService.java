package com.example.demo.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.user.Admin;
import com.example.demo.entity.user.PasswordHistory;
import com.example.demo.entity.user.User;
import com.example.demo.models.user.MailRequest;
import com.example.demo.repository.user.AdminRepository;
import com.example.demo.utils.user.Utils;

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

	public Admin addAdmin(Admin admin) {
		User user = new Admin();
		user = (User) admin;

		String password = this.utils.getRandomString(8);

		// MailRequest
		MailRequest mailRequest = new MailRequest(user.getLastName().toUpperCase() + " " + user.getFirstName(),
				user.getEmail(), "Vous avez ete ajoute comme Admin avec shopini",
				"Votre mot de passe est :" + password, "Consulter", "http://localhost:4200/login");

		user = userService.addUser(user, null, "ADMIN", "VERIFIED", mailRequest);

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
	
	public List<Admin> getAdmins() {

		return adminRepository.findAll();

	}
	
	public int sizeAdmins(){
		return this.adminRepository.sizeAdmins();
	}
	

}
