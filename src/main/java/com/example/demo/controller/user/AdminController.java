package com.example.demo.controller.user;

import java.net.SocketException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.user.Admin;
import com.example.demo.service.user.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@PostMapping(value = "addAdmin")

	public Admin addAdmin(@RequestParam("admin") String adminString)
			throws SocketException, JsonMappingException, JsonProcessingException {
		Admin admin= new ObjectMapper().readValue(adminString, Admin.class);


		return adminService.addAdmin(admin);

	}
	
	
	@GetMapping(value = "getAdmins")

	public ResponseEntity<List<Admin>> getAdmins() {

		List<Admin> admins = adminService.getAdmins();
		return new ResponseEntity<>(admins, HttpStatus.OK);

	}
	
	@GetMapping(value = "/sizeAdmins")
	public int sizeAdmins() {
		return this.adminService.sizeAdmins();
	}

}
