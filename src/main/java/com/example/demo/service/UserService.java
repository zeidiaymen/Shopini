package com.example.demo.service;

import com.example.demo.repository.UserRepository;

import com.example.demo.service.twilio.SmsService;
import com.example.demo.service.twilio.Twilioproperties;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.Utils;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import io.jsonwebtoken.ExpiredJwtException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.PasswordHistory;
import com.example.demo.entity.User;
import com.example.demo.entity.UserPasswordRequest;
import com.example.demo.exception.UserServiceException;
import com.example.demo.models.Address;
import com.example.demo.models.MailRequest;
import com.example.demo.models.SmsRequest;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;
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
	@Autowired
	UserPasswordRequestService userPasswordRequestService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private Twilioproperties twilioproperties;
	@Autowired
	private SmsService smsService;

	public List<User> getUsers() {

		return userRepository.findAll();

	}

	@Autowired
	ServletContext context;
	String randomNamePicture = null;

	public User addUser(User user, MultipartFile file, String role, String accountStatus, MailRequest mailRequest) {
		// Password
		if (user.getPassword() != null) {
			String encodedPassword = this.passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
		}
		// CurrentDate
		user.setCreatedAt(Utils.getCurrentDate());
		// Role
		user.setRole(role);
		// AccountStatus
		user.setAccountStatus(accountStatus);
		// CurrentAddress
		String ipAdress = "";
		try {
			ipAdress = LocationService.MyIpAdress();
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		Address address = LocationService.CurrentLocation(ipAdress);
		user.setAddress(address.getCity());

		User userEmail;
		userEmail = this.findUserByEmail(user.getEmail());
		// userEmail = userRepository.findByEmail(user.getEmail());
		if (!(userEmail == null))
			return null;
		else {
			emailService.sendEmail(mailRequest);

			if (file != null) {
				try {

					user.setPicture(file.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			return user;

		}
	}

	public User addUser(User user, MultipartFile file) {
		// Password
		String encodedPassword = this.passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		// CurrentDate

		user.setCreatedAt(Utils.getCurrentDate());
		// CurrentAddress
		String ipAdress = "";
		try {
			ipAdress = LocationService.MyIpAdress();
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		Address address = LocationService.CurrentLocation(ipAdress);
		user.setAddress(address.getCity());
		// Activation Token
		String activationToken = UUID.randomUUID().toString();
		user.setActivationToken(activationToken);

		MailRequest mailRequest = new MailRequest(user.getLastName().toUpperCase() + " " + user.getFirstName(),
				user.getEmail(), "Verification de votre compte", "Veuillez verifier votre compte",
				"Activer votre compte", "http://localhost:4200/activateAccount/" + activationToken);
		 emailService.sendEmail(mailRequest);
		// Fin
		User userEmail;
		userEmail = this.findUserByEmail(user.getEmail());
		// userEmail = userRepository.findByEmail(user.getEmail());
		if (!(userEmail == null))
			throw new UserServiceException("User exist");

		else {
			try {
				user.setPicture(file.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user.setRole("USER");
			 this.userRepository.save(user);
			PasswordHistory passwordHistory = new PasswordHistory(user, user.getPassword(), Utils.getCurrentDate());
			passwordHistoryService.addPasswordHistory(passwordHistory);

			return user;

		}
	}

	// public void updatePictureName(String namePicture, User user) {
	// user.setPicture(namePicture);
	// this.userRepository.save(user);
	// }

	public Boolean updatePasswordUser(User user, String password) {

		return passwordHistoryService.checkPasswordHistory(user, password);

	}

	public Boolean activateAccount(String activationToken) {
		User user;
		user = userRepository.findByActivationToken(activationToken).orElse(null);
		if (!(user == null)) {
			if (Utils.compareDateByMinutes(user.getCreatedAt(), 60)) {
				user.setActivationToken(null);
				user.setAccountStatus("VERIFIED");
				userRepository.save(user);
				return true;
			}

			else {
				String newActivationToken = UUID.randomUUID().toString();
				user.setActivationToken(newActivationToken);
				MailRequest mailRequest = new MailRequest(user.getLastName().toUpperCase() + " " + user.getFirstName(),
						user.getEmail(), "Verification de votre compte", "Veuillez verifier votre compte",
						"Activer votre compte", "http://localhost:4200/login/activateAccount/" + newActivationToken);
				emailService.sendEmail(mailRequest);
				user.setCreatedAt(Utils.getCurrentDate());

			}
		}
		return false;
	}

	public User findUserByEmail(String email) {

		return userRepository.findByEmail(email).orElse(null);

	}

	public boolean checkAccountStatus(String email) {
		User user = new User();
		user = userRepository.findByEmail(email).orElse(null);
		if (user != null) {
			if (user.getAccountStatus().equals("VERIFIED"))

				return true;
		}

		return false;
	}

	public String getRole(String email) {
		User user = new User();
		user = userRepository.findByEmail(email).orElse(null);
		return user.getRole();

	}

	/*
	 * public Boolean savePicture(MultipartFile file, User user) {
	 * 
	 * boolean isExit = new File(context.getRealPath("/Images/")).exists(); if
	 * (!isExit) { new File(context.getRealPath("/Images/")).mkdir(); }
	 * 
	 * String filename = file.getOriginalFilename(); String randomString =
	 * this.utils.getRandomString(8); String newFileName = randomString + "_" +
	 * user.getId() + "." + FilenameUtils.getExtension(filename);
	 * 
	 * File serverFile = new File(context.getRealPath("/Images/" +
	 * File.separator + newFileName)); try {
	 * 
	 * FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
	 * updatePictureName(newFileName, user);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return true; }
	 */

	public byte[] getPictureByEmail(String email) {
		User user;
		user = this.findUserByEmail(email);
		if (user != null)
			return user.getPicture();
		return null;

	}
	// public User updateUser(String email,String firstName,String
	// lastName,String picture)
	// {
	// User user=this.findUserByEmail(email);
	// if(user!=null)
	// {
	// if(!user.getFirstName().equals(firstName))
	// user.setFirstName(firstName);
	// if(!user.getLastName().equals(lastName))
	// user.setLastName(lastName);
	// if (!user.getPicture().equals(picture))
	// user.setPicture(picture);
	// if(!user.equals(this.findUserByEmail(email)))
	// this.userRepository.save(user);
	// return user;

	// }
	// return null;}
	public User updateUser(User user) {
		if (this.findUserByEmail(user.getEmail()) != null)
			return this.userRepository.save(user);
		return null;
	}

	public User getUserByToken(String token) {
		try {
			String email = this.jwtUtil.extractEmail(token);
			if (email != null) {
				User user = this.findUserByEmail(email);
				if (user != null)

					return user;

			}
		} catch (ExpiredJwtException expiredJwtException) {
			return null;
		}
		return null;

	}

	public void forgetPasswordRequest(User user)

	{
		this.userPasswordRequestService.retrieveUserPasswordRequest(user);
		String token = this.userPasswordRequestService.addUserPasswordRequest(user);

		MailRequest mailRequest = new MailRequest(user.getLastName().toUpperCase() + " " + user.getFirstName(),
				user.getEmail(), "Reset your password", "Click to resest your password", "Reset your password",
				"http://localhost:4200/forgetPasswordPasswordForm/" + token);
		this.emailService.sendEmail(mailRequest);

	}

	public Boolean checkUserPasswordRequestToken(User user, String token) {
		User userToCheck = this.userPasswordRequestService.getUserByToken(token);
		UserPasswordRequest userPasswordRequest = this.userPasswordRequestService.findByToken(token);

		if (userPasswordRequest != null) {

			Boolean checkTokenExpiration = Utils.compareDateByMinutes(userPasswordRequest.getCreatedAt(), 60);
			if (userToCheck != null && userToCheck.getEmail().equals(user.getEmail()) && checkTokenExpiration) {
				this.userPasswordRequestService.retrieveUserPasswordRequest(userPasswordRequest);
				return true;
			}
		}
		return false;
	}

	public void updateUserPicture(User user, MultipartFile file) {
		try {
			user.setPicture(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.userRepository.save(user);

	}

	public String sendSmsTwoFactorAuthentication(User user) {

		String verificationCode = this.utils.generateVerificaionCode(10000, 90000);

		if (this.sendSmsWhatsapp("+21695227678", verificationCode))

			return verificationCode;
		return null;

	}

	public Boolean sendSmsWhatsapp(String number, String verificationCode) {

		SmsRequest smsRequest = new SmsRequest();
		smsRequest.setNumber(number);
		System.out.println(verificationCode);
		smsRequest.setMessage("Voici votre code de verification " + verificationCode);

		String status = this.smsService.sendsms(smsRequest);
		if ("sent".equals(status) || "queued".equals(status))
			return true;
		return false;

	}

	public Boolean sendSmsGsm(String number, String verificationCode) {
		Twilio.init(this.twilioproperties.getAccountSid(), this.twilioproperties.getAuthToken());
		try {
			Message.creator(new PhoneNumber(number), new PhoneNumber(this.twilioproperties.getFromNumber()),
					"Voici votre code de verification " + verificationCode).create();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public void VerifUserOrNot(User user) {
		if (user.getAccountStatus().equals("NOT_VERIFIED"))
			user.setAccountStatus("VERIFIED");
		else if (user.getAccountStatus().equals("VERIFIED"))
			user.setAccountStatus("NOT_VERIFIED");

		this.userRepository.save(user);
	}

	public void deleteUser(User user) {
		user.setAccountStatus("DELETED");

		this.userRepository.save(user);
	}

	public Boolean twoFactorAuthenticate(User user) {
		user.setTwoFactorAuthentication(!user.getTwoFactorAuthentication());
		 this.userRepository.save(user);
		 return user.getTwoFactorAuthentication();
	}
	
	public int sizeUsers(){
		return this.userRepository.sizeUsers();
	}
	
	public int sizeUsersNotVerified(){
		return this.userRepository.sizeUsersNotVerified();
	}
	
	public int sizeUsersVerified(){
		return this.userRepository.sizeUsersVerified();
	}
	
	public int sizeUsersByCountry(String address){
		return this.userRepository.sizeUsersByCountry(address);
	}

	/*
	 * public void addPictureBase64(User user ,MultipartFile file){
	 * 
	 * 
	 * 
	 * try { user.setData(file.getBytes()); } catch (IOException e) {
	 * e.printStackTrace(); } this.userRepository.save(user);
	 * System.out.println(user.getData().toString());
	 * 
	 * }
	 */

}
