package com.example.demo.controller;

import java.net.SocketException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Livreur;
import com.example.demo.service.LivreurService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/livreur")
public class LivreurController {

	@Autowired
	LivreurService livreurService;

	@PostMapping(value = "addLivreur")

	public Livreur addLivreur(@RequestBody Livreur livreur)
			throws SocketException, JsonMappingException, JsonProcessingException {
		MultipartFile file = null;

		return livreurService.addLivreur(livreur, file);

	}

}
