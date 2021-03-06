package com.example.demo.controller.reclamation;
import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.SocketException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Facture;
import com.example.demo.entity.Reclamation;
import com.example.demo.service.reclamation.GeneratePdf;
import com.example.demo.service.reclamation.ReclamationService;

import io.jsonwebtoken.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
	
	@GetMapping("/report")
	@ResponseBody
	public ResponseEntity<InputStreamResource> Report() throws IOException{
        List<Reclamation> list = reclamationService.getAllReclamations();

        ByteArrayInputStream bis = null;
		try {
			bis = GeneratePdf.Report(list);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=reclamations.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}
