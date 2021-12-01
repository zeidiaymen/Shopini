package com.example.demo.controller;

import java.net.SocketException;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.User;
import com.example.demo.models.Address;
import com.example.demo.models.AuthRequest;
import com.example.demo.service.EmailService;
import com.example.demo.service.LocationService;
import com.example.demo.service.PasswordHistoryService;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtil;
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

	@GetMapping(value = "users")
	public ResponseEntity<List<User>> getUsers() {
		List <User> users=userService.getUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	@RequestMapping(value="addUser",method = RequestMethod.POST)

	public User addAdmin(@RequestParam("file") MultipartFile file ,@RequestParam("user") String  userString) throws SocketException, JsonMappingException, JsonProcessingException {
        User user= new ObjectMapper().readValue(userString, User.class);

		return userService.addUser(user,file);

	}

	@PostMapping(value = "savePicture")
	public Boolean savePicture(@RequestParam("file") MultipartFile file)
			throws SocketException, JsonMappingException, JsonProcessingException {
		User user=null;
		return userService.savePicture(file,user);

	}

	@PostMapping("changePassword")
	public Boolean check(@RequestParam String userId, String password) throws SocketException {

		return userService.updatePasswordUser(userId, password);

	}

	@GetMapping(value = "/activateAccount")
	public User activateAccount(@RequestParam String activationToken) {
		return userService.activateAccount(activationToken);

	}
	
	@GetMapping(value = "/getUserByEmail")
	public User getUserByEmail(@RequestParam String email) {
		
		User user =userService.findUserByEmail(email);
		
		return userService.findUserByEmail(email);

	}
	
	@GetMapping(value = "/isEmailExist")
	public Boolean isEmailExist(@RequestParam String email) {
		
		User user =userService.findUserByEmail(email);
		if(user!=null)
			return true;
		
		return false;

	}

	@GetMapping(value = "/test")
	public Boolean addUserTest() throws SocketException {
		List<User> users = userService.getUsers();
		int expected = users.size();
		System.out.println(expected);
		for (int i = 0; i < 10; i++) {
			Random r = new Random();
			char c = (char) (r.nextInt(26) + 'a');

			User user = new User();
			user = new User("Seifeddine", "BENSALAH", "seifeddine.@Yahoo." + c, "12345678", "MASCULIN", "12345678",
					"picture");
			// User savedUser = userService.addUser(user);

		}

		return true;

	}

	@PostMapping(value = "/deleteUser")
	public boolean deleteUser(@RequestParam String id) {
		userService.deleteUser(id);
		return true;

	}

	@GetMapping(value = "/getAddress")
	public String testX() {

		String ipAdress = "Null";
		try {
			ipAdress = LocationService.MyIpAdress();

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Address address = LocationService.CurrentLocation(ipAdress);

		return address.getCity();

		// return customUserDetailsService.getUser().getId();

	}

	@PostMapping("/authenticate/{email}/{password}")

	public String generateToken(@PathVariable(name="email") String email,@PathVariable(name="password") String password ) throws Exception {
		 AuthRequest authRequest = new AuthRequest(email,password);
		if (userService.checkAccountStatus(authRequest.getEmail())) {

			try {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
			} catch (Exception ex) {
				throw new Exception("inavalid username/password");
			}
			return jwtUtil.generateToken(authRequest.getEmail());
		}

		return "false";

	}

}
