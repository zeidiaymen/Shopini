package com.example.demo.controller;

import java.net.SocketException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Fournisseur;
import com.example.demo.service.FournisseurService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/fournisseur")
public class FournisseurController {
	
	@Autowired 
	FournisseurService fournisseurService;

	
	
	
	@PostMapping(value = "addFournisseur")

	public Fournisseur addUserX(@RequestBody Fournisseur Fournisseur)
			throws SocketException, JsonMappingException, JsonProcessingException {
		MultipartFile file = null;

		return fournisseurService.addFournisseur(Fournisseur, file);

	}

}
