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

import com.example.demo.entity.user.Livreur;
import com.example.demo.service.user.LivreurService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/livreur")
public class LivreurController {

	@Autowired
	LivreurService livreurService;

	@PostMapping(value = "addLivreur")

	public Livreur addLivreur(@RequestParam("livreur") String livreurString)
			throws SocketException, JsonMappingException, JsonProcessingException {
		Livreur livreur= new ObjectMapper().readValue(livreurString, Livreur.class);
		
		return livreurService.addLivreur(livreur);

	}
	
	@GetMapping(value = "getLivreurs")

	public ResponseEntity<List<Livreur>> getLivreurs() {

		List<Livreur> livreurs = livreurService.getLivreurs();
		return new ResponseEntity<>(livreurs, HttpStatus.OK);

	}
	
	@GetMapping(value = "/sizeLivreurs")
	public int getCountUser() {
		return this.livreurService.sizeLivreur();
	}
	
	
	@PostMapping(value = "changeLivreur")

	public Livreur changeLivreur(@RequestParam("livreur") String livreurString)
			throws SocketException, JsonMappingException, JsonProcessingException {
		Livreur livreur= new ObjectMapper().readValue(livreurString, Livreur.class);		

		 return livreurService.changeLivreur(livreur);

	}
	

}
