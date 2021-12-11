package com.example.demo.controller;

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
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/client")
public class ClientController {
	
	@Autowired 
	ClientService clientService;

	public ClientController() {
		// TODO Auto-generated constructor stub
	}
	
	
	@PostMapping(value = "addClient")

	public Client addClient(@RequestParam("file") MultipartFile file, @RequestParam("client") String clientString)
			throws SocketException, JsonMappingException, JsonProcessingException {
		Client client= new ObjectMapper().readValue(clientString, Client.class);

		return clientService.addClient(client, file);

	}
	
	@GetMapping(value = "getClients")	

	public ResponseEntity<List<Client>> getClients() {

		List<Client> clients = clientService.getClients();
		return new ResponseEntity<>(clients, HttpStatus.OK);

	}
	
	@GetMapping(value = "/sizeClients")
	public int sizeClients() {
		return this.clientService.sizeClients();
	}
	

}
