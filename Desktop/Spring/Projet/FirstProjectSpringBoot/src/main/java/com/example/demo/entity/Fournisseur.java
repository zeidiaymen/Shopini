package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("FOURNISSEUR")
@Entity
public class Fournisseur extends User implements Serializable {

	public Fournisseur() {
		// TODO Auto-generated constructor stub
	}

	public Fournisseur(String firstName, String lastName, String email, String password,String sexe, String tel, String picture,
			String createdAt,String address, String role) {
		super(firstName, lastName, email, password,sexe, tel, picture, createdAt,address, role);
	}

}
