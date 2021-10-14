package com.example.demo.entity;

import javax.persistence.DiscriminatorValue;

@DiscriminatorValue("FOURNISSEUR")

public class Fournisseur extends User {

	public Fournisseur() {
		// TODO Auto-generated constructor stub
	}

	public Fournisseur(String firstName, String lastName, String email, String password,String sexe, String tel, String picture,
			String createdAt,String address, String role) {
		super(firstName, lastName, email, password,sexe, tel, picture, createdAt,address, role);
	}

}
