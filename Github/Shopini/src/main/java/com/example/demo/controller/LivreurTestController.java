package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Livreur;
import com.example.demo.service.ILivreurForTest;
@RestController
public class LivreurTestController {
	@Autowired
	ILivreurForTest liv ;
	
	@PostMapping("/postLivreur")
	@ResponseBody
	public Livreur addLivreur (@RequestBody Livreur livreur )
	{
		return liv.addLivreur(livreur);
	}

}
