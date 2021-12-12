package com.example.demo.controller;

import java.net.SocketException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.entity.Reclamation;
import com.example.demo.service.ReclamationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping
public class ReclamationController {
	@Autowired
	private ReclamationService reclamationService;
	
	@PostMapping(value="addReclamation")
	@ResponseBody
	public Reclamation addReclamation(@RequestBody Reclamation reclamation,@RequestParam Long idProduit,@RequestParam String idUser) {

		return reclamationService.addReclamation(reclamation,idProduit,idUser);

	}
	@GetMapping(value="reclamations")
	public List<Reclamation> getAllReclamations(){
	return reclamationService.getAllReclamations();
	}
	
	@GetMapping(value="addXReclamation")
	public Boolean addXReclamation() {
		Reclamation reclamation=new Reclamation();
		//reclamation=new Reclamation("1","2","3","4","5","6","7");
		 
			 //reclamationService.addReclamation(reclamation);



		return true;

	}
	
	@PutMapping(value="updateReclamation")
	public Reclamation updateReclamation(@RequestBody Reclamation reclamation,@RequestParam Long idProduit,@RequestParam String idUser) {

		return reclamationService.updateReclamation(reclamation,idProduit,idUser);

	}
	
	@DeleteMapping(value = "deleteReclamation")
	public void deleteReclamation(@RequestParam int id) {
		reclamationService.deleteReclamation(id);

	   /* var isRemoved = postService.delete(id);

	    if (!isRemoved) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    return new ResponseEntity<>(id, HttpStatus.OK);*/
	}

}
