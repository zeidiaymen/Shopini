package com.example.demo.controller;

import java.net.SocketException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Fournisseur;
import com.example.demo.entity.Livreur;
import com.example.demo.service.FournisseurService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/fournisseur")
public class FournisseurController {
	
	@Autowired 
	FournisseurService fournisseurService;

	
	
	
	@PostMapping(value = "addFournisseur")

	public Fournisseur addFournisseur(@RequestParam("fournisseur") String fournisseurString)
			throws SocketException, JsonMappingException, JsonProcessingException {
		Fournisseur fournisseur= new ObjectMapper().readValue(fournisseurString, Fournisseur.class);


		return fournisseurService.addFournisseur(fournisseur);

	}
	
	
	@GetMapping(value = "getFournisseurs")

	public ResponseEntity<List<Fournisseur>> getLivreurs() {

		List<Fournisseur> fournisseurs = fournisseurService.getFournisseurs();
		return new ResponseEntity<>(fournisseurs, HttpStatus.OK);

	}
	
	@GetMapping(value = "/sizeFournisseurs")
	public int sizeFournisseurs() {
		return this.fournisseurService.sizeFournisseurs();
	}
	
	
	
	

}
