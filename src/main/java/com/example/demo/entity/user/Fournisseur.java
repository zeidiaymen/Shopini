package com.example.demo.entity.user;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@DiscriminatorValue("FOURNISSEUR")
@Entity
public class Fournisseur extends User {

	public Fournisseur() {
		// TODO Auto-generated constructor stub
	}


	
}
