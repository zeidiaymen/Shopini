package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Livreur;
import com.example.demo.repository.RepLivreur;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class LivreurForTestIMPL implements ILivreurForTest{
@Autowired
RepLivreur liv ;

	
	public Livreur addLivreur(Livreur livreur) {
		return liv.save(livreur);
	
	}


	@Override
	public void deleteLivreur(String id) {
		// TODO Auto-generated method stub
		
	}

	

}
