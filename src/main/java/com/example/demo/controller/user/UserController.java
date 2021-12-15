package com.example.demo.controller.user;

import java.io.IOException;

import java.net.SocketException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.user.User;
import com.example.demo.models.user.Address;
import com.example.demo.models.user.AuthRequest;
import com.example.demo.models.user.SmsRequest;
import com.example.demo.service.user.EmailService;
import com.example.demo.service.user.LocationService;
import com.example.demo.service.user.PasswordHistoryService;
import com.example.demo.service.user.UserService;
import com.example.demo.service.user.twilio.SmsService;
import com.example.demo.utils.user.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	EmailService emailService;
	@Autowired
	PasswordHistoryService passwordHistoryService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	SmsService smsService;

	@GetMapping(value = "users")
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = userService.getUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "addUser", method = RequestMethod.POST)

	public User addUser(@RequestParam("file") MultipartFile file, @RequestParam("user") String userString)
			throws SocketException, JsonMappingException, JsonProcessingException {
		User user = new ObjectMapper().readValue(userString, User.class);

		return userService.addUser(user, file);
	}	

	@PostMapping("changePassword")
	public Boolean check(@RequestParam String email, String password) throws SocketException {
		User user = this.userService.findUserByEmail(email);
		return userService.updatePasswordUser(user, password);

	}

	@GetMapping(value = "/activateAccount")
	public Boolean activateAccount(@RequestParam String activationToken) {
		return userService.activateAccount(activationToken);

	}

	@GetMapping(value = "/getUserByEmail")
	public User getUserByEmail(@RequestParam String email) {

		return userService.findUserByEmail(email);

	}

	@GetMapping(value = "/isEmailExist")
	public Boolean isEmailExist(@RequestParam String email) {

		User user = userService.findUserByEmail(email);
		if (user != null)
			return true;

		return false;

	}

	/*
	 * @GetMapping(value = "/test") public Boolean addUserTest() throws
	 * SocketException { List<User> users = userService.getUsers(); int expected
	 * = users.size(); for (int i = 0; i < 10; i++) { Random r = new Random();
	 * char c = (char) (r.nextInt(26) + 'a');
	 * 
	 * User user = new User(); user = new User("Seifeddine", "BENSALAH",
	 * "seifeddine.@Yahoo." + c, "12345678", "MASCULIN", "12345678", "picture");
	 * // User savedUser = userService.addUser(user);
	 * 
	 * }
	 * 
	 * return true;
	 * 
	 * }
	 */

	@GetMapping(value = "/getAddress")
	public String getAddress() {

		String ipAdress = "Null";
		try {
			ipAdress = LocationService.MyIpAdress();

		} catch (SocketException e) {
			e.printStackTrace();
		}
		Address address = LocationService.CurrentLocation(ipAdress);

		return address.getCity();

	}

	@GetMapping("/authenticate")

	public String generateToken(@RequestParam String email, @RequestParam String password) throws Exception {
		AuthRequest authRequest = new AuthRequest(email, password);

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		} catch (Exception ex) {
			return "False";
		}
		if (userService.checkAccountStatus(authRequest.getEmail())) {
			return jwtUtil.generateToken(authRequest.getEmail());
		}

		else
			return "Not Verified";

	}

	@GetMapping("/getUserByToken")

	public User getUserByToken(@RequestParam String token) throws Exception {

		return this.userService.getUserByToken(token);

	}

	@GetMapping("/getPictureByEmail")

	public byte[] getPictureByEmail(@RequestParam String email) throws Exception {
		return this.userService.getPictureByEmail(email);

	}

	@PostMapping("/updateUser")

	public User updateUser(@RequestParam("token")String token ,@RequestParam("firstName")String firstName ,@RequestParam("lastName")String lastName) throws JsonMappingException, JsonProcessingException {

		User user = this.userService.getUserByToken(token);
		if (user != null)
			return this.userService.updateUser(user,firstName,lastName);

		return null;
	}

	@PostMapping("/updateUserPicture")

	public void updateUserPicture(@RequestParam("file") MultipartFile userPicture, @RequestParam String token)
			throws IOException {

		User user = this.userService.getUserByToken(token);
		if (user != null)
			this.userService.updateUserPicture(user, userPicture);

	}

	@PostMapping("/changePasswordUser")

	public Boolean changePasswordUser(@RequestParam("password") String password, @RequestParam("token") String token) {

		User user = this.userService.getUserByToken(token);
		if (user != null) {
			return this.userService.updatePasswordUser(user, password);
		}

		return false;
	}

	@PostMapping("/forgetPasswordRequest")

	public Boolean forgetPasswordRequest(@RequestParam("email") String email) {

		User user = this.userService.findUserByEmail(email);
		if (user != null && user.getAccountStatus().equals("VERIFIED")) {

			this.userService.forgetPasswordRequest(user);
			return true;
		}

		return false;
	}

	@PostMapping("/checkUserPasswordRequestToken")

	public Boolean checkUserPasswordRequestToken(@RequestParam("email") String email,
			@RequestParam("token") String token) {

		User user = this.userService.findUserByEmail(email);
		if (user != null) {
			return this.userService.checkUserPasswordRequestToken(user, token);
		}
		return false;
	}

	@GetMapping("/sendSmsTwoFactorAuthentication")

	public String sendSmsTwoFactorAuthentication(@RequestParam String token) {

		User user = this.userService.getUserByToken(token);
		if (user != null) {
			return this.userService.sendSmsTwoFactorAuthentication(user);
			
		}
		return null;

	}

	@PostMapping("/sendSms")

	public String sendSms() {

		SmsRequest smsRequest = new SmsRequest();
		smsRequest.setNumber("+21695227678");
		smsRequest.setMessage("Voici votre code de verification " + "1");

		String status = this.smsService.sendsms(smsRequest);
		if ("sent".equals(status) || "queued".equals(status)) {
			return "true";
		}
		return "false";

	}

	@PostMapping("/verifUserOrNot")
	public void verifUserOrNot(@RequestParam("email") String email) {

		User user = this.userService.findUserByEmail(email);
		if (user != null)
			this.userService.VerifUserOrNot(user);

	}

	@PostMapping("/deleteUser")
	public void deleteUser(@RequestParam("email") String email) {

		User user = this.userService.findUserByEmail(email);
		if (user != null)
			this.userService.deleteUser(user);

	}

	@PostMapping("/twoFactorAuthenticate")
	public Boolean twoFactorAuthenticate(@RequestParam("token") String token) {

		User user = this.userService.getUserByToken(token);
		if (user != null)
			return this.userService.twoFactorAuthenticate(user);
		return null;

	}
	@GetMapping(value = "/sizeUsers")
	public int sizeUsers() {
		return this.userService.sizeUsers();
	}
	
	@GetMapping(value = "/sizeUsersVerified")
	public int sizeUsersVerified() {
		return this.userService.sizeUsersVerified();
	}
	
	@GetMapping(value = "/sizeUsersNotVerified")
	public int sizeUsersNotVerified() {
		return this.userService.sizeUsersNotVerified();
	}
	
	@GetMapping(value = "/sizeUsersByCountry")
	public int sizeUsersNotVerified(@RequestParam String address) {
		return this.userService.sizeUsersByCountry(address);
	}


	/*
	 * 
	 * @PostMapping(value="/addPictureBase64", produces = "image/png")
	 * 
	 * public void addPictureBase64(@RequestParam("file") MultipartFile file) {
	 * 
	 * User
	 * user=this.userService.findUserByEmail("seifeddine.bensalah@gmail.com");
	 * if(user!=null) this.userService.addPictureBase64(user, file); }
	 */

}
