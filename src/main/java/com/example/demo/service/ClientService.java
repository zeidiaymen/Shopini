package com.example.demo.service;

import com.example.demo.entity.Client;
import com.example.demo.entity.PasswordHistory;
import com.example.demo.entity.Profession;
import com.example.demo.entity.User;
import com.example.demo.models.MailRequest;
import com.example.demo.repository.ClientRepository;
import com.example.demo.utils.Utils;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ClientService {

	@Autowired
	UserService userService;
	

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	PasswordHistoryService passwordHistoryService;

	public Client addClient(Client client, MultipartFile file) {
		Profession profession = client.getProfession();
		

		User user=new Client();
			user = (User) client;
			//MailRequest
			String activationToken = UUID.randomUUID().toString();
			user.setActivationToken(activationToken);
			MailRequest mailRequest = new MailRequest(user.getLastName().toUpperCase() + " " + user.getFirstName(),
					user.getEmail(), "Verification de votre compte", "Veuillez verifier votre compte",
					"Activer votre compte",
					"http://localhost:4200/login/activateAccount/" + activationToken);
		
			
			user = userService.addUser(user, file, "CLIENT","NOT_VERIFIED",mailRequest);
		

		


		if (user != null) {
			client =new Client();
			client = (Client) user;
			client.setProfession(profession);
			this.clientRepository.save(client);
			PasswordHistory passwordHistory = new PasswordHistory(client, user.getPassword(), Utils.getCurrentDate());
			passwordHistoryService.addPasswordHistory(passwordHistory);
			return client;
		}
		return null;

	}
	
	public List<Client> getClients() {

		return clientRepository.findAll();

	}
	
	public int sizeClients(){
		return this.clientRepository.sizeClients();
	}
	

}
