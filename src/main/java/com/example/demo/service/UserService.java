package com.example.demo.service;

import com.example.demo.repository.UserRepository;


import com.example.demo.utils.Utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.example.demo.entity.PasswordHistory;
import com.example.demo.entity.User;
import com.example.demo.models.Address;
import com.example.demo.models.MailRequest;

import java.util.Date;
import java.io.File;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletContext;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	PasswordHistoryService passwordHistoryService;
	@Autowired
	EmailService emailService;

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Autowired
	Utils utils;

	public List<User> getUsers() {

		return userRepository.findAll();

	}
	@Autowired  
	ServletContext context;
	String randomNamePicture=null;
	public User addUser(User user,MultipartFile file ) throws SocketException {
		// Password
		String encodedPassword = this.passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		// CurrentDate

		user.setCreatedAt(Utils.getCurrentDate());
		// CurrentAddress
		String ipAdress = LocationService.MyIpAdress();
		Address address = LocationService.CurrentLocation(ipAdress);
		user.setAddress(address.getCity());
		// Activation Token
		String activationToken = UUID.randomUUID().toString();
		user.setActivationToken(activationToken);
		
		
		
		
		
		
		MailRequest mailRequest = new MailRequest(user.getLastName().toUpperCase() + " " + user.getFirstName(),
				user.getEmail(), "Verification de votre compte", "Veuillez verifier votre compte",
				"Activer votre compte",
				"http://localhost:8081/SpringMVC/activateAccount?activationToken=" + activationToken);
		// emailService.sendEmail(mailRequest);
		// Fin
		User userEmail;
		userEmail=this.findUserByEmail(user.getEmail());
		//userEmail = userRepository.findByEmail(user.getEmail());
		if (!(userEmail == null))
			return null;
		else {
			user.setRole("USER");
			User userSaved=userRepository.save(user);
			PasswordHistory passwordHistory = new PasswordHistory(user, user.getPassword(), Utils.getCurrentDate());
			passwordHistoryService.addPasswordHistory(passwordHistory);
			this.savePicture(file,userSaved);
			return user;

		}
	}
	public void updatePictureName(String namePicture,User user)
	{
		user.setPicture(namePicture);
		this.userRepository.save(user);
	}
	

	public Boolean updatePasswordUser(String userId, String password) {

		Optional<User> user = userRepository.findById(userId);

		return passwordHistoryService.checkPasswordHistory(user, password);

	}

	public User activateAccount(String activationToken) {
		User user;
		user = userRepository.findByActivationToken(activationToken);
		if (!(user == null)) {
			if (Utils.compareDateByMinutes(user.getCreatedAt(), 60)) {
				user.setActivationToken(null);
				user.setAccountStatus("VERIFIED");
				userRepository.save(user);
				return user;
			}

			else {
				String newActivationToken = UUID.randomUUID().toString();
				user.setActivationToken(newActivationToken);
				MailRequest mailRequest = new MailRequest(user.getLastName().toUpperCase() + " " + user.getFirstName(),
						user.getEmail(), "Verification de votre compte", "Veuillez verifier votre compte",
						"Activer votre compte",
						"http://localhost:8081/SpringMVC/activateAccount?activationToken=" + newActivationToken);
				emailService.sendEmail(mailRequest);
				user.setCreatedAt(Utils.getCurrentDate());	
				


			}
		}
		return null;
	}

	public User findUserByEmail(String email) {
		User user;
		user = userRepository.findByEmail(email).orElse(null);
		return user;
		
	}

	public void deleteUser(String id) {
		Optional<User> user;
		user = userRepository.findById(id);
		userRepository.delete(user.get());

	}

	public void deleteUser(User user) {

		userRepository.delete(user);

	}

	public boolean checkAccountStatus(String email) {
		User user = new User();
		user = userRepository.findByEmail(email).orElse(null);
		if (user.getAccountStatus().equals("VERIFIED"))

			return true;

		return false;
	}

	public String getRole(String email) {
		User user = new User();
		user = userRepository.findByEmail(email).orElse(null);
		return user.getRole();

	}
	
	public Boolean savePicture(MultipartFile file,User user)
	{
		
		boolean isExit = new File(context.getRealPath("/Images/")).exists();
        if (!isExit)
        {
        	new File (context.getRealPath("/Images/")).mkdir();
        	System.out.println("mk dir.............");
        }
        
        String filename = file.getOriginalFilename();
        System.out.println(filename);
        System.out.println(FilenameUtils.getBaseName(filename));
        String newFileName = FilenameUtils.getBaseName(filename)+user.getId()+"."+FilenameUtils.getExtension(filename);
       
        File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
        try
        {
        	
        	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
        	 updatePictureName(newFileName,user);
        	 
        }catch(Exception e) {
        	e.printStackTrace();
        }
		return true;
	}

}
